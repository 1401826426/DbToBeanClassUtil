package com.test.beans;
import java.util.Date;

public class Orders {

	//����
	private int id;

	//�̻�����
	private int businessId;

	//��Ա����
	private int memberId;

	//��������
	private int num;

	//����״̬ -- 0��δ���� 2��������
	private int commentState;

	//�۸�(���ѽ��)
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