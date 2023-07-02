package JFrame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Class.Admin;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminJFrame extends JFrame {
	private Admin admin;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public AdminJFrame(Admin admin) {
		super("Admin page");
		this.admin = admin;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null); 
		setVisible(true);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(5, 10, 424, 223);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("�����ڴ� ȯ���մϴ�.");
		lblNewLabel.setBounds(113, 23, 200, 24);
		lblNewLabel.setFont(new Font("����", Font.BOLD, 20));
		panel.add(lblNewLabel);

		JButton btnNewButton = new JButton("���� �����ϱ�");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				admin.FillMoney();
				JOptionPane.showMessageDialog(null, "���� ���� �Ϸ�");
			}
		});

		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setFont(new Font("����", Font.BOLD, 20));
		btnNewButton.setBounds(86, 91, 268, 41);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("�ڷ� ����");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ATMJFrame();
				setVisible(false);

			}
		});

		btnNewButton_1.setForeground(Color.DARK_GRAY);
		btnNewButton_1.setFont(new Font("����", Font.BOLD, 20));
		btnNewButton_1.setBounds(86, 158, 268, 41);
		panel.add(btnNewButton_1);

	}

}
