package io.reisys.checkbook.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import io.reisys.checkbook.bdd.cucumber.JsonDataSerenityRunner;
import io.reisys.checkbook.bdd.cucumber.TestDataFile;

@CucumberOptions(plugin = { "pretty" , "html:target/NychaRevenueAdvancedSearchRunner.html" },
tags = {"@AdvancedSearchNychaRevenue", "not @wip"},
features = { "src/test/resources/features/NychaRevenue/AdvancedSearchNychaRevenue.feature"},
//glue={"io.reisys.checkbook"}
glue={"io.reisys.checkbook.nycha.advsearchrevenue"}
)
@TestDataFile(files = { "src/test/resources/data/NYCHARevenueData.json"
						})
@RunWith(JsonDataSerenityRunner.class)
public class AdvancedSearchNychaRevenueRunner {

}
