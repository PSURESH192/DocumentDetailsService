package com.springboot.project.DocumentDetailsService.service.impl;

import com.springboot.project.DocumentDetailsService.exception.IDNotFoundException;
import com.springboot.project.DocumentDetailsService.model.ArticleDocument;
import com.springboot.project.DocumentDetailsService.model.ArticleDocumentRequest;
import com.springboot.project.DocumentDetailsService.model.FAQDocument;
import com.springboot.project.DocumentDetailsService.model.FAQDocumentRequest;
import com.springboot.project.DocumentDetailsService.repository.ArticleDocumentRepository;
import com.springboot.project.DocumentDetailsService.repository.FAQDocumentRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ArticleDocumentServiceImplTest {

    @InjectMocks
    private ArticleDocumentServiceImpl articleDocumentService;

    @Mock
    private ArticleDocumentRepository articleDocumentRepository;

    @Test
    public void testgetAllArticleDocuments(){
        ArticleDocument document = new ArticleDocument();
        document.setDocId(1);
        List<ArticleDocument> documents = new ArrayList<>();
        documents.add(document);
        Mockito.when(articleDocumentRepository.findAll()).thenReturn(documents);
        List<ArticleDocument> allDocuments = articleDocumentService.getAllArticleDocuments();
        Assert.assertNotNull(allDocuments);
    }

    @Test
    public void testgetDocument(){
        ArticleDocument document = new ArticleDocument();
        document.setDocId(1);
        Mockito.when(articleDocumentRepository.findById(1)).thenReturn(Optional.of(document));
        ArticleDocument document1 = articleDocumentService.getDocument(1);
        Assert.assertNotNull(document1);
    }

    @Test
    public void testupdateArticleDocument(){
        ArticleDocumentRequest documentRequest = new ArticleDocumentRequest();
        documentRequest.setDocType("Article");
        documentRequest.setTitle("Title");
        documentRequest.setContent("Content");
        ArticleDocument document = new ArticleDocument();
        document.setDocId(1);
        document.setDocType("Article");
        document.setTitle("Title");
        document.setContent("Content");
        ArticleDocument document1 = articleDocumentService.updateArticleDocument(1, documentRequest);
        Assert.assertNotNull(document1);
    }

    @Test
    public void testupdateArticleDocumentException(){
        ArticleDocumentRequest documentRequest = new ArticleDocumentRequest();
        documentRequest.setDocType("Article");
        documentRequest.setTitle("Title");
        documentRequest.setContent("Content");
        try {
            Mockito.when(articleDocumentRepository.findById(Mockito.anyInt())).thenThrow(IDNotFoundException.class);
            articleDocumentService.updateArticleDocument(1, documentRequest);
        } catch (IDNotFoundException ex){
            ex.getMessage();
        }

    }

    @Test
    public void testpatchArticleDocumentByID(){
        ArticleDocument document = new ArticleDocument();
        document.setDocId(1);
        document.setDocType("Article");
        document.setTitle("Title");
        document.setContent("Content");
        articleDocumentService.patchArticleDocumentByID(document,1);
    }

    @Test
    public void testdeleteArticleDocument(){
        ArticleDocument document = new ArticleDocument();
        document.setDocId(1);
        articleDocumentService.deleteArticleDocument(1);

    }

    @Test
    public void testdeleteAllDocuments(){
        ArticleDocument document = new ArticleDocument();
        document.setDocId(1);
        articleDocumentService.deleteAllArticleDocuments();

    }

    @Test
    public void testCreateArticleDocument(){
        ArticleDocument document = new ArticleDocument();
        document.setDocId(1);
        document.setDocType("Article");
        document.setTitle("Title");
        document.setContent("Content");
        articleDocumentService.createArticleDocument(document);

    }

}
