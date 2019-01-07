package board.model.vo;
import java.util.Comparator;
public class DescBoardNo implements Comparator{
	public int compare(Object o1, Object o2) {
		int result=0;
		if(o1 instanceof Board&& o2 instanceof Board) {
			Board b1 = (Board)o1;
			Board b2 = (Board)o2;
			if(Integer.compare(b1.getBoardNo(), b2.getBoardNo())>0) {
				result = 1;
			}else if(Integer.compare(b1.getBoardNo(), b2.getBoardNo())<0) {
				result = -1;
			}
		}
		return -result;
	}
}
