package com.mysp.vo;

public class CartVO {
	private int cartid;
	private String userid;
	private String username;
	private int proid;
	private String proname;
	private int proprice;
	private int amount;
	private int money;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getCartid() {
		return cartid;
	}
	public void setCartid(int cartid) {
		this.cartid = cartid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getProid() {
		return proid;
	}
	public void setProid(int proid) {
		this.proid = proid;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return "CartVO [cartid=" + cartid + ", userid=" + userid + ", username="+username+", proid=" + proid + ", proname="+proname+", proprice="+proprice+", amount=" + amount + ", money="+money+"]";
	}
}
