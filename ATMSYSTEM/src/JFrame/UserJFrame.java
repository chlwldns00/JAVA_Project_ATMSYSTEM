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
	private Transaction tr; // Ʈ�����
	private User user; // �α��� ������ �Ѱܹ��� ��
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

		JButton btnNewButton = new JButton("�ڷ� ����");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ATMJFrame();
			}
		});

		btnNewButton.setFont(new Font("����", Font.BOLD, 15));
		btnNewButton.setBounds(5, 520, 484, 23);
		contentPane.add(btnNewButton);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 494, 184);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("�α��� ���� :");
		lblNewLabel.setBounds(146, 10, 100, 18);
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 15));
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(user.getId()); // ���� ���̵�
		lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(254, 12, 164, 15);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("   ���� ���� :");
		lblNewLabel_2.setFont(new Font("����", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(146, 38, 96, 28);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("����� ����"); // ���� ��������

		lblNewLabel_3.setFont(new Font("����", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(254, 37, 164, 31);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("���� ���� �ܾ� : ");
		lblNewLabel_4.setFont(new Font("����", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(128, 73, 114, 31);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel(String.valueOf(user.getAccount().getBalance())); // ���� �ܰ���ȸ
		lblNewLabel_5.setFont(new Font("����", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(254, 78, 78, 23);
		panel.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("  �ݾ� �Է� : ");
		lblNewLabel_6.setFont(new Font("����", Font.BOLD, 15));
		lblNewLabel_6.setBounds(106, 132, 95, 28);
		panel.add(lblNewLabel_6);

		// ����ڰ� �Է��ϴ� �ؽ�Ʈ ������Ʈ
		textField = new JTextField();
		textField.setBounds(218, 133, 164, 28);
		panel.add(textField);
		textField.setColumns(15);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 194, 494, 316);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		// 5���� + 1������ radiobutton
		JRadioButton jradiobutton1 = new JRadioButton("5���� + 1������");
		jradiobutton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (jradiobutton1.isSelected()) {
					payment = "5����";
					paymentNumber = 50000;
					jradiobutton1.setSelected(true);
				}
			}
		});
		jradiobutton1.setFont(new Font("����", Font.BOLD, 20));
		jradiobutton1.setBounds(122 - 30, 59, 115 + 100, 23);
		panel_1.add(jradiobutton1);

		// 1������ radiobutton
		JRadioButton jradiobutton2 = new JRadioButton("1������");
		jradiobutton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (jradiobutton2.isSelected()) {
					payment = "1����";
					paymentNumber = 10000;
					jradiobutton2.setSelected(true);

				}
			}
		});
		jradiobutton2.setFont(new Font("����", Font.BOLD, 20));
		jradiobutton2.setBounds(276 + 30, 59, 115, 23);
		panel_1.add(jradiobutton2);

		radioGroup = new ButtonGroup();
		radioGroup.add(jradiobutton1);
		radioGroup.add(jradiobutton2);

		// �Ա�
		JButton btnNewButton_1 = new JButton("�Ա�");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (textField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "�Ա��� �ݾ��� �Է����ּ���.");
				} else {
					// �Ա� �̺�Ʈ
					DepositMoney = Integer.parseInt(textField.getText()); // ����ڰ� �Է��� �ݾ�
					textField.setText("");
					tr.Deposit(user.getAccount(), DepositMoney); // Ʈ����ǿ��� �Ա� �޼ҵ� ����
					JOptionPane.showMessageDialog(null, "�ݾ� :" + DepositMoney + "�Ա� �Ϸ�");
					// ���� �Ա� ����
					content = new ArrayList<Object>();
					content.add("�Ա�");
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
		btnNewButton_1.setFont(new Font("����", Font.BOLD, 20));
		btnNewButton_1.setBounds(72, 10, 343, 43);
		panel_1.add(btnNewButton_1);

		// ���
		JButton btnNewButton_2 = new JButton("���");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String moneyString = textField.getText();
				try {
					WithdrawMoney = Integer.parseInt(moneyString);
				} catch (NumberFormatException numberFormatException) {
				}

				if (paymentNumber == 0) {
					JOptionPane.showMessageDialog(null, "���� ������ �������ּ���");
				} else {
					if (moneyString.equals("")) {
						JOptionPane.showMessageDialog(null, "����� �ݾ��� �Է����ּ���.");

					} else if (user.getAccount().getBalance() >= WithdrawMoney) {

						textField.setText("");
						int count = (WithdrawMoney / paymentNumber);
						int rest = (WithdrawMoney % paymentNumber) / 10000;

						if (rest == 0) {
							AtmMoney = tr.Withdrawal(user.getAccount(), WithdrawMoney, payment);
							JOptionPane.showMessageDialog(null,
									payment + "��  " + count + "��  ��ݿϷ�");
						} else {
							AtmMoney = tr.Withdrawal(user.getAccount(), count * 50000, "5����");
							AtmMoney = tr.Withdrawal(user.getAccount(), rest * 10000, "1����");
							JOptionPane.showMessageDialog(null,
									"5������  " + count + "��  1������ : " + rest + "�� ��ݿϷ�");
						}

						// ���� ��� ���� ����
						content = new ArrayList<Object>();
						content.add("���");
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
						JOptionPane.showMessageDialog(btnNewButton, "������ �ܰ� �����Ͽ� ����� �� �����ϴ�.");
					}
				}

				lblNewLabel_5.setText(String.valueOf(user.getAccount().getBalance()));
			}

		});
		btnNewButton_2.setFont(new Font("����", Font.BOLD, 20));
		btnNewButton_2.setBounds(72, 104, 343, 43);
		panel_1.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("�ܰ� ��ȸ");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �ܰ� ��ȸ �̺�Ʈ
				int leftMoney = tr.CheckBalance(user.getAccount());
				JOptionPane.showMessageDialog(null, "���� �ܰ� �ݾ� : " + leftMoney + " �Դϴ�.");

				// �����ͺ��̽��� ����
				content = new ArrayList<Object>();
				content.add("�ܰ���ȸ");
				content.add(user.getId());
				content.add(user.getPassword());
				content.add(user.getAccountName());
				content.add(user.getAccount().getBalance());
				content.add(0);
				content.add(0);
				DB.setTuples(content);
			}
		});
		btnNewButton_3.setFont(new Font("����", Font.BOLD, 20));
		btnNewButton_3.setBounds(72, 178, 343, 43);
		panel_1.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("Ʈ����� ��ȸ");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Ʈ����� ��ȸ ��ư
				new TransactionFrame();
			}
		});
		
		btnNewButton_4.setFont(new Font("����", Font.BOLD, 20));
		btnNewButton_4.setBounds(72, 252, 343, 43);
		panel_1.add(btnNewButton_4);

	}
}
