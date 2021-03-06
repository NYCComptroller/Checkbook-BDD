# Checkbook-BDD
Checkbook BDD Testing with Serenity, Cucumber and Selenium

## Prerequisites:
- Java SDK 1.8
- Maven
- driver for [phantomJS](http://phantomjs.org/download.html) or [chrome](https://sites.google.com/a/chromium.org/chromedriver/)
- [Cucumber plugin for Eclipse](http://cucumber.github.com/cucumber-eclipse/update-site) or [Cucumber plugin for IntelliJ](https://plugins.jetbrains.com/plugin/download?updateId=39976)

### Writing Cucumber tests
Follow the below steps to implement cucumber test
1. Create feature file with scenario(s) under src/test/resources/features folder in Given, When, And, Then format. Refer to [Cucumber documentation](https://cucumber.io/docs/reference) on how to write scenarios
2. Create a step implementation class. The Steps in the scenario from the feature file are linked to the method using annotations @Given, @When, @Then and @And. Extend io.reisys.checkbook.bdd.common.CheckbookBaseScenarioSteps. Steps common between feature files can be added to CheckbookBaseScenarioSteps.
3. Implement a Page object class. Extend io.reisys.checkbook.bdd.common.CheckbookBasePageObject to get access to WebDriver and ExecutionContext that provides the data from the json files
4. Create json file for data under src/test/resources/data folder
5. Create Runner file and provide the json file as @TestDataFile to use in the runner
  				
### Running Tests locally
1. Update serenity.properties to set the browser to use for running tests
2. Update serenity.properties to set the site url for running tests
3. Run the appropriate runner class as Junit test.

### Running Tests maven configuration
1. Run goals clean verify serenity:aggregate
2. To run only specific runner update the runner name in pom.xml. Its currently set to run all the Runners.
3. Either update serenity.properties to set the parameters or provide them as parameters 
4. e.g) mvn clean verify serenity:aggregate -Dwebdriver.driver= -Dwebdriver.base.url= -Ddatabase.connection.url=
         -Ddatabase.user= -Ddatabase.password=
         

 