package jp.co.ecsite.serviceompl;

import java.util.List;

import org.springframework.stereotype.Service;

import jp.co.ecsite.dao.CartDAO;
import jp.co.ecsite.dto.CartDTO;
import jp.co.ecsite.service.CartListService;

@Service
public class CartListServiceImpl implements CartListService {

	private final CartDAO cartDAO;

	public CartListServiceImpl(CartDAO cartDAO) {
		this.cartDAO = cartDAO;
	}

	@Override
	public List<CartDTO> execute(String userId) {
		// TODO 自動生成されたメソッド・スタブ
		return cartDAO.findByUserId(userId);
	}

}
