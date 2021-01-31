package com.springboot.project.DocumentDetailsService.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class FAQDocumentControllerTest {

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
    public void getAllFAQDocumentsTest() throws Exception {
        String uri = "/faqdocuments";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
    }

    @Test
    public void getAllFAQDocumentsTestException404() throws Exception {
        String uri = "/faqdocument";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(404, status);
    }

    @Test
    public void getDocumentTest() throws Exception {
        String uri = "/faqdocuments/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
    }

    @Test
    public void createFAQDocumentTest() throws Exception {
        String uri = "/faqdocuments";
        FAQDocument document = new FAQDocument();
        document.setDocId(1);
        document.setDocType("FAQ");
        document.setQuestion("Question");
        document.setAnswer("Answer");
        String inputJson = mapToJson(document);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(201, status);
    }

    @Test
    public void createFAQDocumentTestBadRequest() throws Exception {
        String uri = "/faqdocuments";
        FAQDocument faqDocument = null;
        String inputJson = mapToJson(faqDocument);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(400, status);
    }

    @Test
    public void deleteAllFAQDocumentsTest() throws Exception {
        String uri = "/faqdocuments";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
    }

    @Test(expected = Exception.class)
    public void deleteFAQDocumentTest() throws Exception {
        String uri = "/faqdocuments/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
    }

    @Test
    public void updateFAQDocumentTest() throws Exception {
        String uri = "/faqdocuments/1";
        FAQDocumentRequest fAQDocumentRequest = new FAQDocumentRequest();
        fAQDocumentRequest.setDocType("FAQ");
        fAQDocumentRequest.setQuestion("Question");
        fAQDocumentRequest.setAnswer("Answer");
        String inputJson = mapToJson(fAQDocumentRequest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
    }

    @Test
    public void updateFAQDocumentTestBadRequest() throws Exception {
        String uri = "/faqdocuments/1";
        FAQDocumentRequest FAQDocumentRequest = null;
        String inputJson = mapToJson(FAQDocumentRequest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(400, status);
    }

    @Test
    public void patchFAQDocumentByIDTest() throws Exception {
        String uri = "/faqdocuments/1";
        FAQDocument document = new FAQDocument();
        document.setDocId(1);
        document.setDocType("FAQ");
        document.setQuestion("Question");
        document.setAnswer("Answer");
        String inputJson = mapToJson(document);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.patch(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
    }

    @Test
    public void patchFAQDocumentByIDBadRequest() throws Exception {
        String uri = "/faqdocuments/1";
        FAQDocument document = null;
        String inputJson = mapToJson(document);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.patch(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(400, status);
    }

}
