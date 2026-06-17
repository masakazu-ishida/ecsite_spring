package jp.co.ecsite.serviceimpl;

import org.springframework.stereotype.Service;

import jp.co.ecsite.dao.CartDAO;
import jp.co.ecsite.dto.CartDTO;
import jp.co.ecsite.service.CartConfirmService;

@Service
public class CartConfirmServiceImpl implements CartConfirmService {

	private final CartDAO cartDAO;

	public CartConfirmServiceImpl(CartDAO cartDAO) {
		this.cartDAO = cartDAO;
	}

	@Override
	public CartDTO execute(String userId, int itemId) {

		return cartDAO.findById(userId, itemId);

	}

}
