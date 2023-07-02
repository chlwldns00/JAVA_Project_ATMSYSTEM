package Class;

import java.util.*;
//������ Ʈ�����(�Ա�, ���, �ܰ���ȸ)�� �� �� �ִ� Ŭ�����̴�.
public class Transaction {
	
	private ATMSystem atm;

	public void setAtm(ATMSystem atm) {
		this.atm = atm;
	}

	// ���õ� ������ ���¿� ����ڰ� �Է��� �ݾ��� �Ա��ϴ� �޼ҵ��̴�.
	public void Deposit(Account account, int money) {
		// �Է��� �ݾ��� 0���� ������
		if (money < 0) {
			return;
		} else {
			account.setBalance(account.getBalance() + money);
		}
	}

	// ���õ� ������ ���¿� ����ڰ� �Է��� �ݾ��� ����ϴ� �޼ҵ��̴�.
	public HashMap<String, Integer> Withdrawal(Account account, int money, String cashForm) {

		if (money < 0 || money > account.getBalance()) {
			System.out.println("Debit amount exceeded account balance");

		} else {
			if (cashForm.equals("1����")) {
				int count = (money / 10000);
				int AtmCount = atm.getMoney().get("1����");
				atm.setMoney(cashForm, AtmCount - count);
				account.setBalance(account.getBalance() - count * 10000);
				return atm.getMoney();

			} else if (cashForm.equals("5����")) {
				int count = (money / 50000);
				int AtmCount = atm.getMoney().get("5����");
				atm.setMoney(cashForm, AtmCount - count);

				account.setBalance(account.getBalance() - count * 50000);
				return atm.getMoney();
			}

		}
		return null;
	}

	// ������ ���� �ܰ��� ������ִ� �޼ҵ��̴�.
	public int CheckBalance(Account account) {
		return account.getBalance();
	}

}