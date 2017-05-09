package com.studymp.presentation.services;

import com.studymp.domain.interfaces.UserService;
import com.studymp.persistence.entity.Role;
import com.studymp.persistence.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by qwerty on 15.03.2017.
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = Logger.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Autowired
    private final UserService userService;

    @Autowired
    private HttpServletRequest request;


    public UserDetailsServiceImpl(UserService userService){
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String ip = getClientIP();
        if (loginAttemptService.isBlocked(ip)) {
            throw new RuntimeException("blocked");
        }
        try {
            User user = userService.findByUsername(username);
            if (user == null) {
                LOGGER.info("User not found with the provided username");
                return null;
            }
            LOGGER.info("User from username " + user.getUsername());
            LOGGER.debug("User from username " + user.getUsername());
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                    user.isEnabled(), true, true, true, getAuthorities(user));
        }
        catch (Exception e){
            throw new UsernameNotFoundException("User not found");
        }
    }

    private Set<GrantedAuthority> getAuthorities(User user){
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for(Role role : user.getRoles()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRole());
            authorities.add(grantedAuthority);
        }
        LOGGER.debug("User authorities are " + authorities.toString());
        return authorities;
    }

    private String getClientIP() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null){
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
}

