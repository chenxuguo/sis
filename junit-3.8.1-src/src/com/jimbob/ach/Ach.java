package com.jimbob.ach;

public interface Ach {
	public AchResponse issueDebit(AchCredentials credentials, AchTransactionData data);
	public AchResponse markTransactionAsNSF(AchCredentials credentaials,
			AchTransactionData data, String traceCode);
	public AchResponse refundTransaction(AchCredentials credentails,
			AchTransactionData data, String traceCode);
	public AchResponse issueCredit(AchCredentials credentails, AchTransactionData data);
	public 	AchResponse voidSameDayTransaction(AchCredentials credentials,
			AchTransactionData data, String traceCode);
	public AchResponse queryTransactionStatus(AchCredentials credentials,
			AchTransactionData data, String traceCode);

}
