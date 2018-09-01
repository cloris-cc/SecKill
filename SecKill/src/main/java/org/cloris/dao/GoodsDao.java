package org.cloris.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.cloris.domain.SecGoods;
import org.cloris.vo.GoodsVO;

@Mapper
public interface GoodsDao {

	@Select("select sg.sec_stock, sg.sec_price, sg.start_date, sg.end_date, g.* from sec_goods sg left join goods g on sg.goods_id = g.id")
	List<GoodsVO> findAll();

	@Select("select sg.sec_stock, sg.sec_price, sg.start_date, sg.end_date, g.* from sec_goods sg left join goods g on sg.goods_id = g.id where g.id = #{id}")
	GoodsVO findById(Long id);

	@Update("update sec_goods set sec_stock = sec_stock-1 where goods_id = #{goodsId} and sec_stock > 0")
	Integer updateStock(SecGoods goods);
}
