package io.reisys.checkbook.bdd.common;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.SoftAssertions;

import io.reisys.checkbook.bdd.cucumber.ExecutionContext;
import junit.framework.Assert;
import net.thucydides.core.steps.ScenarioSteps;

public class CheckbookBaseScenarioSteps extends ScenarioSteps {

	protected SoftAssertions softAssertion = new SoftAssertions();

	
    public void assertFieldContainsText(String fieldName, String fieldValue, String text) {
    	ExecutionContext.appendToAssertionData(String.format("Validating '%s' field contains  UI count '%s' and DB count  '%s'", fieldName,fieldValue, text));
    	softAssertion.assertThat(fieldValue.contains(text));
    }
    
    public void assertFieldHasText(String fieldName, String fieldValue, String text) {
    	ExecutionContext.appendToAssertionData(String.format("Validating '%s' field has  UI count '%s' and DB count  '%s' ", fieldName,fieldValue, text));
    
    		softAssertion.assertThat(fieldValue.equals(text)); 
    	  	
    	//If failed, this line gets printed and execution is not halted
    	System.out.println("Assertion failed");   	
    	
    	
    }
    
    public void assertListOfValues(String fieldName, List<String>fieldValues, List<String>valuesToValidate ) {
    	ExecutionContext.appendToAssertionData(String.format("Validating '%s' field has UI values '%s' and expcted values '%s'", fieldName,fieldValues, valuesToValidate));
    	softAssertion.assertThat(Arrays.equals(fieldValues.toArray(), valuesToValidate.toArray()));
    }
    
}
