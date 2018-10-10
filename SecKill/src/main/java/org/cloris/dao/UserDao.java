package org.cloris.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.cloris.domain.User;

@Mapper
public interface UserDao {

	@Select("select * from user")
	List<User> findAll();

	@Select("select * from user where id = #{id}")
	User findById(String id);

	@Update("update user set password = #{password} where id = #{id}")
	void updatePassword(String id, String password);

	@Insert("insert into user(id, password) values(#{mobile}, #{password})")
	void insertOne(String mobile, String password);
}
