package io.reisys.checkbook.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import io.reisys.checkbook.bdd.cucumber.JsonDataSerenityRunner;
import io.reisys.checkbook.bdd.cucumber.TestDataFile;

@CucumberOptions(plugin = { "pretty" , "html:target/CitywideRevenueDatafeedsRunner.html" },
tags = {"@DataFeedsCitywidePayroll", "not @wip"},
features = { "src/test/resources/features/CitywidePayroll/DatafeedsCitywidePayroll.feature"},
//glue={"io.reisys.checkbook"}
glue={"io.reisys.checkbook.citywide.datafeeds.payroll"}
)
@TestDataFile(files = { "src/test/resources/data/payrollData.json"
						})
@RunWith(JsonDataSerenityRunner.class)
public class DataFeedsCitywidePayrollRunner {

}
