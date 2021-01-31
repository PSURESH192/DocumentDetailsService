package com.springboot.project.DocumentDetailsService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class FAQDocumentRequest {

    private String docType;
    private String question;
    private String answer;
}
