package com.springboot.project.DocumentDetailsService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ArticleDocumentRequest {

    private String docType;
    private String title;
    private String content;
}
