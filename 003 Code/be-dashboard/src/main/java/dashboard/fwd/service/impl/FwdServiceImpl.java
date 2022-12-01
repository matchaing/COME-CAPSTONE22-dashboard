package dashboard.fwd.service.impl;

import java.util.List;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dashboard.fwd.dao.TestDAO;
import dashboard.fwd.domain.ClimateVO;
import dashboard.fwd.domain.GochangDataVO;
import dashboard.fwd.domain.ParameterList;
import dashboard.fwd.service.FwdService;
import dashboard.fwd.service.TestMapper;

@Service("fwdService")
public class FwdServiceImpl extends EgovAbstractServiceImpl implements FwdService{

	@Autowired
	private TestDAO testDAOService;
	TestMapper mapper;
	
	@Override
	public List<ClimateVO> selectTest(ClimateVO vo) throws Exception{
		System.out.println("FwdServiceImpl");
		return testDAOService.selectTest(vo);
	}
	
	@Override
	public List<GochangDataVO> returnGochangData(GochangDataVO vo) throws Exception{
		return testDAOService.returnGochangData(vo);
	}
	
	@Override
	public List<Object> returnData(ParameterList entity) throws Exception{
		System.out.println("ServiceImpl");
//		ParameterList entity = new ParameterList(list);
		System.out.println("Parameter: " + entity);
		return testDAOService.returnData(entity);
	}
}
