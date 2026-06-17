package jp.co.ecsite.dao;

import org.apache.ibatis.annotations.Mapper;

import jp.co.ecsite.dto.PurchasesDetailsDTO;

@Mapper
public interface PurchasesDetailDAO {

	int insert(PurchasesDetailsDTO purchasesDetailDTO);

}
