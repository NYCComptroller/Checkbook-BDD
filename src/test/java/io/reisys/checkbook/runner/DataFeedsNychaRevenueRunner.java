package io.reisys.checkbook.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import io.reisys.checkbook.bdd.cucumber.JsonDataSerenityRunner;
import io.reisys.checkbook.bdd.cucumber.TestDataFile;

@CucumberOptions(plugin = { "pretty" , "html:target/BudgetDatafeedsRunner.html" },
tags = {"@DataFeedsNychaRevenue", "not @wip"},
features = { "src/test/resources/features/NychaRevenue/DatafeedsNychaRevenue.feature"},
//glue={"io.reisys.checkbook"}
glue={"io.reisys.checkbook.nycha.datafeeds.revenue"}
)
@TestDataFile(files = { "src/test/resources/data/revenueData.json"
						})
@RunWith(JsonDataSerenityRunner.class)
public class DataFeedsNychaRevenueRunner {

}
