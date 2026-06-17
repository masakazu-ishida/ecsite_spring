package jp.co.ecsite.serviceimpl;

import org.springframework.stereotype.Service;

import jp.co.ecsite.dao.CartDAO;
import jp.co.ecsite.dto.CartDTO;
import jp.co.ecsite.service.CartAddService;

@Service
public class CartAddServiceImpl implements CartAddService {

	private final CartDAO cartDAO;

	public CartAddServiceImpl(CartDAO cartDAO) {
		this.cartDAO = cartDAO;
	}

	@Override
	public boolean execute(CartDTO cartDTO) {

		//登録しようとするユーザID・商品IDでまず問い合わせ、既に追加済みか確認する
		CartDTO cartDTOfromDB = cartDAO.findById(cartDTO.getUserId(), cartDTO.getItemId());
		if (cartDTOfromDB == null) {

			return cartDAO.insert(cartDTO) == 1 ? true : false;
		} else {

			cartDTOfromDB.setAmount(cartDTOfromDB.getAmount() + cartDTO.getAmount());
			return cartDAO.update(cartDTO) == 1 ? true : false;

		}
	}

}
