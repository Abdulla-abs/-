package viewGui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
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
public class tenementView extends JPanel implements ActionListener, DocumentListener {

	JLabel label = null;
	JComboBox<String> hAreaBox = null;
	JComboBox<String> nAreaBox = null;
	JList<String> list = null;
	JScrollPane scrollPane = null;
	JButton queryButton = null;
	JFrame jFrame = new JFrame();

	JLabel inputJLabel = null;
	JTextField inputField = null;
	JLabel tipsLabel = null;
	JButton hireButton = null;

	int height;

	public tenementView() {
		height = 0;

		label = new JLabel("���ⷿ:");
		new changeFont().changefontBold(label);

		setLayout(new BorderLayout());

		hAreaBox = new JComboBox<String>();
		nAreaBox = new JComboBox<String>();
		hAreaBox.addItem("������¥");
//		hAreaBox.addItem("B��");
//		hAreaBox.addItem("C��");
		nAreaBox.addItem("����");
		nAreaBox.addItem("1¥");
		nAreaBox.addItem("2¥");

		nAreaBox.addActionListener(this);

		list = new JList<String>();
		// list.setBorder(BorderFactory.createLineBorder(Color.black));
		queryButton = new JButton("ˢ��");
		scrollPane = new JScrollPane(list);
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.black));

		queryButton.addActionListener(this);

		inputJLabel = new JLabel("������Ҫ���õķ���ID:");
		inputField = new JTextField(16);
		tipsLabel = new JLabel();
		hireButton = new JButton("Hire it");

		hireButton.addActionListener(this);

		Box queryBox = Box.createHorizontalBox();
		queryBox.add(hAreaBox);
		queryBox.add(nAreaBox);
		Box inputBox = Box.createHorizontalBox();
		inputBox.add(inputJLabel);
		inputBox.add(inputField);
		inputBox.setPreferredSize(new Dimension(350, 48));
		inputBox.setBorder(BorderFactory.createRaisedBevelBorder());
		JPanel jPanel2 = new JPanel(new BorderLayout());
		jPanel2.add(inputBox, BorderLayout.NORTH);
		jPanel2.add(tipsLabel);
		jPanel2.add(hireButton, BorderLayout.SOUTH);
//		Box rightBox = Box.createVerticalBox();
//		rightBox.add(inputBox);
//		rightBox.add(hireButton);

		inputField.getDocument().addDocumentListener(this);

		JPanel jPanel = new JPanel(new BorderLayout());
		jPanel.add(queryBox, BorderLayout.NORTH);
		jPanel.add(scrollPane, BorderLayout.CENTER);
		jPanel.add(jPanel2, BorderLayout.EAST);

		// queryBox.setBorder(BorderFactory.createLineBorder(Color.black));

		setLayout(new BorderLayout());
		setPreferredSize(new Dimension());
		add(label, BorderLayout.NORTH);
		add(jPanel);
		add(queryButton, BorderLayout.SOUTH);
		setBorder(BorderFactory.createTitledBorder("Wanner Hire..."));
		hireRoomRefresh(0);
	}

	private void hireRoomRefresh(int limite) {

		String string = new String();
		Vector<String> dataVector = new Vector<String>();
		List<RoomId> hireRoomList = new RoomQuery().hireRoomQuery(limite);
		for (RoomId roomId : hireRoomList) {
			string = "����ID��" + roomId.getRoomId() + "    ����¥����" + roomId.getRoombelong() + "    ���������"
					+ roomId.getHeight();
			dataVector.addElement(string);
		}
		list.setListData(dataVector);
		// return dataVector;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == nAreaBox) {
			// height = (String)nAreaBox.getSelectedItem();
			switch ((String) nAreaBox.getSelectedItem()) {
			case "����": {
				height = 0;
				hireRoomRefresh(height);
				break;
			}
			case "1¥": {
				height = 1;
				hireRoomRefresh(height);
				break;
			}
			case "2¥": {
				height = 2;
				hireRoomRefresh(height);
				break;
			}
			}
			System.out.println("t" + nAreaBox.getSelectedItem());
			System.out.println(height);
		}

		if (e.getSource() == queryButton) {
			hireRoomRefresh(height);
			System.out.println("on");
		}

		if (e.getSource() == hireButton) {
			System.out.println("on");
			System.out.println(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
			boolean flag = new check().checkString(inputField.getText());
			if (!flag) {
				int hireId = Integer.valueOf(inputField.getText());
				System.out.println(hireId);
//				List<RoomId> hireRoomList = new RoomQuery().hireRoomQuery(0);
//				boolean at = false;
//				for (RoomId roomId : hireRoomList) {
//					if (!inputField.getText().trim().equals(roomId.getRoomId())) {
//						at = false;
//					}else {
//						at = true;
//					}
//				}
//				if (at) {
//					JOptionPane.showMessageDialog(null, "����Ų����ڣ�");
//				}
				if (tipsLabel!=null) {
					boolean b;
					if (b= new HireRoom().idHireRoom(inputField.getText().trim())) {
						String time ="���óɹ����Ժ�ÿ���µģ�" +String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH))+"���Ƿ���ɷ��գ�";
						JOptionPane.showMessageDialog(null, time);
					}else {
						JOptionPane.showMessageDialog(null, "����ʧ�ܣ����Ժ����ԣ�");
					}
				}
			} else {
				
			}
		}
//		
//		if (e.getSource()) {
//			
//		}
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
