package io.reisys.checkbook.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import io.reisys.checkbook.bdd.cucumber.JsonDataSerenityRunner;
import io.reisys.checkbook.bdd.cucumber.TestDataFile;

@CucumberOptions(plugin = { "pretty" , "html:target/SpendingAdvancedSearchRunner.html" },
tags = {"@AdvancedSearchaNychaSpending", "not @wip"},
features = { "src/test/resources/features/NychaSpending/AdvancedSearchNychaSpending.feature"},
//glue={"io.reisys.checkbook"}
glue={"io.reisys.checkbook.nycha.advsearchspending"}
)
@TestDataFile(files = { "src/test/resources/data/spendingData.json"
						})
@RunWith(JsonDataSerenityRunner.class)
public class AdvancedSearchNychaSpendingRunner {

}
