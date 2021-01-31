#Document Management System
#DocumentDetailsService

#HOW TO RUN THIS PROJECT?#
###FROM THE IDE:###
1. Open the project in an IDE like Eclipse/Intellij IDEA.
2. Check your database connection in src/main/resources/application.properties file and change if needed.
3. Go to com.springboot.project.DocumentDetailsService
4. Right Click on class Application.
5. Hit "Run As Java Application" in the IDE.
6. Check if localhost server has started.
7. Open Postman client service on Google chrome.
8. Hit url : "http://localhost:8085/faqdocuments" and url : "http://localhost:8085/articleDocuments"
9. Accordingly select the request method and the url as follows:
	FAQ Documents:
		POST - "http://localhost:8085/faqdocuments" - inserts into department
		
		Sample Request Body: {
								"docType": "FAQ",
								"question": "How to I reset my password?",
								"answer": "Do Forgot password and generate pin"
							}
		GET - "http://localhost:8085/faqdocuments" - gets list of all Documents
		GET - "http://localhost:8085/faqdocuments/{id}" - gets Document with selected id
		PUT - "http://localhost:8085/faqdocuments/{id}" - updates Document with selected id
		
		Sample Request Body: {
								"docType": "FAQ",
                                "question": "How to I reset my password?",
                                "answer": "Do Forgot password and generate pin using mail ID"
							}
		DELETE - "http://localhost:8085/faqdocuments" - deletes all faq documents
		DELETE - "http://localhost:8085/faqdocuments/{id}" - deletes document with selected id
		PATCH - "http://localhost:8085/faqdocuments/{id}" - patches/updates document with selected id
		
		Sample Request Body: {
								"docId": 1,
								"docType": "FAQ",
                                "question": "How to I reset my password?",
                                "answer": "Do Forgot password and generate pin"
							}
		id value in url and docId should be same.
		
	Article Documents:
		GET - "http://localhost:8085/articleDocuments" - gets list of all article documents
		GET - "http://localhost:8085/articleDocuments/{id}" - gets documents with selected id
		POST - "http://localhost:8085/articleDocuments" - inserts into article documents
		
		Sample Request Body: {
								"docType": "Article",
                                "title": "Account Generation Process",
                                "content": "For Account generation you need to fill up form …"
							}
		PUT - "http://localhost:8085/articleDocuments/{id}" - updates documents with selected id
		
		Sample Request Body: {
                                "docType": "Article",
                                "title": "Account Generation",
                                "content": "you need to fill up form …"
                             }
		DELETE - "http://localhost:8085/articleDocuments" - deletes all documents
		DELETE - "http://localhost:8085/articleDocuments/{id}" - deletes documents with selected id
		PATCH - "http://localhost:8085/articleDocuments/{id}" - patches/updates documents with selected id
		
		Sample Request Body: {
		                        "docId": 1,
								"docType": "Article",
                                "title": "Account Generation",
                                "content": "you need to fill up form …"
							}
			id value in url and docId should be same.

	Swagger URL:
		http://localhost:8085/swagger-ui/
	
	H2 Console:
		http://localhost:8085/h2console/
	Verify the application.properties for connection details, user/password details.
	
#ASSUMPTIONS#
1. H2 Console is configured correctly and running in application .
2. FAQ and Article Document Tables are created in H2 Console.


#TECHNOLOGY STACK#
1. Java
2. Intellij IDEA
3. Postman for Chrome


#DESIGN DISCUSSION#
1. Get mapping will fetch the results, Post mapping will insert results, Put mapping and Patch mapping will update results, Delete mapping will delete results.
