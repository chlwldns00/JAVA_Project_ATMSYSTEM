package Class;

public class Account {

	protected int Balance = 0; // 계좌의 잔고를 정의하는 클래스 필드이다.
	private User owner; // 클래스 자료형 관계 연결

	// 고객의 계좌의 잔고를 반환하는 메소드이다.
	public int getBalance() {

		return Balance;
	}

	public void setBalance(int Money) {
		this.Balance = Money;
	}

	public void setOwner(User user) {
		this.owner = user;
	}

}