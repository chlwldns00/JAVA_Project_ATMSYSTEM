package JFrame;

import Class.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument.Content;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class UserJFrame extends JFrame {
	private DataBase DB;
	private ArrayList<Object> content;
	private HashMap<String, Integer> AtmMoney;
	private Transaction tr; // 트랜잭션
	private User user; // 로그인 성공시 넘겨받은 고객
	private String payment;
	private int paymentNumber;
	private int DepositMoney = 0;
	private int WithdrawMoney;
	private JPanel contentPane;
	private JTextField textField;
	private ButtonGroup radioGroup;

	public UserJFrame(User user, Transaction tr) {
		super("User page");
		this.user = user;
		this.tr = tr;
		DB = new DataBase();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 587);
		setLocationRelativeTo(null);
		setVisible(true);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("뒤로 가기");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ATMJFrame();
			}
		});

		btnNewButton.setFont(new Font("굴림", Font.BOLD, 15));
		btnNewButton.setBounds(5, 520, 484, 23);
		contentPane.add(btnNewButton);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 494, 184);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("로그인 정보 :");
		lblNewLabel.setBounds(146, 10, 100, 18);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(user.getId()); // 고객의 아이디
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(254, 12, 164, 15);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("   계좌 정보 :");
		lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(146, 38, 96, 28);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("입출금 계좌"); // 고객의 계좌정보

		lblNewLabel_3.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(254, 37, 164, 31);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("현재 계좌 잔액 : ");
		lblNewLabel_4.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(128, 73, 114, 31);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel(String.valueOf(user.getAccount().getBalance())); // 고객의 잔고조회
		lblNewLabel_5.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(254, 78, 78, 23);
		panel.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("  금액 입력 : ");
		lblNewLabel_6.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel_6.setBounds(106, 132, 95, 28);
		panel.add(lblNewLabel_6);

		// 사용자가 입력하는 텍스트 컴포넌트
		textField = new JTextField();
		textField.setBounds(218, 133, 164, 28);
		panel.add(textField);
		textField.setColumns(15);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 194, 494, 316);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		// 5만원 + 1만원권 radiobutton
		JRadioButton jradiobutton1 = new JRadioButton("5만원 + 1만원권");
		jradiobutton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (jradiobutton1.isSelected()) {
					payment = "5만원";
					paymentNumber = 50000;
					jradiobutton1.setSelected(true);
				}
			}
		});
		jradiobutton1.setFont(new Font("굴림", Font.BOLD, 20));
		jradiobutton1.setBounds(122 - 30, 59, 115 + 100, 23);
		panel_1.add(jradiobutton1);

		// 1만원권 radiobutton
		JRadioButton jradiobutton2 = new JRadioButton("1만원권");
		jradiobutton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (jradiobutton2.isSelected()) {
					payment = "1만원";
					paymentNumber = 10000;
					jradiobutton2.setSelected(true);

				}
			}
		});
		jradiobutton2.setFont(new Font("굴림", Font.BOLD, 20));
		jradiobutton2.setBounds(276 + 30, 59, 115, 23);
		panel_1.add(jradiobutton2);

		radioGroup = new ButtonGroup();
		radioGroup.add(jradiobutton1);
		radioGroup.add(jradiobutton2);

		// 입금
		JButton btnNewButton_1 = new JButton("입금");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (textField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "입금할 금액을 입력해주세요.");
				} else {
					// 입금 이벤트
					DepositMoney = Integer.parseInt(textField.getText()); // 사용자가 입력한 금액
					textField.setText("");
					tr.Deposit(user.getAccount(), DepositMoney); // 트랜잭션에서 입금 메소드 실행
					JOptionPane.showMessageDialog(null, "금액 :" + DepositMoney + "입금 완료");
					// 고객의 입금 내역
					content = new ArrayList<Object>();
					content.add("입금");
					content.add(user.getId());
					content.add(user.getPassword());
					content.add(user.getAccountName());
					content.add(user.getAccount().getBalance());
					content.add(DepositMoney);
					content.add(0);
					DB.setTuples(content);
				}
				lblNewLabel_5.setText(String.valueOf(user.getAccount().getBalance()));
			}
		});
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton_1.setBounds(72, 10, 343, 43);
		panel_1.add(btnNewButton_1);

		// 출금
		JButton btnNewButton_2 = new JButton("출금");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String moneyString = textField.getText();
				try {
					WithdrawMoney = Integer.parseInt(moneyString);
				} catch (NumberFormatException numberFormatException) {
				}

				if (paymentNumber == 0) {
					JOptionPane.showMessageDialog(null, "현금 유형을 선택해주세요");
				} else {
					if (moneyString.equals("")) {
						JOptionPane.showMessageDialog(null, "출금할 금액을 입력해주세요.");

					} else if (user.getAccount().getBalance() >= WithdrawMoney) {

						textField.setText("");
						int count = (WithdrawMoney / paymentNumber);
						int rest = (WithdrawMoney % paymentNumber) / 10000;

						if (rest == 0) {
							AtmMoney = tr.Withdrawal(user.getAccount(), WithdrawMoney, payment);
							JOptionPane.showMessageDialog(null,
									payment + "권  " + count + "장  출금완료");
						} else {
							AtmMoney = tr.Withdrawal(user.getAccount(), count * 50000, "5만원");
							AtmMoney = tr.Withdrawal(user.getAccount(), rest * 10000, "1만원");
							JOptionPane.showMessageDialog(null,
									"5만원권  " + count + "장  1만원권 : " + rest + "장 출금완료");
						}

						// 고객의 출금 내역 저장
						content = new ArrayList<Object>();
						content.add("출금");
						content.add(user.getId());
						content.add(user.getPassword());
						content.add(user.getAccountName());
						content.add(user.getAccount().getBalance());
						content.add(0);
						content.add(WithdrawMoney);
						DB.setTuples(content);

						jradiobutton1.setSelected(false);
						jradiobutton2.setSelected(false);
					} else {
						JOptionPane.showMessageDialog(btnNewButton, "계좌의 잔고가 부족하여 출금할 수 없습니다.");
					}
				}

				lblNewLabel_5.setText(String.valueOf(user.getAccount().getBalance()));
			}

		});
		btnNewButton_2.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton_2.setBounds(72, 104, 343, 43);
		panel_1.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("잔고 조회");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 잔고 조회 이벤트
				int leftMoney = tr.CheckBalance(user.getAccount());
				JOptionPane.showMessageDialog(null, "현재 잔고 금액 : " + leftMoney + " 입니다.");

				// 데이터베이스에 저장
				content = new ArrayList<Object>();
				content.add("잔고조회");
				content.add(user.getId());
				content.add(user.getPassword());
				content.add(user.getAccountName());
				content.add(user.getAccount().getBalance());
				content.add(0);
				content.add(0);
				DB.setTuples(content);
			}
		});
		btnNewButton_3.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton_3.setBounds(72, 178, 343, 43);
		panel_1.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("트랙잭션 조회");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 트랜잭션 조회 버튼
				new TransactionFrame();
			}
		});
		
		btnNewButton_4.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton_4.setBounds(72, 252, 343, 43);
		panel_1.add(btnNewButton_4);

	}
}
