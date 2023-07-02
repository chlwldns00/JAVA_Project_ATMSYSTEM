package Class;

import java.util.*;
//계좌의 트랜잭션(입금, 출금, 잔고조회)를 할 수 있는 클래스이다.
public class Transaction {
	
	private ATMSystem atm;

	public void setAtm(ATMSystem atm) {
		this.atm = atm;
	}

	// 선택된 고객의 계좌에 사용자가 입력한 금액을 입금하는 메소드이다.
	public void Deposit(Account account, int money) {
		// 입력한 금액이 0보다 작은떄
		if (money < 0) {
			return;
		} else {
			account.setBalance(account.getBalance() + money);
		}
	}

	// 선택된 고객의 계좌에 사용자가 입력한 금액을 출금하는 메소드이다.
	public HashMap<String, Integer> Withdrawal(Account account, int money, String cashForm) {

		if (money < 0 || money > account.getBalance()) {
			System.out.println("Debit amount exceeded account balance");

		} else {
			if (cashForm.equals("1만원")) {
				int count = (money / 10000);
				int AtmCount = atm.getMoney().get("1만원");
				atm.setMoney(cashForm, AtmCount - count);
				account.setBalance(account.getBalance() - count * 10000);
				return atm.getMoney();

			} else if (cashForm.equals("5만원")) {
				int count = (money / 50000);
				int AtmCount = atm.getMoney().get("5만원");
				atm.setMoney(cashForm, AtmCount - count);

				account.setBalance(account.getBalance() - count * 50000);
				return atm.getMoney();
			}

		}
		return null;
	}

	// 고객의 계좌 잔고를 출력해주는 메소드이다.
	public int CheckBalance(Account account) {
		return account.getBalance();
	}

}