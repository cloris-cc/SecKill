package org.cloris.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.cloris.domain.OrderInfo;
import org.cloris.domain.SecOrder;

@Mapper
public interface OrderDao {

	@Select("select * from sec_order where user_id = #{userId} and goods_id = #{goodsId}")
	SecOrder findByUserIdAndGoodsId(Long userId, Long goodsId);

	@Insert("insert into order_info(user_id, goods_id, goods_name, goods_count, goods_price, order_channel, status, create_date) values(#{userId}, #{goodsId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel}, #{status}, #{createDate})")

	@SelectKey(keyColumn = "id", keyProperty = "id", resultType = Long.class, before = false, statement = "select last_insert_id()")
	Long insertOrder(OrderInfo order);

	@Insert("insert into sec_order (user_id, goods_id, order_id) values(#{userId}, #{goodsId}, #{orderId})")
	Long insertSecOrder(SecOrder secOrder);

	@Select("select * from order_info where id = #{orderId}")
	OrderInfo findById(Long orderId);
}
