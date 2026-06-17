package jp.co.ecsite.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.ecsite.dto.PurchasesDTO;

@Mapper
public interface PurchasesDAO {

	PurchasesDTO findById(int purchaseId);

	List<PurchasesDTO> findByUserId(String purchaseUserId);

	int insert(PurchasesDTO dto);

	int update(PurchasesDTO dto);

}
