package JFrame;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Class.*;

public class TimeDepositFrame extends JFrame {
	private DataBase DB;
	private ArrayList<Object> content;
	private Transaction tr; // 트랜잭션
	private User user; // 로그인 성공시 넘겨받은 고객
	private int DepositMoney; // 납입 금액
	private int month; // 사용자가 입력한 개월수
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	public TimeDepositFrame(User user, Transaction tr) {
		super("TimeDeposit Frame");
		this.user = user; // 고객을 받아옴 .
		this.tr = tr;
		DB = new DataBase(); // 데이터베이스 생성
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 587);
		setLocationRelativeTo(null);
		setVisible(true);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 뒤로가기 버튼
		JButton btnNewButton = new JButton("뒤로가기");
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
		panel.setBounds(0, 0, 494, 193);
		contentPane.add(panel);
		panel.setLayout(null);

		// 로그인 정보
		JLabel lblNewLabel = new JLabel("로그인 정보 : ");
		lblNewLabel.setBounds(140, 49, 96, 18);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		panel.add(lblNewLabel);

		// 고객의 아이디
		JLabel lblNewLabel_1 = new JLabel(user.getId());
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(254, 51, 164, 15);
		panel.add(lblNewLabel_1);

		// 개인 정보
		JLabel lblNewLabel_2 = new JLabel("개인 정보 :");
		lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(153, 95, 96, 28);
		panel.add(lblNewLabel_2);

		// 고객 계좌 정보
		JLabel lblNewLabel_3 = new JLabel("정기 예금 계좌"); // 고객의 계좌정보
		lblNewLabel_3.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(254, 94, 164, 31);
		panel.add(lblNewLabel_3);

		// 월 납입 금액 Text
		textField = new JTextField();
		textField.setBounds(218, 155, 227, 28);
		panel.add(textField);
		textField.setColumns(30);

		JLabel lblNewLabel_21 = new JLabel("납입 금액");
		lblNewLabel_21.setBounds(95, 155, 74, 27);
		panel.add(lblNewLabel_21);
		lblNewLabel_21.setFont(new Font("굴림", Font.BOLD, 15));

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 198, 498, 311);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_31 = new JLabel("납입 개월");
		lblNewLabel_31.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel_31.setBounds(94, 10, 76, 28);
		panel_2.add(lblNewLabel_31);

		// 납입 개월 Text
		textField_1 = new JTextField();
		textField_1.setBounds(219, 11, 227, 28);
		panel_2.add(textField_1);
		textField_1.setColumns(30);

		// 입금 버튼
		JButton btnNewButton1 = new JButton("입금하기");
		btnNewButton1.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TimeDeposit account = (TimeDeposit) user.getAccount();

				if (textField.getText().equals("") || textField_1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "텍스트를 입력해주세요.");
				} else { // 입금 이벤트
					DepositMoney = Integer.parseInt(textField.getText()); // 입금 금액
					month = Integer.parseInt(textField_1.getText()); // 사용자가 입력한 개월수
					textField.setText("");
					textField_1.setText("");

					tr.Deposit(account, account.AddTimeDeposit(DepositMoney, month)); // 트랜잭션에서 입금 메소드 실행

					JOptionPane.showMessageDialog(null, "금액 :" + DepositMoney + "입금 완료");

				}

			}
		});

		btnNewButton1.setBounds(64, 134, 378, 41);
		panel_2.add(btnNewButton1);

		JButton btnNewButton_2 = new JButton("잔고 조회");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 잔고 조회
				JOptionPane.showMessageDialog(null,
						month + "개월동안 총 계좌 잔액은\n" + user.getAccountList().get("TimeDeposit").getBalance() + "원 입니다.");
			}
		});
		btnNewButton_2.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton_2.setBounds(64, 212, 377, 44);
		panel_2.add(btnNewButton_2);

		JLabel lblNewLabel_4 = new JLabel("     (이자율 : 5%)");
		lblNewLabel_4.setFont(new Font("굴림", Font.BOLD, 12));
		lblNewLabel_4.setBounds(70, 48, 128, 15);
		panel_2.add(lblNewLabel_4);
	}
}
