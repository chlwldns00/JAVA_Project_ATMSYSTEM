package Class;

public class Account {

	protected int Balance = 0; // ������ �ܰ� �����ϴ� Ŭ���� �ʵ��̴�.
	private User owner; // Ŭ���� �ڷ��� ���� ����

	// ���� ������ �ܰ� ��ȯ�ϴ� �޼ҵ��̴�.
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