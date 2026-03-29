package Listener;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.AppiumDriverFactory;
import utils.Screenshots;

public class TestListener implements ITestListener {

    private static ExtentReports extent;
    private static ExtentTest extentTest;

    @Override
    public void onTestStart(ITestResult result) {
        extentTest = extent.createTest(result.getName());
    }
    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.pass("Test Case " + result.getName() + " has passed");
        //takeScreenshot(result, "PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.fail("Test Case " + result.getName() + " has failed");
        //takeScreenshot(result, "FAILED");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.skip("Test Case " + result.getName() + " has been skipped");
        //takeScreenshot(result, "SKIPPED");
    }

    private void takeScreenshot(ITestResult result, String status) {
        Screenshots.captureScreenshot(
                AppiumDriverFactory.getDriver(),
                result.getName() + "_" + status
        );
    }

    @Override
    public void onFinish(ITestContext result) {
        extent.flush();
    }

    @Override
    public void onStart(ITestContext result) {
        extent = ExtentReportManager.extentSetup();
    }

}
