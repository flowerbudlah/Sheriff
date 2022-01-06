package vo;

import java.io.Serializable;

public class Criminal extends Person implements Serializable {

	private String crimeTitle;// 범죄명
	private int bounty;// 현상금
	private String criminalNum;// 범죄자등록번호

	public Criminal(String name, String ssn, String crimeTitle, int bounty, String criminalNum) {
		super(name, ssn);
		this.crimeTitle = crimeTitle;
		this.bounty = bounty;
		this.criminalNum = criminalNum;
	}

	public Criminal() {
	}

	public String getCrimeTitle() {
		return crimeTitle;
	}

	public void setCrimeTitle(String crimeTitle) {
		this.crimeTitle = crimeTitle;
	}

	public int getBounty() {
		return bounty;
	}

	public void setBounty(int bounty) {
		this.bounty = bounty;
	}

	public String getCriminalNum() {
		return criminalNum;
	}

	public void setCriminalNum(String criminalNum) {
		this.criminalNum = criminalNum;
	}

	@Override
	public String toString() {
		return super.toString() + ", 죄목명=" + crimeTitle + ", 현상금=" + bounty + "원, 범죄자등록번호=" + criminalNum;
	}

}
