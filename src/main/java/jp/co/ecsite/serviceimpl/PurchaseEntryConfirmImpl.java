package jp.co.ecsite.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import jp.co.ecsite.dao.CartDAO;
import jp.co.ecsite.dto.CartDTO;
import jp.co.ecsite.service.PurchaseEntryConfirm;

@Service
public class PurchaseEntryConfirmImpl implements PurchaseEntryConfirm {

	private CartDAO cartDAO;

	public PurchaseEntryConfirmImpl(CartDAO cartDAO) {
		this.cartDAO = cartDAO;
	}

	@Override
	public List<CartDTO> confirm(String userId) {
		return cartDAO.findByUserId(userId);
	}

}
