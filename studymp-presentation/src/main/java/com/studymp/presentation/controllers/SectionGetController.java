package com.studymp.presentation.controllers;

import com.studymp.domain.interfaces.SectionService;
import com.studymp.persistence.entity.Section;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by qwerty on 19.05.2017.
 */
@Controller
public class SectionGetController {
    private static final Logger LOGGER = Logger.getLogger(UserGetController.class);

    private final SectionService sectionService;

    @Autowired
    public SectionGetController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @RequestMapping(
            value = "/",
            method = RequestMethod.GET
    )
    public String getUsers(Model model){
        try {
            List<Section> sections = sectionService.findAll();
            model.addAttribute("sections", sections);
        } catch (Exception e){
            LOGGER.debug(e);
            LOGGER.error("Не удалось получить список секций");
        }
        return "index";
    }
}
