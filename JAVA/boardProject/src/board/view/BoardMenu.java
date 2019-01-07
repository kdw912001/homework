package board.view;

import java.util.*;

import board.controller.BoardManager;

public class BoardMenu {
	private Scanner sc = new Scanner(System.in);
	BoardManager bm = new BoardManager();
	public void mainMenu() {
		
		do {
			System.out.print("******* 게시글 서비스 프로그램 *******\r\n" + 
					"\r\n" + 
					"	1. 게시글 쓰기		//BoardManager의 writeBoard() 실행\r\n" + 
					"	2. 게시글 전체 보기		//		displayAllList()\r\n" + 
					"	3. 게시글 한개 보기		//		displayBoard()\r\n" + 
					"	4. 게시글 제목 수정		//		modifyTitle()\r\n" + 
					"	5. 게시글 내용 수정		//		modifyContent()\r\n" + 
					"	6. 게시글 삭제		//		deleteBoard()\r\n" + 
					"	7. 게시글 검색		//		searchBoard()\r\n" + 
					"	8. 파일에 저장하기		//		saveListFile()\r\n" + 
					"	9. 정렬하기		// BookMenu 의 sortSubMenu() 실행\r\n" + 
					"	10. 끝내기		//main() 으로 리턴함\r\n" + 
					"\r\n" + 
					"	메뉴 번호 선택 : ");
			int no = sc.nextInt();
			switch(no) {
			case 1: bm.writeBoard(); break;
			case 2: bm.displayAllList(); break;
			case 3: bm.displayBoard(); break;
			case 4: bm.modifyTitle(); break;
			case 5: bm.modifyContent(); break;
			case 6: bm.deleteBoard(); break;
			case 7: bm.searchBoard(); break;
			case 8: bm.saveListFile(); break;
			case 9: sortSubMenu(); break;
			case 10: System.out.println("종료하였습니다.");return;
			default : System.out.println("잘못 입력하셨습니다.");
			}
			
		}while(true);
	}
	public void sortSubMenu() {
		do {
			System.out.print("****** 게시글 정렬 메뉴 ******\r\n" + 
					"\r\n" + 
					"	1. 글번호순 오름차순정렬	//BookManager 의 	sortList(1, false) 실행\r\n" + 
					"	2. 글번호순 내림차순정렬	//		sortList(1, true) 실행\r\n" + 
					"	3. 작성날짜순 오름차순정렬	//		sortList(2, false) 실행		\r\n" + 
					"	4. 작성날짜순 내림차순정렬	//		sortList(2, true) 실행\r\n" + 
					"	5. 글제목순 오름차순정렬	//		sortList(3, false) 실행\r\n" + 
					"	6. 글제목순 내림차순정렬	//		sortList(3, true) 실행\r\n" + 
					"	7. 이전 메뉴로 이동		//return 처리\r\n" + 
					"\r\n" + 
					"	메뉴 번호 :");
			int no = sc.nextInt();
			switch(no) {
			case 1: bm.sortList(1, false); break;
			case 2: bm.sortList(1, true); break;
			case 3: bm.sortList(2, false); break;
			case 4: bm.sortList(2, true); break;
			case 5: bm.sortList(3, false); break;
			case 6: bm.sortList(3, true); break;
			case 7: System.out.println("이전 메뉴로 돌아갑니다."); return;
			default : System.out.println("잘못 입력하셨습니다.");
			}
		}while(true);
	}
}
