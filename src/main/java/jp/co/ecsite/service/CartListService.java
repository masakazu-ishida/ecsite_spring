package jp.co.ecsite.service;

import java.util.List;

import jp.co.ecsite.dto.CartDTO;

public interface CartListService {

	List<CartDTO> execute(String userId);

}
