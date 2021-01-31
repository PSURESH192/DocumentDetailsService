package com.springboot.project.DocumentDetailsService.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name="document_article")
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticleDocument {

	@Id
	@Column(name = "document_Id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int docId;

	@NotBlank(message = "Document Type is mandatory")
	@Column(name = "document_Type")
	private String docType;

	@Column(name = "title")
	private String title;

	@Column(name = "content")
	private String content;

}