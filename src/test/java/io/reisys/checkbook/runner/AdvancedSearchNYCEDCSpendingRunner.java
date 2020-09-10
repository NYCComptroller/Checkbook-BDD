package io.reisys.checkbook.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import io.reisys.checkbook.bdd.cucumber.JsonDataSerenityRunner;
import io.reisys.checkbook.bdd.cucumber.TestDataFile;

@CucumberOptions(plugin = { "pretty" , "html:target/SpendingAdvancedSearchRunner.html" },
tags = {"@AdvancedSearchaNYCEDCSpending", "not @wip"},
features = { "src/test/resources/features/NycedcSpending/AdvancedSearchNYCEDCSpending.feature"},
//glue={"io.reisys.checkbook"}
glue={"io.reisys.checkbook.nycedc.advsearchspending"}
)
@TestDataFile(files = { "src/test/resources/data/NYCEDCSpendingData.json"
						})
@RunWith(JsonDataSerenityRunner.class)
public class AdvancedSearchNYCEDCSpendingRunner {

}
