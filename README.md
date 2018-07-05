# TW-GitHub-Repository-API

TW-GitHub-Repository-API is a Restful service using JAX-RS to retrieve all languagues into ThoughtWorks GitHub repository.  

To build this service these technologies were used: 

- Cobertura
- JAX-RS
- JUnit
- Log4J
- Maven
- Spring Boot


### How to run:

The application must be started with the following command: 

```sh
mvn spring-boot:run
```

### How to test the service:

This service were tested with the use of Postman.

### Postman:

Postman is an app for interacting with HTTP APIs. It is available on [https://www.getpostman.com/].

The steps to test the service will be shown below:

| Method | Endpoint |
| ------ | ------ |
| GET | http://localhost:3000/thoughtworks/repositories |

After execute this method, Postman will show nothing, it´s correct. There is no record in the database.

Now, lets include the first record on the database.

| Method | Endpoint |
| ------ | ------ |
| POST | http://localhost:8080/products |


JSON Request:
```sh
{
"name": "COCA-COLA",
"description": "COKE"
}
```

JSON Response:

```sh
Status: 201 Created
```

The status "201 Created" will be showed.

Lets check if the record really was save in the database.

| Method | Endpoint |
| ------ | ------ |
| GET | http://localhost:8080/products |

JSON Response:
```sh
[
  {
    "id": "16a4db98-e659-433c-bd2a-aec43b6539ee",
    "name": "COCA-COLA",
    "description": "COKE",
    "parent": null,
    "images": null,
    "childProducts": null
  }
]
```

Now lets include another record.

| Method | Endpoint |
| ------ | ------ |
| POST | http://localhost:8080/products |


JSON Request:
```sh
{
"name": "COCA-COLA",
"description": "CCOKE ZERO"
}
```

JSON Response:

```sh
Status: 201 Created
```

The double C in the word CCOKE was intentional, just because in the next step we will update it correctly.

Before, lets see how theses two records are in the database:

| Method | Endpoint |
| ------ | ------ |
| GET | http://localhost:8080/products |

JSON Response:
```sh
[
  {
    "id": "08d2138d-0932-4583-bf94-650e0cb39a5e",
    "name": "COCA-COLA",
    "description": "CCOKE ZERO",
    "parent": null,
    "images": null,
    "childProducts": null
  },
  {
    "id": "16a4db98-e659-433c-bd2a-aec43b6539ee",
    "name": "COCA-COLA",
    "description": "COKE",
    "parent": null,
    "images": null,
    "childProducts": null
  }
]
```

Now lets update the record with double C letter, "CCOKE ZERO":

| Method | Endpoint |
| ------ | ------ |
| PUT | http://localhost:8080/products/08d2138d-0932-4583-bf94-650e0cb39a5e |

JSON Request:

```sh
{
    "name": "COCA-COLA",
    "description": "COKE ZERO"
  }
```

JSON Response:

```sh
Status: 200 Ok
```

Lets show again all the records.

| Method | Endpoint |
| ------ | ------ |
| GET | http://localhost:8080/products |

JSON Response:
```sh
[
  {
    "id": "08d2138d-0932-4583-bf94-650e0cb39a5e",
    "name": "COCA-COLA",
    "description": "COKE ZERO",
    "parent": null,
    "images": null,
    "childProducts": null
  },
  {
    "id": "16a4db98-e659-433c-bd2a-aec43b6539ee",
    "name": "COCA-COLA",
    "description": "COKE",
    "parent": null,
    "images": null,
    "childProducts": null
  }
]
```

If we want to see only the record COKE ZERO, we need to do it:

| Method | Endpoint |
| ------ | ------ |
| GET | http://localhost:8080/products/08d2138d-0932-4583-bf94-650e0cb39a5e |

JSON Response:
```sh
{
  "id": "08d2138d-0932-4583-bf94-650e0cb39a5e",
  "name": "COCA-COLA",
  "description": "COKE ZERO",
  "parent": null,
  "images": null,
  "childProducts": null
}
```

Now lets include another record.

| Method | Endpoint |
| ------ | ------ |
| POST | http://localhost:8080/products |

JSON Request:
```sh
{
"name": "SPRITE",
"description": "LEMON-LIME"
}
```

JSON Response:

```sh
Status: 201 Created
```

And lets see how are the records on the database:

| Method | Endpoint |
| ------ | ------ |
| GET | http://localhost:8080/products |

JSON Response:
```sh
[
  {
    "id": "08d2138d-0932-4583-bf94-650e0cb39a5e",
    "name": "COCA-COLA",
    "description": "COKE ZERO",
    "parent": null,
    "images": null,
    "childProducts": null
  },
  {
    "id": "16a4db98-e659-433c-bd2a-aec43b6539ee",
    "name": "COCA-COLA",
    "description": "COKE",
    "parent": null,
    "images": null,
    "childProducts": null
  },
  {
    "id": "3ddd54ae-1569-4761-9f27-83f0f60d1960",
    "name": "SPRITE",
    "description": "LEMON-LIME",
    "parent": null,
    "images": null,
    "childProducts": null
  }
]
```

Now we need to test the exclude operation. lets remove "SPRITE" record on the database.

| Method | Endpoint |
| ------ | ------ |
| DELETE | http://localhost:8080/products/3ddd54ae-1569-4761-9f27-83f0f60d1960 |


JSON Response:

```sh
Status: 200 Ok
```

Invoke the GET method we will see all the records again. This time without "SPRITE" record.

| Method | Endpoint |
| ------ | ------ |
| GET | http://localhost:8080/products |

JSON Response:
```sh
[
  {
    "id": "08d2138d-0932-4583-bf94-650e0cb39a5e",
    "name": "COCA-COLA",
    "description": "COKE ZERO",
    "parent": null,
    "images": null,
    "childProducts": null
  },
  {
    "id": "16a4db98-e659-433c-bd2a-aec43b6539ee",
    "name": "COCA-COLA",
    "description": "COKE",
    "parent": null,
    "images": null,
    "childProducts": null
  }
]
```

Lets include an image to record COKE ZERO.

| Method | Endpoint |
| ------ | ------ |
| POST | http://localhost:8080/products/08d2138d-0932-4583-bf94-650e0cb39a5e/images |

JSON Request:
```sh
{
	"type": "jpg"
}
```

JSON Response:

```sh
Status: 201 Created
```

To see it we need to do this:

| Method | Endpoint |
| ------ | ------ |
| GET | http://localhost:8080/products/?include=images |

JSON Response:

```sh
[
  {
    "id": "08d2138d-0932-4583-bf94-650e0cb39a5e",
    "name": "COCA-COLA",
    "description": "COKE ZERO",
    "parent": null,
    "images": [
      {
        "id": "5b59f8d6-3cea-46b1-9c73-884b22f51544",
        "type": "jpg"
      }
    ],
    "childProducts": null
  },
  {
    "id": "16a4db98-e659-433c-bd2a-aec43b6539ee",
    "name": "COCA-COLA",
    "description": "COKE",
    "parent": null,
    "images": [],
    "childProducts": null
  }
]
```

Now lets create a new record with the name "DIET COKE" child to "COKE" record. In this new record, we need input on parent attribute the father id, in this case "16a4db98-e659-433c-bd2a-aec43b6539ee"

| Method | Endpoint |
| ------ | ------ |
| POST | http://localhost:8080/products |

JSON Request:

```sh
{
"name": "COCA-COLA",
"description": "DIET COKE",
"parent": {
"id": "16a4db98-e659-433c-bd2a-aec43b6539ee"
	}
}
```

To see it we need to invoke the method GET like this:

| Method | Endpoint |
| ------ | ------ |
| GET | http://localhost:8080/products?include=childProducts |

JSON Response:

```sh
[
  {
    "id": "07e5774f-97f6-435f-aaa8-f2aeb262a4c3",
    "name": "COCA-COLA",
    "description": "DIET COKE",
    "parent": {
      "id": "16a4db98-e659-433c-bd2a-aec43b6539ee",
      "name": "COCA-COLA",
      "description": "COKE",
      "parent": null,
      "images": null,
      "childProducts": null
    },
    "images": null,
    "childProducts": []
  },
  {
    "id": "08d2138d-0932-4583-bf94-650e0cb39a5e",
    "name": "COCA-COLA",
    "description": "COKE ZERO",
    "parent": null,
    "images": null,
    "childProducts": []
  },
  {
    "id": "16a4db98-e659-433c-bd2a-aec43b6539ee",
    "name": "COCA-COLA",
    "description": "COKE",
    "parent": null,
    "images": null,
    "childProducts": [
      {
        "id": "07e5774f-97f6-435f-aaa8-f2aeb262a4c3",
        "name": "COCA-COLA",
        "description": "DIET COKE",
        "parent": {
          "id": "16a4db98-e659-433c-bd2a-aec43b6539ee",
          "name": "COCA-COLA",
          "description": "COKE",
          "parent": null,
          "images": null,
          "childProducts": null
        },
        "images": null,
        "childProducts": null
      }
    ]
  }
]
```

Note that "COKE" record inside childProducts shows the new record "DIET COKE".

And to see the images and childProducts we need to invoke the method GET like this:

| Method | Endpoint |
| ------ | ------ |
| GET | http://localhost:8080/products?include=images,childProducts |

JSON Response:

```sh
[
  {
    "id": "07e5774f-97f6-435f-aaa8-f2aeb262a4c3",
    "name": "COCA-COLA",
    "description": "DIET COKE",
    "parent": {
      "id": "16a4db98-e659-433c-bd2a-aec43b6539ee",
      "name": "COCA-COLA",
      "description": "COKE",
      "parent": null,
      "images": null,
      "childProducts": null
    },
    "images": [],
    "childProducts": []
  },
  {
    "id": "08d2138d-0932-4583-bf94-650e0cb39a5e",
    "name": "COCA-COLA",
    "description": "COKE ZERO",
    "parent": null,
    "images": [
      {
        "id": "5b59f8d6-3cea-46b1-9c73-884b22f51544",
        "type": "jpg"
      }
    ],
    "childProducts": []
  },
  {
    "id": "16a4db98-e659-433c-bd2a-aec43b6539ee",
    "name": "COCA-COLA",
    "description": "COKE",
    "parent": null,
    "images": [],
    "childProducts": [
      {
        "id": "07e5774f-97f6-435f-aaa8-f2aeb262a4c3",
        "name": "COCA-COLA",
        "description": "DIET COKE",
        "parent": {
          "id": "16a4db98-e659-433c-bd2a-aec43b6539ee",
          "name": "COCA-COLA",
          "description": "COKE",
          "parent": null,
          "images": null,
          "childProducts": null
        },
        "images": null,
        "childProducts": null
      }
    ]
  }
]
```

### Important:

To see all product relationships we need to use the "include" parameter to specify their relations with images and childProducts like this: 

```sh
http://localhost:8080/products?include=images,childProducts
```

### How to run unit test

To execute unit test we need to execute the following commmand:

```sh
mvn test
```

After execute this commmand, in the root of the project, inside the folder "target" will be created some folders with the result of unit tests:

- target
	- classes
	- cobertura
	- generated-classes
	- generated-sources
	- generated-test-sources
	- maven-status
	- site
		- cobertura
	- surefire-reports
	- test-classes

Inside the folder "cobertura" there are a lot of files with the result of this execution.    

To create a package and test this project we need to execute this command:

```sh
mvn package
```