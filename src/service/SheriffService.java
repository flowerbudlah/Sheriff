package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
	public boolean insertPerson(Person p) {  // 인적사항등록
		return this.personList.add(p);
	}

	@Override
	public Person searchPersonBySsn(String ssn) {
		int idx = -1;
		for(int i = 0 ; i < this.personList.size(); i++) {
			if(this.personList.get(i).getSsn().equals(ssn)) {
				idx = i;  break;
			}
		}
		if(idx < 0) return null;
		else return this.personList.get(idx);
	}

	@Override
	public Sheriff searchSheriffByOfficerNum(String officerNum) {
		int idx = -1;
		for(int i = 0 ; i < this.personList.size() ; i++) {
			if(this.personList.get(i) instanceof Sheriff) {  //보안관이라면
				Person p = this.personList.get(i);
				Sheriff s = (Sheriff)p;
				if(s.getOfficerNum().equals(officerNum)) {
					idx = i;   break;
				}
			}
		}
		if(idx < 0) return null;
		else return (Sheriff)this.personList.get(idx);
	}

	@Override
	public Criminal searchCriminalByCriminalNum(String criminalNum) {
		int idx = -1;
		for(int i = 0 ; i < this.personList.size() ; i++) {
			if(this.personList.get(i) instanceof Criminal) {//범죄자라면
				Person p = this.personList.get(i);
				Criminal c = (Criminal)p;
				if(c.getCriminalNum().equals(criminalNum)) {
					idx = i;   break;
				}
			}
		}
		if(idx < 0) return null;
		else return (Criminal)this.personList.get(idx);
	}

	@Override
	public boolean caughtCriminal(String officerNum, String criminalNum) {
		Sheriff s = this.searchSheriffByOfficerNum(officerNum);
		Criminal c = this.searchCriminalByCriminalNum(criminalNum);
		ArrayList<Criminal> list = s.getcList();
		list.add(c);
		s.setcList(list);
		if(s != null && c != null) return true;
		else return false;
	}
	
	@Override
	public String orderSheriffByHighCaught() { //높은 검거율순으로 보안관 정보출력
		ArrayList<Person> printList = new ArrayList<Person>();
		
		for(int i = 0 ; i < this.personList.size() ; i++) {
			if(this.personList.get(i) instanceof Sheriff) {
				printList.add(this.personList.get(i));
			}
		}
		
		Collections.sort(printList, new Comparator<Person>() {
				@Override
				public int compare(Person s1, Person s2) {
					
					if(((Sheriff) s1).getcList().size() > ((Sheriff) s2).getcList().size() ) {	
						return -1;
					} else if(((Sheriff) s1).getcList().size() < ((Sheriff) s2).getcList().size()) {
						return 1;
					} else {
						return 0; 
					}
					
				}}); 
		String str = "";
		for(Person s : printList) {
			str += s.toString() + "\n";
		}
		return str;
	}
	
	@Override
	public String orderCriminalByHighBounty() { //높은 현상금순으로 범죄자 출력
		ArrayList<Person> printList = new ArrayList<Person>();
		
		for(int i = 0 ; i < this.personList.size() ; i++) {
			if(this.personList.get(i) instanceof Criminal) {
				printList.add(this.personList.get(i));
			}
		}
		
		//높은 현상금 순 정렬하기
		Collections.sort(printList, new Comparator<Person>() {
			@Override
			public int compare(Person s1, Person s2) {
				
				if(((Criminal) s1).getBounty() < ((Criminal) s2).getBounty() ) {
					return 1;
				} else if(((Criminal) s1).getBounty() > ((Criminal) s2).getBounty()) {
					return -1;
				} else {
					return 0; 
				}
			}
		}); 
		
			String str = "";
			for(Person p : printList) {
				str += p.toString() + "\n";
		}
			return str;
	}

	@Override
	public String unCaughtCriminalList() { //미검거 범죄자 목록
		ArrayList<Person> printList = new ArrayList<Person>();
		Sheriff s = new Sheriff(); 
	
		
		for(int i = 0 ; i < this.personList.size() ; i++) {
			if(this.personList.get(i) instanceof Criminal) {
				printList.add(this.personList.get(i));
			}
		}
		
		for(int i = 0 ; i < this.personList.size() ; i++) { //검거된 범죄자
			if(this.personList.get(i) instanceof Sheriff) {
				printList.removeAll(((Sheriff) this.personList.get(i)).getcList()); 
			}
		}
		
		String str = "";
		for(Person p : printList) {
			str += p.toString() + "\n";
		}
		return str;
	}
	
	@Override
	public String printAll() {
		ArrayList<Person> printList = new ArrayList<Person>();
		
		for(int i = 0 ; i < this.personList.size() ; i++) {
			if(this.personList.get(i) instanceof Sheriff) {
				printList.add(this.personList.get(i));
			}
		}
		for(int i = 0 ; i < this.personList.size() ; i++) {
			if(this.personList.get(i) instanceof Criminal) {
				printList.add(this.personList.get(i));
			}
		}
		String str = "";
		for(Person p : printList) {
			str += p.toString() + "\n";
		}
		return str;
	}

	@Override
	public void saveFile() {   //직렬화
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(this.FILE_NAME);
			oos = new ObjectOutputStream(fos); 
			oos.writeObject(this.personList);
		}catch(IOException ex) {
			System.out.println(ex);
		}finally {
			try {
				if(fos != null) fos.close();
				if(oos != null) oos.close();
			}catch(IOException ex) {}
		}
	}

	@Override
	public void loadFile() {   //역직렬화
		File file = new File(this.FILE_NAME);
		try {
			if(file.exists()) {
				this.fis = new FileInputStream(file);
				this.ois = new ObjectInputStream(this.fis);
				this.personList = (ArrayList<Person>)this.ois.readObject();
			}else {
				file.createNewFile();   //없으면 새로 생성하기
				this.personList = new ArrayList<Person>();
			}
		}catch(IOException ex) {
			System.out.println(ex);
		}catch(ClassNotFoundException ex){
			System.out.println(ex);
		}finally {
			try {
				if(fis != null) fis.close();
				if(ois != null) ois.close();
			}catch(IOException ex) {}
		}

	}

}