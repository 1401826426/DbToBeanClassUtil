package com.test.beans;

public class Business {

	//主键
	private int id;

	//图片文件名
	private String imgFileName;

	//标题
	private String title;

	//副标题
	private String subtitle;

	//价格(单位：元)
	private String price;

	//距离(单位：米)
	private int distance;

	//已售数量
	private int number;

	//描述
	private String desc;

	//城市
	private String city;

	//类别
	private String category;

	//评论星星总数
	private int starTotalNum;

	//评论总次数
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