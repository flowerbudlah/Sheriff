package service;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

import vo.Criminal;
import vo.Person;
import vo.Sheriff;

public class SheriffService implements ServiceInterface {
	
	private final String FILE_NAME = "person.dat"; 
	private List<Person> personList; 
	
	private FileInputStream fis; 
	private ObjectInputStream ois; 
	
	public SheriffService() {
		this.loadFile();
	}

	@Override
	public boolean insertPerson(Person p) {//인적사항등록
		return this.personList.add(p); 
	}

	@Override
	public Person searchPersonBySsn(String ssn) {//주민등록번호로 사람(보안관 Sheriff, 범죄자)을 검색한다. 
		
		int idx = -1; //아직 못찾았으니 -1
		for(int i=0; i<this.personList.size();i++) {
			if(this.personList.get(i).getSsn().equals(ssn)) {//입력한 주민등록번호가 있다면(발견)
				idx = i; break; 
				} //for문 내부 if문의 끝
			} //for문의 끝
		
		if(idx < 0) { //검색결과 없음(정보없음). 따라서 null
			return null;
		} else {//검색결과 나옴(정보있음)
			return this.personList.get(idx);	
		}
	}
	
	@Override
	public Sheriff searchSheriffByOfficerNum(String officerNum) {//보안관번호로 검색하여 보안관 정보 출력
	
		int idx = -1; 
		for(int i=0; i<this.personList.size();i++) {
			if(this.personList.get(i) instanceof Sheriff) {//보안관 정보존재한다면 
				Person p = this.personList.get(i); 
				Sheriff s = (Sheriff) p;  
				if(s.getOfficerNum().equals(officerNum)) {
					idx = i; 
					break; 
				}
			}
		}//for문의 종료
		if(idx < 0){ //정보가 없다. 
			return null;
		} else {
			return (Sheriff) this.personList.get(idx); 
		}	
	}

	@Override
	public Criminal searchCriminalByCriminalNum(String criminalNum) { //범죄자 번호로 검색하여 범죄자정보 출력
		int idx = -1; 
		for(int i=0; i<this.personList.size(); i++) {
			if(this.personList.get(i) instanceof Criminal) {//범죄자 정보존재
				Person p = this.personList.get(i); 
				Criminal c = (Criminal) p;
				if(c.getCriminalNum().equals(criminalNum)) {
					idx = i; break; 
				}
			}//if문의 끝
		}//for문의 끝
		
		if(idx < 0) {//검색 결과가 없다. 
			return null;
		}else { //검색결과가 있다.검색결과인 범죄자 정보를 가져온다.  
			return (Criminal) this.personList.get(idx);
		}
	}
	
	@Override
	public boolean caughtCriminal(String officerNum, String criminalNum){//잡은 보안관번호, 검거된 범죄자번호 입력 받아 맵핑
		Sheriff s = this.searchSheriffByOfficerNum(officerNum); 
		Criminal c = this.searchCriminalByCriminalNum(criminalNum); 
		
		//보안관이 잡은 범죄자들 
		
		
		
		
		return false;
	}

	@Override
	public String orderSheriffByHighCaught() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String orderCriminalByHighBounty() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String unCaughtCriminalList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String printAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveFile() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadFile() {
		// TODO Auto-generated method stub
		
	}

}
