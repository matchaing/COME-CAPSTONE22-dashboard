package dashboard.fwd.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import dashboard.fwd.domain.ParameterList;
import dashboard.fwd.service.FwdService;


@Controller
//@RequestMapping("/detail/*")
//@RestController
public class FwdController {
	
	@Autowired
	private FwdService service;
	
	@RequestMapping(value="/{parameter}", method=RequestMethod.GET)
	@ResponseBody
	// "Dashboard/detail/{}"로 해야 됨
	// parameter는 climate, gochang
	// "Dashboard/detail/climate?sdate=20180101&edate=20180102
	public String queryString(@PathVariable("parameter")String parameter, @RequestParam(name="sdate") String sdate, @RequestParam(name="edate") String edate) throws Exception {  //Model model
	
		System.out.println("Start Date: " + edate);
		System.out.println("End Date: " + sdate);
//		System.out.println("파라미터: "+parameter);
		
		List<String> list = new ArrayList<>();
		list.add(sdate);
		list.add(edate);
		ParameterList param = new ParameterList(list);
		
		if(parameter.equals("climate")) {
			param.setType("tb_climate_tp");
			param.setDataName("DAILYDATADT");
		}else if(parameter.equals("gochang")) {
			param.setType("tb_gochang_data_tp");
			param.setDataName("FILE_DT");
		}else if(parameter.equals("risk")) {
			param.setType("ae");
			param.setDataName("FILE_DT");
		}
		
		ObjectMapper mapper = new ObjectMapper();
		List<Object> list2 = service.returnData(param);
		
		String json = mapper.writeValueAsString(list2);	
		return json;

	}
	
	// JSP 화면 연결
	@GetMapping("/main.do")
	public String mainPage() {
		return "main";
	}
	@GetMapping("/detail.do")
	public String detailPage() {
		return "detail";
	}
	@GetMapping("/risk.do")
	public String riskPage() {
		return "risk";
	}
		
}
