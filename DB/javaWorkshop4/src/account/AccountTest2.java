package account;

public class AccountTest2 {

	public static void main(String[] args) {
		Account[] account = new Account[5];
		for(int i=0; i<account.length;i++) {
			account[i] = new Account("221-0101-211"+(i+1),100000,4.5);
			System.out.println(account[i].accountInfo());
		}
		for(int i=0; i<account.length;i++) {
			account[i].setInterestRate(3.7);
			System.out.println(account[i].accountInfo()+"이자 : "+(int)account[i].calculateInterest()+"원");
		}
	}

}
