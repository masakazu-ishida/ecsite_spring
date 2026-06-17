package jp.co.ecsite.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jp.co.ecsite.dao.ItemsDAO;
import jp.co.ecsite.dto.ItemsDTO;
import jp.co.ecsite.form.SearchForm;

@Service
public class ItemsSearchServiceImpl implements ItemsSearchService {

	private ItemsDAO itemsDAO;

	public ItemsSearchServiceImpl(ItemsDAO itemsDAO) {
		this.itemsDAO = itemsDAO;

	}

	@Override
	public List<ItemsDTO> execute(SearchForm form) {

		return itemsDAO.findByCriteria(form.getKeyword(), form.getCategory());
	}

}
