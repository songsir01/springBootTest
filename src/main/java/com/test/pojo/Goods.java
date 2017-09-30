/**
 * Project Name:SpringBootTest
 * File Name:Goods.java
 * Package Name:com.test.pojo
 * Date:2017年8月31日下午4:39:03
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.test.pojo;
/**
 * ClassName:Goods <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年8月31日 下午4:39:03 <br/>
 * @author   SongYapeng
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class Goods {
	
	private int gid;
	
	private String gname;
	
	private double gprice;
	
	private String jijie;
	
	private String size;
	
	private String descript;
	
	private String brand;

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public double getGprice() {
		return gprice;
	}

	public void setGprice(double gprice) {
		this.gprice = gprice;
	}

	public String getJijie() {
		return jijie;
	}

	public void setJijie(String jijie) {
		this.jijie = jijie;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Goods() {
		
		super();
		// TODO Auto-generated constructor stub
		
	}

	public Goods(int gid, String gname, double gprice, String jijie, String size, String descript, String brand) {
		super();
		this.gid = gid;
		this.gname = gname;
		this.gprice = gprice;
		this.jijie = jijie;
		this.size = size;
		this.descript = descript;
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "Goods [gid=" + gid + ", gname=" + gname + ", gprice=" + gprice + ", jijie=" + jijie + ", size=" + size
				+ ", descript=" + descript + ", brand=" + brand + "]";
	}
	

}

