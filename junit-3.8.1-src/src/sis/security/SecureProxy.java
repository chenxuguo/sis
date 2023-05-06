package sis.security;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class SecureProxy implements InvocationHandler{
	private List<String> secureMethods;
	Object target;
	
	public SecureProxy(Object object, String... secureMethods) {
		this.target = object;
		this.secureMethods = Arrays.asList(secureMethods);
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		try {
			if (!isSecure(method))
				throw new PermissionException();
			return method.invoke(target,  args);
			
		} catch (InvocationTargetException e) {
			throw e.getTargetException();
		}

	}
	public boolean isSecure(Method method) {
		return secureMethods.contains(method.getName());
	}
	
}
