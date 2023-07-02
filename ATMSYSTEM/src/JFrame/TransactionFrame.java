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
	private String header[] = {"로그 기록", "아이디", "비밀번호", "계좌정보", "잔고조회", "입금내역", "출금내역" };
	private Object[][] rows;
	
	private JTable table;
	private JButton btnNewButton;
	


	/**
	 * Create the frame.
	 */
	public TransactionFrame() {
		rows = new Object[DataBase.getTuples().size()][]; // 테이블 형식으로 보여주는 틀을 생성 
		setLocationRelativeTo(null); 
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 578, 675);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		//검색 결과에 대한 ArrayList 를 Object[][] 로 변환
		int i = 0;
		for (ArrayList<Object> nested : DataBase.getTuples()) {
			// ArrayList를 toArray함수를 통해서 array(배열)로 변환
			rows[i++] = nested.toArray(new Object[nested.size()]);
		}
		
		
		table = new JTable(rows,header);
		contentPane.add(table, BorderLayout.CENTER);
		JScrollPane jscp1 = new JScrollPane(table);
		jscp1.setLocation(0, 0);
		jscp1.setSize(300, 160);
		getContentPane().add(jscp1);
		
		
		btnNewButton = new JButton("뒤로 가기");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//뒤로 가기 버튼 
				setVisible(false);
				
			}
		});
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 20));
		contentPane.add(btnNewButton);
		pack();
	}

}
