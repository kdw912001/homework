package board.controller;

import java.util.*;
import board.model.vo.Board;


public class BoardManager {
	ArrayList<Board>list = new ArrayList<Board>();
	private Scanner sc = new Scanner(System.in);
	
	public BoardManager() {
		//board_list.dat 파일의 내용을 읽어서list에 저장함
		//null 될 때까지 저장함
	}
	
	public void writeBoard() {
		System.out.println("새 게시글 쓰기입니다.");
		System.out.print("글제목 : ");
		String name = sc.nextLine();//공백포함
		sc.nextLine();
		System.out.print("작성자 : ");//공백 없이
		String author = sc.next();
		Date d = new Date();//작성날짜는 현재 날짜로 입력처리
		System.out.print("글내용 : ");
		//String Builder??? 써야되나
		
		//list에 추가함
	}
	
	public void displayAllList() {
		System.out.println(list);
	}
	
	public void modifyTitle() {
		System.out.print("수정할 글번호 : ");
		int boardNo = sc.nextInt();
		System.out.println(list.get(boardNo).getBoardContent());
		System.out.print("변경할 제목 : ");
		String boardTitle = sc.next();
		list.get(boardNo).setBoardTitle(boardTitle);
		
		//새 제목 입력받음 >> 제목변경
		
		//변경된 객체 정보를 list의 해당 번호 객체에 적용함
		
	}
	
	public void modifyContent() {
		System.out.print("수정할 글 번호 : ");
		
	}
	
}
