package com.test.beans;
import java.util.Date;

public class Comment {

	//主键
	private int id;

	//用户名
	private int ordersId;

	//评论内容
	private String comment;

	//星级评分
	private int star;

	//
	private Date createTime;

	public void setId(int id){
	    this.id = id;
	}

	public int getId(){
	    return id;
	}

	public void setOrdersId(int ordersId){
	    this.ordersId = ordersId;
	}

	public int getOrdersId(){
	    return ordersId;
	}

	public void setComment(String comment){
	    this.comment = comment;
	}

	public String getComment(){
	    return comment;
	}

	public void setStar(int star){
	    this.star = star;
	}

	public int getStar(){
	    return star;
	}

	public void setCreateTime(Date createTime){
	    this.createTime = createTime;
	}

	public Date getCreateTime(){
	    return createTime;
	}

}