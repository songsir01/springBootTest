/**
 * Project Name:SpringBootTest
 * File Name:ListManagerServiceImpl.java
 * Package Name:com.test.service.impl
 * Date:2017年8月31日下午4:21:11
 * Copyright (c) 2017, songsir01@163.com All Rights Reserved.
 *
*/

package com.test.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.mapper.ListMapper;
import com.test.pojo.Goods;
import com.test.service.ListManagerServiceI;
import com.test.util.vo.PageData;
import com.test.util.vo.Result;

/**
 * ClassName:ListManagerServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年8月31日 下午4:21:11 <br/>
 * @author   SongYapeng
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
@Service
public class ListManagerServiceImpl implements ListManagerServiceI {
	
	@Autowired
	private ListMapper listDao;

	@Override
	public Result<PageData<Goods>> catListPage(PageData<Goods> pageData, Map<String, Object> params) {
		int count = listDao.selectListCount(params);
		pageData.setTotal(count);
		params.put("start", pageData.getOffset());
		params.put("length", pageData.getLimit());
		List<Goods> list=listDao.catList(params);
		pageData.setRows(list);
		Result<PageData<Goods>> result= new Result<PageData<Goods>>();				
		result.setBusinessResult(pageData);
		result.setSuccess(true);
		return result;
	}

	@Override
	public Result<String> deleteGoodsById(int gid) {
		Result<String> result = new Result<String>();
		//删除模板基本信息
		listDao.delGoods(gid);
		result.setSuccess(true);
		result.setBusinessResult("删除成功！");
		return result;
	}

	@Override
	public Result<String> addGoods(Goods goods) {
		
		Result<String> result = new Result<String>();
		if(goods.getGid() == 0){
		listDao.addGoods(goods);
		result.setSuccess(true);
		result.setBusinessResult("保存成功！");
		}else{
			listDao.updateGoods(goods);
			result.setSuccess(true);
			result.setBusinessResult("修改成功！");
		}
		return result;
	}


	@Override
	public Map<String, Object> getGoodsById(int gid) {
		Map<String,Object> goodsResult =new HashMap<String,Object>();
		Goods goodsByGid = listDao.selectGoodsById(gid);
		goodsResult.put("goodsByGid", goodsByGid);
		return goodsResult;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getAllGoodsList() {
		// TODO Auto-generated method stub
		return listDao.getAllGoodsList();
	}
	
	

}

