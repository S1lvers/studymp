package com.studymp.presentation.interfaces;

import com.studymp.domain.exceptions.NotFoundException;
import com.studymp.persistence.entity.Order;
import com.studymp.presentation.dto.OrderDto;

/**
 * Created by qwerty on 19.05.2017.
 */
public interface OrderMapping {
    Order map(OrderDto orderDto, String creator) throws NotFoundException;
    Order update(Order order, OrderDto orderDto);
}
