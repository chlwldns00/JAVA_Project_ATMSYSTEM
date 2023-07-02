package Class;

// 정기 예금 계좌 
public class TimeDeposit extends Account {
	private final double interest = 0.05; // 월별 이자 5%
	private int period; // 사용자가 기간을 설정.
	private int total;

	// 기간을 설정하는 메소드
	public void setPeriod(int period) {
		this.period = period;
	}

	// 개월수 계산하는 메소드
	public int AddTimeDeposit(int money, int period) {

		total = (int) (money * Math.pow(1 + (interest / 12), period));

		return total;
	}

}