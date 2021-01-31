package com.springboot.project.DocumentDetailsService.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.project.DocumentDetailsService.model.ArticleDocument;
import com.springboot.project.DocumentDetailsService.model.ArticleDocumentRequest;
import com.springboot.project.DocumentDetailsService.model.FAQDocument;
import com.springboot.project.DocumentDetailsService.model.FAQDocumentRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class ArticleDocumentControllerTest {

    protected MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    @Test
    public void getAllArticleDocumentsTest() throws Exception {
        String uri = "/articleDocuments";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
    }

    @Test
    public void getAllArticleDocumentsTestException404() throws Exception {
        String uri = "/articledocument";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(404, status);
    }

    @Test
    public void getDocumentTest() throws Exception {
        String uri = "/articleDocuments/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
    }

    @Test
    public void createArticleDocumentTest() throws Exception {
        String uri = "/articleDocuments";
        ArticleDocument document = new ArticleDocument();
        document.setDocId(1);
        document.setDocType("Article");
        document.setTitle("Title");
        document.setContent("Content");
        String inputJson = mapToJson(document);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(201, status);
    }

    @Test
    public void createArticleDocumentTestBadRequest() throws Exception {
        String uri = "/articleDocuments";
        ArticleDocument articleDocument = null;
        String inputJson = mapToJson(articleDocument);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(400, status);
    }

    @Test
    public void deleteAllArticleDocumentsTest() throws Exception {
        String uri = "/articleDocuments";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
    }

    @Test
    public void deleteArticleDocumentTest() throws Exception {
        String uri = "/articleDocuments/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
    }

    @Test
    public void updateArticleDocumentTest() throws Exception {
        String uri = "/articleDocuments/1";
        ArticleDocumentRequest documentRequest = new ArticleDocumentRequest();
        documentRequest.setDocType("Article");
        documentRequest.setTitle("Title");
        documentRequest.setContent("Content");
        String inputJson = mapToJson(documentRequest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
    }

    @Test
    public void updateFAQDocumentTestBadRequest() throws Exception {
        String uri = "/articleDocuments/1";
        ArticleDocumentRequest documentRequest = null;
        String inputJson = mapToJson(documentRequest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(400, status);
    }

    @Test
    public void patchArticleDocumentByIDTest() throws Exception {
        String uri = "/articleDocuments/1";
        ArticleDocument document = new ArticleDocument();
        document.setDocId(1);
        document.setDocType("Article");
        document.setTitle("Title");
        document.setContent("Content");
        String inputJson = mapToJson(document);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.patch(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
    }

    @Test
    public void patchArticleDocumentByIDBadRequest() throws Exception {
        String uri = "/articleDocuments/1";
        ArticleDocument document = null;
        String inputJson = mapToJson(document);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.patch(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(400, status);
    }

}
