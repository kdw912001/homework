package board.model.vo;
import java.io.*;
import java.util.*;
public class Board implements Serializable{
	private static final long serialVersionUID = -5219066150385199445L;
	
	private int boardNo;
	private String boardTitle;
	private String boardWriter;
	private Date boardDate;
	private String boardContent;
	private int readCount;
	
	public Board() {}

	public Board(int boardNo, String boardTitle, String boardWriter, Date boardDate, String boardContent,
			int readCount) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardWriter = boardWriter;
		this.boardDate = boardDate;
		this.boardContent = boardContent;
		this.readCount = readCount;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public Date getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String toString() {
		return this.boardNo+" "+this.boardTitle+" "+this.boardWriter+" "+this.boardDate+" "+this.boardContent+" "+this.readCount;
	}
	
}
