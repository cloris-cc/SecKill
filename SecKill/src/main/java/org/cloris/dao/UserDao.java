package org.cloris.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.cloris.domain.User;

@Mapper
public interface UserDao {

	@Select("select * from user")
	List<User> findAll();

	@Select("select * from user where id = #{id}")
	User findById(String id);
}
