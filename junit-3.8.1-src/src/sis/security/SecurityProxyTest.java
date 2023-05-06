package sis.security;

import java.lang.reflect.*;
import junit.framework.*;

public class SecurityProxyTest extends TestCase {
	private static final String secureMethodName = "secure";
	private static final String insecureMethodName = "insecure";
	private Object object;
	private SecureProxy proxy;
	private boolean secureMethodCalled;
	private boolean insecureMethodCalled;
	
	protected void setUp() {
		object = new Object() {
			public void secure() {
				secureMethodCalled = true;
			}
			
			public void insecure() {
				insecureMethodCalled = true;
			}
		};
		proxy = new SecureProxy(object, secureMethodName);
	}
	public void testInSecureMethod() throws Throwable {
		Method insecureMethod =
				object.getClass().getDeclaredMethod(insecureMethodName, new Class[] {});
		try {
			proxy.invoke(proxy, insecureMethod, new Object[] {});
			fail("expected permissionException");
		}
		catch (PermissionException expected) {
			assertFalse(insecureMethodCalled);
		}
		
	
	}
	
	public void testSecureMethod() throws Throwable {
		Method secureMethod =
				object.getClass().getDeclaredMethod(
						secureMethodName, new Class[] {});
		proxy.invoke(proxy,  secureMethod, new Object[] {});
		assertTrue(secureMethodCalled);
	}
	
}
