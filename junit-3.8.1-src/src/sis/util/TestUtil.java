package sis.util;

import junit.framework.*;
import sis.studentinfo.DateUtil;

import java.io.*;
import java.util.Date;

public class TestUtil {
	public static void assertGone(String... filenames) {
		for (String filename : filenames)
			Assert.assertFalse(new File(filename).exists());
	}
	public static void delete(String filename) {
		File file = new File(filename);
		if (file.exists())
			Assert.assertTrue(file.delete());
	}
	public static void assertDateEquals(int year, int month, int day, Date value) {
		Assert.assertEquals(DateUtil.createDate(year, month, day), value);
	}
}
