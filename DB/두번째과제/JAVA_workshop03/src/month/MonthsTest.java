package month;

public class MonthsTest {

	public static void main(String[] args) {
		int month = Integer.parseInt(args[0]);
		Months m = new Months();
		if (args.length > 1) {
			System.out.println("다시 입력해 주세요.");
			return;
		} else if (args.length == 1) {
			if (month >= 1 && month <= 12) {
				System.out.println("입력받은월 : " + month + "월");
				System.out.println("마지막 일자 : " + m.getDays(month) + "일");

			} else {
				System.out.println("입력된 값이 잘못 되었습니다.");
			}
		}
	}
}
