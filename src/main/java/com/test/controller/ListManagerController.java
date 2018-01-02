/**
 * Project Name:SpringBootTest
 * File Name:ListManagerController.java
 * Package Name:com.test.controller
 * Date:2017年8月31日下午4:19:07
 * Copyright (c) 2017, songsir01@163.com All Rights Reserved.
 *
 */

package com.test.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.test.pojo.Goods;
import com.test.util.Constants;
import com.test.util.ExportExcel;
import com.test.util.RequestUtil;
import com.test.util.vo.PageData;
import com.test.util.vo.Result;

/**
 * ClassName:ListManagerController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年8月31日 下午4:19:07 <br/>
 * 
 * @author SongYapeng
 * @version
 * @since JDK 1.7
 * @see
 */
@Controller
public class ListManagerController extends BaseController{
	
	public Logger logger = LogManager.getLogger(ListManagerController.class);

	@RequestMapping("/listManager")
	public String listManager() {
		logger.info("list页面操作跳转");
		return "sys/listManager";
	}

	/**
	 * 
	 * catList:(查看商品列表). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author SongYapeng
	 * @param pageData
	 * @param request
	 * @param response
	 * @return
	 * @since JDK 1.7
	 */
	@RequestMapping("/catList")
	public ModelAndView catList(PageData<Goods> pageData, HttpServletRequest request, HttpServletResponse response) {
		String search = RequestUtil.getString(request, "search");
		Map<String, Object> params = new HashMap<String, Object>();
		if (search != null) {
			params.put("search", "%" + search + "%");
		}
		Result<PageData<Goods>> result = listService.catListPage(pageData, params);
		logger.info("查看list列表："+result);
		return new ModelAndView(Constants.JSON_VIEW, Constants.JSON_ROOT, result.getBusinessResult());
	}

	/**
	 * 
	 * delGoods:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author SongYapeng
	 * @param request
	 * @return
	 * @since JDK 1.7
	 */
	@RequestMapping(value = "/delGoods")
	public ModelAndView delGoods(HttpServletRequest request) {
		String gid = RequestUtil.getString(request, "gid");
		String[] gids = gid.split(",");
		Result<String> templets = new Result<String>();
		for (String s : gids) {
			templets = listService.deleteGoodsById(Integer.parseInt(s));
		}
		logger.info("删除商品："+templets);
		return new ModelAndView(Constants.JSON_VIEW, Constants.JSON_ROOT, templets);
	}

	@RequestMapping("/addGoods")
	public ModelAndView addGoods(HttpServletRequest request, Goods goods) {
		Result<String> result = listService.addGoods(goods);
		logger.info("增加商品："+result);
		return new ModelAndView(Constants.JSON_VIEW, Constants.JSON_ROOT, result);
	}

	@RequestMapping("/goodsByGid")
	public ModelAndView goodsByGid(HttpServletRequest request, Goods goods) {
		String gid = RequestUtil.getString(request, "gid");
		Map<String, Object> resultMap = listService.getGoodsById(Integer.parseInt(gid));
		logger.info("根据gid查找商品："+resultMap);
		return new ModelAndView(Constants.JSON_VIEW, Constants.JSON_ROOT, resultMap);
	}

	@RequestMapping("/ExportExcel")
	public void ExportExcel(HttpServletResponse response) {
		
		logger.info("报表导出");
		@SuppressWarnings("rawtypes")
		List goodsList = listService.getAllGoodsList();

		String title = "商品表";

		String[] rowsName = new String[] { "商品标号", "商品名", "价格", "季节", "型号", "描述", "品牌" };

		List<Object[]> dataList = new ArrayList<Object[]>();

		for (int i = 0; i < goodsList.size(); i++) {
			Goods g = (Goods) goodsList.get(i);
			Object[] objs = new Object[rowsName.length];
			objs[0] = g.getGid();
			objs[1] = g.getGname();
			objs[2] = g.getGprice();
			objs[3] = g.getJijie();
			objs[4] = g.getSize();
			objs[5] = g.getDescript();
			objs[6] = g.getBrand();
			dataList.add(objs);
		}
		ExportExcel ex = new ExportExcel(title, rowsName, dataList);

		try {
			OutputStream output = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename=ExportExcle.xls");
			response.setContentType("application/msexcel");
			ex.export(output);
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			response.getWriter().write("导出成功！");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
