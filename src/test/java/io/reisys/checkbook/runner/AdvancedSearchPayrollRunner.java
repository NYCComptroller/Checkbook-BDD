package io.reisys.checkbook.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import io.reisys.checkbook.bdd.cucumber.JsonDataSerenityRunner;
import io.reisys.checkbook.bdd.cucumber.TestDataFile;

@CucumberOptions(plugin = { "pretty" , "html:target/PayrollRunner.html" },
tags = {"@AdvancedSearchPayroll", "not @wip"},
//features = { "src/test/resources/features/AdvancedSearch/AdvancedSearchPayroll.feature"},
features = { "src/test/resources/features/AdvancedSearch/AdvancedSearchPayroll.feature"},
glue={"io.reisys.checkbook.payroll"}

)
@TestDataFile(files = { "src/test/resources/data/payrollData.json"
						})
@RunWith(JsonDataSerenityRunner.class)
public class AdvancedSearchPayrollRunner {

}
