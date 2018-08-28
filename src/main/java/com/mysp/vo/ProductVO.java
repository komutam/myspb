package com.mysp.vo;

import org.springframework.web.multipart.MultipartFile;

public class ProductVO {
	private int proid;
	private String proname;
	private int proprice;
	private String proinfo;
	private String prourl; //이미지 경로
	private MultipartFile productPhoto; //이미지 파일
	
	public int getProid() {
		return proid;
	}
	public void setProid(int proid) {
		this.proid = proid;
	}
	public String getProname() {
		return proname;
	}
	public void setProname(String proname) {
		this.proname = proname;
	}
	public int getProprice() {
		return proprice;
	}
	public void setProprice(int proprice) {
		this.proprice = proprice;
	}
	public String getProinfo() {
		return proinfo;
	}
	public void setProinfo(String proinfo) {
		this.proinfo = proinfo;
	}
	public String getProurl() {
		return prourl;
	}
	public void setProurl(String prourl) {
		this.prourl = prourl;
	}
	public MultipartFile getProductPhoto() {
		return productPhoto;
	}
	public void setProductPhoto(MultipartFile productPhoto) {
		this.productPhoto = productPhoto;
	}
	
	@Override
	public String toString() {
		return "productVO[proid="+proid+", proname="+proname+", proprice="+proprice+", proinfo="+proinfo+", prourl="+prourl+", productPhoto="+productPhoto+"]";
	}
	
}
