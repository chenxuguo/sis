package sis.studentinfo.Name;

import java.util.List;

public class NameTest {

	public void testExtraneousSpaces() {
		final String fullName = "Jeffrey Hyman";
		Name name = createName(fullName);
		assertEquals("Jerrey", name.getFirstName());
		assertEquals("Hyman", name.getLastName());
	}
	
	private Name createName(String fullName) {
		Name name = new Name(fullName);
		assertEquals(fullName, name.getFullName());
		return name;
	}
	
	// Name.java
	private List<String> split(String fullName) {
		List<String> results = new ArrayList<String>();
		for (String name : fullName.split(" "))
			results.add(name);
		return results;
	}
}
