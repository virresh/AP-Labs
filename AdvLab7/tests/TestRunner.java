package tests;

import	org.junit.runner.JUnitCore;	
import	org.junit.runner.Result;	
import	org.junit.runner.notification.Failure;	
import	org.junit.runner.RunWith;	
import	org.junit.runners.Suite;	

@RunWith(Suite.class)	

@Suite.SuiteClasses({	
	TestCaseAddShow.class,	
	TestCaseDelete.class,
	TestCaseShow.class
})
/* Junit test runner class */
public class TestRunner	{	
	public static void main(String[] args)	{	
		Result result=	JUnitCore.runClasses(TestRunner.class);	
		for	(Failure failure : result.getFailures())	{	
			System.out.println(failure.toString());	
		}
		System.out.println(result.wasSuccessful());	
	}	
}