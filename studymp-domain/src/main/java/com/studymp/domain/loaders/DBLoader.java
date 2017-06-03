package com.studymp.domain.loaders;

import com.studymp.domain.interfaces.RoleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by qwerty on 03.06.2017.
 */
@Component
public class DBLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private RoleService roleService;

    private Logger LOGGER = Logger.getLogger(DBLoader.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(!roleService.isRoleExist("USER")){
            roleService.createRole("USER");
        }
        if(!roleService.isRoleExist("ADMIN")){
            roleService.createRole("ADMIN");
        }
    }
}

