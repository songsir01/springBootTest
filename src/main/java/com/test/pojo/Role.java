/**
 * Project Name:SpringBootTest
 * File Name:Role.java
 * Package Name:com.test.pojo
 * Date:2017年8月28日下午1:33:03
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.test.pojo;

import java.util.List;

/**
 * ClassName:Role <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年8月28日 下午1:33:03 <br/>
 * @author   SongYapeng
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class Role {
	
	private int rid;
	
	private String rname;
	
	private List <Permission> permissiones;

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public List<Permission> getPermissiones() {
		return permissiones;
	}

	public void setPermissiones(List<Permission> permissiones) {
		this.permissiones = permissiones;
	}

	public Role() {
		
		super();
		// TODO Auto-generated constructor stub
		
	}

	public Role(int rid, String rname, List<Permission> permissiones) {
		super();
		this.rid = rid;
		this.rname = rname;
		this.permissiones = permissiones;
	}

	@Override
	public String toString() {
		return "Role [rid=" + rid + ", rname=" + rname + ", permissiones=" + permissiones + "]";
	}
	
	

}

