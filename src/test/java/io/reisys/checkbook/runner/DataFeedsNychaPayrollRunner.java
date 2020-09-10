package io.reisys.checkbook.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import io.reisys.checkbook.bdd.cucumber.JsonDataSerenityRunner;
import io.reisys.checkbook.bdd.cucumber.TestDataFile;

@CucumberOptions(plugin = { "pretty" , "html:target/DatafeedsRunner.html" },
tags = {"@DataFeedsNychaPayroll", "not @wip"},
features = { "src/test/resources/features/NychaPayroll/DatafeedsNychaPayroll.feature"},
//glue={"io.reisys.checkbook"}
glue={"io.reisys.checkbook.nycha.datafeeds"}
)
@TestDataFile(files = { "src/test/resources/data/PayrollData.json"
						})
@RunWith(JsonDataSerenityRunner.class)
public class DataFeedsNychaPayrollRunner {

}
