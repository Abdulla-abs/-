package viewGui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import propertyClass.RoomId;
import querySql.RoomQuery;
import querySql.UserQuery;
import utils.NamAndPasRoo;

@SuppressWarnings("serial")
public class queryPersonal extends JPanel implements ActionListener{
	
	JTextField idField = null;
	JButton button = null;
	JLabel perMasLabel = null;
	JList<String> perMaslist = null;
	JScrollPane perMaScrollPane = null;
	JLabel bJLabel = null;
	JList<String> bJList =null;
	JScrollPane bJScrollPane = null;
	JLabel qJLabel = null;	
	JList<String> qJList = null;
	JScrollPane qJScrollPane = null;
	
	public queryPersonal() {
		// TODO Auto-generated constructor stub
		
		idField = new JTextField();
		button = new JButton("��ѯ"); 
		Box topBox = Box.createHorizontalBox();
		topBox.add(idField);
		topBox.add(button);
		
		button.addActionListener(this);
		
		JPanel leftPanel = new JPanel(new BorderLayout());
		Box leftBox = Box.createVerticalBox();
		perMasLabel = new JLabel("������Ϣ:");
		perMaslist = new JList<String>();
		perMaScrollPane = new JScrollPane(perMaslist);
		leftBox.add(perMasLabel,JLabel.CENTER_ALIGNMENT);
		leftBox.add(perMaScrollPane);
		leftBox.setBorder(BorderFactory.createLineBorder(Color.black));
		leftPanel.add(leftBox);
		
		JPanel rightPanel = new JPanel(new GridLayout(2,1));
		bJLabel = new JLabel("����list");
		bJList = new JList<String>();
		bJScrollPane = new JScrollPane(bJList);
		Box rightTopBox = Box.createVerticalBox();
		rightTopBox.add(bJLabel,JLabel.CENTER);
		rightTopBox.add(bJScrollPane);
		rightTopBox.setBorder(BorderFactory.createLineBorder(Color.black));
		rightPanel.add(rightTopBox);
		
		qJLabel = new JLabel("Ƿ���嵥");
		qJList = new JList<String>();
		qJScrollPane = new JScrollPane(qJList);
		Box rightBottomBox = Box.createVerticalBox();
		rightBottomBox.add(qJLabel,JLabel.CENTER);
		rightBottomBox.add(qJScrollPane);
		rightBottomBox.setBorder(BorderFactory.createLineBorder(Color.black));
		//rightBottomBox.setVisible(false);
		rightPanel.add(rightBottomBox);
		
		rightPanel.setPreferredSize(new Dimension(475,getComponentCount()));
		
		Box centerBox = Box.createHorizontalBox();
		centerBox.add(leftPanel);
		centerBox.add(rightPanel);
		
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder("������Ϣ��ѯ��"));
		add(topBox,BorderLayout.NORTH);
		add(centerBox);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == button) {
			DefaultListModel<String> defaultListModel = new DefaultListModel<String>();
			ResultSet resultSet = new UserQuery().queryPersonal(idField.getText().trim());
			try {
				while (resultSet.next()) {
					String a = "����id ��"+resultSet.getString("userid")+",���� ��"+resultSet.getString("username")+",�Ա� �� "+resultSet.getString("sex")+",������ϵ ��"+resultSet.getString("ralative")+",���ƺ� ��"+resultSet.getString("carnumber")+",��ϵ�绰 ��"+resultSet.getLong("phone")+",������Ϣ ��"+resultSet.getString("pet")+",���з�ID ��";
					String[] b = a.split(",");
//					perMaslist.setListData(b);
					for (String string : b) {
						defaultListModel.addElement(string);
					}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//			Vector<String> dataVector = new Vector<String>();
			List<RoomId> roomidlist = new RoomQuery().IdQuery(idField.getText().trim());
			int count = 0;
			for (RoomId roomId : roomidlist) {
//				dataVector.add(roomId.getRoomId());
				count = count+1;
				defaultListModel.addElement(count+" : "+roomId.getRoomId());
			}
//			perMaslist.setListData(dataVector);
			perMaslist.setModel(defaultListModel);
		}
	}

}
