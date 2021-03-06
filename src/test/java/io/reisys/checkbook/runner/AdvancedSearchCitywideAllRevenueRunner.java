package io.reisys.checkbook.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import io.reisys.checkbook.bdd.cucumber.JsonDataSerenityRunner;
import io.reisys.checkbook.bdd.cucumber.TestDataFile;

@CucumberOptions(plugin = { "pretty" , "html:target/RevenueAdvancedSearchRunner.html" },
tags = {"@AdvancedSearchRevenue", "not @wip"},
features = { "src/test/resources/features/AdvancedSearch/AdvancedSearchRevenue.feature"},
glue={"io.reisys.checkbook.revenue"}

)
@TestDataFile(files = { "src/test/resources/data/revenueData.json"
						})
@RunWith(JsonDataSerenityRunner.class)
public class AdvancedSearchCitywideAllRevenueRunner {

}
