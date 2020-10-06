package io.reisys.checkbook.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import io.reisys.checkbook.bdd.cucumber.JsonDataSerenityRunner;
import io.reisys.checkbook.bdd.cucumber.TestDataFile;

@CucumberOptions(plugin = { "pretty" , "html:target/SpendingDatafeedsRunner.html" },
tags = {"@DataFeedsNychaSpending", "not @wip"},
features = { "src/test/resources/features/NychaSpending/DatafeedsNychaSpending.feature"},
//glue={"io.reisys.checkbook"}
glue={"io.reisys.checkbook.nycha.datafeeds.spending"}
)
@TestDataFile(files = { "src/test/resources/data/SpendingData.json"
						})
@RunWith(JsonDataSerenityRunner.class)
public class DataFeedsNychaSpendingRunner {

}
