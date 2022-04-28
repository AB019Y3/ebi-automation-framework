package africa.absa.ebi.ExecutionReport;

import africa.absa.ebi.Utilities.Constants;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;
import java.util.Date;

public class ExtentReportListener implements ITestListener {
    static Date d = new Date();
    static String fileName = "";
    private static ExtentReports extent;
    public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();

    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest("TestCase : " + result.getMethod().getMethodName());
        testReport.set(test);
    }

    public void onTestSuccess(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " PASSED" + "</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
        testReport.get().log(Status.INFO, "<b>" + ExtentReportManager.description_ + "</b>");
        testReport.get().log(Status.INFO, ("<pre>" + ExtentReportManager.request_ + "</pre>"));
        testReport.get().log(Status.INFO, MarkupHelper.createCodeBlock(ExtentReportManager.response_, CodeLanguage.JSON));
        testReport.get().pass(m);
    }

    public void onTestFailure(ITestResult result) {
        String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
        testReport.get().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occurred:Click to see"
                + "</font>" + "</b >" + "</summary>" + exceptionMessage.replaceAll(",", "<br>") + "</details>" + " \n");

        testReport.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>");
        String failureLogg = "TEST CASE FAILED";
        Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
        testReport.get().log(Status.INFO, "<b>" + ExtentReportManager.description_ + "</b>");
        testReport.get().log(Status.INFO, ("<pre>" + ExtentReportManager.request_ + "</pre>"));
        testReport.get().log(Status.INFO, MarkupHelper.createCodeBlock(ExtentReportManager.response_, CodeLanguage.JSON));
        testReport.get().log(Status.FAIL, m);
    }

    public void onTestSkipped(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String logText = "<b>" + "Test Case:- " + methodName + " Skipped" + "</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
        testReport.get().log(Status.INFO, "<b>" + ExtentReportManager.description_ + "</b>");
        testReport.get().skip(m);
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}

    public void onStart(ITestContext context) {
        fileName = context.getSuite().getName() + "_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
        extent = ExtentReportManager.createInstance(Constants.EXTENTREPORTPATH + fileName);
    }

    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
        }
    }
}
