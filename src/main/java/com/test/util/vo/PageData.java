package com.test.util.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 适用于datatables插件的mysql分页查询
 * 
 * @author wangdaoming
 * @param <T>
 */
public class PageData<T> {
	
    // 当前页显示条数
    private int limit=10;
    
    //最大显示条数
    private int maxrows;
    
    //开始位置
    private int offset;
    
    //总条数
    private int total;
    
    //排序方式
    private String order;
    
    //排序字段
    private String orderField;
    
    // 查询出来的数据
    private List<T> rows=new ArrayList<T>();

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getMaxrows() {
		return maxrows;
	}

	public void setMaxrows(int maxrows) {
		this.maxrows = maxrows;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
