package com.studymp.domain.services;

import com.studymp.domain.exceptions.NotFoundException;
import com.studymp.domain.interfaces.SectionService;
import com.studymp.persistence.entity.Section;
import com.studymp.persistence.repositories.SectionRepository;
import org.apache.log4j.Logger;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.jooq.lambda.Seq.seq;

/**
 * Created by qwerty on 07.05.2017.
 */
@Component
public class SectionServiceImpl implements SectionService {

    private final static Logger LOGGER = Logger.getLogger(SectionService.class);

    private final SectionRepository sectionRepository;

    @Autowired
    public SectionServiceImpl(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    @Override
    public Section findById(Long id) throws NotFoundException {
        Section result = sectionRepository.findOne(id);
        if (result == null) {
            LOGGER.error("Не удалось найти секцию с id " + id);
            throw new NotFoundException();
        }
        return result;
    }

    @Override
    public Section findByName(String name) throws NotFoundException {
        Section result = seq(sectionRepository.findByName(name))
                .findFirst()
                .orElseThrow(() -> {
                    LOGGER.error("Не удалось найти секцию с именем " + name);
                    return new NotFoundException();
                });
        return result;
    }

    @Transactional
    @Override
    public Long create(Section section) {
        Long id = sectionRepository.save(section).getId();
        sectionRepository.flush();
        return id;
    }

    @Transactional
    @Override
    public void update(Section section) {
        sectionRepository.save(section);
        sectionRepository.flush();
    }

    @Transactional
    @Override
    public void delete(Section section) {
        sectionRepository.delete(section);
        sectionRepository.flush();
    }

    @Transactional
    @Override
    public void delete(Long id) {
        sectionRepository.delete(id);
        sectionRepository.flush();
    }

    @Override
    public List<Section> findAll() {
       return sectionRepository.findAll();
    }
}
