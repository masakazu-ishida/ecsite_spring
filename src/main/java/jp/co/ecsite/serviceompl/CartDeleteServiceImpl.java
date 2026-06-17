package jp.co.ecsite.serviceompl;

import org.springframework.stereotype.Service;

import jp.co.ecsite.dao.CartDAO;
import jp.co.ecsite.dto.CartDTO;
import jp.co.ecsite.service.CartDeleteService;

@Service
public class CartDeleteServiceImpl implements CartDeleteService {

	private final CartDAO cartDAO;

	public CartDeleteServiceImpl(CartDAO cartDAO) {
		this.cartDAO = cartDAO;
	}

	@Override
	public boolean execute(String userId, int itemId) {

		CartDTO cart = new CartDTO();
		cart.setUserId(userId);
		cart.setItemId(itemId);

		return cartDAO.delete(cart) == 1 ? true : false;
	}

}
