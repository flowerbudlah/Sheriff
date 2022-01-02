package ui;

import java.util.Scanner;

import service.ServiceInterface;
import service.SheriffService;
import vo.Criminal;
import vo.Sheriff;

public class SheriffUI {
	
	private Scanner sc = new Scanner(System.in); //단순 숫자번호 입력
	private Scanner scLine = new Scanner(System.in); //숫자이외의 문자열 입력
	
	private ServiceInterface ss = new SheriffService();
	private boolean flag = true;
	private int choice, bounty;
	private String name, ssn, crimeTitle, criminalNum, officerNum;
	
	public SheriffUI() {
		
		while(flag) {
			
			menu(); 
			choice = sc.nextInt(); 
			switch(choice) {
				case 1: //인적사항 등록	
					if (insertPerson()) {
						System.out.println("등록성공");
					} else {
						System.out.println("등록실패");
					}	
					break;
					
				case 2: 
					System.out.println("1) 주민등록번호 입력: "); ssn = scLine.nextLine(); 
					
					//The person information can be searched by entering the ssn(Resident registration number)
					System.out.println(ss.searchPersonBySsn(ssn));//주민등록번호로 사람 검색해서 그 검색결과를 가져오는 것. 
					break;
					
				case 3: 
					System.out.println("1) 보안관번호 입력: "); officerNum = scLine.nextLine(); 
					
					//The Sheriff information can be searched by entering the officerNum.  
					System.out.println(ss.searchSheriffByOfficerNum(officerNum)); //보안관번호를 이용하여 보안관 정보를 출력한다. 
					break;
					
				case 4: 
					System.out.println("1) 범죄자번호입력 : ");
					criminalNum = scLine.nextLine();
					//The information of criminal person can be searched by inputting the criminal Number.   
					System.out.println(ss.searchCriminalByCriminalNum(criminalNum));
					break;
					
				case 5: //잡힌 범죄자 등록 (매핑 이용)
					System.out.println("1) 잡은 보안관번호 입력 : "); officerNum = scLine.nextLine();
					System.out.println("2) 잡힌 범죄자번호 입력 : "); criminalNum = scLine.nextLine();
					
					if (ss.caughtCriminal(officerNum, criminalNum)) {
						System.out.println("맵핑 성공");
					} else {
						System.out.println("맵핑 실패");
					}
					break;
					
				case 6: //보안관 출력(범죄자 검거수 순으로) 
					System.out.println(ss.orderSheriffByHighCaught()); break;
				case 7: //범죄자 출력(높은 현상금 순으로)
					System.out.println(ss.orderCriminalByHighBounty()); break;
				case 8: //미검거 범죄자 전체출력
					System.out.println(ss.unCaughtCriminalList()); break;
				case 9: //모든사람 전체출력(보안관 ---> 범죄자순) 
					System.out.println(ss.printAll()); break;
				case 99: //프로그램 종료(저장하면서 종료하는 듯)
					ss.saveFile();
					flag = false;
					break;
						} //switch(choice)의 종료 
				} // while(flag)의 종료  
		} //public SheriffUI() 의 종료


	public void menu() {
		System.out.println("============================");
		System.out.println("1.인적사항 등록");
		System.out.println("2.주민등록번호로 사람검색");
		System.out.println("3.보안관번호로 검색");
		System.out.println("4.범죄자등록번호로 검색");
		System.out.println("5.잡힌범죄자 등록");
		System.out.println("6.보안관 출력(범죄자검거수 순으로)");
		System.out.println("7.범죄자 출력(높은 현상금 순으로)");
		System.out.println("8.미검거범죄자 전체출력");
		System.out.println("9.모든사람 전체출력(보안관 ->범죄자순)");
		System.out.println("99.프로그램종료");
		System.out.println("============================");
	}
	
	//인적사항 등록	
	public boolean insertPerson() {
		// TODO Auto-generated method stub
		boolean result = false; 
		System.out.println("1.보안관 등록");
		System.out.println("2.범죄자 등록");
		System.out.println("============================"); 
		choice = sc.nextInt(); 
		
		System.out.println("1) 이름 입력: "); name = scLine.nextLine();
		System.out.println("2) 주민번호 입력: "); ssn = scLine.nextLine();
		
		switch(choice) {
			case 1: //보안관 등록
				System.out.println("3) 보안관 번호 입력: "); officerNum = scLine.nextLine(); 
				
				//보안관 정보를 등록했으니 result의 false가 true가 되어서 
				result = ss.insertPerson(new Sheriff(name, ssn, officerNum)); //변수로 입력되는 new Sheriff(name, ssn, officerNum)은 vo의 Sheriff의 속에 있는 것이다.  
				break;

			case 2: //범죄자 등록
				System.out.println("3) 범죄명 입력: "); crimeTitle = scLine.nextLine();
				System.out.println("4) 현상금 입력: "); bounty = sc.nextInt();
				System.out.println("5) 범죄자 등록번호 입력: "); criminalNum = scLine.nextLine();
				
				//변수로 입력되는 new Criminal(name, ssn, crimeTitle, bounty, criminalNum)은 vo의 Criminal의 속에 있는 것이다.  
				result = ss.insertPerson(new Criminal(name, ssn, crimeTitle, bounty, criminalNum));
				break; 
						}
		
		return result;
	}



}
