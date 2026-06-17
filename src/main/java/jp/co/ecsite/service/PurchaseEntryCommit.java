package jp.co.ecsite.service;

import jp.co.ecsite.dto.PurchasesDTO;
import jp.co.ecsite.util.PurchasesException;

public interface PurchaseEntryCommit {

	PurchasesDTO commit(String userId, String payment, String destination) throws PurchasesException;

}
