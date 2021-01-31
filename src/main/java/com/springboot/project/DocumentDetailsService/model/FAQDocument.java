package com.springboot.project.DocumentDetailsService.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name="document_faq")
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class FAQDocument {

	@Id
	@Column(name = "document_Id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int docId;

	@NotBlank(message = "Document Type is mandatory")
    @Column(name = "document_Type")
	private String docType;

	@Column(name = "question")
	private String question;

	@Column(name = "answer")
	private String answer;

}