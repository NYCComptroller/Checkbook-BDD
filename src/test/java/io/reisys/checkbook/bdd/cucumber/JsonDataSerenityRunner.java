package io.reisys.checkbook.bdd.cucumber;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import cucumber.runtime.junit.FeatureRunner;
import io.reisys.checkbook.utilities.DatabaseUtil;
import io.reisys.checkbook.utilities.DatabaseUtil2;
import net.serenitybdd.cucumber.CucumberWithSerenity;

public class JsonDataSerenityRunner extends CucumberWithSerenity {


  private final Logger logger = Logger.getLogger(JsonDataSerenityRunner.class.getName());

  public JsonDataSerenityRunner(Class<?> clazz) throws InitializationError, IOException {
    super(clazz);
    TestDataFile files = clazz.getAnnotation(TestDataFile.class);

    initializeExecutionContextWithData(files);

  }


  public void initializeExecutionContextWithData(TestDataFile files) {
    List<String> allFiles = new ArrayList<String>();
    for (String dataFile : files.files()) {
      File file = new File(dataFile);
      if (file.isDirectory()) {
        Collection<File> filesInDir = FileUtils.listFiles(new File(dataFile),
            TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
        for (File f : filesInDir) {
          logger.info("Including Json Data from " + f.getName());
          allFiles.add(f.getAbsolutePath());
        }
      } else {
        allFiles.add(dataFile);
      }
    }

    JsonObject mergedData = new JsonObject();
    for (String dataFile : allFiles) {
      StringWriter writer = new StringWriter();

      try {
        IOUtils.copy(new FileInputStream(new File(dataFile)), writer, "UTF-8");
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }

      String jsonString = writer.toString();
      if (jsonString != null) {
        JsonParser parser = new JsonParser();
        JsonObject dataObj = (JsonObject) parser.parse(jsonString);
        for (Map.Entry<String, JsonElement> entry : dataObj.entrySet()) {
          mergedData.add(entry.getKey(), entry.getValue());
        }
      }
    }
    ExecutionContext.init(mergedData);
  }

  @Override
  protected void runChild(FeatureRunner child, RunNotifier notifier) {
    Result result = new Result();
    RunListener listener = result.createListener();
    notifier.addListener(listener);

    try {
      DatabaseUtil.connectToDatabase();
      DatabaseUtil2.connectToDatabase();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    super.runChild(child, notifier);
    logger.info(
        "result count " + result.getRunCount() + " and failures is " + result.getFailureCount());
    for (Failure failure : result.getFailures()) {
      logger.info("Failure is " + failure.getMessage() + " and description is "
          + failure.getDescription() + " and test header is " + failure.getTestHeader());
    }

    try {
      DatabaseUtil.closeDatabase();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }


}
