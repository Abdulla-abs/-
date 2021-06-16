package viewGui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import propertyClass.Area;
import querySql.AreaMassageQuery;


@SuppressWarnings("serial")
public class propertyManage extends JPanel implements ActionListener,ItemListener{
	JMenuBar bar= null;
	JMenu menu = null;
	JMenuItem item1= null;
	JMenuItem item2= null;
	JMenuItem item3= null;
	
	JPanel cPanel = null;
	
	propertyPanel properPanel = null;
	buildingManage buildingManage = null;
	roomManage roomManage = null;
	
	CardLayout card = null;
	
 public propertyManage() {
		// TODO Auto-generated constructor stub
	 
		card = new CardLayout();

		setLayout(new BorderLayout());
		
		
		properPanel= new propertyPanel();
		buildingManage = new buildingManage();
		roomManage = new roomManage();
		
		cPanel = new JPanel(card);
		
		bar = new JMenuBar();
		menu = new JMenu("信息");
		item1 = new JMenuItem("小区");
		item2 = new JMenuItem("房产");
		item3 = new JMenuItem("房间");
		menu.add(item1);
		menu.add(item2);
		menu.add(item3);
		bar.add(menu);
		menu.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		
		//menu.addItemListener(this);
		item1.addActionListener(this);
		item2.addActionListener(this);
		item3.addActionListener(this);
		
		cPanel.add(properPanel,"propertypanel");
		cPanel.add(buildingManage,"buildingpanel");
		cPanel.add(roomManage,"roompanel");
		add(bar,BorderLayout.NORTH);
		//setLayout(new BorderLayout());
		add(cPanel);
		
		
		
	}
	
	public class propertyPanel extends JPanel implements ActionListener{
		

		JPanel panel = null;

		JLabel idLabel = null;
		JLabel idText = null;
		JTextField idField = null;
		JLabel nameLabel =  null;
		JLabel nameText = null;
		JTextField nameField = null;
		JLabel allBuiLabel = null;
		JLabel allBuiText = null;
		JTextField allField = null;
		JLabel buiLabel = null;
		JLabel buiText = null;
		JTextField buiField = null;
		JLabel viJLabel = null;
		JLabel vilText = null;
		JTextField vilField = null;
//		JTextField field = null;

		JButton queryButton = null;
		JButton refreshButton = null;
		JButton okButton = null;
		public propertyPanel() {
			// TODO Auto-generated constructor stub
			panel = new JPanel(new GridLayout(12,1));
			
			idLabel = new JLabel("小区ID:");
			idText = new JLabel();
			idField = new JTextField();
			idField.setVisible(false);

			nameLabel = new JLabel("小区名称:");
			nameText = new JLabel();
			nameField = new JTextField(4);
			nameField.setVisible(false);
			
			allBuiLabel = new JLabel("所有建筑总数:");
			allBuiText = new JLabel();
			allField = new JTextField();
			allField.setVisible(false);
			
			buiLabel = new JLabel("大楼数:");
			buiText = new JLabel();
			buiField = new JTextField();
			buiField.setVisible(false);
			
			viJLabel = new JLabel("别墅数:");
			vilText = new JLabel();
			vilField = new JTextField();
			vilField.setVisible(false);
			
			
			queryButton = new JButton("信息有误？点击修改");
			refreshButton = new JButton("刷新信息");
			
			queryButton.addActionListener(this);
			refreshButton.addActionListener(this);
			
			Box idBox = Box.createHorizontalBox();
			idBox.add(idLabel);
			idBox.add(idText);
			idBox.add(idField);
			Box nameBox = Box.createHorizontalBox();
			nameBox.add(nameLabel);
			nameBox.add(nameText);
			nameBox.add(nameField);
			Box allbuiBox = Box.createHorizontalBox();
			allbuiBox.add(allBuiLabel);
			allbuiBox.add(allBuiText);
			allbuiBox.add(allField);
			Box buiBox = Box.createHorizontalBox();
			buiBox.add(buiLabel);
			buiBox.add(buiText);
			buiBox.add(buiField);
			Box vilBox = Box.createHorizontalBox();
			vilBox.add(viJLabel);
			vilBox.add(vilText);
			vilBox.add(vilField);
			Box buttonBox = Box.createHorizontalBox();
			buttonBox.add(queryButton);
			buttonBox.add(refreshButton);
			
			panel.add(idBox);
			panel.add(nameBox);
			panel.add(allbuiBox);
			panel.add(buiBox);
			panel.add(vilBox);
			//panel.add(buttonBox);

			setLayout(new BorderLayout());
			add(panel);
			add(buttonBox,BorderLayout.SOUTH);
			//queryButton.addActionListener(this);
			setBorder(BorderFactory.createTitledBorder("小区信息管理："));
			AreaMassageRefresh();
		}
		
		private void AreaMassageRefresh() {
			// TODO Auto-generated method stub
			List<Area> list = new AreaMassageQuery().areaQuery();
			for (Area area : list) {
				idText.setText(area.getAreaid());
				nameText.setText(area.getName());
				allBuiText.setText(String.valueOf(area.getHavingall()));
				buiText.setText(String.valueOf(area.getBuilding()));
				vilText.setText(String.valueOf(area.getVilla()));
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == refreshButton) {
				AreaMassageRefresh();
				idText.setVisible(true);
				idField.setVisible(false);
				nameText.setVisible(true);
				nameField.setVisible(false);
				allBuiText.setVisible(true);
				allField.setVisible(false);
				buiText.setVisible(true);
				buiField.setVisible(false);
				vilText.setVisible(true);
				vilField.setVisible(false);
			}
			if (e.getSource() == queryButton) {
				queryButton.setText("确定修改");
				if (idField.isVisible()) {
					new AreaMassageQuery().changeAreaQuery(idText.getText(), nameField.getText(), Integer.valueOf(allField.getText()),Integer.valueOf(buiField.getText()) , Integer.valueOf(vilField.getText()));
					queryButton.setText("信息有误？点击修改");
					return;
				}
				idField.setText(idText.getText());
				idText.setVisible(false);
				idField.setVisible(true);
				nameField.setText(nameText.getText());
				nameText.setVisible(false);
				nameField.setVisible(true);
				allField.setText(allBuiText.getText());
				allBuiText.setVisible(false);
				allField.setVisible(true);
				buiField.setText(buiText.getText());
				buiText.setVisible(false);
				buiField.setVisible(true);
				vilField.setText(vilText.getText());
				vilText.setVisible(false);
				vilField.setVisible(true);
			}
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == item1) {
			System.out.println("item1");
			card.show(cPanel, "propertypanel");
		}
		if (e.getSource() == item2) {
			System.out.println("item2");
			card.show(cPanel, "buildingpanel");
		}
		if (e.getSource() == item3) {
			System.out.println("item3");
			card.show(cPanel, "roompanel");
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
//		if (item1.isSelected()) {
//			//cPanel.setVisible(true);
//			System.out.println("xuanxiang");
//		}
//		if (e.getSource() == item1) {
//			System.out.println("xuanxiang");
//		}
		
	}
	
	

}
