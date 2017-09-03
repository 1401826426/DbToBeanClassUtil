package com.test.beans;
import com.test.beans.BaseBean;
import java.io.Serializable;

public class Ad extends BaseBean implements Serializable{

	//
	private int id = 10;

	//标题
	private String title;

	//图片文件名
	private String imgFileName;

	//链接地址
	private String link;

	//权重
	private int weight;

	public void setId(int id){
	    this.id = id;
	}

	public int getId(){
	    return id;
	}

	public void setTitle(String title){
	    this.title = title;
	}

	public String getTitle(){
	    return title;
	}

	public void setImgFileName(String imgFileName){
	    this.imgFileName = imgFileName;
	}

	public String getImgFileName(){
	    return imgFileName;
	}

	public void setLink(String link){
	    this.link = link;
	}

	public String getLink(){
	    return link;
	}

	public void setWeight(int weight){
	    this.weight = weight;
	}

	public int getWeight(){
	    return weight;
	}

}