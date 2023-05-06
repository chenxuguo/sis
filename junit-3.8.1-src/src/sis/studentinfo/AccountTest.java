package sis.studentinfo;
import java.math.BigDecimal;
import java.util.Date;

import com.jimbob.ach.Ach;
import com.jimbob.ach.AchCredentials;
import com.jimbob.ach.AchResponse;
import com.jimbob.ach.AchStatus;
import com.jimbob.ach.AchTransactionData;

import junit.framework.*;
public class AccountTest extends TestCase {
	static final String ABA = "102000012";
	static final String ACCOUNT_NUMBER = "194431518811";
	
	private Account account;

	protected void setUp() {
		account = new Account();
		account.setBankAba(ABA);
		account.setBankAccountNumber(ACCOUNT_NUMBER);
		account.setBankAccountType(Account.BankAccountType.CHECKING);
	}
	public void testTransaction() {
		
		Account account = new Account();
		account.credit(new BigDecimal("0.10"));
		account.credit(new BigDecimal("11.00"));
		assertEquals(new BigDecimal("11.10"), account.getBalance());
	}
	public void testTransactionAverage() {
		Account account = new Account();
		account.credit(new BigDecimal("0.10"));
		account.credit(new BigDecimal("11.00"));
		account.credit(new BigDecimal("2.99"));
		assertEquals(new BigDecimal("4.70"), account.transactionAverage());
	}
	public void testTransferFromBank() {
		account.setAch(createMockAch(AchStatus.SUCCESS));	// uh-oh
		
		final BigDecimal amount = new BigDecimal("50.00");
		account.transferFromBank(amount);
		
		assertEquals(amount, account.getBalance());
	}
	public void testFailedTranferFromBank() {
		account.setAch(createMockAch(AchStatus.FAILURE));
		final BigDecimal amount = new BigDecimal("50.00");
		account.transferFromBank(amount);
		assertEquals(new BigDecimal("0.00"),account.getBalance());
			
	}
	private Ach createMockAch(final AchStatus status) {
		return new MockAch() {
			public AchResponse issueDebit(
					AchCredentials credentials, AchTransactionData data) {
				Assert.assertTrue(
						data.account.equals(AccountTest.ACCOUNT_NUMBER));
				Assert.assertTrue(data.aba.equals(AccountTest.ABA));
				
				AchResponse response = new AchResponse();
				response.timestamp = new Date();
				response.traceCode = "1";
				response.status = status;
				return response;
			}
		};
	}
	public void testWithdraw() throws Exception {
		account.credit(new BigDecimal("100.00"));
		account.withdraw(new BigDecimal("40.00"));
		assertEquals(new BigDecimal("60.00"), account.getBalance());
	}
	
	public void testWithdrawInsufficientFunds() {
		account.credit(new BigDecimal("100.00"));
		account.withdraw(new BigDecimal("140.00"));
		assertEquals(new BigDecimal("100.00"), account.getBalance());
	}
}
