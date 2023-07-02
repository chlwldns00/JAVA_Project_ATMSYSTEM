package Class;

import java.util.*;

public class User {
	private String Id; // ���� ���̵�
	private String PassWord; // ���� ��й�ȣ
	private Account account; // Ŭ���� �ڷ������� ���� ����
	private String AccountName; // �������� �̸� ����
	private HashMap<String, Account> AccountList; // ���� ���� ���¸� ���� �� �����ϱ� ���� HashMap�� �����Ͽ���.

	public User(String Id, String PassWord) {
		AccountList = new HashMap<>();
		this.Id = Id;
		this.PassWord = PassWord;
	}

	// ������� ���¸� ����ϴ� �޼ҵ��̴�.
	public void setAccount(String AccountName, Account account) {
		this.AccountName = AccountName;
		this.account = account;
		AccountList.put(AccountName, account);
	}
	//����
	public void setAcountName(String AccountName) {
		this.AccountName = AccountName;
	}
	
	// ���� ���¸� ��ȯ�ϴ� �޼ҵ��̴�.
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