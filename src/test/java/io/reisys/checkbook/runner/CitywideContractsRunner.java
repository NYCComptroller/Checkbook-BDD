package io.reisys.checkbook.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import io.reisys.checkbook.bdd.cucumber.JsonDataSerenityRunner;
import io.reisys.checkbook.bdd.cucumber.TestDataFile;

@CucumberOptions(plugin = { "pretty" , "html:target/ContractsRunner.html" },
tags = {"@ActiveExpenseContracts", "not @wip"},
features = { "src/test/resources/features/CitywideContracts/Contracts.feature"},
glue={"io.reisys.checkbook.contracts"}
)
@TestDataFile(files = { "src/test/resources/data/CitywideContractsData.json"
						})
@RunWith(JsonDataSerenityRunner.class)
public class CitywideContractsRunner {

}

