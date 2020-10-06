package io.reisys.checkbook.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import io.reisys.checkbook.bdd.cucumber.JsonDataSerenityRunner;
import io.reisys.checkbook.bdd.cucumber.TestDataFile;

@CucumberOptions(plugin = { "pretty" , "html:target/RevenueAdvancedSearchRunner.html" },
tags = {"@AdvancedSearchaCitywideRevenue", "not @wip"},
features = { "src/test/resources/features/CitywideRevenue/AdvancedSearchCitywideRevenue.feature"},
glue={"io.reisys.checkbook.citywide.advsearchrevenue"}

)
@TestDataFile(files = { "src/test/resources/data/revenueData.json"
						})
@RunWith(JsonDataSerenityRunner.class)
public class AdvancedSearchCitywideRevenueRunner {

}
