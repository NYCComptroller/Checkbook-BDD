package io.reisys.checkbook.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import io.reisys.checkbook.bdd.cucumber.JsonDataSerenityRunner;
import io.reisys.checkbook.bdd.cucumber.TestDataFile;

@CucumberOptions(plugin = { "pretty" , "html:target/PayrollCitywideRunner.html" },
tags = {"@CitywidePayroll", "not @wip"},
features = { "src/test/resources/features/CitywidePayroll/payroll.feature"},
glue={"io.reisys.checkbook.citywide.payroll"}
)
@TestDataFile(files = { "src/test/resources/data/CitywidePayrollData.json"
						})
@RunWith(JsonDataSerenityRunner.class)
public class PayrollCitywideRunner {

}
