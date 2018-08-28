package com.mysp.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.mysp.dao.ProductDAO;
import com.mysp.vo.ProductVO;

@Service
public class ProductServiceImpl implements ProductService {

	@Inject
	private ProductDAO productDAO;
	
	@Override
	public List<ProductVO> prolist() throws Exception {
		return productDAO.prolist();
	}

	@Override
	public ProductVO proinfo(int proid) throws Exception {
		return productDAO.proinfo(proid);
	}

	@Override
	public void proupdate(ProductVO vo) throws Exception {
		productDAO.proupdate(vo);
	}

	@Override
	public void prodelete(int proid) throws Exception {
		productDAO.prodelete(proid);
	}
	
	@Override
	public void insertProduct(ProductVO vo) throws Exception{
		productDAO.insertProduct(vo);
	}
	
	@Override
	public String fileInfo(int proid) throws Exception{
		return productDAO.fileInfo(proid);
	}
}
