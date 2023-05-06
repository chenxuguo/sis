package sis.ui;

import java.awt.*;
import junit.framework.*;

public class ImageUtilTest extends TestCase {
	public void testLoadImage() {
		assertNull(ImageUtil.create("/images/bogusFilename.git"));
		assertNotNull(ImageUtil.create("/images/courses.gif"));
	}
}
