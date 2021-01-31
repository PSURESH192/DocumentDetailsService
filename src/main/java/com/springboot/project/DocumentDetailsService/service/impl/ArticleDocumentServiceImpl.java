package com.springboot.project.DocumentDetailsService.service.impl;

import com.springboot.project.DocumentDetailsService.exception.IDNotFoundException;
import com.springboot.project.DocumentDetailsService.model.FAQDocumentRequest;
import com.springboot.project.DocumentDetailsService.model.FAQDocument;
import com.springboot.project.DocumentDetailsService.repository.FAQDocumentRepository;
import com.springboot.project.DocumentDetailsService.service.FAQDocumentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class FAQDocumentServiceImpl implements FAQDocumentService {

    static final Logger logger  = LogManager.getLogger(FAQDocumentServiceImpl.class.getName());

    @Autowired
    private FAQDocumentRepository fAQDocumentRepository;

    @Override
    public List<FAQDocument> getAllFAQDocuments() {
        return fAQDocumentRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public FAQDocument updateFAQDocument(int id, FAQDocumentRequest documentRequest){
        FAQDocument faqDocument = new FAQDocument();
        try {
            Optional<FAQDocument> optionalDepartment = fAQDocumentRepository.findById(id);
            if (optionalDepartment.isPresent()) {
                FAQDocument faqDocumentUpdated = optionalDepartment.get();
                faqDocumentUpdated.setDocType(documentRequest.getDocType());
                faqDocumentUpdated.setQuestion(documentRequest.getQuestion());
                faqDocumentUpdated.setAnswer(documentRequest.getAnswer());
                faqDocument = fAQDocumentRepository.saveAndFlush(faqDocumentUpdated);
            }
        } catch (Exception ex){
            logger.error(ex.getMessage());
            throw new IDNotFoundException();
        }
        return faqDocument;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void patchFAQDocumentByID(FAQDocument document, int id) {
        if(id == document.getDocId()) {
            fAQDocumentRepository.save(document);
        }
    }

    @Override
    public FAQDocument getDocument(int id) {
        FAQDocument faqDocument = new FAQDocument();

            Optional<FAQDocument> optionalDepartment = fAQDocumentRepository.findById(id);
            if (optionalDepartment.isPresent()){
               faqDocument = optionalDepartment.get();
            }
        return faqDocument;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteFAQDocument(int id) {
        fAQDocumentRepository.deleteById(id);
    }

    @Override
    public void deleteAllFAQDocuments() {
        fAQDocumentRepository.deleteAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createFAQDocument(FAQDocument faqDocument)
    {
        fAQDocumentRepository.save(faqDocument);

    }
}
