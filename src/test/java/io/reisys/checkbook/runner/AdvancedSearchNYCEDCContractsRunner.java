package io.reisys.checkbook.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import io.reisys.checkbook.bdd.cucumber.JsonDataSerenityRunner;
import io.reisys.checkbook.bdd.cucumber.TestDataFile;

@CucumberOptions(plugin = { "pretty" , "html:target/ContractsAdvancedSearchRunner.html" },
tags = {"@AdvancedSearchNYCEDCContracts", "not @wip"},
features = { "src/test/resources/features/NycedcContracts/AdvancedSearchNYCEDCContracts.feature"},
//glue={"io.reisys.checkbook"}
glue={"io.reisys.checkbook.nycedc.advsearchcontracts"}
)
@TestDataFile(files = { "src/test/resources/data/NYCEDCContractsData.json"
						})
@RunWith(JsonDataSerenityRunner.class)
public class AdvancedSearchNYCEDCContractsRunner {

}
