package io.reisys.checkbook.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import io.reisys.checkbook.bdd.cucumber.JsonDataSerenityRunner;
import io.reisys.checkbook.bdd.cucumber.TestDataFile;

@CucumberOptions(plugin = { "pretty" , "html:target/BudgetRunner.html" },
tags = {"@CitywideBudget", "not @wip"},
features = { "src/test/resources/features/CitywideBudget/budget.feature"},
glue={"io.reisys.checkbook.citywide.budget"}
)
@TestDataFile(files = { "src/test/resources/data/CitywideBudgetData.json"
						})
@RunWith(JsonDataSerenityRunner.class)
public class CitywideBudgetRunner {

}

