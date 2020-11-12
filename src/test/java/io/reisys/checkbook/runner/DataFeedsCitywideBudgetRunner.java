package io.reisys.checkbook.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import io.reisys.checkbook.bdd.cucumber.JsonDataSerenityRunner;
import io.reisys.checkbook.bdd.cucumber.TestDataFile;

@CucumberOptions(plugin = { "pretty" , "html:target/CitywideBudgetDatafeedsRunner.html" },
tags = {"@DataFeedsCitywideBudgetAllFields", "not @wip"},
features = { "src/test/resources/features/CitywideBudget/DatafeedsCitywideBudget.feature"},
//glue={"io.reisys.checkbook"}
glue={"io.reisys.checkbook.citywide.datafeeds.budget"}
)
@TestDataFile(files = { "src/test/resources/data/CitywideBudgetData.json"
						})
@RunWith(JsonDataSerenityRunner.class)
public class DataFeedsCitywideBudgetRunner {

}
