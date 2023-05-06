package sis.util;

import java.util.*;
import junit.framework.*;

public class ListUtilTest extends TestCase{
	public void testPad() {
		final int count= 5;
		List<Date> list = new ArrayList<Date>();
		final Date element = new Date();
		ListUtil.pad(list, element, count);
		assertEquals(count, list.size());
		for (int i = 0; i < count; i++) 
			assertEquals("unexpected element at " + i ,
					element, list.get(i));
		
	}
	
	public void testWildcardCapture() {
		List<String> names = new ArrayList<String>();
		names.add("alpha");
		names.add("beta");
		inPlaceReverse(names);
		assertEquals("beta", names.get(0));
		assertEquals("alpha", names.get(1));
	}
	
	static void inPlaceReverse(List<?> list) {
		int size = list.size();
		for (int i = 0; i < size / 2; i++) {
			swap(list, i, size - 1 - i);
		}
	}
	private static <T> void swap(List<T> list, int i, int opposite) {
		T temp = list.get(i);
		list.set(i, list.get(opposite));
		list.set(opposite, temp);
	}
}
