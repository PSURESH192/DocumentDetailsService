package com.springboot.project.DocumentDetailsService.repository;

import com.springboot.project.DocumentDetailsService.model.ArticleDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleDocumentRepository extends JpaRepository<ArticleDocument, Integer> {
}
