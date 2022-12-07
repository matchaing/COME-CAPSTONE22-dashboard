package dashboard.fwd.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import dashboard.fwd.domain.ClimateVO;
import dashboard.fwd.domain.GochangDataVO;
import dashboard.fwd.domain.ParameterList;

//query 적힌 xml 위치 
//<property name="mapperLocations"
//value="classpath:/egovframework/sqlmap/mappers/**/*Mapper.xml" />
//</bean>
//Mapper namespace 와 ID를 연결할 Interface 를 두어서 interface를 호출하는 방법.
//Mybatis 매핑XML에 기재된 SQL을 호출하기 위한 인터페이스이다. Mybatis3.0부터 생겼다.
//SQL id는 인터페이스에 정의된 메서드명과 동일하게 작성한다
@Service
@Mapper
public interface TestMapper {
	public List<ClimateVO> selectTest(ClimateVO ClimateVO) throws Exception;
	public List<GochangDataVO> returnGochangData(GochangDataVO vo) throws Exception;
	public List<Object> returnData(ParameterList entity) throws Exception;

}
