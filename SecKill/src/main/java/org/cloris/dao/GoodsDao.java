package org.cloris.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.cloris.vo.GoodsVO;

@Mapper
public interface GoodsDao {

	@Select("select sg.sec_stock, sg.sec_price, sg.start_date, sg.end_date, g.* from sec_goods sg left join goods g on sg.goods_id = g.id")
	List<GoodsVO> findAll();

	@Select("select sg.sec_stock, sg.sec_price, sg.start_date, sg.end_date, g.* from sec_goods sg left join goods g on sg.goods_id = g.id where g.id = #{id}")
	GoodsVO findById(Long id);
}
