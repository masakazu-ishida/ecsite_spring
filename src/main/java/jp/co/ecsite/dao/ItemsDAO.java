package jp.co.ecsite.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.ecsite.dto.ItemsDTO;

@Mapper
public interface ItemsDAO {

	ItemsDTO findById(int itemId);

	List<ItemsDTO> findByCriteria(String keyword, int catedgoryId);

	List<ItemsDTO> findByCriteria(String keyword, int catedgoryId, int offset, int count);

	int update(ItemsDTO itemsDTO);

}
