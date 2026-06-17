package jp.co.ecsite.dao;

import org.apache.ibatis.annotations.Mapper;

import jp.co.ecsite.dto.UsersDTO;

@Mapper
public interface UsersDAO {

	UsersDTO findById(String strUserId);

}
