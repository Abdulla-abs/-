package viewGui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import propertyClass.RoomId;
import querySql.HireRoom;
import querySql.RoomQuery;
import utils.*;

@SuppressWarnings("serial")
public class tenementBackView extends JPanel implements ActionListener,DocumentListener{
	
	JLabel label = null;
	JComboBox<String> hAreaBox = null;
	JComboBox<String> nAreaBox = null;
	JList<String> list = null;
	JScrollPane scrollPane = null;
	JButton queryButton = null;
	
	JLabel inputJLabel = null;
	JTextField inputField = null;
	JButton hireButton = null;
	JLabel tipsLabel = null;
	
	int height;
	
	public tenementBackView() {
		label = new JLabel("�����е�:");
		new changeFont().changefontBold(label);
		
		setLayout(new BorderLayout());
		
		tipsLabel = new JLabel();

		hAreaBox = new JComboBox<String>();
		nAreaBox = new JComboBox<String>();
		hAreaBox.addItem("��������");
		nAreaBox.addItem("����");
		nAreaBox.addItem("1¥");
		nAreaBox.addItem("2¥");
		
		nAreaBox.addActionListener(this);
//		String string = new String();
//		Vector<String> dataVector = new Vector<String>();
//		for (int i = 0; i < 11; i++) {
//			string = "Text Field";
//			dataVector.addElement(string);
//		}
		list = new JList<String>();
		//list.setBorder(BorderFactory.createLineBorder(Color.black));
		queryButton = new JButton("ˢ��");
		scrollPane = new JScrollPane(list);
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.black));
		
		queryButton.addActionListener(this);
		
		inputJLabel = new JLabel("��������Ҫ����ķ���ID:");
		inputField = new JTextField(16);
		hireButton = new JButton("����˷���");
		
		hireButton.addActionListener(this);
		
		inputField.getDocument().addDocumentListener(this);
		
		Box queryBox = Box.createHorizontalBox();
		queryBox.add(hAreaBox);
		queryBox.add(nAreaBox);
		Box inputBox = Box.createHorizontalBox();
		inputBox.add(inputJLabel);
		inputBox.add(inputField);
		inputBox.setPreferredSize(new Dimension(380,48));
		inputBox.setBorder(BorderFactory.createRaisedBevelBorder());
		JPanel jPanel2 = new JPanel(new BorderLayout());
		jPanel2.add(inputBox,BorderLayout.NORTH);
		jPanel2.add(tipsLabel);
		jPanel2.add(hireButton,BorderLayout.SOUTH);
//		Box rightBox = Box.createVerticalBox();
//		rightBox.add(inputBox);
//		rightBox.add(hireButton);
		
		JPanel jPanel = new JPanel(new BorderLayout());
		jPanel.add(queryBox,BorderLayout.NORTH);
		jPanel.add(scrollPane,BorderLayout.CENTER);
		jPanel.add(jPanel2,BorderLayout.EAST);

		//queryBox.setBorder(BorderFactory.createLineBorder(Color.black));
		
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension());
		add(label,BorderLayout.NORTH);
		add(jPanel);
		add(queryButton,BorderLayout.SOUTH);
		setBorder(BorderFactory.createTitledBorder("Back Hire..."));
		backHireRefresh(0);
	}
	
	private void backHireRefresh(int limite) {
		String string = new String();
		Vector<String> persionalHire = new Vector<String>();
		List<RoomId> hireRoomList = new RoomQuery().backHireQuery(limite);
		System.out.println(hireRoomList==null?"y":"f");
		for (RoomId roomId : hireRoomList) {
			string = "����ID��" + roomId.getRoomId() + "    ����¥����" + roomId.getRoombelong() + "    ���������"
					+ roomId.getHeight();
			persionalHire.addElement(string);
		}
		list.setListData(persionalHire);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == nAreaBox) {
			// height = (String)nAreaBox.getSelectedItem();
			switch ((String) nAreaBox.getSelectedItem()) {
			case "����": {
				height = 0;
				backHireRefresh(height);
				break;
			}
			case "1¥": {
				height = 1;
				backHireRefresh(height);
				break;
			}
			case "2¥": {
				height = 2;
				backHireRefresh(height);
				break;
			}
			}
			System.out.println("t" + nAreaBox.getSelectedItem());
			System.out.println(height);
		}

		if (e.getSource() == queryButton) {
			backHireRefresh(height);
			System.out.println("on");
		}
		
		if (e.getSource() == hireButton) {
			System.out.println("on");
			System.out.println(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
			boolean flag = new check().checkString(inputField.getText());
			if (!flag) {
				int roomId = Integer.valueOf(inputField.getText());
				System.out.println(roomId);
				if (tipsLabel!=null) {
					boolean b;
					if (b = new HireRoom().idBackHireRoom(inputField.getText().trim())) {
						String time ="����ɹ���";
						JOptionPane.showMessageDialog(null, time);
					}else {
						JOptionPane.showMessageDialog(null, "����ʧ�ܣ����Ժ����ԣ�");
					}
				}
			} else {
				
			}
		}
		
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		System.out.println(inputField.getText().trim());
		boolean flag = new check().checkString(inputField.getText().trim());
		if (flag) {
			tipsLabel.setText("ע������������������");
		}else {
			tipsLabel.setText(null);
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		System.out.println(inputField.getText().trim());
		boolean flag = new check().checkString(inputField.getText().trim());
		if (flag) {
			tipsLabel.setText("ע������������������");
		}else {
			tipsLabel.setText(null);
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		System.out.println(inputField.getText().trim());
		boolean flag = new check().checkString(inputField.getText().trim());
		if (flag) {
			tipsLabel.setText("ע������������������");
		}else {
			tipsLabel.setText(null);
		}
	}

}
