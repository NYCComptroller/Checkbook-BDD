package io.reisys.checkbook.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import io.reisys.checkbook.bdd.cucumber.JsonDataSerenityRunner;
import io.reisys.checkbook.bdd.cucumber.TestDataFile;

@CucumberOptions(plugin = { "pretty" , "html:target/CitywideBudgetDatafeedsRunner.html" },
tags = {"@DataFeedsCitywideContracts", "not @wip"},
features = { "src/test/resources/features/CitywideContracts/DatafeedsCitywideContracts.feature"},
//glue={"io.reisys.checkbook"}
glue={"io.reisys.checkbook.citywide.datafeeds.contracts"}
)
@TestDataFile(files = { "src/test/resources/data/contractsData.json"
						})
@RunWith(JsonDataSerenityRunner.class)
public class DataFeedsCitywideContractsRunner {

}
