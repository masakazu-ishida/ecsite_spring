package jp.co.ecsite.service;

import java.util.List;

import jp.co.ecsite.dto.CartDTO;

public interface PurchaseEntryConfirm {

	List<CartDTO> confirm(String userId);

}
