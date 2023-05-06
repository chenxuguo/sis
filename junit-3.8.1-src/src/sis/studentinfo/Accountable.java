package sis.studentinfo;

import java.math.BigDecimal;

public interface Accountable {
	public void credit(BigDecimal amount);
	public BigDecimal getBalance();
	public BigDecimal transactionAverage();
	public void setBankAba(String bankAba);
	public void setBankAccountNumber(String bankAccountNumber);
	public void setBankAccountType(
			Account.BankAccountType BankAccountType);
	public void transferFromBank(BigDecimal amount);
}
