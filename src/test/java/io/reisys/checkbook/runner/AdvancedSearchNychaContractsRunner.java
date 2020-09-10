package io.reisys.checkbook.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import io.reisys.checkbook.bdd.cucumber.JsonDataSerenityRunner;
import io.reisys.checkbook.bdd.cucumber.TestDataFile;

@CucumberOptions(plugin = { "pretty" , "html:target/ContractsAdvancedSearchRunner.html" },
tags = {"@AdvancedSearchNychaContracts", "not @wip"},
features = { "src/test/resources/features/NychaContracts/AdvancedSearchNychaContracts.feature"},
//glue={"io.reisys.checkbook"}
glue={"io.reisys.checkbook.nycha.advsearchcontracts"}
)
@TestDataFile(files = { "src/test/resources/data/contractsData.json"
						})
@RunWith(JsonDataSerenityRunner.class)
public class AdvancedSearchNychaContractsRunner {

}
