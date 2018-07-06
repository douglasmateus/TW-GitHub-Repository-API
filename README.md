# TW-GitHub-Repository-API

TW-GitHub-Repository-API is a Restful service that uses JAX-RS to retrieve information from the ThoughtWorks repository on GitHub to create a structure that tells you which languages are most used in the enterprise, its repositories, and key contributors.  

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

### GitHub:

GitHub Inc. is a web-based hosting service for version control using Git. It is mostly used for computer code. It offers all of the distributed version control and source code management (SCM) functionality of Git as well as adding its own features. It provides access control and several collaboration features such as bug tracking, feature requests, task management, and wikis for every project.
https://en.wikipedia.org/wiki/GitHub

### Personal Access Token:

During the development of this service many requests were made in order to test the service. At one moment, it was necessary to insert in the service the use of Personal Access Token in the service to proceed with the tests because it was not performing authenticated requests to the GitHub API.

For unauthenticated requests, the rate limit allows for up to 60 requests per hour. Unauthenticated requests are associated with the originating IP address, and not the user making requests.

To generate de token it will be necessary go to site: https://github.com/settings/tokens

### How to test the service:

This service were tested with the use of Postman.

### Postman:

Postman is an app for interacting with HTTP APIs. It is available on [https://www.getpostman.com/].

The steps to test the service will be shown below:

| Method | Endpoint |
| ------ | ------ |
| GET | http://localhost:3000/thoughtworks/repositories |

JSON Response:

```sh
Status: 200 OK
```

[
{
    "thoughtworks": {
        "languages": [
            {
                "language": "Ruby",
                "contributors": 163,
                "stars": 688,
                "forks": 255,
                "repositories": "cruisecontrol.rb, cruisecontrol.rb-contrib, letshelp.it, HackNightLA, mongoid, metric_fu, mongomapper, global_collect, sinatra-mongoid-config, validates_url, common-ci-tasks, twdreams, israelovesiran"
            },
            {
                "language": "JavaScript",
                "contributors": 31,
                "stars": 15,
                "forks": 3,
                "repositories": "murmurs.air, shadow-poll, RapidFTR, kinect-spike, twonrails, pringle"
            },
            {
                "language": "Java",
                "contributors": 9,
                "stars": 8,
                "forks": 2,
                "repositories": "krypton, eclipse_editor_widget, videoworld"
            },
            {
                "language": "C++",
                "contributors": 3,
                "stars": 3,
                "forks": 0,
                "repositories": "depthjs"
            },
            {
                "language": "Objective-C",
                "contributors": 2,
                "stars": 2,
                "forks": 0,
                "repositories": "ijeopardy"
            },
            {
                "language": "Python",
                "contributors": 12,
                "stars": 5,
                "forks": 1,
                "repositories": "rapidsms"
            }
        ],
        "topRepositories": [
            {
                "language": "Ruby",
                "position": 1,
                "contributors": 163,
                "stars": 688,
                "forks": 255,
                "repository": "cruisecontrol.rb, cruisecontrol.rb-contrib, letshelp.it, HackNightLA, mongoid, metric_fu, mongomapper, global_collect, sinatra-mongoid-config, validates_url, common-ci-tasks, twdreams, israelovesiran",
                "topContributors": [
                    {
                        "login": "durran",
                        "contributions": 2314
                    },
                    {
                        "login": "jnunemaker",
                        "contributions": 802
                    },
                    {
                        "login": "alexeyv",
                        "contributions": 355
                    }
                ]
            },
            {
                "language": "JavaScript",
                "position": 2,
                "contributors": 31,
                "stars": 15,
                "forks": 3,
                "repository": "murmurs.air, shadow-poll, RapidFTR, kinect-spike, twonrails, pringle",
                "topContributors": [
                    {
                        "login": "pungoyal",
                        "contributions": 207
                    },
                    {
                        "login": "wpc",
                        "contributions": 157
                    },
                    {
                        "login": "bguthrie",
                        "contributions": 81
                    }
                ]
            },
            {
                "language": "Java",
                "position": 3,
                "contributors": 9,
                "stars": 8,
                "forks": 2,
                "repository": "krypton, eclipse_editor_widget, videoworld",
                "topContributors": [
                    {
                        "login": "hraberg",
                        "contributions": 134
                    },
                    {
                        "login": "cleishm",
                        "contributions": 50
                    },
                    {
                        "login": "ketan",
                        "contributions": 9
                    }
                ]
            }
        ]
    }
}
]

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