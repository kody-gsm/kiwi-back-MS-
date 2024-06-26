package com.example.kiwi.repository;

import com.example.kiwi.domain.selection.Selection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface SelectionRep extends JpaRepository<Selection, Integer> {

}
