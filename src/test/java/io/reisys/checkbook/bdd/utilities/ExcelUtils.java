package io.reisys.checkbook.bdd.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.reisys.checkbook.bdd.cucumber.BDDResultsSummary;
import io.reisys.checkbook.bdd.cucumber.BDDStepExecutionResult;
import io.reisys.checkbook.bdd.cucumber.BDDTestResult;

public class ExcelUtils {

  private static Logger logger = LoggerFactory.getLogger(ExcelUtils.class);
  private static final String RESULTS_TEMPLATE_FILE =
      "src/test/resources/customization/TestResults.xlsx";
  private static final String RESULTS_FILE = "target/site/serenity/BDDResults.xlsx";

  private ExcelUtils() {}

  public static void createResultsFile() {
    File file = new File(RESULTS_FILE);
    if (!file.exists()) {
      File srcFile = new File(RESULTS_TEMPLATE_FILE);
      try {
        FileUtils.copyFile(srcFile, file);
      } catch (IOException e) {
        logger.error(e.getMessage());
      }
    }
  }

  private static void createAsHyperlink(XSSFWorkbook workbook, XSSFCell cell, String cellValue) {
    cell.setCellValue(cellValue);
    CreationHelper createHelper = workbook.getCreationHelper();
    XSSFHyperlink link = (XSSFHyperlink) createHelper.createHyperlink(HyperlinkType.FILE);
    link.setAddress(cellValue);
    cell.setHyperlink(link);
  }

  public static void addScenarioResults(Set<BDDTestResult> scenarioResults) {
    try (FileInputStream excelFile = new FileInputStream(new File(RESULTS_FILE));
        XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
        FileOutputStream resultsFile = new FileOutputStream(new File(RESULTS_FILE))) {
      XSSFSheet sheet = workbook.getSheet("Scenarios");
      int currentRow = sheet.getPhysicalNumberOfRows();
      for (BDDTestResult result : scenarioResults) {
        XSSFRow row = sheet.createRow(currentRow++);
        row.createCell(0).setCellValue(result.getFeatureDescription());
        row.createCell(1).setCellValue(result.getFeatureFile());
        row.createCell(2).setCellValue(result.getScenarioName());
        row.createCell(3).setCellValue(result.getTestResult());
        row.createCell(4).setCellValue(result.getStepCount());
        row.createCell(5).setCellValue(result.getTimeTaken());

        XSSFCell cell = row.createCell(6);
        createAsHyperlink(workbook, cell, result.getHtmlReport());
        row.createCell(6).setCellValue(result.getFailedStep());
        row.createCell(6).setCellValue(result.getErrorMessage());
      }
      workbook.write(resultsFile);
    } catch (IOException e) {
      logger.error(e.getMessage());
    }
  }

  public static void addStepExecutionResults(Set<BDDStepExecutionResult> stepExecutionResults) {
    try (FileInputStream excelFile = new FileInputStream(new File(RESULTS_FILE));
        XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
        FileOutputStream resultsFile = new FileOutputStream(new File(RESULTS_FILE))) {
      XSSFSheet sheet = workbook.getSheet("Scenario Steps");
      int currentRow = sheet.getPhysicalNumberOfRows();
      for (BDDStepExecutionResult result : stepExecutionResults) {
        XSSFRow row = sheet.createRow(currentRow++);
        row.createCell(0).setCellValue(result.getFeatureDescription());
        row.createCell(1).setCellValue(result.getFeatureFile());
        row.createCell(2).setCellValue(result.getScenarioName());
        row.createCell(3).setCellValue(result.getStepName());
        row.createCell(4).setCellValue(result.getStepResult());
        row.createCell(5).setCellValue(result.getTimeTaken());

        XSSFCell cell = row.createCell(6);
        createAsHyperlink(workbook, cell, result.getScreenshotFileName());
      }
      workbook.write(resultsFile);
    } catch (IOException e) {
      logger.error(e.getMessage());
    }
  }

  public static void addSummaryResults(Map<String, BDDResultsSummary> resultsSummary) {
    try (FileInputStream excelFile = new FileInputStream(new File(RESULTS_FILE));
        XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
        FileOutputStream resultsFile = new FileOutputStream(new File(RESULTS_FILE))) {
      XSSFSheet sheet = workbook.getSheet("Features");
      int currentRow = sheet.getPhysicalNumberOfRows();
      for (BDDResultsSummary result : resultsSummary.values()) {
        XSSFRow row = sheet.createRow(currentRow++);
        row.createCell(0).setCellValue(result.getFeatureName());
        row.createCell(1).setCellValue(result.getTotalScenariosCount());
        row.createCell(2).setCellValue(result.getSuccessScenariosCount());
        row.createCell(3).setCellValue(result.getFailedScenariosCount());
        row.createCell(4).setCellValue(result.getErrorScenariosCount());
      }
      workbook.write(resultsFile);
    } catch (IOException e) {
      logger.error(e.getMessage());
    }
  }
}
