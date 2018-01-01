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
        createUserRoleIfDoesntExist();
        createAdminRoleIfDoesntExist();
    }
    private void createUserRoleIfDoesntExist(){
        if(!roleService.isRoleExist("USER")){
            LOGGER.info("Role USER doesn't exist \n Creating new role USER" );
            roleService.createRole("USER");
        }
    }

    private void createAdminRoleIfDoesntExist(){
        if(!roleService.isRoleExist("ADMIN")){
            LOGGER.info("Role USER doesn't exist \n Creating new role ADMIN" );
            roleService.createRole("ADMIN");
        }
    }
}

