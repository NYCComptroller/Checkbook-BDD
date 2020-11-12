package io.reisys.checkbook.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import io.reisys.checkbook.bdd.cucumber.JsonDataSerenityRunner;
import io.reisys.checkbook.bdd.cucumber.TestDataFile;

@CucumberOptions(plugin = { "pretty" , "html:target/SpendingAdvancedSearchRunner.html" },
tags = {"@AdvancedSearchaCitywideSpending", "not @wip"},
features = { "src/test/resources/features/CitywideSpending/AdvancedSearchCitywideSpending.feature"},
//glue={"io.reisys.checkbook"}
glue={"io.reisys.checkbook.citywide.advsearchspending"}
)
@TestDataFile(files = { "src/test/resources/data/spendingData.json"
						})
@RunWith(JsonDataSerenityRunner.class)
public class AdvancedSearchCitywideSpendingRunner {

}
