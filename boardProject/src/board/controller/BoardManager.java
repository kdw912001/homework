package board.controller;

import java.util.*;
import board.model.vo.*;
import java.io.*;


public class BoardManager {
	ArrayList<Board>list = new ArrayList<Board>();
	private Scanner sc = new Scanner(System.in);
	
	public BoardManager() {
		//board_list.dat 파일의 내용을 읽어서list에 저장함  null 될 때까지 저장함
		Board[] bo = new Board[100];
		int count=0;
		try (ObjectInputStream objIn = new ObjectInputStream(new FileInputStream("board_list.dat"))){
			//objIn.readObject();
			/*while(true) {
				System.out.println(list.size());
				System.out.println(list);
				System.out.println(objIn.readObject());
			}*/
			
			/*while(true) {
				bo[count]=(Board)objIn.readObject();
				count++;
			}*/
			list = (ArrayList<Board>) objIn.readObject();//list로 저장했으니 list로 읽기
			
		/*}catch(EOFException e) { 
			System.out.println("board_list.dat 불러오기 완료");
			System.out.println(bo[0]);
			for(int i=0; i<count;i++) {
				list.add(bo[i]);
			}
			System.out.println(count);
			System.out.println(list);*/
		}catch(FileNotFoundException e) {
			 System.out.println("board_list.dat 파일이 없습니다.");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void writeBoard() {
		
		System.out.println("새 게시글 쓰기입니다.");
		System.out.print("글제목 : ");
		String name = sc.nextLine();//공백포함
		System.out.print("작성자 : ");//공백 없이
		String author = sc.next();
		Date d = new Date();//작성날짜는 현재 날짜로 입력처리
		System.out.print("글내용 : \n");
		StringBuilder sb = new StringBuilder();
		String str= null;
		while(!((str=sc.nextLine()).equals("exit"))) {
			sb.append(str+"\n");
		}
			
		Board b = new Board(list.size(),name,author,d,sb.toString(),0);
		list.add(b);
		//list에 추가함
	}
	
	public void displayAllList() {
		System.out.println(list);
	}
	public void displayBoard() {
		try {
			System.out.print("조회할 글번호 : ");
			int boardNo = sc.nextInt();
			System.out.println(list.get(boardNo).getBoardContent());
			list.get(boardNo).setReadCount(list.get(boardNo).getReadCount()+1);
		}catch(Exception e) {
			System.out.println("잘못된 숫자 혹은 다른 타입을 입력하셨습니다.");
			System.out.println(0+"~"+(list.size()-1)+"사이의 숫자를 입력하십시오.");
		}
	}
	
	public void modifyTitle() {
		try{
			System.out.print("수정할 글번호 : ");
			int boardNo = sc.nextInt();
			System.out.println(list.get(boardNo).getBoardContent());
			System.out.print("변경할 제목 : ");
			String boardTitle = sc.next();
			list.get(boardNo).setBoardTitle(boardTitle);
		}catch(Exception e) {
			System.out.println("잘못된 숫자 혹은 다른 타입을 입력하셨습니다.");
			System.out.println(0+"~"+(list.size()-1)+"사이의 숫자를 입력하십시오.");
		}
				
	}
	
	public void modifyContent() {
		try {
			System.out.print("수정할 글 번호 : ");
			int boardNo = sc.nextInt();
			System.out.println(list.get(boardNo).getBoardContent());
			System.out.print("변경할 내용 : \n");
			StringBuilder sb = new StringBuilder();
			String str = null;
			while(!((str=sc.nextLine()).equals("exit"))) {
				sb.append(str+"\n");
			}
			list.get(boardNo).setBoardContent(sb.toString());
		} catch (Exception e) {
			System.out.println("잘못된 숫자 혹은 다른 타입을 입력하셨습니다.");
			System.out.println(0+"~"+(list.size()-1)+"사이의 숫자를 입력하십시오.");
		}
	
		
		
	}
	public void deleteBoard() {
		try {
			System.out.print("삭제할 글 번호 : ");
			int boardNo = sc.nextInt();
			System.out.println(list.get(boardNo).getBoardContent());
			System.out.print("정말로 삭제하시겠습니까?(y/n) : ");
			if(sc.next().toUpperCase().charAt(0)=='Y') {
				list.remove(boardNo);
				System.out.println(boardNo+"번 글이 삭제되었습니다.");
			}
		}catch(Exception e) {
			System.out.println("잘못된 숫자 혹은 다른 타입을 입력하셨습니다.");
			System.out.println(0+"~"+(list.size()-1)+"사이의 숫자를 입력하십시오.");
		}
	}
	public void searchBoard() {
		
		System.out.print("검색할 제목 : ");
		String boardTitle = sc.nextLine();
		for(int i=0; i<list.size();i++) {
			if(boardTitle.equals(list.get(i).getBoardTitle())) {
				System.out.println(list.get(i));
			}
		}
		System.out.println("입력한 제목이 없습니다.");
	}
	public void saveListFile() {
		Board[] b1 = new Board[100];
		try (ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream("board_list.dat"))){
			/*b1=(Board[]) list.toArray();
			for(int i=0; i<list.size();i++) {
				objOut.writeObject(b1[i]);
			}*/
			objOut.writeObject(list);
			//objOut.writeObject(list);
			System.out.println("board_list.dat 에 성공적으로 저장되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void sortList(int item, boolean isDesc) {
		if(item==1) {
			if(!isDesc) 
				list.sort(new AscBoardNo());
			else 
				list.sort(new DescBoardNo());
		}
		if(item==2) {
			if(!isDesc) 
				list.sort(new AscBoardTitle());
			else 
				list.sort(new DescBoardTitle());
		}
		if(item==3) {
			if(!isDesc) 
				list.sort(new AscBoardDate());
			else 
				list.sort(new DescBoardDate());
		}
		System.out.println("정렬 후 출력");
		displayAllList();
	}
}
