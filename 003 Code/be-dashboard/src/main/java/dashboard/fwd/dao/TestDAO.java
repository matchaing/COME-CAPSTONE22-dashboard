package dashboard.fwd.dao;

import java.util.List;

import dashboard.fwd.domain.ClimateVO;
import dashboard.fwd.domain.GochangDataVO;
import dashboard.fwd.domain.ParameterList;

public interface TestDAO {
	List<ClimateVO> selectTest(ClimateVO ClimateVO) throws Exception;
	List<GochangDataVO> returnGochangData(GochangDataVO vo) throws Exception;
	List<Object> returnData(ParameterList entity) throws Exception;
}
