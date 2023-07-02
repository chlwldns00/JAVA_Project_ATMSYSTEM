package JFrame;

import Class.*;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;

public class ATMJFrame extends JFrame {
	private ATMSystem ATM; // ATM 객체 선언
	private Admin admin; // ATM 관리자 선언
	private Transaction tr; // 트랜잭션 선언
	private HashMap<String, User> userList = DataBase.getUserList();
	private String SelectAccount; // 계좌종류 선택
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private ButtonGroup radioGroup;

	// ATM 실행
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ATMJFrame frame = new ATMJFrame();
					frame.setLocationRelativeTo(null);
					// frame.setLocation(750,250);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public ATMJFrame() {
		super("ATM SYSTEM");

		ATM = new ATMSystem(); // ATM 객체 생성
		tr = new Transaction(); // 트랜잭션 생성
		tr.setAtm(ATM); // ATM 트랜잭션 설정
		admin = new Admin(); // 관리자 생성
		admin.setAtm(ATM); // ATM의 관리자로 임명
		ATM.FillMoney(admin); // 관리자가 1만원권과 5만원권을 충전한다.

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 514, 575);
		setLocationRelativeTo(null);
		setVisible(true);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 5, 498, 113);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("( 10 조)");
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_1.setBounds(205, 79, 91, 24);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("Welcome to ATM System");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 24));
		lblNewLabel.setBounds(94, 24, 325, 48);
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 128, 498, 55);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("입출금 계좌");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 입출금 계좌 선택
				SelectAccount = "SavingsAccount";

			}
		});
		rdbtnNewRadioButton.setBounds(102, 26, 121, 23);
		panel_1.add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("정기 예금 계좌");
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 정기 계좌 선택
				SelectAccount = "TimeDeposit";
			}
		});
		rdbtnNewRadioButton_1.setBounds(277, 26, 121, 23);
		panel_1.add(rdbtnNewRadioButton_1);

		// create logical relationship between JRadioButtons
		radioGroup = new ButtonGroup();
		radioGroup.add(rdbtnNewRadioButton);
		radioGroup.add(rdbtnNewRadioButton_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 198, 498, 328);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("ID");
		lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel_2.setBounds(105, 38, 40, 25);
		panel_2.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("PassWord");
		lblNewLabel_3.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel_3.setBounds(72, 75, 87, 29);
		panel_2.add(lblNewLabel_3);

		textField = new JTextField();
		textField.setBounds(160, 34, 264, 28);
		panel_2.add(textField);
		textField.setColumns(30);

		textField_1 = new JPasswordField();
		textField_1.setBounds(160, 75, 264, 28);
		panel_2.add(textField_1);
		textField_1.setColumns(30);

		JButton btnNewButton = new JButton("로그인");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// 사용자가 입력한 아이디와 비밀번호를 가져온다 .
				String field1 = textField.getText().replace(" ", "");
				String field2 = textField_1.getText().replace(" ", "");

				// 계좌종류 확인후 매칭 및 DB에 존재하는 고객의 아이디와 비밀번호를 매칭

				User user = userList.get(field1); // 입력된 고객의 아이디를 키로서 DB내에서 일치하는 고객의 객체를 가져온다.

				// 관리자 로그인 아이디 root 비밀번호 root
				if ("root".equals(field1) && "root".equals(field2)) {
					JOptionPane.showMessageDialog(null, "관리자 로그인 성공");
					new AdminJFrame(admin); // 관리자 패널로 이동
					setVisible(false);
				} else if (user == null) { // 아이디(키)에 일치하는 객체가 없으면 로그인 실패
					JOptionPane.showMessageDialog(null, "로그인 실패");
				} else if (user.getId().equals(field1) && user.getPassword().equals(field2)
						&& user.getAccountList().get(SelectAccount) != null) {
					JOptionPane.showMessageDialog(null, "로그인 성공");

					if (SelectAccount.equals("SavingsAccount")) {
						// 입출금 계좌일떄
						user.setAcountName(SelectAccount);
						new UserJFrame(user, tr); // 로그인 패널로 이동시 로그인 성공한 고객을 전달
						setVisible(false);
					} else if (SelectAccount.equals("TimeDeposit")) {
						// 정기 예금 계좌일떄
						if (user.getAccountList().get("TimeDeposit").getBalance() == 0) {
							user.setAcountName(SelectAccount);
							new TimeDepositFrame(user, tr);
							setVisible(false);
						} else {
							JOptionPane.showMessageDialog(null, "계좌가 존재합니다.\n 현재 고객님의 정기 계좌에는 "
									+ user.getAccountList().get("TimeDeposit").getBalance() + "원이 있습니다.");

						}

					}

					// setVisible(false);

				} else {
					JOptionPane.showMessageDialog(null, "로그인 실패");
				}

			}
		});

		btnNewButton.setBounds(329, 123, 97, 41);
		panel_2.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("회원 등록");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new enrollJFrame();
			}
		});
		btnNewButton_1.setBounds(68, 228, 357, 38);
		panel_2.add(btnNewButton_1);

	}
}
