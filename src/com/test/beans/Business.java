package com.test.beans;

public class Business {

	//����
	private int id;

	//ͼƬ�ļ���
	private String imgFileName;

	//����
	private String title;

	//������
	private String subtitle;

	//�۸�(��λ��Ԫ)
	private String price;

	//����(��λ����)
	private int distance;

	//��������
	private int number;

	//����
	private String desc;

	//����
	private String city;

	//���
	private String category;

	//������������
	private int starTotalNum;

	//�����ܴ���
	private int commentTotalNum;

	public void setId(int id){
	    this.id = id;
	}

	public int getId(){
	    return id;
	}

	public void setImgFileName(String imgFileName){
	    this.imgFileName = imgFileName;
	}

	public String getImgFileName(){
	    return imgFileName;
	}

	public void setTitle(String title){
	    this.title = title;
	}

	public String getTitle(){
	    return title;
	}

	public void setSubtitle(String subtitle){
	    this.subtitle = subtitle;
	}

	public String getSubtitle(){
	    return subtitle;
	}

	public void setPrice(String price){
	    this.price = price;
	}

	public String getPrice(){
	    return price;
	}

	public void setDistance(int distance){
	    this.distance = distance;
	}

	public int getDistance(){
	    return distance;
	}

	public void setNumber(int number){
	    this.number = number;
	}

	public int getNumber(){
	    return number;
	}

	public void setDesc(String desc){
	    this.desc = desc;
	}

	public String getDesc(){
	    return desc;
	}

	public void setCity(String city){
	    this.city = city;
	}

	public String getCity(){
	    return city;
	}

	public void setCategory(String category){
	    this.category = category;
	}

	public String getCategory(){
	    return category;
	}

	public void setStarTotalNum(int starTotalNum){
	    this.starTotalNum = starTotalNum;
	}

	public int getStarTotalNum(){
	    return starTotalNum;
	}

	public void setCommentTotalNum(int commentTotalNum){
	    this.commentTotalNum = commentTotalNum;
	}

	public int getCommentTotalNum(){
	    return commentTotalNum;
	}

}