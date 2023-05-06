package sis.martin;

public class Pair {
	private String from;
	private String to;
	
	Pair(String from, String to){
		this.from = from;
		this.to = to;
	}
	public boolean equals(Object object) {
		if (object == null)
			return false;
		Pair pair = (Pair) object;
		return from.equals(pair.from) && to.equals(pair.to);
	}
	
	public int hashCode() {
		final int hashMultiplier = 41;
		int result = 7;
	System.out.println("from.hascode = " + from.hashCode());
		result = result * hashMultiplier + from.hashCode();
	System.out.println("to.hascode = " + to.hashCode());
		result = result * hashMultiplier +  to.hashCode();
		return result;
	}
	
	public String toString() {
		return from + " " + to;
	}
	public String getFirstElement() {
		// TODO Auto-generated method stub
		return from;
	}
	public String getSecondElement() {
		// TODO Auto-generated method stub
		return to;
	}
}
