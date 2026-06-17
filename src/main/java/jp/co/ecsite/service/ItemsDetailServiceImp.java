package jp.co.ecsite.service;

import org.springframework.stereotype.Service;

import jp.co.ecsite.dao.ItemsDAO;
import jp.co.ecsite.dto.ItemsDTO;

@Service
public class ItemsDetailServiceImp implements ItemsDetailService {

	private final ItemsDAO dao;

	public ItemsDetailServiceImp(ItemsDAO dao) {
		this.dao = dao;
	}

	@Override
	public ItemsDTO execute(int itemId) {
		// TODO 自動生成されたメソッド・スタブ
		return dao.findById(itemId);
	}

}
