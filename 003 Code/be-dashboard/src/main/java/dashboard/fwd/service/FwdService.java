package dashboard.fwd.service;

import java.util.List;

import dashboard.fwd.domain.ClimateVO;
import dashboard.fwd.domain.GochangDataVO;
import dashboard.fwd.domain.ParameterList;

public interface FwdService {

	public List<ClimateVO> selectTest(ClimateVO ClimateVO) throws Exception;
	public List<GochangDataVO> returnGochangData(GochangDataVO vo) throws Exception;
//	public List<ClimateVO> returnData(ParameterList list) throws Exception;
	public List<Object> returnData(ParameterList param) throws Exception;
}
