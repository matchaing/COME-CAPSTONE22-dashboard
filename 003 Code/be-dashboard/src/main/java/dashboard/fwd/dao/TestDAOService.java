package dashboard.fwd.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dashboard.fwd.domain.ClimateVO;
import dashboard.fwd.domain.GochangDataVO;
import dashboard.fwd.domain.ParameterList;
import dashboard.fwd.service.TestMapper;

@Service("testDAOService")
public class TestDAOService implements TestDAO{
	
	@Autowired
	private SqlSession sqlSession;
//	TestMapper mapper;
	
	public List<ClimateVO> selectTest(ClimateVO ClimateVO) throws Exception{
//		System.out.println("begin TestDAOService");
		TestMapper mapper = sqlSession.getMapper(TestMapper.class);
//		System.out.println("end TestDAOService");
		return mapper.selectTest(ClimateVO);
	}
	
	public List<GochangDataVO> returnGochangData(GochangDataVO vo) throws Exception{
		TestMapper mapper = sqlSession.getMapper(TestMapper.class);
		return mapper.returnGochangData(vo);
	}
	
	public List<Object> returnData(ParameterList entity) throws Exception{
//		System.out.println("ServiceDAO: " +list.get(0));
//		ParameterList entity = new ParameterList(list);
		TestMapper mapper = sqlSession.getMapper(TestMapper.class);
		return mapper.returnData(entity);
	}

}
