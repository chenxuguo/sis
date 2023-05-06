package sis.testing;

import java.lang.reflect.*;
import java.util.*;

public class TestRunnerUI {
	private TestRunner runner;
	
	public static void main(String[] args) throws Exception {
		TestRunnerUI ui = new TestRunnerUI("sis.testing.TestRunnerUI");
		ui.run();
		System.exit(ui.getNumberOfFailedTests());
	}
	
	public TestRunnerUI(String testClassName) throws Exception {
		runner = new TestRunner(testClassName);
	}
	
	public void run() {
		runner.run();
		showResults();
		showIgnoreMethods();
	}
	
	public int getNumberOfFailedTests() {
		return runner.failed();
	}
	
	private void showResults() {
		System.out.println(
				"passed: " + runner.passed() +
				" failed: " + runner.failed());
	}
	
	private void showIgnoreMethods() {
		if (runner.getIgnoredMethods().isEmpty())
			return;
		
		System.out.println("\nIgnored Methods");
		for (Map.Entry<Method, Ignore> entry :
				runner.getIgnoredMethods().entrySet()) {
			Ignore ignore = entry.getValue();
			System.out.println(entry.getKey() + " : " +
			ignore.value());
		}
	}
}
