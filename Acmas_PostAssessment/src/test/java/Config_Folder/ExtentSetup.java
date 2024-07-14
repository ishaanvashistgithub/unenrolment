 package Config_Folder;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentSetup {
 public ExtentTest eTest;

	public static ExtentReports setupExtentReport() {
		SimpleDateFormat newDate = new SimpleDateFormat("YYYY-MMM-dd HH-mm-ss");
		Date date = new Date();
		String rdate = newDate.format(date);

    	String reportPath = System.getProperty("user.dir") + "/ReportOutput/postAssessment_" + rdate + ".html";
		ExtentSparkReporter esReporter = new ExtentSparkReporter(reportPath);
		ExtentReports eReport = new ExtentReports();
		eReport.attachReporter(esReporter);
		esReporter.config().setDocumentTitle("Acams User Feedback Report Project");
		esReporter.config().setReportName("Execution Report");
		esReporter.config().setTheme(Theme.DARK);
		eReport.setSystemInfo("Host", "Local Machine");
		eReport.setSystemInfo("Testing By", "Vinod Kumar"); 
				
		return eReport;
	}
}
 