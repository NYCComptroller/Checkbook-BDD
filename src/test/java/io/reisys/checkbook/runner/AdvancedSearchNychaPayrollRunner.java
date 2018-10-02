package io.reisys.checkbook.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import io.reisys.checkbook.bdd.cucumber.JsonDataSerenityRunner;
import io.reisys.checkbook.bdd.cucumber.TestDataFile;

@CucumberOptions(plugin = { "pretty" , "html:target/AdvancedSearchPayrollRunner.html" },
tags = {"@AdvancedSearchPayroll", "not @wip"},
features = { "src/test/resources/features"},
glue={"io.reisys.checkbook.advsearch"}
)
@TestDataFile(files = { "src/test/resources/data/payrollData.json"
						})
@RunWith(JsonDataSerenityRunner.class)
public class AdvancedSearchNychaPayrollRunner {

}
