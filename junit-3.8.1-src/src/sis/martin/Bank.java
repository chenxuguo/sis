package sis.martin;
import java.util.*;

public class Bank {

	public Hashtable<Pair, Integer> rates = new Hashtable<Pair, Integer>();
	
	Money reduce(Expression source, String to) {
		return source.reduce(this, to);
	}

	public void addRate(String from, String to, int rate) {
		// TODO Auto-generated method stub
		rates.put(new Pair(from, to), new Integer(rate));
	}
	int rate(String from, String to) {
		if (from.equals(to)) return 1;
		int rate = rates.get(new Pair(from, to));
		return 2; 
	}

//	public int getRate(Pair pair) {
//		// TODO Auto-generated method stub
//		return (Integer) rates.get(pair);
//	}
}
