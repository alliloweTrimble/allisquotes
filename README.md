# allisquotes
Quotes project
API Specification Doc
Index:


1. Get Authors
2. Get Authors by Name
3. Get Quotes
4. Get Quotes by Text
5. Get Quotes by AuthorId
6. Post Quotes
7. Put Quotes by QuoteId
8. Delete Quotes by QuoteId


























Methods
1. Get Authors
Return a paged list of all authors


Method
	URL            
	GET
	http://localhost:8080/authors
	

Status
	Response
	200
	{
         “id”: 126,
         “name”: “Abba Eban”
     },
         “id”: 172,
         “name”: “Abel Ferrara”
}


	

2. Get authors by name
Return a paged list of all authors that matches the submitted name fragment 


Method
	URL            
	GET
	http://localhost:8080/authors?name={nameFragement}
	

Status
	Response
	200
	{
         “id”: 1271,
         “name”: “Alex Haley”
     },
         “id”: 1282,
         “name”: “Alex Van Halen”
}
	



3. Get quotes
Return a paged list of all quotes


Method
	URL            
	GET
	http://localhost:8080/quotes
	

Status
	Response
	200
	{
    "id": 2,
    “author”: {
         “id”: 1,
         “Name”: “ATL”
     },
     “text”: “This is all the quotes”
}
	

4. Get quotes by text
Return a paged list of quotes that contain the text fragment


Method
	URL            
	GET
	http://localhost:8080/quotes?text={textFragment}
	

Status
	Response
	200
	{
    "id": 2,
    “author”: {
         “id”: 1,
         “Name”: “ATL”
     },
     “text”: “This is all the quotes with this {text}.”
}  
	5. Get quotes by authorId
Return a paged list of quotes where the author of the quote matches the submitted author identifier


Method
	URL            
	GET
	http://localhost:8080/quotes?authorId={authorId}
	

Status
	Response
	200
	{   "id": 2,
    “author”: {
         “id”: 1,
         “Name”: “ATL”
     },
     “text”: “This is all the quotes with author, id {1}.”
}  
	6. Post a new quote
Create a quote by an existing author
Method
	URL            
	POST
	http://localhost:8080/quotes
	

Type
	Body
	Raw JSON (application/json)
	{
    “author”: {
         “id”: 15843,
         “name”: “Hale Irwin”
     },
     “text”: “This is a new quote by an existing author.”
}
	

Type
	Body
	Raw JSON (application/json) Response 200 OK
	{   “id”: 42381,
    “author”: {
         “id”: 15843,
         “name”: “Hale Irwin”
     },
     “text”: “This is a new quote by an existing author.”
}
	7. Put a quote
Update a quote’s text - using the quote id.
Method
	URL            
	POST
	http://localhost:8080/quotes/{id}
	

Type
	Body
	Raw JSON (application/json)
	{
     “text”: “This quote has now been updated.”
}
	

Type
	Body
	Raw JSON (application/json) 200 OK Response
	{
    "id": 15858,
    "author": {
        "id": 15844,
        "name": "Hale Irwin"
    },
    "text": "This quote has now been updated."
}
	8. Delete a quote
Delete a quote- by quote id.
Method
	URL            
	DELETE
	http://localhost:8080/quotes/{id}
	

Status
	Response
	200
	OK.
