package sis.testing;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TestRunner {
	public static final String DEFAULT_IGNORE_REASON =
			"temporarily commenting out";
	private Class testClass;
	private int failed = 0;
	private Set<Method> testMethods = null;
	private Map<Method, Ignore> ignoreMethods = null;
	
	public static void main(String[] args) throws Exception  {
		TestRunner runner = new TestRunner(args[0]);
		runner.run();
		System.out.println(
				"passed " + runner.passed() + " failed: " + runner.failed());
	}	
	
	public TestRunner(Class testClass) {
		this.testClass = testClass;
	}
	
	public TestRunner(String className) throws Exception {
		this(Class.forName(className));
	}
	
	public Set<Method> getTestMethods() {
		if (testMethods == null)
			loadTestMethods();
		return testMethods;
	}
	
	public void run() {
		for (Method method : getTestMethods())
			run(method);
	}
	
	private void run(Method method) {
		try {
			Object testObject = testClass.getDeclaredConstructor().newInstance();
			method.invoke(testObject, new Object[] {});
		}
		catch (InvocationTargetException e) {
			Throwable cause = e.getCause();
			if (cause instanceof AssertionError)
				System.out.println(e.getMessage());
			else
				e.printStackTrace();
			failed++;
		}
		catch(Throwable t) {
			t.printStackTrace();
			failed++;
		}
	}
	public int passed() {
		return testMethods.size() - failed;
	}
	public int failed() {
		return failed;
	}

	private void loadTestMethods() {
		testMethods = new HashSet<Method>();
		ignoreMethods = new HashMap<Method, Ignore>();
		for (Method method : testClass.getDeclaredMethods())
			if (method.isAnnotationPresent(TestMethod.class))
				if (method.isAnnotationPresent(Ignore.class)) {
					Ignore ignore = method.getAnnotation(Ignore.class);
					ignoreMethods.put(method, ignore);
				}
				else 
					testMethods.add(method);
	}
	public Map<Method, Ignore> getIgnoredMethods() {
		if (testMethods == null)
			loadTestMethods();		
		return ignoreMethods;
	}
	

}
