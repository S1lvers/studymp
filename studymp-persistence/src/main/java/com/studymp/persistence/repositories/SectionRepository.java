package com.studymp.persistence.repositories;

import com.studymp.persistence.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by qwerty on 07.05.2017.
 */
public interface SectionRepository extends JpaRepository<Section, Long> {
    List<Section> findByName(String name);
}
