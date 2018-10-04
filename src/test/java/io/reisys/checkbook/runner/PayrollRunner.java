package io.reisys.checkbook.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import io.reisys.checkbook.bdd.cucumber.JsonDataSerenityRunner;
import io.reisys.checkbook.bdd.cucumber.TestDataFile;

@CucumberOptions(plugin = { "pretty" , "html:target/PayrollRunner.html" },
tags = {"@payroll", "not @wip"},
features = { "src/test/resources/features"},
glue={"io.reisys.checkbook"}
)
@TestDataFile(files = { "src/test/resources/data/payrollData.json"
						})
@RunWith(JsonDataSerenityRunner.class)
public class PayrollRunner {

}