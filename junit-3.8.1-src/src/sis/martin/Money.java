package sis.martin;

class Money implements Expression {
	protected String currency;
	
	protected int amount;
	public Money() {};
	public Money(int amount, String currency) {
		this.amount = amount;
		this.currency = currency;
	}
	public boolean equals(Object object) {
		Money that = (Money)object;
		return amount == that.amount
				&& currency().equals(that.currency());
	}
	static Money dollar(int amount) {
		return new Money(amount, "USD");
	}
	static Money franc(int amount) {
		return new Money(amount, "CHF");
	}	
	public Expression times(int multiplier) {
		return new Money(amount * multiplier, currency);
	};
	String currency() {
		return currency;
	}
	public Expression plus(Expression addend) {
		// TODO Auto-generated method stub
		return new Sum(this, addend);
	}
	public static Money dollar(int amount2, String to) {
		// TODO Auto-generated method stub
		return null;
	};
	public Money reduce(Bank bank, String to) {
		int rate = bank.rate(currency, to);
		return new Money(amount / rate, to);
	}
	public String toString() {
		return amount + currency;
	}
}
