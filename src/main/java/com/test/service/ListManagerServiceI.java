/**
 * Project Name:SpringBootTest
 * File Name:ListManagerServiceI.java
 * Package Name:com.test.service
 * Date:2017年8月31日下午4:20:45
 * Copyright (c) 2017, songsir01@163.com All Rights Reserved.
 *
*/

package com.test.service;

import java.util.List;
import java.util.Map;

import com.test.pojo.Goods;
import com.test.util.vo.PageData;
import com.test.util.vo.Result;

/**
 * ClassName:ListManagerServiceI <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年8月31日 下午4:20:45 <br/>
 * @author   SongYapeng
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public interface ListManagerServiceI {

	Result<PageData<Goods>> catListPage(PageData<Goods> pageData, Map<String, Object> params);

	Result<String> deleteGoodsById(int parseInt);

	Result<String> addGoods(Goods goods);

	Map<String, Object> getGoodsById(int parseInt);

	@SuppressWarnings("rawtypes")
	List getAllGoodsList();

}

