package org.cloris.service.impl;

import java.util.List;

import org.cloris.dao.GoodsDao;
import org.cloris.service.GoodsService;
import org.cloris.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	GoodsDao goodsDao;

	@Override
	public List<GoodsVO> showAll() {
		return goodsDao.findAll();
	}

	@Override
	public GoodsVO showDetail(Long id) {
		return goodsDao.findById(id);
	}

}
