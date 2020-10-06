package io.reisys.checkbook.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import io.reisys.checkbook.bdd.cucumber.JsonDataSerenityRunner;
import io.reisys.checkbook.bdd.cucumber.TestDataFile;

@CucumberOptions(plugin = { "pretty" , "html:target/NycedcSmartSearchRunner.html" },
tags = {"@SmartSearchNycedc", "not @wip"},
features = { "src/test/resources/features/SmartSearch/SmartSearchNycedc.feature"},
//glue={"io.reisys.checkbook"}
glue={"io.reisys.checkbook.nycedc.smartsearch"}
)
@TestDataFile(files = { "src/test/resources/data/HomeData.json"
						})
@RunWith(JsonDataSerenityRunner.class)
public class SmartSeachNycedcRunner {

}
