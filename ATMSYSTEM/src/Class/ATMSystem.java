package Class;

import java.util.*;

public class ATMSystem {

	// 클래스의 필드를 HashMap으로 정의하여 key값으로 만원, 5만원 지폐를 구분하여 value값을 저장한다.
	private HashMap<String, Integer> Money = new HashMap<>();

	public HashMap<String, Integer> getMoney() {
		return this.Money;
	}

	public void setMoney(String cashForm, int count) {
		this.Money.put(cashForm, count);
	}

	// 입출금, 정기예금 계좌 둘중 하나를 선택할 수 있는 메소드이다.
	private Account SelectAccount(String AccountName, User user) {
		Account account = user.getAccountList().get(AccountName);
		return account;
	}

	// ATM관리자가 매일 아침 업무시작을 위해 ATM에 만원권 1000장, 5만원권 200장 을 충전해주는 메소드이다.
	public void FillMoney(Admin admin) {
		admin.FillMoney();
	}

}