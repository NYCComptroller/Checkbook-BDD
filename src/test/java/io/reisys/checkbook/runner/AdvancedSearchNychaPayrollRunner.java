package io.reisys.checkbook.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import io.reisys.checkbook.bdd.cucumber.JsonDataSerenityRunner;
import io.reisys.checkbook.bdd.cucumber.TestDataFile;

@CucumberOptions(plugin = { "pretty" , "html:target/AdvancedSearchRunner.html" },
tags = {"@AdvancedSearchaNychaPayroll", "not @wip"},
features = { "src/test/resources/features/NychaPayroll/AdvancedSearchNychaPayroll.feature"},
//glue={"io.reisys.checkbook"}
glue={"io.reisys.checkbook.nycha.advsearch"}
)
@TestDataFile(files = { "src/test/resources/data/payrollData.json"
						})
@RunWith(JsonDataSerenityRunner.class)
public class AdvancedSearchNychaPayrollRunner {

}
