package day20.silsub1;
import java.util.*;
import java.io.*;


public class MyNote {
	private Scanner sc = new Scanner(System.in);
	public void fileSave() {
		System.out.print("파일에 저장할 내용을 입력하시오");
		StringBuilder sb = new StringBuilder();
		String line = null;
		while(!(line = sc.nextLine()).equals("exit")) {
			sb.append("\n"); //이걸 해야 엔터를 했을 때 입력
			sb.append(line);
		}
		System.out.print("저장하시겠습니까?(y/n) : ");
		char ch = ' ';
		if((ch = sc.next().toUpperCase().charAt(0))=='Y') {
			System.out.print("저장할 파일명 : ");
			String fileName = sc.next();
			
			try (BufferedWriter bout = new BufferedWriter(
						new FileWriter(fileName))){
							//bout.write(sb.toString().getBytes());			
						bout.write(sb.toString());
						
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void fileOpen() {
		System.out.print("열기할 파일명 : ");
		String fileName = sc.next();
		try (BufferedReader bin = new BufferedReader(new FileReader(fileName))){
			/*while(true) {
				if(bin.readLine()==null)
					break;
				System.out.println(bin.readLine());
				
			}*/
			String s = null;
			while((s=bin.readLine()) !=null) {
				//readLine()이 없으면 null임 별짓을 다 해봤지만 이거만 됨.
				System.out.println(s);
			}
			/*while(bin.readLine() != null) {
				System.out.println(bin.read());
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void fileEdit() {
		System.out.print("수정할 파일명 : ");
		String fileName = sc.next();
		//FileWriter fw = null;
		StringBuilder sb = new StringBuilder();
		try(BufferedReader br = new BufferedReader(new FileReader(fileName));
				BufferedWriter bw = new BufferedWriter(new FileWriter(fileName,true))) {
			String s = null;
			while((s=br.readLine()) != null) {
				sb.append("\n");
				sb.append(s);
			}
			System.out.print("파일에 추가할 내용을 입력하시오. " );
			sc.nextLine();
			while(!(s = sc.nextLine()).equals("exit")) {
				sb.append("\n"); //한문장 씩 읽는데 \n안해주면 다 붙어서 StringBuilder에 입력
				sb.append(s);
			}
			System.out.print("변경된 내용을 파일에 추가하시겠습니까?(y/n) : ");
			char ch = ' ';
			if((ch=sc.next().toUpperCase().charAt(0))=='Y') {
				bw.write(sb.toString());
				System.out.println(fileName+" 파일의 내용이 변경되었습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


