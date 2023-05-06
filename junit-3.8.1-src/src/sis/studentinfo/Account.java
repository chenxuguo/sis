package sis.studentinfo;

import java.math.BigDecimal;
import com.jimbob.ach.*;

public class Account implements Accountable{
	private BigDecimal balance = new BigDecimal("0.00");
	private int transactionCount = 0;
	private String bankAba;
	private String bankAccountNumber;
	private BankAccountType bankAccountType;
	private Ach ach;
	
	public enum BankAccountType {
		CHECKING("OK"), 
		SAVINGS("SV");
		private String value;
		private BankAccountType(String value) {
			this.value = value;
		}
		@Override
		public String toString() {
			return value;
		}
	}
	

	public void credit(BigDecimal amount) {
		balance = balance.add(amount);
		transactionCount++;
	}
	
	public BigDecimal getBalance() {
		return balance;
	}

	@SuppressWarnings("deprecation")
	public BigDecimal transactionAverage() {
		// TODO Auto-generated method stub
		return balance.divide(
				new BigDecimal(transactionCount), BigDecimal.ROUND_HALF_UP);
	}
	public void setBankAba(String bankAba) {
		this.bankAba = bankAba;
	}
	public void setBankAccountType(
			Account.BankAccountType bankAccountType) {
		this.bankAccountType = bankAccountType;
	}
	
	public void transferFromBank(BigDecimal amount) {
		AchResponse achResponse = getAch().issueDebit(createCredentials(),  createData(amount));
		if (achResponse.status == AchStatus.SUCCESS)
			credit(amount);		
	}
	
	private AchCredentials createCredentials() {
		AchCredentials credentials = new AchCredentials();
		credentials.merchantId = "12355";
		credentials.username = "sismeric1920";
		credentials.password = "pitseleh411";
		return credentials;
	}
	
	private AchTransactionData createData(BigDecimal amount) {
		AchTransactionData data = new AchTransactionData();
		data.description = "tranfer from back";
		data.amount = amount;
		data.aba = bankAba;
		data.account = bankAccountNumber;
		data.accountType = bankAccountType.toString();
		return data;				
	}
	
	private Ach getAch() {
		return ach;
	}
	
	void setAch(Ach ach) {
		this.ach = ach;
	}

	public void setBankAccountNumber(String accountNumber) {
		this.bankAccountNumber = accountNumber;
		
		
	}

	public synchronized void withdraw(BigDecimal amount) {
		if (amount.compareTo(balance) > 0)
			return;
	try {Thread.sleep(1);}
	catch (InterruptedException e) {}
		balance = balance.subtract(amount);
	}
}
