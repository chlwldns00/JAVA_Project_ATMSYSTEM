package Class;

// ���� ���� ���� 
public class TimeDeposit extends Account {
	private final double interest = 0.05; // ���� ���� 5%
	private int period; // ����ڰ� �Ⱓ�� ����.
	private int total;

	// �Ⱓ�� �����ϴ� �޼ҵ�
	public void setPeriod(int period) {
		this.period = period;
	}

	// ������ ����ϴ� �޼ҵ�
	public int AddTimeDeposit(int money, int period) {

		total = (int) (money * Math.pow(1 + (interest / 12), period));

		return total;
	}

}