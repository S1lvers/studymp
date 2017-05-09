package com.studymp.domain.services;

import com.studymp.domain.exceptions.NotFoundException;
import com.studymp.domain.interfaces.OrderService;
import com.studymp.persistence.entity.Order;
import com.studymp.persistence.entity.Section;
import com.studymp.persistence.entity.User;
import com.studymp.persistence.repositories.OrderRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import static org.jooq.lambda.Seq.seq;

/**
 * Created by qwerty on 07.05.2017.
 */
@Component
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = Logger.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order find(Long id) throws NotFoundException {
        Order result = orderRepository.findOne(id);
        if (result == null) {
            LOGGER.error("Не удалось найти заказ с id " + id);
            throw new NotFoundException();
        }
        return result;
    }

    @Override
    public Order findByNameAndCreator(String name, User creator) throws NotFoundException {
        Order result = seq(orderRepository.findByNameAndCreator(name, creator))
                .findFirst()
                .orElseThrow(() -> {
                    LOGGER.error("Не удалось найти заказ с именем " + name);
                    return new NotFoundException();
                });
        return result;
    }

    @Transactional
    @Override
    public Long create(Order order) {
        Long id = orderRepository.save(order).getId();
        orderRepository.flush();
        return id;
    }

    @Transactional
    @Override
    public void update(Order order) {
        orderRepository.save(order);
        orderRepository.flush();
    }

    @Transactional
    @Override
    public void delete(Long id) {
        orderRepository.delete(id);
        orderRepository.flush();
    }

    @Override
    public List<Order> findBySectionWithPageable(Section section, Pageable pageable) throws Exception {
        List<Order> result = orderRepository.findBySection(section, pageable).getContent();
        return result;
    }
}
