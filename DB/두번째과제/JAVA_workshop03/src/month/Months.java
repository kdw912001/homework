package month;

public class Months {
	public int getDays(int months) {
		int days;
		if(months == 9 || months == 4 || months == 6 || months == 11) {
			days = 31;
		}else if(months == 1 || months == 3 || months == 5 || months == 7 
				|| months == 8 || months == 10 || months == 12) {
			days = 30;
		}else {
			days = 28;
		}
		return days;
	}
}
