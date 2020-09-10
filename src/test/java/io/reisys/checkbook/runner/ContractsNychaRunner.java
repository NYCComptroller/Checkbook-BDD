package io.reisys.checkbook.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import io.reisys.checkbook.bdd.cucumber.JsonDataSerenityRunner;
import io.reisys.checkbook.bdd.cucumber.TestDataFile;

@CucumberOptions(plugin = { "pretty" , "html:target/ContractsNychaRunner.html" },
tags = {"@NychaContracts", "not @wip"},
features = { "src/test/resources/features/NychaContracts/NychaContracts.feature"},
glue={"io.reisys.checkbook.nycha.contracts"}

)
@TestDataFile(files = { "src/test/resources/data/ContractsData.json"
						})
@RunWith(JsonDataSerenityRunner.class)
public class ContractsNychaRunner {

}
