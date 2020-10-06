package io.reisys.checkbook.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import io.reisys.checkbook.bdd.cucumber.JsonDataSerenityRunner;
import io.reisys.checkbook.bdd.cucumber.TestDataFile;

@CucumberOptions(plugin = { "pretty" , "html:target/CitywideSmartSearchRunner.html" },
tags = {"@SmartSearchCitywide", "not @wip"},
features = { "src/test/resources/features/SmartSearch/SmartSearchCitywide.feature"},
//glue={"io.reisys.checkbook"}
glue={"io.reisys.checkbook.citywide.smartsearch"}
)
@TestDataFile(files = { "src/test/resources/data/HomeData.json"
						})
@RunWith(JsonDataSerenityRunner.class)
public class SmartSeachCitywideRunner {

}
