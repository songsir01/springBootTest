/**
 * Project Name:SpringBootTest
 * File Name:ListManagerDaoI.java
 * Package Name:com.test.mapper
 * Date:2017年8月31日下午4:21:52
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.test.mapper;

import java.util.List;
import java.util.Map;

import com.test.pojo.Goods;

/**
 * ClassName:ListManagerDaoI <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年8月31日 下午4:21:52 <br/>
 * @author   SongYapeng
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public interface ListMapper {

	int selectListCount(Map<String, Object> params);

	List<Goods> catList(Map<String, Object> params);

	void delGoods(int gid);

	void addGoods(Goods goods);

	void updateGoods(Goods goods);

	Goods selectGoodsById(int gid);

	@SuppressWarnings("rawtypes")
	List getAllGoodsList();

}

