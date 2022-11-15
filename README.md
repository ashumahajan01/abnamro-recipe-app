Project Overview
-------------
This Application is used for users to manage their favourite recipes. 
Operations Like adding, updating, removing and fetching recipes. 
Additionally users should be able to filter recipe based of search criteria.

### Running the application

Import Maven Project in you IDE of choice.

A basic spring boot application is already provided (mainclass: `com.abnamro.MainApplication`).

mvn clean install

### Embedded H2 database

You can view the database through the h2 webconsole.   

Url: [http://localhost:8080/h2-console]   
Database url: `jdbc:h2:mem:receipesTest`   
Username: `sa` (no password required)   


### Swagger-UI 
 
You can view the API documents through the Swagger-UI .   

Url: http://localhost:8080/swagger-ui/index.html 


```
1. Save Recipe 
url: http://localhost:8080/api/receipe/save

Note : 
receipeType 1 - Means Veg
receipeType 2 - Non Veg

Request Body:

{
  "name": "tomato curry",
  "noOfServers": 2,
  "instructions": "gas",
  "receipeType": 1,
  "ingredients": [
     {
        "id": 1,
        "ingredients": "tomato"
    }
  ]
}

Response :

{
    "id": 2,
    "name": "tomato curry",
    "noOfServers": 2,
    "instructions": "gas",
    "receipeType": 1,
    "ingredients": [
        {
            "id": 1,
            "ingredients": "tomato"
        }
    ]
}


2nd : Example
Request:
{
  "name": "fish curry",
  "noOfServers": 2,
  "instructions": "oven",
  "receipeType": 2,
  "ingredients": [
     {
        "id": 3,
        "ingredients": "fish"
    }
  ]
}

Response :
{
    "id": 3,
    "name": "fish curry",
    "noOfServers": 2,
    "instructions": "oven",
    "receipeType": 2,
    "ingredients": [
        {
            "id": 3,
            "ingredients": "fish"
        }
    ]
}

```

```
Search Recipe 

url: http://localhost:8080/api/receipe/search

#Case 1: All vegetarian recipes
Request:
{
  "receipeType": 1
}

Response :

{
    "content": [
        {
            "id": 1,
            "name": "potato tomato curry",
            "noOfServers": 4,
            "instructions": "oven",
            "receipeType": 1,
            "ingredients": [
                {
                    "id": 1,
                    "ingredients": "tomato"
                },
                {
                    "id": 2,
                    "ingredients": "onion"
                }
            ]
        },
        {
            "id": 2,
            "name": "tomato curry",
            "noOfServers": 2,
            "instructions": "gas",
            "receipeType": 1,
            "ingredients": [
                {
                    "id": 1,
                    "ingredients": "tomato"
                }
            ]
        }
    ],
    "pageable": "INSTANCE",
    "last": true,
    "totalPages": 1,
    "totalElements": 2,
    "size": 2,
    "number": 0,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "first": true,
    "numberOfElements": 2,
    "empty": false
}

#Case 2: For Non-Veg Only

Request:
{
  "receipeType": 2
}

Response :
{
    "content": [
        {
            "id": 3,
            "name": "fish curry",
            "noOfServers": 2,
            "instructions": "oven",
            "receipeType": 2,
            "ingredients": [
                {
                    "id": 3,
                    "ingredients": "fish"
                }
            ]
        }
    ],
    "pageable": "INSTANCE",
    "last": true,
    "totalPages": 1,
    "totalElements": 1,
    "size": 1,
    "number": 0,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "first": true,
    "numberOfElements": 1,
    "empty": false
}


#Case 3: Recipes that can serve 4 persons and have “potatoes” as an ingredient
Request:

{
  "noOfServers": 4,
  "ingredients": "potato"
}

Response :
{
    "content": [
        {
            "id": 4,
            "name": "potato curry",
            "noOfServers": 4,
            "instructions": "oven",
            "receipeType": 1,
            "ingredients": [
                {
                    "id": 6,
                    "ingredients": "potato"
                }
            ]
        }
    ],
    "pageable": "INSTANCE",
    "last": true,
    "totalPages": 1,
    "totalElements": 1,
    "size": 1,
    "number": 0,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "first": true,
    "numberOfElements": 1,
    "empty": false
}


```

```
#Case 4: Recipes without “fish” as an ingredient that has “oven” in the instructions.
Note : isIngredientsIncluded = 0 means exclude ingredient and isIngredientsIncluded = 1 means include ingredient

Request:

{
  "instructions": "oven",
  "isIngredientsIncluded":0,
  "ingredients":"fish"
}

Response :
{
    "content": [
        {
            "id": 1,
            "name": "tomato curry",
            "noOfServers": 2,
            "instructions": "oven",
            "receipeType": 2,
            "ingredients": [
                {
                    "id": 1,
                    "ingredients": "tomato"
                }
            ]
        }
    ],
    "pageable": "INSTANCE",
    "last": true,
    "totalPages": 1,
    "totalElements": 1,
    "size": 1,
    "number": 0,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "first": true,
    "numberOfElements": 1,
    "empty": false
}

```




