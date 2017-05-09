package com.studymp.domain.interfaces;

import com.studymp.domain.exceptions.NotFoundException;
import com.studymp.persistence.entity.Section;

/**
 * Created by qwerty on 07.05.2017.
 */
public interface SectionService {
    Section findById(Long id) throws NotFoundException;
    Section findByName(String name) throws Exception;
    Long create(Section section);
    void update(Section section);
    void delete(Section section);
    void delete(Long id);
}
