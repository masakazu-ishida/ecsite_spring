package jp.co.ecsite.util;

public class PurchasesException extends Exception {
	public PurchasesException(Throwable except) {
		super(except);

	}

	public PurchasesException(String errorMessage) {
		super(errorMessage);
	}

}
