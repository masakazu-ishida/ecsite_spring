package jp.co.ecsite.service;

import java.util.List;

import jp.co.ecsite.dto.ItemsDTO;
import jp.co.ecsite.form.SearchForm;

public interface ItemsSearchService {

	List<ItemsDTO> execute(SearchForm form);

}
