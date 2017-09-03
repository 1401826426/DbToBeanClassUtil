package com.test.beans;
import java.util.Date;

public class Orders {

	//主键
	private int id;

	//商户主键
	private int businessId;

	//会员主键
	private int memberId;

	//消费人数
	private int num;

	//评论状态 -- 0：未评论 2：已评论
	private int commentState;

	//价格(消费金额)
	private String price;

	//
	private Date createTime;

	public void setId(int id){
	    this.id = id;
	}

	public int getId(){
	    return id;
	}

	public void setBusinessId(int businessId){
	    this.businessId = businessId;
	}

	public int getBusinessId(){
	    return businessId;
	}

	public void setMemberId(int memberId){
	    this.memberId = memberId;
	}

	public int getMemberId(){
	    return memberId;
	}

	public void setNum(int num){
	    this.num = num;
	}

	public int getNum(){
	    return num;
	}

	public void setCommentState(int commentState){
	    this.commentState = commentState;
	}

	public int getCommentState(){
	    return commentState;
	}

	public void setPrice(String price){
	    this.price = price;
	}

	public String getPrice(){
	    return price;
	}

	public void setCreateTime(Date createTime){
	    this.createTime = createTime;
	}

	public Date getCreateTime(){
	    return createTime;
	}

}