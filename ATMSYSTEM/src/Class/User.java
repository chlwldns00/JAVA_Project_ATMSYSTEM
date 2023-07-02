package Class;

import java.util.*;

public class User {
	private String Id; // 고객의 아이디
	private String PassWord; // 고객의 비밀번호
	private Account account; // 클래스 자료형으로 계좌 연결
	private String AccountName; // 계좌종류 이름 설정
	private HashMap<String, Account> AccountList; // 고객이 여러 계좌를 생성 및 저장하기 위해 HashMap를 정의하였다.

	public User(String Id, String PassWord) {
		AccountList = new HashMap<>();
		this.Id = Id;
		this.PassWord = PassWord;
	}

	// 사용자의 계좌를 등록하는 메소드이다.
	public void setAccount(String AccountName, Account account) {
		this.AccountName = AccountName;
		this.account = account;
		AccountList.put(AccountName, account);
	}
	//수정
	public void setAcountName(String AccountName) {
		this.AccountName = AccountName;
	}
	
	// 고객의 계좌를 반환하는 메소드이다.
	public HashMap<String, Account> getAccountList() {
		return AccountList;

	}

	public String getId() {
		return Id;
	}

	public String getPassword() {
		return PassWord;
	}

	public String getAccountName() {
		return AccountName;
	}

	public Account getAccount() {
		return account;
	}

}