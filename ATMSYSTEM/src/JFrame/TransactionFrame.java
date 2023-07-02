package JFrame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Class.DataBase;

import javax.swing.JTable;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TransactionFrame extends JFrame {
	
	private JPanel contentPane;
	private String header[] = {"�α� ���", "���̵�", "��й�ȣ", "��������", "�ܰ���ȸ", "�Աݳ���", "��ݳ���" };
	private Object[][] rows;
	
	private JTable table;
	private JButton btnNewButton;
	


	/**
	 * Create the frame.
	 */
	public TransactionFrame() {
		rows = new Object[DataBase.getTuples().size()][]; // ���̺� �������� �����ִ� Ʋ�� ���� 
		setLocationRelativeTo(null); 
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 578, 675);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		//�˻� ����� ���� ArrayList �� Object[][] �� ��ȯ
		int i = 0;
		for (ArrayList<Object> nested : DataBase.getTuples()) {
			// ArrayList�� toArray�Լ��� ���ؼ� array(�迭)�� ��ȯ
			rows[i++] = nested.toArray(new Object[nested.size()]);
		}
		
		
		table = new JTable(rows,header);
		contentPane.add(table, BorderLayout.CENTER);
		JScrollPane jscp1 = new JScrollPane(table);
		jscp1.setLocation(0, 0);
		jscp1.setSize(300, 160);
		getContentPane().add(jscp1);
		
		
		btnNewButton = new JButton("�ڷ� ����");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//�ڷ� ���� ��ư 
				setVisible(false);
				
			}
		});
		btnNewButton.setFont(new Font("����", Font.BOLD, 20));
		contentPane.add(btnNewButton);
		pack();
	}

}
