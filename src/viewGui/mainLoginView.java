package viewGui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import propertyClass.RoomId;
import querySql.RoomQuery;
import utils.*;

@SuppressWarnings("serial")
public class mainLoginView extends JPanel implements ActionListener {
//	int root = 0;
	
	JLabel jLabel;
	JTextField jTextField;
	public JButton personal,tenement,tenementback,buy,feelist,complain,property,persionalManager,complainButton;
	JPanel pCenterPanel;
	
	tenementView tenementView;
	mainOpraterWindow mOpraterWindow;
	tenementBackView tenementBackView;
	buyView buyView;
	feeListView feeListView;
	complainView complainView;
	propertyManage propertyView;
	queryPersonal queryPersonal;
	complainManager complainManager;
	
	CardLayout serverCardLayout;
	

	public mainLoginView() {
		
		serverCardLayout = new CardLayout();
		

		
		pCenterPanel = new JPanel(serverCardLayout);

		
		jLabel = new JLabel(NamAndPasRoo.rootid==1?"个人信息":"管理系统",JLabel.CENTER);
		jLabel.setFont(new Font("黑体", Font.BOLD, 20));
		jLabel.setPreferredSize(new Dimension(getComponentCount(),60));
		personal = new JButton("个人中心");
		//personal.setPreferredSize(new Dimension(200,60));
		tenement = new JButton("租房        ");
		tenementback = new JButton("退租        ");
		buy = new JButton("购房        ");
		feelist = new JButton("费用清单");
		complain = new JButton("投诉        ");
		property = new JButton("房产信息管理");
		persionalManager = new JButton("查询个人");
		complainButton = new JButton("投诉管理");

		Box leftmarginBox = Box.createHorizontalBox();
		leftmarginBox.add(Box.createHorizontalStrut(30));
		Box leftBox = Box.createVerticalBox();
		leftBox.setPreferredSize(new Dimension(150,getComponentCount()));
		leftBox.add(Box.createVerticalStrut(30));
		leftBox.add(personal);
		leftBox.add(Box.createVerticalStrut(30));
		leftBox.add(tenement);
		leftBox.add(Box.createVerticalStrut(30));
		leftBox.add(tenementback);
		leftBox.add(Box.createVerticalStrut(30));
		leftBox.add(buy);
		leftBox.add(Box.createVerticalStrut(30));
		leftBox.add(feelist);
		leftBox.add(Box.createVerticalStrut(30));
		leftBox.add(complain);
		leftBox.add(Box.createVerticalStrut(30));
		leftBox.add(property);
		leftBox.add(Box.createVerticalStrut(30));
		leftBox.add(persionalManager);
		leftBox.add(Box.createVerticalStrut(30));
		leftBox.add(complainButton);
		leftBox.add(leftmarginBox);
		
		leftBox.setBorder(BorderFactory.createLineBorder(Color.black));
	//	leftBox.setLayout(new FlowLayout());
		
		if (NamAndPasRoo.rootid<2) {
			tenementView = new tenementView();
			mOpraterWindow = new mainOpraterWindow();
			tenementBackView = new tenementBackView();
			buyView = new buyView();
			feeListView = new feeListView();
			complainView = new complainView();
			
			pCenterPanel.add(mOpraterWindow,"main");
			pCenterPanel.add(tenementView,"hire");
			pCenterPanel.add(tenementBackView,"backhire");
			pCenterPanel.add(buyView,"buy");
			pCenterPanel.add(feeListView,"feelist");
			pCenterPanel.add(complainView,"complain");
			

			personal.addActionListener(this);
			tenement.addActionListener(this);
			tenementback.addActionListener(this);
			buy.addActionListener(this);
			feelist.addActionListener(this);
			complain.addActionListener(this);
		}else {
			propertyView = new propertyManage();
			queryPersonal = new queryPersonal();
			complainManager = new complainManager();

			
			pCenterPanel.add(propertyView,"property");
			pCenterPanel.add(queryPersonal,"queryPersional");
			pCenterPanel.add(complainManager,"complainManager");
			

			property.addActionListener(this);
			persionalManager.addActionListener(this);
			complainButton.addActionListener(this);
		}
		
		
		personal.setBackground(Color.getHSBColor(0 ,100 ,97));
		
		setLayout(new BorderLayout());
		add(jLabel,BorderLayout.NORTH);
		add(leftBox,BorderLayout.WEST);
		
		add(pCenterPanel);
		//add(jTextField);
		//add(new mainOpraterWindow());
		//new Vis(leftBox.getComponents());
		switch (NamAndPasRoo.rootid) {
		case 0: {
			throw new IllegalArgumentException("Unexpected value: " + NamAndPasRoo.rootid);
			//break;
			}
		case 1:{
			serverCardLayout.show(pCenterPanel, "main");
			property.setVisible(false);
			persionalManager.setVisible(false);
			complainButton.setVisible(false);
			break;
			}
		case 3:{
			serverCardLayout.show(pCenterPanel,"property" );
			personal.setVisible(false);
			tenement.setVisible(false);
			tenementback.setVisible(false);
			buy.setVisible(false);
			feelist.setVisible(false);
			complain.setVisible(false);
		}
		}
	}
	
	class mainOpraterWindow extends JPanel implements ActionListener{
		JLabel label;
		JLabel nameLabel;
		JLabel sexLabel;
		JLabel idLabel;
		JLabel condictionLabel;
		JLabel owenrLabel;
		JButton reflashButton;
		//CardLayout cardView;
		//JPanel conditionPanel;
		//JTextField text = new JTextField(20);
		
		JList<String> personalList = null;
		JScrollPane persionalScrollPane = null;
		
		JList<String> owenrList = null;
		JScrollPane owenrScrollPane = null;
		Box owenrListBox = Box.createVerticalBox();
		
		public mainOpraterWindow() {
			//conditionPanel = new JPanel(new BorderLayout());
			label = new JLabel("个人清单 :",JLabel.CENTER);
			nameLabel = new JLabel("姓名:"+NamAndPasRoo.username);
			sexLabel = new JLabel("性别:"+NamAndPasRoo.sex);
			idLabel = new JLabel("ID:"+NamAndPasRoo.userid);
			condictionLabel = new JLabel("租有房:");
			owenrLabel = new JLabel("拥有房:");
			reflashButton = new JButton("刷新信息");
			
			reflashButton.addActionListener(this);

//			ListSelectionListener l = new ListSelectionListener() {
//				
//				@Override
//				public void valueChanged(ListSelectionEvent e) {
//					System.out.println("LIST选中事件--->"+personalList.getSelectedValue());
//				}
//			};
			
			personalList = new JList<String>();
			persionalScrollPane = new JScrollPane(personalList);
//			personalList.addListSelectionListener(l);
//			personalList.addListSelectionListener(new ListSelectionListener() {
//				
//				@Override
//				public void valueChanged(ListSelectionEvent e) {
//					//JList<String> list = (JList<String>) e.getSource();
//					String listObject = personalList.getSelectedValue();
//					System.out.println(listObject);
//				}
//			});
			//persionalScrollPane.setPreferredSize(new Dimension(300, 250));
			
			
			owenrList = new JList<String>();
			owenrScrollPane = new JScrollPane(owenrList);
			
			//theHirePanel.add(text,theHirePanel.LEFT_ALIGNMENT);
			
			Box hireListBox = Box.createVerticalBox();
			hireListBox.add(persionalScrollPane);	
			
			owenrListBox.add(owenrLabel);
			owenrListBox.add(owenrScrollPane);
			//owenrListBox.setVisible(false);
			
			Box leftBox = Box.createVerticalBox();
			leftBox.add(Box.createVerticalStrut(10));
			leftBox.add(nameLabel);
			leftBox.add(Box.createVerticalStrut(10));
			leftBox.add(sexLabel);
			leftBox.add(Box.createVerticalStrut(10));
			leftBox.add(idLabel);
			leftBox.add(Box.createVerticalStrut(10));
			leftBox.add(condictionLabel);
			//leftBox.setPreferredSize(new Dimension(getComponentCount(), 550));
			leftBox.add(hireListBox);
			leftBox.add(owenrListBox);
			//leftBox.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Hire:"));
			
			//label.setFont(new Font("list", Font.BOLD, 16));
			
			//setLabelBorder(label,nameLabel,sexLabel,idLabel,condictionLabel,owenrLabel);
			new changeFont().changefontBold(label,nameLabel,sexLabel,idLabel,condictionLabel,owenrLabel);
			//setLayout(cardView);
			setLayout(new BorderLayout());
			setBorder(BorderFactory.createTitledBorder("Personal..."));
			
			add(leftBox,BorderLayout.CENTER);
			add(reflashButton,BorderLayout.SOUTH);
			//add(nameLabel,BorderLayout.WEST);
			//add(conditionPanel,BorderLayout.CENTER);
			if (!marstModel()) {
				owenrListBox.setVisible(false);
			}else {
				owenrListBox.setVisible(true);
			}
			hireRefesh();
			OwenrRefresh();
		}
		
		private boolean marstModel() {
			// TODO Auto-generated method stub
			List<RoomId> oweList = new RoomQuery().IdOwenrQuery(NamAndPasRoo.userid);
			if (oweList.isEmpty()) {
				System.out.println("t");
				return false;
				//owenrListBox.setVisible(false);
			}else {
				return true;
			}
		}
		
		public void hireRefesh() {
			// TODO Auto-generated method stub
			//personalList.removeAll();
			//owenrList.removeAll();
//			while (!persionalHire.isEmpty()) {
//				persionalHire.removeElementAt(0);	
//			}
			Vector<String> persionalHire = new Vector<String>();
			ResultSet hireSet = new RoomQuery().IdBelongQuery(NamAndPasRoo.userid);
			System.out.println(hireSet==null?"y":"f");
			try {
				while (hireSet.next()) {
					try {
						persionalHire.addElement("租用的房间id为： "+hireSet.getString("roomid")+"    所属楼房："+hireSet.getString("buildingname"));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			personalList.setListData(persionalHire);
		}
		
		public void OwenrRefresh() {
			Vector<String> ownerHouse = new Vector<String>();
			List<RoomId> oweList = new RoomQuery().IdOwenrQuery(NamAndPasRoo.userid);
			System.out.println(oweList==null?"y":"f");
			for (RoomId roomId : oweList) {
				ownerHouse.addElement("拥有的房间id为： "+roomId.getRoomId()+"    所属楼房："+roomId.getRoombelong());
			}
			
			owenrList.setListData(ownerHouse);
			if (!marstModel()) {
				owenrListBox.setVisible(false);
			}else {
				owenrListBox.setVisible(true);
			}
		}
		
//		public void setLabelBorder(Component ...components){
//			
//			for (Component component : components) {
//				component.setFont(new Font("", Font.BOLD, 16));
//			}
//						
//		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == reflashButton) {
				
				hireRefesh();
				OwenrRefresh();
			}
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == personal) {
			
			personal.setBackground(Color.getHSBColor(0 ,100 ,97));
			tenement.setBackground(null);
			tenementback.setBackground(null);
			buy.setBackground(null);
			feelist.setBackground(null);
			complain.setBackground(null);
			serverCardLayout.show(pCenterPanel, "main");
			//new mainOpraterWindow().personalRefesh();
		}
		if (e.getSource() == tenement) {
			tenement.setBackground(Color.getHSBColor(0 ,100 ,97));
			personal.setBackground(null);
			tenementback.setBackground(null);
			buy.setBackground(null);
			feelist.setBackground(null);
			complain.setBackground(null);
			serverCardLayout.show(pCenterPanel, "hire");
			//add(new tenementView());
		}
		if (e.getSource() == tenementback) {
			tenementback.setBackground(Color.getHSBColor(0 ,100 ,97));
			tenement.setBackground(null);
			personal.setBackground(null);
			buy.setBackground(null);
			feelist.setBackground(null);
			complain.setBackground(null);
			serverCardLayout.show(pCenterPanel, "backhire");
		}
		if (e.getSource() == buy) {
			buy.setBackground(Color.getHSBColor(0 ,100 ,97));
			tenement.setBackground(null);
			tenementback.setBackground(null);
			personal.setBackground(null);
			feelist.setBackground(null);
			complain.setBackground(null);
			property.setBackground(null);
			serverCardLayout.show(pCenterPanel, "buy");
		}
		if (e.getSource() == feelist) {
			feelist.setBackground(Color.getHSBColor(0 ,100 ,97));
			tenement.setBackground(null);
			tenementback.setBackground(null);
			buy.setBackground(null);
			personal.setBackground(null);
			complain.setBackground(null);
			property.setBackground(null);
			serverCardLayout.show(pCenterPanel, "feelist");
		}
		if (e.getSource() == complain) {
			complain.setBackground(Color.getHSBColor(0 ,100 ,97));
			tenement.setBackground(null);
			tenementback.setBackground(null);
			buy.setBackground(null);
			feelist.setBackground(null);
			personal.setBackground(null);
			property.setBackground(null);
			serverCardLayout.show(pCenterPanel, "complain");
		}
		if (e.getSource() == property) {
			property.setBackground(Color.getHSBColor(0 ,100 ,97));
			persionalManager.setBackground(null);
			complainButton.setBackground(null);
			serverCardLayout.show(pCenterPanel, "property");
		}
		if (e.getSource() == persionalManager) {
			persionalManager.setBackground(Color.getHSBColor(0 ,100 ,97));
			property.setBackground(null);
			complainButton.setBackground(null);
			serverCardLayout.show(pCenterPanel, "queryPersional");
		}
		if (e.getSource() == complainButton) {
			complainButton.setBackground(Color.getHSBColor(0 ,100 ,97));
			persionalManager.setBackground(null);
			property.setBackground(null);
			serverCardLayout.show(pCenterPanel, "complainManager");
		}
	}
	
	
	
}
