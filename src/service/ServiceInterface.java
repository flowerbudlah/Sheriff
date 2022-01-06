package service;

import vo.Criminal;
import vo.Person;
import vo.Sheriff;

public interface ServiceInterface {

	public boolean insertPerson(Person p);//인적사항등록-범죄자나 보안관을 등록한다. 

	public Person searchPersonBySsn(String ssn);//주민번호로 검색

	public Sheriff searchSheriffByOfficerNum(String officerNum);//보안관번호로 검색

	public Criminal searchCriminalByCriminalNum(String criminalNum);//범죄자번호로 검색

	public boolean caughtCriminal(String officerNum, String criminalNum);//잡은보안관번호, 검거된 범죄자번호 입력 받아 맵핑

	public String orderSheriffByHighCaught();//높은 검거율순으로 보안관 정보출력

	public String orderCriminalByHighBounty();//높은 현상금순으로 범죄자 출력

	public String unCaughtCriminalList();//미검거 범죄자 목록

	public String printAll();//전체출력, 보안관->범죄자순

	public void saveFile();//파일저장, 종료시 1회 저장

	public void loadFile();//파일로드, 시작시 1번 로드

}

