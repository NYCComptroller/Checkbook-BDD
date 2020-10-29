package io.reisys.checkbook.bdd.cucumber;

import com.google.gson.JsonObject;

/**
 * Execution Context class
 */
public class ExecutionContext {

    private static JsonObject jsonTestData;
    private static String dataProvided;
    private static String dataAsserted;
    public static String firstNameProvided;
    public static String updatedEmail;
    public static String email;

    private ExecutionContext () {}

    public static void init ( JsonObject jsonData ) { jsonTestData = jsonData; }

    public static void cleanUp() {
        jsonTestData = new JsonObject();
    }

    public static JsonObject getJsonData() {
        return jsonTestData;
    }

    public static void resetStepData() {
        dataProvided = "";
        dataAsserted = "";
    }

    public static String getDataProvidedForStep() {
        return dataProvided;
    }

    public static String getDataAssertedForStep() {
        return dataAsserted;
    }

    public static void appendToStepData(String type, String locator, String value) {
        dataProvided += "Type: " + type + "\nLocator : " + locator + "\nValue : " + value + "\n\n";
    }

    public static void appendToStepData(String failedMethodMessage, String info) {
        dataProvided += failedMethodMessage + "\n" + info + "\n\n";
    }

    public static void appendToAssertionData(String assertion) {
        dataAsserted += assertion + "\n\n";
    }
}
