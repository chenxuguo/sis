package sis.testing;

import java.util.*;
import java.lang.reflect.*;

public class TestRunnerTest {
	private TestRunner runner;
	private static final String methodNameA = "testA";
	private static final String methodNameB = "testB";
	public static final String IGNORE_REASON1 = "because";
	public static final String IGNORE_REASON2 = "why not";
	public static final String IGNORE_INITIALS = "jjl";
	
	@TestMethod
	public void singleMethodTest() {
		TestRunner runner = new TestRunner(SingleMethodTest.class);
		
		Set<Method> testMethods = runner.getTestMethods();
		assert 1 == testMethods.size() : "expected single test method";
		
		Iterator<Method> it = testMethods.iterator();
		Method method = it.next();
		
		final String testMethodName = "testA";
		assert testMethodName.equals(method.getName()) :
			"expected " + testMethodName + " as test method";
		assert 1 == runner.passed() : "expected 1 pass";
		assert 0 == runner.failed()  : "expected no failue";
	}
	
	@TestMethod
	public void multipleMethodTest() {
		TestRunner runner = new TestRunner(MultipleMethodTest.class);
		//runner.run();
		
		assert 2 == runner.passed() : "expected 2 pass";
		assert 0 == runner.failed() : "expected no failure";
		
		Set<Method> testMethods = runner.getTestMethods();
		assert 2 == testMethods.size();
		
		Set<String> methodNames = new HashSet<String>();
		for (Method method: testMethods) 
			methodNames.add(method.getName());
			
		final String testMethodNameA = "testA";
		final String testMethodNameB = "testB";
	
		assert methodNames.contains(testMethodNameA) :
			"expected " + testMethodNameA + " as test method";
		assert methodNames.contains(testMethodNameB) :
			"expected " + testMethodNameB + " as test method";
	}
	
	@TestMethod
	public void ignoreMethodTest() {
		runTests(IgnoreMethodTest.class);
		verifyTests(methodNameA, methodNameB);
		assertIgnoreReasons();
	}
	
	@TestMethod
	public void dateTest() {
		runTests(IgnoreDateTest.class);
		Map<Method, Ignore> ignoredMethods = runner.getIgnoredMethods();
	System.out.println(ignoredMethods);
		Map.Entry<Method, Ignore> entry = getSoleEntry(ignoredMethods);
		Ignore ignore = entry.getValue();
		Date date = ignore.date();
		assert 1 == date.month();
		assert 2 == date.day();
		assert 2005 == date.year();
	}
	private void runTests(Class testClass) {
		runner = new TestRunner(testClass);
		// runner.run();
	}
	
	@TestMethod
	public void ignoreWithDefaultReason() {
		runTests(DefaultIgnoreMethodTest.class);
		verifyTests(methodNameA, methodNameB);
		Map<Method, Ignore> IgnoreMethods = runner.getIgnoredMethods();
		Map.Entry<Method, Ignore> entry = getSoleEntry(IgnoreMethods);
		Ignore ignore = entry.getValue();
		assert TestRunner.DEFAULT_IGNORE_REASON.equals(ignore.reasons()[0]);
	}
	
	@TestMethod
	public void packageAnnotation() {
		Package pkg = this.getClass().getPackage();
		TestPackage testPackage = pkg.getAnnotation(TestPackage.class);
		assert testPackage.isPerformance();
	}
	private void assertIgnoreReasons() {
		Map<Method, Ignore> ignoreMethods = runner.getIgnoredMethods();
		Map.Entry<Method, Ignore> entry = getSoleEntry(ignoreMethods);
		assert "testC".equals(entry.getKey().getName()) :
			"unexpected ignore method: " + entry.getKey();
		Ignore ignore = entry.getValue();
		String[] ignoreReasons = ignore.reasons();
System.out.println(ignoreReasons[0] + " " + ignoreReasons[1]);
		assert IGNORE_REASON1.equals(ignoreReasons[0]);
		assert IGNORE_REASON2.equals(ignoreReasons[1]);
		assert IGNORE_INITIALS.equals(ignore.initials());
		Date date = ignore.date();
		assert 1 == date.month();
		assert 2 == date.day();
		assert 2005 == date.year();
	}
	private <K, V> Map.Entry<K, V> getSoleEntry(Map<K, V> map) {
		assert 1 == map.size() : "expected one entry";
		Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
		return it.next();
	}
	private void verifyTests(String... expectedTestMethodNames) {
		verifyNumberOfTests(expectedTestMethodNames);
		verifyMethodNames(expectedTestMethodNames);
		verifyCounts(expectedTestMethodNames);
	}
	
	private void verifyCounts(String... testMethodNames) {
		assert testMethodNames.length == runner.passed() :
			"expected " + testMethodNames.length + " passed";
		assert 0 == runner.failed() :  " expected no failure";
		
	}
	
	private void verifyNumberOfTests(String... testMethodNames) {
		assert testMethodNames.length == runner.getTestMethods().size() :
			"expected " + testMethodNames.length + " test method(s)";
	
		
	}
	
	private void verifyMethodNames(String... testMethodNames) {
		Set<String> actualMethodNames = getTestMethodNames();
		for (String methodName : testMethodNames)
			assert actualMethodNames.contains(methodName) :
				"expected " + methodName + " as test method";
		
	}
	
	private Set<String> getTestMethodNames() {
		Set<String> methodNames = new HashSet<String>();
		for (Method method : runner.getTestMethods())
			methodNames.add(method.getName());
		return methodNames;
	}
	
	class SingleMethodTest {
		public SingleMethodTest() {}
		@TestMethod public void testA() {}
	}
	
	class MultipleMethodTest {
		public MultipleMethodTest() {}
		@TestMethod public void testA() {}
		@TestMethod public void testB() {}
	}
	
	class IgnoreMethodTest {
		public IgnoreMethodTest() {}
		@TestMethod public void testA() {}
		@TestMethod public void testB() {}		
		@Ignore(
				reasons = {TestRunnerTest.IGNORE_REASON1,
					TestRunnerTest.IGNORE_REASON2}, 
				initials = TestRunnerTest.IGNORE_INITIALS,
				date = @Date(month=1, day=2, year=2005))
			@TestMethod public void testC() {}
	}
	
	class IgnoreDateTest {
		public IgnoreDateTest() {}
		@TestMethod public void testA() {}
		@TestMethod public void testB() {}		
		@Ignore(
				reasons = {TestRunnerTest.IGNORE_REASON1,
						TestRunnerTest.IGNORE_REASON2}, 
				initials = TestRunnerTest.IGNORE_INITIALS,
				date=@Date(month=1, day=2,year=2005))
			@TestMethod public void testC() {}
	}
	
	class DefaultIgnoreMethodTest {
		@TestMethod public void testA() {}
		@TestMethod public void testB() {}
		
		@Ignore(initials=TestRunnerTest.IGNORE_INITIALS,
				date=@Date(month=1, day=2, year=2005))
			@TestMethod public void testC() {}
	}
	
}
