package Class;

import java.util.*;


public class DataBase {
	private static HashMap<String, User> userList = new HashMap<>();// 고객을 저장하는 해쉬맵

	private static ArrayList<ArrayList<Object>> tuples = new ArrayList<ArrayList<Object>>(); // Table을 이용하여 ATM의 모든
																								// 트랜잭션을 기록

	// 고객을 반환하는 메소드
	public static HashMap<String, User> getUserList() {
		return userList;
	}

	// 고객을 저장하는 메소드
	public void SetUserList(String name, User user) {
		userList.put(name, user);
	}

	public static ArrayList<ArrayList<Object>> getTuples() {
		return tuples;
	}

	public void setTuples(ArrayList<Object> content) {
		tuples.add(content); // 행 단위로 테이블에 저장
	}

}
