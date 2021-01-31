package com.springboot.project.DocumentDetailsService.service;

import com.springboot.project.DocumentDetailsService.model.FAQDocumentRequest;
import com.springboot.project.DocumentDetailsService.model.FAQDocument;


import java.util.List;

public interface FAQDocumentService {

   public List<FAQDocument> getAllFAQDocuments();

   public void createFAQDocument(FAQDocument faqDocument);

   public FAQDocument getDocument(int id);

   public void deleteFAQDocument(int id);

   public void deleteAllFAQDocuments();

   public FAQDocument updateFAQDocument(int id, FAQDocumentRequest documentRequest);

   public void patchFAQDocumentByID(FAQDocument faqDocument, int id);
}
