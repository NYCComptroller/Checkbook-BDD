package io.reisys.checkbook.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import io.reisys.checkbook.bdd.cucumber.JsonDataSerenityRunner;
import io.reisys.checkbook.bdd.cucumber.TestDataFile;

@CucumberOptions(plugin = { "pretty" , "html:target/RevenueNychaRunner.html" },
tags = {"@NychaRevenue", "not @wip"},
features = { "src/test/resources/features/NychaRevenue/NychaRevenue.feature"},
glue={"io.reisys.checkbook.nycha.revenue"}
)
@TestDataFile(files = { "src/test/resources/data/NYCHARevenueData.json"
						})
@RunWith(JsonDataSerenityRunner.class)
public class RevenueNychaRunner {

}
