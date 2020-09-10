package io.reisys.checkbook.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import io.reisys.checkbook.bdd.cucumber.JsonDataSerenityRunner;
import io.reisys.checkbook.bdd.cucumber.TestDataFile;

@CucumberOptions(plugin = { "pretty" , "html:target/SpendingNychaRunner.html" },
tags = {"@NychaSpending", "not @wip"},
//tags = {"@otherSpending" ,"not @wip"},
features = { "src/test/resources/features/NychaSpending/NychaSpending.feature"},
glue={"io.reisys.checkbook.nycha.spending"}

)
@TestDataFile(files = { "src/test/resources/data/SpendingNYCHAData.json"
						})
@RunWith(JsonDataSerenityRunner.class)
public class SepndingNychaRunner {

}
