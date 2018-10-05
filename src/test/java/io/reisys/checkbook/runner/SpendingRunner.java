package io.reisys.checkbook.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import io.reisys.checkbook.bdd.cucumber.JsonDataSerenityRunner;
import io.reisys.checkbook.bdd.cucumber.TestDataFile;

@CucumberOptions(plugin = { "pretty" , "html:target/SpendingRunner.html" },
tags = {"@spending", "not @wip"},
features = { "src/test/resources/features/spending/spending.feature"},
glue={"io.reisys.checkbook.spending","io.reisys.checkbook.home"}
)
@TestDataFile(files = { "src/test/resources/data/spendingData.json"
						})
@RunWith(JsonDataSerenityRunner.class)
public class SpendingRunner {

}