package com.studymp.domain.services;

import com.studymp.domain.interfaces.ResponderService;
import com.studymp.persistence.entity.Order;
import com.studymp.persistence.entity.Responder;
import com.studymp.persistence.entity.User;
import com.studymp.persistence.repositories.ResponderRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static org.jooq.lambda.Seq.seq;

/**
 * Created by qwerty on 07.05.2017.
 */
@Component
public class ResponderServiceImpl implements ResponderService {

    private final static Logger LOGGER = Logger.getLogger(ResponderService.class);

    private final ResponderRepository responderRepository;

    @Autowired
    public ResponderServiceImpl(ResponderRepository responderRepository) {
        this.responderRepository = responderRepository;
    }

    @Transactional
    @Override
    public Long create(Responder responder) {
        Long id = responderRepository.save(responder).getId();
        responderRepository.flush();
        return id;
    }

    @Transactional
    @Override
    public void delete(Responder responder) {
        responderRepository.delete(responder);
        responderRepository.flush();
    }

    @Transactional
    @Override
    public void delete(Long id) {
        responderRepository.delete(id);
        responderRepository.flush();
    }

    @Override
    public Responder findByOrderAndResponder(Order order, User responder) throws Exception {
        Responder result = seq(responderRepository.findByOrderAndResponder(order, responder))
                .findFirst()
                .orElseThrow(() -> {
                    LOGGER.error("Не удалось найти ассоциацию на заказ " + order.getName()
                            + " с откликнувшимся " + responder.getUsername() );
                    return new Exception();
                });
        return result;
    }
}
