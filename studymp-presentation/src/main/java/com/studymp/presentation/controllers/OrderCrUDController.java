package com.studymp.presentation.controllers;

import com.studymp.domain.exceptions.NotFoundException;
import com.studymp.domain.interfaces.OrderService;
import com.studymp.domain.interfaces.UserService;
import com.studymp.domain.interfaces.Validation;
import com.studymp.persistence.entity.Order;
import com.studymp.presentation.dto.OrderDto;
import com.studymp.presentation.interfaces.OrderMapping;
import com.studymp.presentation.interfaces.OrderValidator;
import com.studymp.presentation.interfaces.ResponseDtoFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by qwerty on 19.05.2017.
 */
@Controller
@RequestMapping("/user/order")
public class OrderCrUDController {

    private final static Logger LOGGER = Logger.getLogger(OrderCrUDController.class);

    private final ResponseDtoFactory responseDtoFactory;
    private final OrderService orderService;
    private final OrderValidator orderValidator;
    private final OrderMapping orderMapping;
    private final UserService userService;

    @Autowired
    public OrderCrUDController(ResponseDtoFactory responseDtoFactory, OrderService orderService,
                               OrderValidator orderValidator, OrderMapping orderMapping, UserService userService) {
        this.responseDtoFactory = responseDtoFactory;
        this.orderService = orderService;
        this.orderValidator = orderValidator;
        this.orderMapping = orderMapping;
        this.userService = userService;
    }

    @RequestMapping(
            value = "/create",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.PUT
    )
    public ResponseEntity createOrder(@RequestBody OrderDto orderDto){
        Validation validation = orderValidator.validate(orderDto);
        if (!validation.isValid()){
            LOGGER.info("Заказ не был создан");
            LOGGER.debug(validation.getErrorMessage());
            return ResponseEntity.ok(responseDtoFactory.failure(validation.getErrorMessage()));
        }
        Order order = null;
        try {
            order = orderMapping.map(orderDto, SecurityContextHolder.getContext().getAuthentication().getName());
        } catch (NotFoundException e) {
            LOGGER.info("Заказ не был создан");
            LOGGER.debug(e);
            return ResponseEntity.ok(responseDtoFactory.failure("Заказ не был создан, пожалуйста проверьте правильность вводимых данных"));
        }
        orderService.create(order);
        return ResponseEntity.ok(responseDtoFactory.success());
    }

    @RequestMapping(
            value = "/update",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.POST
    )
    public ResponseEntity updateOrder(@RequestBody OrderDto orderDto) {
        Validation validation = orderValidator.validate(orderDto);
        if (!validation.isValid()) {
            LOGGER.info("Ошибка при обновлении заказа");
            LOGGER.debug(validation.getErrorMessage());
            return ResponseEntity.ok(responseDtoFactory.failure(validation.getErrorMessage()));
        }
        try {
            Order order = orderService.find(orderDto.id);
            if(!userService.findByUsername(SecurityContextHolder.getContext()
                    .getAuthentication().getName())
                    .equals(order.getCreator())) {
                return ResponseEntity.ok(responseDtoFactory.failure("Вы пытаетесь обновить заказ, который не был создан вами"));
            }
            order = orderMapping.update(order, orderDto);
            orderService.update(order);
            return ResponseEntity.ok(responseDtoFactory.success());
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.info(String.format("Не удалось обновить заказ с id=%s", orderDto.id));
            return ResponseEntity.ok(responseDtoFactory.failure("Не удалось обновить заказ"));
        }
    }
}
