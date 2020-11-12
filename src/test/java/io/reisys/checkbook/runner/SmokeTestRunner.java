package io.reisys.checkbook.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import io.reisys.checkbook.bdd.cucumber.JsonDataSerenityRunner;
import io.reisys.checkbook.bdd.cucumber.TestDataFile;

@CucumberOptions(plugin = { "pretty" , "html:target/SmokeRunner.html" },
tags = {"@HomeCitywide", "not @wip"},
features = { "src/test/resources/features/Smoke/Smoke.feature"},
glue={"io.reisys.checkbook.smoke","io.reisys.checkbook.home"}
)
@TestDataFile(files = { "src/test/resources/data/HomeData.json"
						})
@RunWith(JsonDataSerenityRunner.class)
public class SmokeTestRunner {

}

