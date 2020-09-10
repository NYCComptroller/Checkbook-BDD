package io.reisys.checkbook.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import io.reisys.checkbook.bdd.cucumber.JsonDataSerenityRunner;
import io.reisys.checkbook.bdd.cucumber.TestDataFile;

@CucumberOptions(plugin = { "pretty" , "html:target/NychaSmartSearchRunner.html" },
tags = {"@SmartSearchNycha", "not @wip"},
features = { "src/test/resources/features/SmartSearch/SmartSearchNycha.feature"},
//glue={"io.reisys.checkbook"}
glue={"io.reisys.checkbook.nycha.smartsearch"}
)
@TestDataFile(files = { "src/test/resources/data/HomeData.json"
						})
@RunWith(JsonDataSerenityRunner.class)
public class SmartSeachNychaRunner {

}
