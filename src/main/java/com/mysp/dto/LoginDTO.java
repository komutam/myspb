package com.mysp.dto;

public class LoginDTO {//인터셉터를 이용한 로그인 처리: LoginDTO의 용도는 화면에서 전달되는 데이터를 수집하는 용도로 사용

	private String userid;
	private String userpwd;
	private boolean useCookie;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	public boolean isUseCookie() {
		return useCookie;
	}
	public void setUseCookie(boolean useCookie) {
		this.useCookie = useCookie;
	}
	
	@Override
	public String toString() {
		return "LoginDTO [userid="+userid+", userpwd="+userpwd+", useCookie="+useCookie+"]";
	}

}
/*VO(Value Object)와 DTO(Data Transfer Object)

일반적으로 컨트롤러에 전달되는 데이터를 수집하는 용도로 VO를 사용하는 경우도 있고 DTO라는 것을 사용하는 경우도 있습니다. 두 용어에 대해서 개인적으로 다음과 같이 구분하고 있습니다.
DTO와 VO의 용도는 데이터의 수집과 전달에 사용할 수 있다는 공통점이 있습니다. 양쪽 모두 파라미터나 리턴 타입으로 사용하는 것이 가능합니다.
다만 VO의 경우 보다 데이터베이스와의 거리가 가깝습니다. 즉 VO는 테이블의 구조를 이용해서 작성되는 경우가 더 많습니다. DTO의 경우는 보다 화면과 가깝습니다.
화면에서 전달되는 데이터를 수집하는 용도로 사용하는 경우가 많습니다. 스프링 MVC를 이용하는 경우 DTO는 검증을 위한 처리가 들어갑니다.
스프링은 Controller에 전달되는 데이터에 대해서 검증하는 기능을 추가할 수 있는데 이러한 상황에서는 별도의 DTO를 구성해서 사용합니다.*/