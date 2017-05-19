package com.studymp.presentation.controllers;

import com.studymp.domain.interfaces.OrderService;
import com.studymp.domain.interfaces.SectionService;
import com.studymp.persistence.entity.Order;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by qwerty on 19.05.2017.
 */
@Controller
public class OrderGetController {
    private final static Logger LOGGER = Logger.getLogger(OrderGetController.class);

    private final OrderService orderService;
    private final SectionService sectionService;

    @Autowired
    public OrderGetController(OrderService orderService, SectionService sectionService) {
        this.orderService = orderService;
        this.sectionService = sectionService;
    }

    @RequestMapping(
            value = "/discipline",
            method = RequestMethod.GET
    )
    public String getAllOrders(Model model, @RequestParam(name = "id") Long id){
        List<Order> orders = null;
        try {
            orders =  orderService.findBySection(sectionService.findById(id));
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error("Не удалось получить список заказов");
        }
        model.addAttribute("orders", orders);
        return "discipline";
    }
}
