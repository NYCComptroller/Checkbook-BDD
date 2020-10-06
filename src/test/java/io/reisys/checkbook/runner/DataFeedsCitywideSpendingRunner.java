package io.reisys.checkbook.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import io.reisys.checkbook.bdd.cucumber.JsonDataSerenityRunner;
import io.reisys.checkbook.bdd.cucumber.TestDataFile;

@CucumberOptions(plugin = { "pretty" , "html:target/CitywideSpendingDatafeedsRunner.html" },
tags = {"@DataFeedsCitywideSpending", "not @wip"},
features = { "src/test/resources/features/CitywideSpending/DatafeedsCitywideSpending.feature"},
//glue={"io.reisys.checkbook"}
glue={"io.reisys.checkbook.citywide.datafeeds.spending"}
)
@TestDataFile(files = { "src/test/resources/data/spendingData.json"
						})
@RunWith(JsonDataSerenityRunner.class)
public class DataFeedsCitywideSpendingRunner {

}
