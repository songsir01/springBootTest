package com.test.util;

public class PageUtil {
	private String page;	
	private int pagesize=0;	
	private int count=0;		
	private int allpage=0;	
	private int pageindex=0;
	private int nextpage=0;	
	private int prevpage=0;	
	private int lastpage=0;	
	
	public PageUtil(String page, int pagesize, int count) {
		super();
		this.page = page;
		this.pagesize = pagesize;
		this.count = count;
		initAllpage();	
		initPageIndex();
		initPrevpage();	
		initNextpage();	
		initEndpage();	
	}
	
	private void initPageIndex() {
		if(page!=null&&!page.equals("")){
			pageindex = Integer.parseInt(page);
		}else{
			pageindex = 1;
			//pageindex = Integer.parseInt(page);
		}
	}

	private void initEndpage() {
		lastpage = allpage;
	}



	private void initNextpage() {
		
		if(pageindex>=allpage){
			nextpage = allpage;
		}else{
			nextpage = pageindex+1;
		}
	}

	private void initPrevpage() {
		
		if(pageindex>1){
			prevpage = pageindex-1;
		}else{
			prevpage = 1;
		}
		
	}

	private void initAllpage() {
		if(count%pagesize==0){
			allpage = count/pagesize;
		}else{
			allpage = count/pagesize+1;
		}
	}

	public void init(){
		
	}

	public int getPageindex() {
		return pageindex;
	}

	public void setPageindex(int pageindex) {
		this.pageindex = pageindex;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}


	public int getNextpage() {
		return nextpage;
	}

	public void setNextpage(int nextpage) {
		this.nextpage = nextpage;
	}

	public int getPrevpage() {
		return prevpage;
	}

	public void setPrevpage(int prevpage) {
		this.prevpage = prevpage;
	}
	
	public int getLastpage() {
		return lastpage;
	}
	
	public void setLastpage(int lastpage) {
		this.lastpage = lastpage;
	}
	
}
