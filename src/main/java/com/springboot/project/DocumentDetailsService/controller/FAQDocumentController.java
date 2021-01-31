package com.springboot.project.DocumentDetailsService.controller;

import com.springboot.project.DocumentDetailsService.model.FAQDocumentRequest;
import com.springboot.project.DocumentDetailsService.model.FAQDocument;
import com.springboot.project.DocumentDetailsService.service.FAQDocumentService;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class FAQDocumentController {

    static final Logger logger  = LogManager.getLogger(FAQDocumentController.class.getName());

    @Autowired
    private FAQDocumentService fAQDocumentService;

    @GetMapping(value="/faqdocuments")
    @ApiOperation(value = "Get All FAQ Documents",
            response = FAQDocument.class, responseContainer = "List",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FAQDocument>> getAllFAQDocuments() {
        {
            List<FAQDocument> departments = fAQDocumentService.getAllFAQDocuments();
            return new ResponseEntity<>(departments, HttpStatus.OK);
        }
    }

    @GetMapping(value="/faqdocuments/{id}")
    @ApiOperation(value = "Get Document by ID",
            response = FAQDocument.class,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FAQDocument> getDocument(@NotNull @PathVariable("id") int id) {
        {
            if (Optional.ofNullable(id).isPresent()) {
                FAQDocument document = fAQDocumentService.getDocument(id);
                return new ResponseEntity<>(document, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
    }

    @PostMapping("/faqdocuments")
    @ApiOperation(value = "Create FAQ Document",
            consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Integer> createFAQDocument(@Valid @RequestBody FAQDocument faqDocument)
    {
        if(Optional.ofNullable(faqDocument).isPresent()) {
            fAQDocumentService.createFAQDocument(faqDocument);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(faqDocument.getDocId(),HttpStatus.CREATED);
    }


    @PutMapping("/faqdocuments/{id}")
    @ApiOperation(value = "Update FAQ Document",
            response = FAQDocument.class, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FAQDocument> updateFAQDocument(@NotNull @PathVariable("id") int id,@Valid @RequestBody FAQDocumentRequest documentRequest) {
        if (Optional.ofNullable(documentRequest).isPresent()){
            FAQDocument faqDocument = fAQDocumentService.updateFAQDocument(id, documentRequest);
            return new ResponseEntity<>(faqDocument,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        }

    }

    @DeleteMapping("/faqdocuments")
    @ApiOperation(value = "Delete All FAQ Documents", response = HttpStatus.class, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<HttpStatus> deleteAllFAQDocuments()
    {
        fAQDocumentService.deleteAllFAQDocuments();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value="/faqdocuments/{id}")
    @ApiOperation(value = "Delete Document by ID", response = HttpStatus.class, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> deleteFAQDocument(@NotNull @PathVariable("id") int id) {

            if (Optional.ofNullable(id).isPresent()) {
                fAQDocumentService.deleteFAQDocument(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

    }
    @PatchMapping("/faqdocuments/{id}")
    @ApiOperation(value = "Patch FAQ Document Details by ID",notes = "Partial Update of Document is Provided",
            response = HttpStatus.class, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> patchFAQDocumentByID(@Valid @RequestBody FAQDocument faqDocument,@NotNull @PathVariable("id") int id) {

        if (Optional.ofNullable(id).isPresent()) {
            fAQDocumentService.patchFAQDocumentByID(faqDocument, id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}

