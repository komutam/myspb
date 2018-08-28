package com.mysp.dao;

import java.util.List;

import com.mysp.vo.ProductVO;

public interface ProductDAO {
	
	//상품목록
	public List<ProductVO> prolist() throws Exception;
	
	//상품상세
	public ProductVO proinfo(int proid) throws Exception;
	
	//상품수정
	public void proupdate(ProductVO vo) throws Exception;

	//상품삭제
	public void prodelete(int proid) throws Exception;
	
	//상품추가
	public void insertProduct(ProductVO vo) throws Exception;
	
    //상품이미지 삭제를 위한 파일정보
    public String fileInfo(int proid) throws Exception;
}
