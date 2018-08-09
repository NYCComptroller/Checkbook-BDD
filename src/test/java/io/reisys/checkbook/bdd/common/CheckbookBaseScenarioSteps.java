package io.reisys.checkbook.bdd.common;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.SoftAssertions;

import io.reisys.checkbook.bdd.cucumber.ExecutionContext;
import net.thucydides.core.steps.ScenarioSteps;

public class CheckbookBaseScenarioSteps extends ScenarioSteps {

	protected SoftAssertions softAssertion = new SoftAssertions();
	
    public void assertFieldContainsText(String fieldName, String fieldValue, String text) {
    	ExecutionContext.appendToAssertionData(String.format("Validating '%s' field contains text '%s'", fieldName, text));
    	softAssertion.assertThat(fieldValue.contains(text));
    }
    
    public void assertFieldHasText(String fieldName, String fieldValue, String text) {
    	ExecutionContext.appendToAssertionData(String.format("Validating '%s' field has text '%s'", fieldName, text));
    	softAssertion.assertThat(fieldValue.equals(text));
    }
    
    public void assertListOfValues(String fieldName, List<String>fieldValues, List<String>valuesToValidate ) {
    	ExecutionContext.appendToAssertionData(String.format("Validating '%s' field has values '%s'", fieldName, valuesToValidate));
    	softAssertion.assertThat(Arrays.equals(fieldValues.toArray(), valuesToValidate.toArray()));
    }
    
}
