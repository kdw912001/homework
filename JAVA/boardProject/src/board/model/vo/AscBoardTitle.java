package board.model.vo;


import java.util.Comparator;

public class AscBoardTitle implements Comparator {
	public int compare(Object o1, Object o2) {
		int result=0;
		if(o1 instanceof Board&& o2 instanceof Board) {
			Board b1 = (Board)o1;
			Board b2 = (Board)o2;
			if(b1.getBoardTitle().compareTo(b2.getBoardTitle())>0) {
				result = 1;
			}else if(b1.getBoardTitle().compareTo(b2.getBoardTitle())<0) {
				result = -1;
			}
		}
		return result;
	}
}
