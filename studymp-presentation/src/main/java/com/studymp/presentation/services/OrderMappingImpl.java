package com.studymp.presentation.services;

import com.studymp.domain.exceptions.NotFoundException;
import com.studymp.domain.interfaces.SectionService;
import com.studymp.domain.interfaces.UserService;
import com.studymp.persistence.entity.Order;
import com.studymp.persistence.entity.Section;
import com.studymp.persistence.entity.User;
import com.studymp.presentation.dto.OrderDto;
import com.studymp.presentation.interfaces.OrderMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by qwerty on 19.05.2017.
*/
@Component
public class OrderMappingImpl implements OrderMapping {

    private final UserService userService;
    private final SectionService sectionService;

    @Autowired
    public OrderMappingImpl(UserService userService, SectionService sectionService) {
        this.userService = userService;
        this.sectionService = sectionService;
    }

    @Override
    public Order map(OrderDto orderDto, String creator) throws NotFoundException {
        User creatorUser = userService.findByUsername(creator);
        Section section = sectionService.findByName(orderDto.section);
        Order order = new Order(orderDto.name, new Date(), creatorUser, section, orderDto.description, orderDto.cost);
        return order;
    }

    @Override
    public Order update(Order order, OrderDto orderDto) {
        order.setDescription(orderDto.description);
        order.setName(orderDto.name);
        order.setCost(orderDto.cost);
        return order;
    }
}
