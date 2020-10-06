package io.reisys.checkbook.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import io.reisys.checkbook.bdd.cucumber.JsonDataSerenityRunner;
import io.reisys.checkbook.bdd.cucumber.TestDataFile;

@CucumberOptions(plugin = { "pretty" , "html:target/SpendingDatafeedsRunner.html" },
tags = {"@DataFeedsNYCEDCContracts", "not @wip"},
features = { "src/test/resources/features/NycedcContracts/DatafeedsNycedcContracts.feature"},
//glue={"io.reisys.checkbook"}
glue={"io.reisys.checkbook.nycedc.datafeeds.contracts"}
)
@TestDataFile(files = { "src/test/resources/data/NYCEDCContractsData.json"
						})
@RunWith(JsonDataSerenityRunner.class)
public class DataFeedsNyedcContractsRunner {

}
