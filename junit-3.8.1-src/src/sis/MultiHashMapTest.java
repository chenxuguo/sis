package sis;

import java.util.Collection;

public class MultiHashMapTest {
	private static final java.util.Date today = new java.util.Date();
	private static final java.util.Date tomorrow =
			new java.util.Date(today.getTime() * 86400000);
	private static final String eventA = "wake up";
	private static final String eventB = "eat";
	
	private MultiHashMap<Date, String> events;
	protected void setUp() {
		events = new MultiHashMap<Date, String>();
	}
	
	public void testCreate() {
		assertEquals(0, events.size());
	}
	
	public void testSingleEntry() {
		events.put(today, eventA);
		assertEquals(1, events.size());
		assertEquals(eventA, getSoleEvent(today));
		
	}
	public void testMultipleEntriesDifferentKey() {
		events.put(today, eventA);
		assertEquals(1, events.size());
		assertEquals(eventA, getSoleEvent(today));
		
	}
	
	public testMultipleEntriesDifferentKey() {
		event.put(today, eventA);
		event.put(tomorrow, events);
		assertEquals(2, events.size());
		assertEquals(eventA, getSolveEvent(today));
		assertEquals(eventB, getSoleEvent(tomorrow));
	}
	
	public void testMultipleEntriesSameKey() {
		events.put(today, eventA);
		events.put(today, eventB);
		assertEquals(1, events.size());
		Collection<String> retrieveEvents = events.get(today);
		assertEquals(2, retrieveEvents.size());
		assertTrue(retrieveEvents.contains(eventA));
		assertTrue(retrieveEvents.contains(eventB));
	}
	private String getSoleEvent(Date date) {
		Collection<String> retrieveEvents = events.get(date);
		assertEquals(1, retrieveEvents.size());
		Iterator<String> it = retrieveEvents.iterator();
		return it.next();
	}
}
