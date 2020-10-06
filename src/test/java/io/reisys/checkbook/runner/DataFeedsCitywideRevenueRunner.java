package io.reisys.checkbook.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import io.reisys.checkbook.bdd.cucumber.JsonDataSerenityRunner;
import io.reisys.checkbook.bdd.cucumber.TestDataFile;

@CucumberOptions(plugin = { "pretty" , "html:target/CitywideRevenueDatafeedsRunner.html" },
tags = {"@DataFeedsCitywideRevenue", "not @wip"},
features = { "src/test/resources/features/CitywideRevenue/DatafeedsCitywideRevenue.feature"},
//glue={"io.reisys.checkbook"}
glue={"io.reisys.checkbook.citywide.datafeeds.revenue"}
)
@TestDataFile(files = { "src/test/resources/data/revenueData.json"
						})
@RunWith(JsonDataSerenityRunner.class)
public class DataFeedsCitywideRevenueRunner {

}
