package com.springboot.project.DocumentDetailsService.service;

import com.springboot.project.DocumentDetailsService.model.ArticleDocument;
import com.springboot.project.DocumentDetailsService.model.ArticleDocumentRequest;
import com.springboot.project.DocumentDetailsService.model.FAQDocument;
import com.springboot.project.DocumentDetailsService.model.FAQDocumentRequest;

import java.util.List;

public interface ArticleDocumentService {

   public List<ArticleDocument> getAllArticleDocuments();

   public void createArticleDocument(ArticleDocument articleDocument);

   public ArticleDocument getDocument(int id);

   public void deleteArticleDocument(int id);

   public void deleteAllArticleDocuments();

   public ArticleDocument updateArticleDocument(int id, ArticleDocumentRequest documentRequest);

   public void patchArticleDocumentByID(ArticleDocument articleDocument, int id);
}
