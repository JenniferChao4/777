
# Getting Started
### 1. Fork this repository
* Delegate a team member to be a repository host
	* Repository host will fork the respective repository
	* Repository host will [add each group member as a project-contributor](https://stackoverflow.com/questions/7920320/adding-a-collaborator-to-my-free-github-account)
* `feat/dev` should branch from `master`
* Each feature should branch from `feat/dev` with a naming convention of `feat/dev/featurename`.
* As implementation is added to an individual-feature branch and proven to be stable, merge changes from `feat/dev/featurename` to `feat/dev`
* As features are added to a development branch and proven to be integrable, merge changes from `feat/dev/` to `master`.

### 2. Clone this repository from your forked repository
* `git clone https://git.zipcode.rocks/ProjectRepositoryHostName/ProjectName`

### 3. Architect the Application
* Upon executing `jhipster` in the terminal, you are prompted tp architect the application:
	1. Which type of application would you like to create?
		* monolith applicaiton
	2. What is the base name of your application?
		* my-project-name
	3. What is your default java package name?
		* `rocks.zipcode.io`
	4. Do you want to use the JHipster registry to configure, monitor and scale your project?
		* yes
	5. Which type of database would you like to use?
		* SQL (H2, MySQL, MariaDB, PostgreSQL, Oracle, MSSQL
	6. Which production database would you like to use?
		* MySQL
	7. Which development database would you like to use?
		* H2 with in-memory persistence
	8. You do want use the Spring cache abstraction?
		* Yes, with Hazelcast implementation (distributed cache, for multiple nodes)
	9. Do you want to use Hibernate 2nd level cache?
		* Yes
	10. Would you like to use Maven or Gradle for building the backend?
		* Maven
	11. Which other technologies would you like to use?
		* Nothing recommended here
	12. Which framework would you like to use for the client?
		* Angular 6
	13. Would you like to enable SASS support using LibSass stylesheet preprocessor?
		* yes
	14. Would you like to enable internationalization support?
		* yes
	15. Please choose the native language of the application
		* English
	16. Please choose additional languages to install
		* Spanish
	17. Besides JUnit and Jest, which testing frameworks would you like to use?
		* Protractor
	18. Would you like to install other generators from the JHipster Marketplace?
		* Nothing recommended here

### 4. Running the Application
* From the root directory of the project execute `./mvnw` to run the application
* Ensure that you can sign in as an administrator using
	* username: `admin`
	* password: `admin`
* Ensure that you can sign in as a user using
	* username: `user`
	* password: `user`

### 5. Viewing the Application
* Navigate to [http://localhost:8080/](http://localhost:8080/)

### 6. Design your project Entities
* Using the [JdlStudio](https://start.jhipster.tech/jdl-studio/), create a UML representative of the application that you would like to build.
* After designing your entities, download the resulting `jhipster-jdl.jh` by selecting the download button at the top right of the interface.
* Place the `jhipster-jdl.jh` in the root directory of the application.
* Execute the `jhipster-jdl.jh` file by navigating to the directory and running the command
	* `yo jhipster:import-jdl jhipster-jdl.jh`
		* Jhipster prompts you to make refactoring decisions; Read carefully, choose wisely
		* Upon refactoring, Jhipster generates the respective the respective Angular and Spring code.

### 7. Viewing the newly refactored Application
* Navigate to [http://localhost:8080/](http://localhost:8080/) to ensure the application still runs as expected.

### 8. Separate the Client from the Server
* Fork the respective client-repository
* Clone your newly forked client-repository
* Copy the contents of the the `/src/main/webapp` directory of the monolith that was created in `Part 3`, to the root directory of the newly cloned client-repository

### 9. Run the server
* Execute `./mvnw` from the the root directory of the server project.

### 10. Run the client
* Execute `npm install`

### 11. Ensure application still runs after separating monolith
* Navigate to [http://localhost:8080/](http://localhost:8080/) to ensure the application is still behaving as expected.

## Frequently Asked Questions
* How do I kill a process listening on port 8080?
	* `npx kill-port 8080`
