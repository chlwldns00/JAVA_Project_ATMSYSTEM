package Class;

import java.util.*;


public class DataBase {
	private static HashMap<String, User> userList = new HashMap<>();// ���� �����ϴ� �ؽ���

	private static ArrayList<ArrayList<Object>> tuples = new ArrayList<ArrayList<Object>>(); // Table�� �̿��Ͽ� ATM�� ���
																								// Ʈ������� ���

	// ���� ��ȯ�ϴ� �޼ҵ�
	public static HashMap<String, User> getUserList() {
		return userList;
	}

	// ���� �����ϴ� �޼ҵ�
	public void SetUserList(String name, User user) {
		userList.put(name, user);
	}

	public static ArrayList<ArrayList<Object>> getTuples() {
		return tuples;
	}

	public void setTuples(ArrayList<Object> content) {
		tuples.add(content); // �� ������ ���̺� ����
	}

}
