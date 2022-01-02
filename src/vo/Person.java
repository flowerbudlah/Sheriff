package vo;

import java.io.Serializable;

public class Person implements Serializable {

	private String name;// 이름
	private String ssn;// 등록번호

	public Person(String name, String ssn) {
		super();
		this.name = name;
		this.ssn = ssn;
	}

	public Person() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	@Override
	public String toString() {
		return "이름=" + name + ", 등록번호=" + ssn;
	}

}
