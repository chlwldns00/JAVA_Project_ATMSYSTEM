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
	private String SelectAccountName; // �������� �̸� ����
	private Account account; // ���� ����
	private JPanel contentPane;
	private JTextField textField;
	private JTextField passwordField;
	private ButtonGroup radioGroup;

	public enrollJFrame() {
		super("enroll page");
		DB = new DataBase(); // �� �����ͺ��̽� ����
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

		JLabel lblNewLabel = new JLabel("ȸ�� ����");
		lblNewLabel.setBounds(138, 24, 107, 28);
		lblNewLabel.setFont(new Font("����", Font.BOLD, 24));
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(5, 101, 384, 287);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setFont(new Font("����", Font.BOLD, 15));
		lblNewLabel_1.setBounds(66, 60, 43, 15);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("PassWord");
		lblNewLabel_2.setFont(new Font("����", Font.BOLD, 15));
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

		JButton btnNewButton = new JButton("ȸ�� ���");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String Id = textField.getText().replace(" ", "");
				String password = passwordField.getText().replace(" ", "");
				// ���̵� �� �н����� ���Է½� ȸ����� �ź�
				// ���̵�� Ư������ ��� x
				// ���̵��� ���̴� �ּұ��� 3�̻� ��й�ȣ ���̴� �ּ� 8���̻�

				// �����ͺ��̽��� ���̵� ���� ���
				if (SelectAccountName == null) {

					JOptionPane.showMessageDialog(null, "���� ������ �������ּ���.");
				} else {
					if (DataBase.getUserList().get(Id) == null) {
						if (Id.equals("") && password.equals("")) {
							JOptionPane.showMessageDialog(null, "���̵�� ��й�ȣ�� �Է����� �ʾҽ��ϴ�.", "ȸ������ error",
									JOptionPane.WARNING_MESSAGE);
						} else if (Id.equals("") || Id.length() < 3) {
							JOptionPane.showMessageDialog(null, "���̵� �Է����� �ʾҽ��ϴ�.", "ȸ������ error",
									JOptionPane.WARNING_MESSAGE);
						} else if (password.equals("")) {
							JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է����� �ʾҽ��ϴ�.", "ȸ������ error",
									JOptionPane.WARNING_MESSAGE);
						} else if (password.length() < 8) {
							JOptionPane.showMessageDialog(null, "��й�ȣ�� ���̸� �ּ� 8���̻� �Է����ּ���.", "ȸ������ error",
									JOptionPane.WARNING_MESSAGE);
						} else if (!Id.matches("[0-9|a-z|A-Z|��-��|��-��|��-��]*")) {
							JOptionPane.showMessageDialog(null, "���̵𿡼� Ư�����ڸ� �������ּ���.", "ȸ������ error",
									JOptionPane.WARNING_MESSAGE);
						} else {

							DB.SetUserList(Id, new User(Id, password)); // �������� �����ͺ��̽��� ����
							User user = DataBase.getUserList().get(Id); // �����ͺ��̽����� �� ��������
							user.setAccount(SelectAccountName, account); // ���� ���� ���

							if (SelectAccountName.equals("SavingsAccount")) {
								JOptionPane.showMessageDialog(null, Id + "ȸ���� \n" + "����� ����" + " ���� �Ϸ�\n");
							} else {
								JOptionPane.showMessageDialog(null, Id + "ȸ���� \n" + "���⿹�� ����" + " ���� �Ϸ�\n");
							}

							setVisible(false);
							new ATMJFrame();

						}

						// �����ͺ��̽��� ���̵� �ִ� ���
					} else if (DataBase.getUserList().get(Id) != null) {

						// ���̵� �ְ� �ش� ���µ� �ִ� ���
						if (DataBase.getUserList().get(Id).getAccountList().get(SelectAccountName) != null) {

							JOptionPane.showMessageDialog(null, "���̵� �ߺ��Ǿ����ϴ�.", "ȸ������ error",
									JOptionPane.WARNING_MESSAGE);
						} else {

							User user = DataBase.getUserList().get(Id); // �����ͺ��̽����� �� ��������
							user.setAccount(SelectAccountName, account); // ���� ���� ���

							if (SelectAccountName.equals("SavingsAccount")) {
								JOptionPane.showMessageDialog(null, Id + "ȸ���� \n" + "����� ����" + " ���� �Ϸ�\n");
							} else {
								JOptionPane.showMessageDialog(null, Id + "ȸ���� \n" + "���⿹�� ����" + " ���� �Ϸ�\n");
							}

							setVisible(false);
							new ATMJFrame();

						}
					}
				}

			}
		});
		btnNewButton.setFont(new Font("����", Font.BOLD, 20));
		btnNewButton.setBounds(55, 151, 270, 45);
		panel_1.add(btnNewButton);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("����� ����");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ����� ���� ����
				SelectAccountName = "SavingsAccount";
				account = new SavingsAccount();
			}
		});

		rdbtnNewRadioButton.setBounds(66, 6, 121, 23);
		panel_1.add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("���� ���� ����");
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ���� ���� ���� ����
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

		JButton btnNewButton_1 = new JButton("�ڷ� ����");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �ڷ� ���� ��ɱ���
				setVisible(false);
				new ATMJFrame();
			}
		});

		btnNewButton_1.setFont(new Font("����", Font.BOLD, 20));
		btnNewButton_1.setBounds(55, 218, 269, 39);
		panel_1.add(btnNewButton_1);
	}
}
