package org.cloris.service;

import java.util.List;

import org.cloris.vo.GoodsVO;

public interface GoodsService {
	
	List<GoodsVO> showAll();
	
	GoodsVO showDetail(Long id);
}
