package jp.co.ecsite.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.ecsite.dto.CartDTO;

@Mapper
public interface CartDAO {

	List<CartDTO> findByUserId(String userId);

	CartDTO findById(String userId, int itemId);

	int insert(CartDTO cartDTO);

	int update(CartDTO cartDTO);

	int delete(CartDTO cartDTO);

}
