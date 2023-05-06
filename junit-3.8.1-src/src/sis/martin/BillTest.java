package sis.martin;

import junit.framework.*;
import java.util.*;

public class BillTest extends TestCase {
	
	public void testMultiplication() 
	{	
		Money five = Money.dollar(5);
		assertEquals(Money.dollar(10), five.times(2));
		assertEquals(Money.dollar(15), five.times(3));
	}
	public void testEquality() {
		assertTrue(Money.dollar(5).equals(Money.dollar(5)));
		assertFalse(Money.dollar(5).equals(Money.dollar(6)));
		assertFalse(Money.franc(5).equals(Money.dollar(5)));
		assertTrue(Money.franc(5).equals(Money.franc(5)));
		assertFalse(Money.franc(5).equals(Money.franc(6)));
	}
	public void testFranceMultiplication() 
	{	
		Money five = Money.franc(5);
		assertEquals(Money.franc(10), five.times(2));
		assertEquals(Money.franc(15), five.times(3));
	}
	public void testCurrency() {
		assertEquals("USD", Money.dollar(1).currency());
		assertEquals("CHF", Money.franc(1).currency());
	}
	public void testDifferentClassEquality() {
		assertTrue(new Money(10, "CHF").equals(Money.franc(10)));
	}
	public void testSimpleAdition() {
		Expression sum = Money.dollar(5).plus(Money.dollar(5));
		
		assertEquals(Money.dollar(10), sum.reduce(new Bank(),"USD"));
	}
	public void testPlusReturnSum() {
		Money five = Money.dollar(5);
		Expression result = five.plus(five);
		Sum sum = (Sum) result;
		assertEquals(five, sum.augend);
		assertEquals(five, sum.addend);
	}
	public void testReduceSum() {
		Expression sum = new Sum(Money.dollar(3), Money.dollar(4));
		Bank bank = new Bank();
		Money result = bank.reduce(sum,  "USD");
		assertEquals(Money.dollar(7), result);
	}
	public void testReduceMoney() {
		Bank bank = new Bank();
		Money result = bank.reduce(Money.dollar(1), "USD");
		assertEquals(Money.dollar(1), result);
	}
	public void testReduceMoneyOfDifferentCurrency() {
		Bank bank = new Bank();
		bank.addRate("CHF", "USD", 2);
		Money result = bank.reduce(Money.franc(2), "USD");
		assertEquals(Money.dollar(1), result);
	}
	public void testArrayEquals() {
		assertEquals(new Object[] {"abc"}, new Object[] {"abc"});
	}
	public void testIdentityRate() {
		assertEquals(1, new Bank().rate("USD","USD"));
		Bank bank = new Bank();
		bank.addRate("CHF", "USD", 2);
		Pair pair1 = new Pair("CHF", "USD");
		assertEquals(pair1, new Pair("CHF", "USD"));
		assertFalse(pair1.equals(new Pair("1", "2")));
		assertEquals(2, bank.rate("CHF","USD"));;
	}
	public void testMixedAddition() {
		Expression fiveBucks = Money.dollar(5);
		Expression tenFrancs = Money.franc(10);
		Bank bank = new Bank();
		bank.addRate("CHF", "USD", 2);
		Money result = bank.reduce(fiveBucks.plus(tenFrancs), "USD");
		assertEquals(Money.dollar(10), result);
	}
	public void testHashTable() {
		Hashtable<Integer, Integer> dict = new Hashtable<>();
		dict.put(1,2 );
		int a = dict.get(1);
		assertEquals(2, a);
		dict.put(2,  4);
		assertEquals(4, (int)dict.get(2));
		
	}
	public void testStringHashTable() {
		Hashtable<String, String> dict = new Hashtable<>();
		dict.put("1","2" );
		String a = dict.get("1");
		assertEquals("2", a);
		dict.put("2",  "4");
		assertEquals("4", dict.get("2"));
		
	}
	public void testPairHashTable() {
		Map<Pair, Integer> dict = new HashMap<>();
		Pair pair = new Pair("a", "b");
		Pair pair2 = new Pair("a", "b");
		dict.put(pair, 1);
		assertTrue(dict.containsKey(pair2));
		assertEquals(1, (int)dict.get(new Pair("a", "b")));
		assertEquals(pair.hashCode(), pair2.hashCode());
		assertEquals(pair.hashCode(), pair.hashCode());
		assertEquals(pair, pair2);
	}
	public void testPair() {
		Pair pairAB = new Pair("a", "b");
		Pair pairABCopy = new Pair("a", "b");
		assertEquals("a", pairAB.getFirstElement());
		assertEquals("b", pairAB.getSecondElement());
		assertEquals(pairAB, pairABCopy);
		
		Pair pairAC = new Pair("a", "c");
		assertFalse(pairAB.equals(pairAC));
		
		// reflexivity
		assertEquals(pairAB, pairAB);
		
		// transitivty
		assertEquals(pairAB, pairABCopy);
		Pair pairABCopy2 = new Pair("a", "b");		
		assertEquals(pairABCopy, pairABCopy2);
		assertEquals(pairAB, pairABCopy2);
		
		// symmetry
		assertEquals(pairABCopy, pairAB);
		
		// consistency
		assertEquals(pairAB, pairABCopy);
		
		// comparison to null
		assertFalse(pairAB.equals(null));
		
	}
	public void testSumPlusMoney() {
		Expression fiveBucks = Money.dollar(5);
		Expression tenFrancs = Money.franc(10);
		Bank bank = new Bank();
		bank.addRate("CHF", "USD", 2);
		Expression sum = new Sum(fiveBucks, tenFrancs).plus(fiveBucks);
		Money result = bank.reduce(sum,  "USD");
		assertEquals(Money.dollar(15), result);
	}
	public void testSumTimes() {
		Expression fiveBucks = Money.dollar(5);
		Expression tenFrancs = Money.franc(10);
		Bank bank = new Bank();
		bank.addRate("CHF", "USD", 2);
		Expression sum = new Sum(fiveBucks, tenFrancs).times(2);
		Money result = bank.reduce(sum,  "USD");
		assertEquals(Money.dollar(20), result);
	}
	public void testPlusSameCurrencyReturnMoney() {
		Expression sum = Money.dollar(1).plus(Money.dollar(1));
		assertTrue(sum instanceof Sum);
	}
}

