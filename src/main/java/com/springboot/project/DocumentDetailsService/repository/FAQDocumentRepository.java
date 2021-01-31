package com.springboot.project.DocumentDetailsService.repository;

import com.springboot.project.DocumentDetailsService.model.FAQDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FAQDocumentRepository extends JpaRepository<FAQDocument, Integer> {
}
