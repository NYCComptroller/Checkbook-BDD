package io.reisys.checkbook.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import io.reisys.checkbook.bdd.cucumber.JsonDataSerenityRunner;
import io.reisys.checkbook.bdd.cucumber.TestDataFile;

@CucumberOptions(plugin = { "pretty" , "html:target/AdvancedSearchNychaBudgetRunner.html" },
tags = {"@AdvancedSearchaNychaBudget", "not @wip"},
features = { "src/test/resources/features/NychaBudget/AdvancedSearchNychaBudget.feature"},
//glue={"io.reisys.checkbook"}
glue={"io.reisys.checkbook.nycha.advsearchbudget"}
)
@TestDataFile(files = { "src/test/resources/data/budgetData.json"})
@RunWith(JsonDataSerenityRunner.class)
public class AdvancedSearchNychaBudgetRunner {

}
