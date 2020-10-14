package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;

import freemarker.core.Configurable.SettingValueAssignmentException;;


public class ExtentReportmanager {

	public static ExtentReports report;
	public static ExtentReports getReportInstance() {
		
		if(report==null) {
			String reportname=DateUtils.getTymStamp()+".html";
			ExtentHtmlReporter htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"\\output\\"+reportname);
			report=new ExtentReports();
			report.attachReporter(htmlReporter);
			
			report.setSystemInfo("os", "windows8.1");
			
			htmlReporter.config().setDocumentTitle("Automation Results");
			htmlReporter.config().setReportName("all headings");
			htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
			htmlReporter.config().setTimeStampFormat("MM dd, yyyy HH:mm:ss");
			
			System.out.println("report instance returned");
		
		}
		
		return report;
	}
}
