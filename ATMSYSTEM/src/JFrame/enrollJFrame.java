package JFrame;

import Class.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.security.KeyStore.Entry;
import java.util.HashMap;
import java.awt.event.ActionEvent;

public class enrollJFrame extends JFrame {
	private DataBase DB;
	private String SelectAccountName; // 계좌종류 이름 변수
	private Account account; // 계좌 변수
	private JPanel contentPane;
	private JTextField textField;
	private JTextField passwordField;
	private ButtonGroup radioGroup;

	public enrollJFrame() {
		super("enroll page");
		DB = new DataBase(); // 고객 데이터베이스 생성
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 410, 437);
		setLocationRelativeTo(null);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 384, 76);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("회원 가입");
		lblNewLabel.setBounds(138, 24, 107, 28);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 24));
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(5, 101, 384, 287);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel_1.setBounds(66, 60, 43, 15);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("PassWord");
		lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel_2.setBounds(37, 95, 83, 21);
		panel_1.add(lblNewLabel_2);

		textField = new JTextField();
		textField.setBounds(121, 56, 203, 21);
		panel_1.add(textField);
		textField.setColumns(20);

		passwordField = new JTextField();
		passwordField.setBounds(121, 95, 203, 21);
		panel_1.add(passwordField);
		passwordField.setColumns(20);

		JButton btnNewButton = new JButton("회원 등록");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String Id = textField.getText().replace(" ", "");
				String password = passwordField.getText().replace(" ", "");
				// 아이디 및 패스워드 미입력시 회원등록 거부
				// 아이디는 특수문자 사용 x
				// 아이디의 길이는 최소길이 3이상 비밀번호 길이는 최소 8자이상

				// 데이터베이스에 아이디가 없는 경우
				if (SelectAccountName == null) {

					JOptionPane.showMessageDialog(null, "계좌 유형을 선택해주세요.");
				} else {
					if (DataBase.getUserList().get(Id) == null) {
						if (Id.equals("") && password.equals("")) {
							JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 입력하지 않았습니다.", "회원가입 error",
									JOptionPane.WARNING_MESSAGE);
						} else if (Id.equals("") || Id.length() < 3) {
							JOptionPane.showMessageDialog(null, "아이디를 입력하지 않았습니다.", "회원가입 error",
									JOptionPane.WARNING_MESSAGE);
						} else if (password.equals("")) {
							JOptionPane.showMessageDialog(null, "비밀번호를 입력하지 않았습니다.", "회원가입 error",
									JOptionPane.WARNING_MESSAGE);
						} else if (password.length() < 8) {
							JOptionPane.showMessageDialog(null, "비밀번호의 길이를 최소 8자이상 입력해주세요.", "회원가입 error",
									JOptionPane.WARNING_MESSAGE);
						} else if (!Id.matches("[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힝]*")) {
							JOptionPane.showMessageDialog(null, "아이디에서 특수문자를 제거해주세요.", "회원가입 error",
									JOptionPane.WARNING_MESSAGE);
						} else {

							DB.SetUserList(Id, new User(Id, password)); // 고객정보를 데이터베이스에 저장
							User user = DataBase.getUserList().get(Id); // 데이터베이스에서 고객 가져오기
							user.setAccount(SelectAccountName, account); // 고객에 계좌 등록

							if (SelectAccountName.equals("SavingsAccount")) {
								JOptionPane.showMessageDialog(null, Id + "회원님 \n" + "입출금 계좌" + " 개설 완료\n");
							} else {
								JOptionPane.showMessageDialog(null, Id + "회원님 \n" + "정기예금 계좌" + " 개설 완료\n");
							}

							setVisible(false);
							new ATMJFrame();

						}

						// 데이터베이스에 아이디가 있는 경우
					} else if (DataBase.getUserList().get(Id) != null) {

						// 아이디도 있고 해당 계좌도 있는 경우
						if (DataBase.getUserList().get(Id).getAccountList().get(SelectAccountName) != null) {

							JOptionPane.showMessageDialog(null, "아이디가 중복되었습니다.", "회원가입 error",
									JOptionPane.WARNING_MESSAGE);
						} else {

							User user = DataBase.getUserList().get(Id); // 데이터베이스에서 고객 가져오기
							user.setAccount(SelectAccountName, account); // 고객에 계좌 등록

							if (SelectAccountName.equals("SavingsAccount")) {
								JOptionPane.showMessageDialog(null, Id + "회원님 \n" + "입출금 계좌" + " 개설 완료\n");
							} else {
								JOptionPane.showMessageDialog(null, Id + "회원님 \n" + "정기예금 계좌" + " 개설 완료\n");
							}

							setVisible(false);
							new ATMJFrame();

						}
					}
				}

			}
		});
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton.setBounds(55, 151, 270, 45);
		panel_1.add(btnNewButton);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("입출금 계좌");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 입출금 계좌 선택
				SelectAccountName = "SavingsAccount";
				account = new SavingsAccount();
			}
		});

		rdbtnNewRadioButton.setBounds(66, 6, 121, 23);
		panel_1.add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("정기 예금 계좌");
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 정기 예금 계좌 선택
				SelectAccountName = "TimeDeposit";
				account = new TimeDeposit();
			}
		});
		rdbtnNewRadioButton_1.setBounds(211, 6, 121, 23);
		panel_1.add(rdbtnNewRadioButton_1);

		// create logical relationship between JRadioButtons
		radioGroup = new ButtonGroup();
		radioGroup.add(rdbtnNewRadioButton);
		radioGroup.add(rdbtnNewRadioButton_1);

		JButton btnNewButton_1 = new JButton("뒤로 가기");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 뒤로 가기 기능구현
				setVisible(false);
				new ATMJFrame();
			}
		});

		btnNewButton_1.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton_1.setBounds(55, 218, 269, 39);
		panel_1.add(btnNewButton_1);
	}
}
