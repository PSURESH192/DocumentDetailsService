package com.springboot.project.DocumentDetailsService.controller;

import com.springboot.project.DocumentDetailsService.model.ArticleDocument;
import com.springboot.project.DocumentDetailsService.model.ArticleDocumentRequest;
import com.springboot.project.DocumentDetailsService.model.FAQDocument;
import com.springboot.project.DocumentDetailsService.model.FAQDocumentRequest;
import com.springboot.project.DocumentDetailsService.service.ArticleDocumentService;
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
public class ArticleDocumentController {

    static final Logger logger  = LogManager.getLogger(ArticleDocumentController.class.getName());

    @Autowired
    private ArticleDocumentService articleDocumentService;

    @GetMapping(value="/articleDocuments")
    @ApiOperation(value = "Get All Article Documents",
            response = ArticleDocument.class, responseContainer = "List",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ArticleDocument>> getAllArticleDocuments() {
        {
            List<ArticleDocument> departments = articleDocumentService.getAllArticleDocuments();
            return new ResponseEntity<>(departments, HttpStatus.OK);
        }
    }

    @GetMapping(value="/articleDocuments/{id}")
    @ApiOperation(value = "Get Document by ID",
            response = ArticleDocument.class,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArticleDocument> getDocument(@NotNull @PathVariable("id") int id) {
        {
            if (Optional.ofNullable(id).isPresent()) {
                ArticleDocument document = articleDocumentService.getDocument(id);
                return new ResponseEntity<>(document, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
    }

    @PostMapping("/articleDocuments")
    @ApiOperation(value = "Create Article Document",
            consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Integer> createArticleDocument(@Valid @RequestBody ArticleDocument articleDocument)
    {
        if(Optional.ofNullable(articleDocument).isPresent()) {
            articleDocumentService.createArticleDocument(articleDocument);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(articleDocument.getDocId(),HttpStatus.CREATED);
    }


    @PutMapping("/articleDocuments/{id}")
    @ApiOperation(value = "Update Article Document",
            response = ArticleDocument.class, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArticleDocument> updateArticleDocument(@NotNull @PathVariable("id") int id,@Valid @RequestBody ArticleDocumentRequest documentRequest) {
        if (Optional.ofNullable(documentRequest).isPresent()){
            ArticleDocument document = articleDocumentService.updateArticleDocument(id, documentRequest);
            return new ResponseEntity<>(document,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        }

    }

    @DeleteMapping("/articleDocuments")
    @ApiOperation(value = "Delete All Article Documents", response = HttpStatus.class, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<HttpStatus> deleteAllArticleDocuments()
    {
        articleDocumentService.deleteAllArticleDocuments();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value="/articleDocuments/{id}")
    @ApiOperation(value = "Delete Document by ID", response = HttpStatus.class, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> deleteArticleDocument(@NotNull @PathVariable("id") int id) {

            if (Optional.ofNullable(id).isPresent()) {
                articleDocumentService.deleteArticleDocument(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

    }
    @PatchMapping("/articleDocuments/{id}")
    @ApiOperation(value = "Patch Article Document Details by ID",notes = "Partial Update of Document is Provided",
            response = HttpStatus.class, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> patchArticleDocumentByID(@Valid @RequestBody ArticleDocument document,@NotNull @PathVariable("id") int id) {

        if (Optional.ofNullable(id).isPresent()) {
            articleDocumentService.patchArticleDocumentByID(document, id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}

