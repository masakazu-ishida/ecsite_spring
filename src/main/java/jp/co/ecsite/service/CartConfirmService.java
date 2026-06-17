package jp.co.ecsite.service;

import jp.co.ecsite.dto.CartDTO;

public interface CartConfirmService {
	CartDTO execute(String userId, int itemId);

}
