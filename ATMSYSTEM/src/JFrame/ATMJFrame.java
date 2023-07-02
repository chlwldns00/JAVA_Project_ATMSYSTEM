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
	private ATMSystem ATM; // ATM ��ü ����
	private Admin admin; // ATM ������ ����
	private Transaction tr; // Ʈ����� ����
	private HashMap<String, User> userList = DataBase.getUserList();
	private String SelectAccount; // �������� ����
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private ButtonGroup radioGroup;

	// ATM ����
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

		ATM = new ATMSystem(); // ATM ��ü ����
		tr = new Transaction(); // Ʈ����� ����
		tr.setAtm(ATM); // ATM Ʈ����� ����
		admin = new Admin(); // ������ ����
		admin.setAtm(ATM); // ATM�� �����ڷ� �Ӹ�
		ATM.FillMoney(admin); // �����ڰ� 1�����ǰ� 5�������� �����Ѵ�.

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

		JLabel lblNewLabel_1 = new JLabel("( 10 ��)");
		lblNewLabel_1.setFont(new Font("����", Font.BOLD, 20));
		lblNewLabel_1.setBounds(205, 79, 91, 24);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("Welcome to ATM System");
		lblNewLabel.setFont(new Font("����", Font.BOLD, 24));
		lblNewLabel.setBounds(94, 24, 325, 48);
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 128, 498, 55);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("����� ����");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ����� ���� ����
				SelectAccount = "SavingsAccount";

			}
		});
		rdbtnNewRadioButton.setBounds(102, 26, 121, 23);
		panel_1.add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("���� ���� ����");
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ���� ���� ����
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
		lblNewLabel_2.setFont(new Font("����", Font.BOLD, 15));
		lblNewLabel_2.setBounds(105, 38, 40, 25);
		panel_2.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("PassWord");
		lblNewLabel_3.setFont(new Font("����", Font.BOLD, 15));
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

		JButton btnNewButton = new JButton("�α���");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// ����ڰ� �Է��� ���̵�� ��й�ȣ�� �����´� .
				String field1 = textField.getText().replace(" ", "");
				String field2 = textField_1.getText().replace(" ", "");

				// �������� Ȯ���� ��Ī �� DB�� �����ϴ� ���� ���̵�� ��й�ȣ�� ��Ī

				User user = userList.get(field1); // �Էµ� ���� ���̵� Ű�μ� DB������ ��ġ�ϴ� ���� ��ü�� �����´�.

				// ������ �α��� ���̵� root ��й�ȣ root
				if ("root".equals(field1) && "root".equals(field2)) {
					JOptionPane.showMessageDialog(null, "������ �α��� ����");
					new AdminJFrame(admin); // ������ �гη� �̵�
					setVisible(false);
				} else if (user == null) { // ���̵�(Ű)�� ��ġ�ϴ� ��ü�� ������ �α��� ����
					JOptionPane.showMessageDialog(null, "�α��� ����");
				} else if (user.getId().equals(field1) && user.getPassword().equals(field2)
						&& user.getAccountList().get(SelectAccount) != null) {
					JOptionPane.showMessageDialog(null, "�α��� ����");

					if (SelectAccount.equals("SavingsAccount")) {
						// ����� �����ϋ�
						user.setAcountName(SelectAccount);
						new UserJFrame(user, tr); // �α��� �гη� �̵��� �α��� ������ ���� ����
						setVisible(false);
					} else if (SelectAccount.equals("TimeDeposit")) {
						// ���� ���� �����ϋ�
						if (user.getAccountList().get("TimeDeposit").getBalance() == 0) {
							user.setAcountName(SelectAccount);
							new TimeDepositFrame(user, tr);
							setVisible(false);
						} else {
							JOptionPane.showMessageDialog(null, "���°� �����մϴ�.\n ���� ������ ���� ���¿��� "
									+ user.getAccountList().get("TimeDeposit").getBalance() + "���� �ֽ��ϴ�.");

						}

					}

					// setVisible(false);

				} else {
					JOptionPane.showMessageDialog(null, "�α��� ����");
				}

			}
		});

		btnNewButton.setBounds(329, 123, 97, 41);
		panel_2.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("ȸ�� ���");
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
