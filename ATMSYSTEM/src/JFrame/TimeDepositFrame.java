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
	private Transaction tr; // Ʈ�����
	private User user; // �α��� ������ �Ѱܹ��� ��
	private int DepositMoney; // ���� �ݾ�
	private int month; // ����ڰ� �Է��� ������
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	public TimeDepositFrame(User user, Transaction tr) {
		super("TimeDeposit Frame");
		this.user = user; // ���� �޾ƿ� .
		this.tr = tr;
		DB = new DataBase(); // �����ͺ��̽� ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 587);
		setLocationRelativeTo(null);
		setVisible(true);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// �ڷΰ��� ��ư
		JButton btnNewButton = new JButton("�ڷΰ���");
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
		panel.setBounds(0, 0, 494, 193);
		contentPane.add(panel);
		panel.setLayout(null);

		// �α��� ����
		JLabel lblNewLabel = new JLabel("�α��� ���� : ");
		lblNewLabel.setBounds(140, 49, 96, 18);
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 15));
		panel.add(lblNewLabel);

		// ���� ���̵�
		JLabel lblNewLabel_1 = new JLabel(user.getId());
		lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(254, 51, 164, 15);
		panel.add(lblNewLabel_1);

		// ���� ����
		JLabel lblNewLabel_2 = new JLabel("���� ���� :");
		lblNewLabel_2.setFont(new Font("����", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(153, 95, 96, 28);
		panel.add(lblNewLabel_2);

		// �� ���� ����
		JLabel lblNewLabel_3 = new JLabel("���� ���� ����"); // ���� ��������
		lblNewLabel_3.setFont(new Font("����", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(254, 94, 164, 31);
		panel.add(lblNewLabel_3);

		// �� ���� �ݾ� Text
		textField = new JTextField();
		textField.setBounds(218, 155, 227, 28);
		panel.add(textField);
		textField.setColumns(30);

		JLabel lblNewLabel_21 = new JLabel("���� �ݾ�");
		lblNewLabel_21.setBounds(95, 155, 74, 27);
		panel.add(lblNewLabel_21);
		lblNewLabel_21.setFont(new Font("����", Font.BOLD, 15));

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 198, 498, 311);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_31 = new JLabel("���� ����");
		lblNewLabel_31.setFont(new Font("����", Font.BOLD, 15));
		lblNewLabel_31.setBounds(94, 10, 76, 28);
		panel_2.add(lblNewLabel_31);

		// ���� ���� Text
		textField_1 = new JTextField();
		textField_1.setBounds(219, 11, 227, 28);
		panel_2.add(textField_1);
		textField_1.setColumns(30);

		// �Ա� ��ư
		JButton btnNewButton1 = new JButton("�Ա��ϱ�");
		btnNewButton1.setFont(new Font("����", Font.BOLD, 20));
		btnNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TimeDeposit account = (TimeDeposit) user.getAccount();

				if (textField.getText().equals("") || textField_1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "�ؽ�Ʈ�� �Է����ּ���.");
				} else { // �Ա� �̺�Ʈ
					DepositMoney = Integer.parseInt(textField.getText()); // �Ա� �ݾ�
					month = Integer.parseInt(textField_1.getText()); // ����ڰ� �Է��� ������
					textField.setText("");
					textField_1.setText("");

					tr.Deposit(account, account.AddTimeDeposit(DepositMoney, month)); // Ʈ����ǿ��� �Ա� �޼ҵ� ����

					JOptionPane.showMessageDialog(null, "�ݾ� :" + DepositMoney + "�Ա� �Ϸ�");

				}

			}
		});

		btnNewButton1.setBounds(64, 134, 378, 41);
		panel_2.add(btnNewButton1);

		JButton btnNewButton_2 = new JButton("�ܰ� ��ȸ");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �ܰ� ��ȸ
				JOptionPane.showMessageDialog(null,
						month + "�������� �� ���� �ܾ���\n" + user.getAccountList().get("TimeDeposit").getBalance() + "�� �Դϴ�.");
			}
		});
		btnNewButton_2.setFont(new Font("����", Font.BOLD, 20));
		btnNewButton_2.setBounds(64, 212, 377, 44);
		panel_2.add(btnNewButton_2);

		JLabel lblNewLabel_4 = new JLabel("     (������ : 5%)");
		lblNewLabel_4.setFont(new Font("����", Font.BOLD, 12));
		lblNewLabel_4.setBounds(70, 48, 128, 15);
		panel_2.add(lblNewLabel_4);
	}
}
