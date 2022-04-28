package africa.absa.ebi.ExecutionReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
  private static ExtentReports extent;

  public static ExtentReports createInstance(String fileName) {
    ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);
    htmlReporter.config().setDocumentTitle(fileName);
    htmlReporter.config().setEncoding("utf-8");
    htmlReporter.config().setReportName(fileName);
    htmlReporter.config().setTheme(Theme.DARK);

    extent = new ExtentReports();
    extent.attachReporter(htmlReporter);
    extent.setSystemInfo("Automation Tester", System.getProperty("user.name"));
    extent.setSystemInfo("Organization", "ABSA Everyday Banking");
    extent.setSystemInfo("Environment", "");
    return extent;
  }

  static String request_ = "";
  static String response_ = "";
  static String description_ = "";

  public static void writePayloads(String request, String response, String description) {
    request_ = request.replace("\n", "<br>");
    response_ = response;
    description_ = description;
  }
}
