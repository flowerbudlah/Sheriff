package vo;

import java.io.Serializable;
import java.util.ArrayList;

public class Sheriff extends Person implements Serializable {

	private String officerNum;// 보안관번호
	private ArrayList<Criminal> cList = new ArrayList<Criminal>();// 보안관이 잡은 범죄자 리스트

	
	public Sheriff(String name, String ssn, String officerNum, ArrayList<Criminal> cList) {
		super(name, ssn);
		this.officerNum = officerNum;
		this.cList = cList;
	}

	public Sheriff(String name, String ssn, String officerNum) {
		super(name, ssn);
		this.officerNum = officerNum;
	}

	public Sheriff() {
	}

	public String getOfficerNum() {
		return officerNum;
	}

	public void setOfficerNum(String officerNum) {
		this.officerNum = officerNum;
	}

	public ArrayList<Criminal> getcList() {
		return cList;
	}

	public void setcList(ArrayList<Criminal> cList) {
		this.cList = cList;
	}

	@Override
	public String toString() {
		return super.toString() + ", 보안관번호=" + officerNum + ", 잡은 범죄자목록=" + cList;
	}

}