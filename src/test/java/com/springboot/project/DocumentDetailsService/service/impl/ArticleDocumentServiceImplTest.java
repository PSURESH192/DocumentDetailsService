package com.springboot.project.DocumentDetailsService.service.impl;

import com.springboot.project.DocumentDetailsService.exception.IDNotFoundException;
import com.springboot.project.DocumentDetailsService.model.FAQDocument;
import com.springboot.project.DocumentDetailsService.model.FAQDocumentRequest;
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
public class FAQDocumentServiceImplTest {

    @InjectMocks
    private FAQDocumentServiceImpl faqDocumentService;

    @Mock
    private FAQDocumentRepository fAQDocumentRepository;

    @Test
    public void testgetAllFAQDocuments(){
        FAQDocument document = new FAQDocument();
        document.setDocId(1);
        List<FAQDocument> documents = new ArrayList<>();
        documents.add(document);
        Mockito.when(fAQDocumentRepository.findAll()).thenReturn(documents);
        List<FAQDocument> allFAQDocuments = faqDocumentService.getAllFAQDocuments();
        Assert.assertNotNull(allFAQDocuments);
    }

    @Test
    public void testgetDocument(){
        FAQDocument document = new FAQDocument();
        document.setDocId(1);
        Mockito.when(fAQDocumentRepository.findById(1)).thenReturn(Optional.of(document));
        FAQDocument document1 = faqDocumentService.getDocument(1);
        Assert.assertNotNull(document1);
    }

    @Test
    public void testupdateFAQDocument(){
        FAQDocumentRequest fAQDocumentRequest = new FAQDocumentRequest();
        fAQDocumentRequest.setDocType("FAQ");
        fAQDocumentRequest.setQuestion("Question");
        fAQDocumentRequest.setAnswer("Answer");
        FAQDocument document = new FAQDocument();
        document.setDocId(1);
        document.setDocType("FAQ");
        document.setQuestion("Question");
        document.setAnswer("Answer");
        FAQDocument faqDocument = faqDocumentService.updateFAQDocument(1, fAQDocumentRequest);
        Assert.assertNotNull(faqDocument);
    }

    @Test
    public void testupdateFAQDocumentException(){
        FAQDocumentRequest fAQDocumentRequest = new FAQDocumentRequest();
        fAQDocumentRequest.setDocType("FAQ");
        fAQDocumentRequest.setQuestion("Question");
        fAQDocumentRequest.setAnswer("Answer");
        try {
            Mockito.when(fAQDocumentRepository.findById(Mockito.anyInt())).thenThrow(IDNotFoundException.class);
            faqDocumentService.updateFAQDocument(1, fAQDocumentRequest);
        } catch (IDNotFoundException ex){
            ex.getMessage();
        }

    }

    @Test
    public void testpatchFAQDocumentByID(){
        FAQDocument document = new FAQDocument();
        document.setDocId(1);
        document.setDocType("FAQ");
        document.setQuestion("Question");
        document.setAnswer("Answer");
        faqDocumentService.patchFAQDocumentByID(document,1);
    }

    @Test
    public void testdeleteFAQDocument(){
        FAQDocument document = new FAQDocument();
        document.setDocId(1);
        faqDocumentService.deleteFAQDocument(1);

    }

    @Test
    public void testdeleteAllFAQDocuments(){
        FAQDocument document = new FAQDocument();
        document.setDocId(1);
        faqDocumentService.deleteAllFAQDocuments();

    }

    @Test
    public void testCreateDepartment(){
        FAQDocument document = new FAQDocument();
        document.setDocId(1);
        document.setDocType("FAQ");
        document.setQuestion("Question");
        document.setAnswer("Answer");
        faqDocumentService.createFAQDocument(document);

    }

}
