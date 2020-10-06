package io.reisys.checkbook.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import io.reisys.checkbook.bdd.cucumber.JsonDataSerenityRunner;
import io.reisys.checkbook.bdd.cucumber.TestDataFile;

@CucumberOptions(plugin = { "pretty" , "html:target/RevenueCitywideRunner.html" },
tags = {"@CitywideRevenue", "not @wip"},
features = { "src/test/resources/features/CitywideRevenue/revenue.feature"},
glue={"io.reisys.checkbook.citywide.revenue"}
)
@TestDataFile(files = { "src/test/resources/data/CitywideRevenueData.json"
						})
@RunWith(JsonDataSerenityRunner.class)
public class RevenueCitywideRunner {

}
