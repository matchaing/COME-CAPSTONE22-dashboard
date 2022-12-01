package dashboard.fwd.domain;

import java.util.ArrayList;
import java.util.List;

public class ParameterList {
	private String type;
	private String dataName;
	private ArrayList<String> date = new ArrayList<String>();
	private ArrayList<String> time = new ArrayList<String>();
	
	public ParameterList() {}
	
	public ParameterList(List<String> list) {
//		if(!(date.isEmpty())) {
//			date.clear();
//		}
		System.out.println(list.size());
		for(int i = 0; i < list.size(); i++) {
			System.out.println("List: " +list.get(i));
			this.date.add(list.get(i));
		}
	}
	
	
	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public ArrayList<String> getDate() {
		return date;
	}
	public void setDate(ArrayList<String> date) {
		this.date = date;
	}
	public ArrayList<String> getTime() {
		return time;
	}
	public void setTime(ArrayList<String> time) {
		this.time = time;
	}
	
}
