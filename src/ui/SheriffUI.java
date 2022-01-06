package ui;

import java.util.Scanner;

import service.ServiceInterface;
import service.SheriffService;
import vo.Criminal;
import vo.Sheriff;

public class SheriffUI {

	private Scanner sc = new Scanner(System.in);
	private Scanner scLine = new Scanner(System.in);
	private ServiceInterface ss = new SheriffService();
	private boolean flag = true;
	private int choice, bounty;
	private String name, ssn, crimeTitle, criminalNum, officerNum;

	public SheriffUI() {

		while (flag) {
			menu();
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				if (insertPerson()) {
					System.out.println("등록성공");
				} else {
					System.out.println("등록실패 ****");
				}
				break;
			case 2:
				System.out.println("주민번호입력 : ");
				ssn = scLine.nextLine();
				System.out.println(ss.searchPersonBySsn(ssn));
				break;
			case 3:
				System.out.println("보안관번호입력 : ");
				officerNum = scLine.nextLine();
				System.out.println(ss.searchSheriffByOfficerNum(officerNum));
				break;
			case 4:
				System.out.println("범죄자번호입력 : ");
				criminalNum = scLine.nextLine();
				System.out.println(ss.searchCriminalByCriminalNum(criminalNum));
				break;
			case 5:
				System.out.println("잡은보안관번호입력 : ");
				officerNum = scLine.nextLine();
				System.out.println("잡힌범죄자번호입력 : ");
				criminalNum = scLine.nextLine();
				if (ss.caughtCriminal(officerNum, criminalNum)) {
					System.out.println("맵핑성공");
				} else {
					System.out.println("맵핑실패");
				}
				break;
			case 6:
				System.out.println(ss.orderSheriffByHighCaught());
				break;
			case 7:
				System.out.println(ss.orderCriminalByHighBounty());
				break;
			case 8:
				System.out.println(ss.unCaughtCriminalList());
				break;
			case 9:
				System.out.println(ss.printAll());
				break;
			case 99:
				ss.saveFile();
				flag = false;
				break;
			}
		}

	}

	public void menu() {
		System.out.println("============================");
		System.out.println("1.인적사항등록");
		System.out.println("2.주민번호로사람검색");
		System.out.println("3.보안관번호로검색");
		System.out.println("4.범죄자등록번호로검색");
		System.out.println("5.잡힌범죄자등록");
		System.out.println("6.보안관출력(범죄자검거수순으로)");
		System.out.println("7.범죄자출력(높은현상금순으로)");
		System.out.println("8.미검거범죄자 전체출력");
		System.out.println("9.모든사람 전체출력(보안관 ->범죄자순)");
		System.out.println("99.프로그램종료");
		System.out.println("============================");
	}

	public boolean insertPerson() {
		boolean result = false;
		System.out.println("1.보안관 등록");
		System.out.println("2.범죄자 등록");
		System.out.println("============================");
		choice = sc.nextInt();

		System.out.println("이름 입력 : ");
		name = scLine.nextLine();
		System.out.println("주민번호 입력 : ");
		ssn = scLine.nextLine();
		switch (choice) {
		case 1:
			System.out.println("보안관 번호 입력 : ");
			officerNum = scLine.nextLine();
			result = ss.insertPerson(new Sheriff(name, ssn, officerNum));
			break;
		case 2:
			System.out.println("범죄명 입력 : ");
			crimeTitle = scLine.nextLine();
			System.out.println("현상금 입력 : ");
			bounty = sc.nextInt();
			System.out.println("범죄자 등록번호 입력 : ");
			criminalNum = scLine.nextLine();
			result = ss.insertPerson(new Criminal(name, ssn, crimeTitle, bounty, criminalNum));
			break;
		}
		return result;

	}

}

