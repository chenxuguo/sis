package sis.martin;

public class Sum implements Expression {
	public Sum(Expression augend, Expression addend) {
		this.augend = augend;
		this.addend = addend;
	}
	public Money reduce(Bank bank, String to) {
		int amount = augend.reduce(bank, to).amount
				+ addend.reduce(bank, to).amount;
		return Money.dollar(amount);
	}	
	Expression augend;
	Expression addend;
	
	@Override
	public Expression plus(Expression addend) {
		// TODO Auto-generated method stub
		return new Sum(this, addend);
	}
	public Expression times(int multiplier) {
		// TODO Auto-generated method stub
		return new Sum(augend.times(multiplier) , addend.times(multiplier));
	}
}
