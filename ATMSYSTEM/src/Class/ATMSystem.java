package Class;

import java.util.*;

public class ATMSystem {

	// Ŭ������ �ʵ带 HashMap���� �����Ͽ� key������ ����, 5���� ���� �����Ͽ� value���� �����Ѵ�.
	private HashMap<String, Integer> Money = new HashMap<>();

	public HashMap<String, Integer> getMoney() {
		return this.Money;
	}

	public void setMoney(String cashForm, int count) {
		this.Money.put(cashForm, count);
	}

	// �����, ���⿹�� ���� ���� �ϳ��� ������ �� �ִ� �޼ҵ��̴�.
	private Account SelectAccount(String AccountName, User user) {
		Account account = user.getAccountList().get(AccountName);
		return account;
	}

	// ATM�����ڰ� ���� ��ħ ���������� ���� ATM�� ������ 1000��, 5������ 200�� �� �������ִ� �޼ҵ��̴�.
	public void FillMoney(Admin admin) {
		admin.FillMoney();
	}

}