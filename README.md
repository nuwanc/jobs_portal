* Internet connection.
* how to run the project. this is based on spring boot. mvn spring-boot:run
* frontend code is in frontend folder. its based on ractjs and vite.
* use frontend-maven-plugin . so backend and frontend is built at the same time.
* use h2 db as of now for ease of development. a file based db is created on the root folder.
* mailhog is used for mail testing. run the included mailhog .exe file before starting the project.
* mailhog UI can be access http://localhost:8025/
* h2 db console. http://localhost:8080/h2-console/
* how to register a user.
    1. go to register user. enter detail.
    2. go to mailhog client. client the verify link to verify user.
    3. go to login and enter the details to login
 
    4. if you want to register a user as an agent. the mail address should end with @star.com. eg: agent@star.com
