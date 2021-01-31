package com.springboot.project.DocumentDetailsService.service.impl;

import com.springboot.project.DocumentDetailsService.exception.IDNotFoundException;
import com.springboot.project.DocumentDetailsService.model.ArticleDocument;
import com.springboot.project.DocumentDetailsService.model.ArticleDocumentRequest;
import com.springboot.project.DocumentDetailsService.model.FAQDocument;
import com.springboot.project.DocumentDetailsService.model.FAQDocumentRequest;
import com.springboot.project.DocumentDetailsService.repository.ArticleDocumentRepository;
import com.springboot.project.DocumentDetailsService.repository.FAQDocumentRepository;
import com.springboot.project.DocumentDetailsService.service.ArticleDocumentService;
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
public class ArticleDocumentServiceImpl implements ArticleDocumentService {

    static final Logger logger  = LogManager.getLogger(ArticleDocumentServiceImpl.class.getName());

    @Autowired
    private ArticleDocumentRepository articleDocumentRepository;

    @Override
    public List<ArticleDocument> getAllArticleDocuments() {
        return articleDocumentRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ArticleDocument updateArticleDocument(int id, ArticleDocumentRequest documentRequest){
        ArticleDocument document = new ArticleDocument();
        try {
            Optional<ArticleDocument> optionalDepartment = articleDocumentRepository.findById(id);
            if (optionalDepartment.isPresent()) {
                ArticleDocument documentUpdated = optionalDepartment.get();
                documentUpdated.setDocType(documentRequest.getDocType());
                documentUpdated.setTitle(documentRequest.getTitle());
                documentUpdated.setContent(documentRequest.getContent());
                document = articleDocumentRepository.saveAndFlush(documentUpdated);
            }
        } catch (Exception ex){
            logger.error(ex.getMessage());
            throw new IDNotFoundException();
        }
        return document;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void patchArticleDocumentByID(ArticleDocument document, int id) {
        if(id == document.getDocId()) {
            articleDocumentRepository.save(document);
        }
    }

    @Override
    public ArticleDocument getDocument(int id) {
        ArticleDocument document = new ArticleDocument();

            Optional<ArticleDocument> optionalDepartment = articleDocumentRepository.findById(id);
            if (optionalDepartment.isPresent()){
               document = optionalDepartment.get();
            }
        return document;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteArticleDocument(int id) {
        articleDocumentRepository.deleteById(id);
    }

    @Override
    public void deleteAllArticleDocuments() {
        articleDocumentRepository.deleteAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createArticleDocument(ArticleDocument document)
    {
        articleDocumentRepository.save(document);

    }
}
