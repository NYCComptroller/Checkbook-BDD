package io.reisys.checkbook.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import io.reisys.checkbook.bdd.cucumber.JsonDataSerenityRunner;
import io.reisys.checkbook.bdd.cucumber.TestDataFile;

@CucumberOptions(plugin = { "pretty" , "html:target/BudgetNychaRunner.html" },
tags = {"@NychaBudget", "not @wip"},
features = { "src/test/resources/features/NychaBudget/NychaBudget.feature"},
glue={"io.reisys.checkbook.nycha.budget"}
)
@TestDataFile(files = { "src/test/resources/data/budgetData.json"
						})
@RunWith(JsonDataSerenityRunner.class)
public class BudgetNychaRunner {

}
