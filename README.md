# Flight-Service-Rest-API
## Web service to manage flights from user and company point of view, exposing mature REST API    

Aim of this application is to enable company manage flights' reservations, make flight tickets transactions and let people browse, reserve and buy tickets. There is a mature REST API, using HATEOAS to let other applications and users communicate with it.   

**Note:** full API documentation is available, after launching this app, thanks to Swagger.   

*Technologies used:*      
-Spring Boot    
-Spring MVC    
-Spring REST   
-Spring Security   
-Spring Data JPA   

Whole application is secured, API exposed could be reached by anonymous user, nevertheless it is made easy to make API key be required, for instance using spring AOP. After having user registered for a flight, there is a link sent on his email with redirection to transaction, which has been stubbed in this case for obvious reasons.    



