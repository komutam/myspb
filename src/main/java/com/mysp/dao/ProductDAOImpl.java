package com.mysp.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mysp.vo.ProductVO;

@Repository
public class ProductDAOImpl implements ProductDAO {

	@Inject
	SqlSession sqlSession;
	
	//상품목록
	@Override
	public List<ProductVO> prolist() throws Exception {
		return sqlSession.selectList("ProductMapper.prolist");
	}
	
	//상품상세
	@Override
	public ProductVO proinfo(int proid) throws Exception {
		return sqlSession.selectOne("ProductMapper.proinfo", proid);
	}
	
	//상품수정
	@Override
	public void proupdate(ProductVO vo) throws Exception {
		sqlSession.update("ProductMapper.proupdate", vo);
	}
	
	//상품삭제
	@Override
	public void prodelete(int proid) throws Exception {
		sqlSession.delete("ProductMapper.prodelete", proid);
	}
	
	//상품추가
	@Override
	public void insertProduct(ProductVO vo) throws Exception {
		sqlSession.insert("ProductMapper.insertProduct", vo);
	}
	
	@Override
	public String fileInfo(int proid) throws Exception {
		return sqlSession.selectOne("ProductMapper.fileInfo", proid);
	}
	
}
