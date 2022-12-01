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
	

	/*
	//../Dashboard/risk  (포트 번호 5000으로 해야 하는지 테스트 해야 됨.. 백엔드에서 실행할 때는 주소를 5000으로 해야 됐음)
	@RequestMapping(value = "/risk", method = RequestMethod.GET)
	public ModelAndView riskReturn(Model model, @RequestParam(name="sdate") String sdate, @RequestParam(name="edate") String edate) throws Exception {
		ModelAndView mav = new ModelAndView();
//		Model model = null;
		String url = "http://127.0.0.1:5000/Dashboard/risk";
		String sb = "";
		try {     
			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

			String line = null;
			while ((line = br.readLine()) != null) {
				sb = sb + line + "\n";
			}
			System.out.println("===============" + sb.toString());
			if (sb.toString().contains("ok")) {
				System.out.println("risk page ok");	
			}
			br.close();

			System.out.println("" + sb.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.addAttribute("risk", sb.toString());
		/*
		mav.addObject("risk", sb.toString()); // "risk"는 jsp파일에서 받을때 이름, 
        						//sb.toString은 value값(여기에선 risk)
		mav.addObject("fail", false);
		mav.setViewName("risk");   // jsp파일 이름
		
		System.out.println();
		return mav;
	}
	*/
	
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
	
	/*
	@RequestMapping(value="/{parameter}", method=RequestMethod.GET)
	@ResponseBody
	public String returnData(@PathVariable("parameter") Object vo, String parameter ) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		List<Object> list = service.returnData(vo);
			
		for(int i = 0; i < list.size(); i++) {
		System.out.println(list.get(i));
		}
		
		String json = mapper.writeValueAsString(list);	
		return json;
	}
	
	*/
	/*
	public String returnClimate() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		ClimateVO vo = new ClimateVO();
		List<ClimateVO> list = service.selectTest(vo);
		
		for(int i = 0; i < list.size(); i++) {
		System.out.println(list.get(i));
		}
		
		String json = mapper.writeValueAsString(list);	
		return json;
	}
	
	
	@RequestMapping(value="/{parameter}", method=RequestMethod.GET)
	@ResponseBody
	// "Dashboard/detail/{}"로 해야 됨
	// parameter는 climate, gochang
	public String returnParameter(@PathVariable("parameter") String parameter) throws Exception {  //Model model
	
		String json = "";
		System.out.println("파라미터: "+parameter);
			
		if(parameter.equals("climate")) {
			System.out.println("parameter: climate");
			json = returnClimate();
		}else if(parameter.equals("gochang")){
			System.out.println("parameter: gochang");
			json = returnGochang();
		}
		
		return json;
	}
	
	
	@RequestMapping(value="/detail.do")
	public String selectReqUrl(@ModelAttribute("searchVO") ClimateVO searchVO, ModelMap model) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		List<ClimateVO> list = service.selectTest(searchVO);
		String json = mapper.writeValueAsString(list);
		model.addAttribute("json", json); //service.selectTest(searchVO)
		return "detail";
	}
	*/
	
}
