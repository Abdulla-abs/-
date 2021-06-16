package viewGui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import propertyClass.Fee;
import propertyClass.RoomId;
import querySql.FeeQuery;
import querySql.RoomQuery;
import utils.NamAndPasRoo;
import utils.check;

@SuppressWarnings("serial")
public class feeListView extends JPanel implements ActionListener,DocumentListener{
	//GridBagLayout gridBagLayout = new GridBagLayout(1,2);
	JPanel leftJPanel = null;
	JPanel rightJPanel = null;
	JComboBox<String> comboBox1 = null;
	JComboBox<String> comboBox2 = null;
	JComboBox<String> comboBox3 = null;
	JList<String> list = null;
	JScrollPane scrollPane = null;
	JButton refresh = null;
	JTextField textField = null;
	JButton queryButton = null;
	String[][] datas = {};
	String[] userTitle = {"物业费", "房租", "停车费", "水费", "场地费"};
	JScrollPane jScrollPane = null;
	DefaultTableModel model = null;
	JTable table = null;
	JButton payment = null;
	private int height;
	private double[] countfee = new double[1];
	
	public feeListView() {
		
		
		leftJPanel = new JPanel(new BorderLayout());
		rightJPanel = new JPanel(new BorderLayout());
		comboBox1 = new JComboBox<String>();
		comboBox2 = new JComboBox<String>();
		comboBox3 = new JComboBox<String>();
		list = new JList<String>();
		scrollPane = new JScrollPane(list);
		refresh = new JButton("刷新");
		textField = new JTextField(8);
		queryButton = new JButton("查询");
		
		//textField.getDocument().addDocumentListener(this);
		
		refresh.addActionListener(this);
		queryButton.addActionListener(this);

		model = new DefaultTableModel(datas,userTitle);
		table = new JTable(model);
		jScrollPane = new JScrollPane(table);
		payment = new JButton("付款");
		comboBox1.addItem("朝阳东栋");
		//comboBox1.addItem("2区");
		comboBox2.addItem("1栋");
		//comboBox2.addItem("2栋");
		comboBox3.addItem("1楼");
		comboBox3.addItem("2楼");
		
		comboBox3.addActionListener(this);
		payment.addActionListener(this);
		
//		for (int i = 0; i < 8; i++) {
//			Object[] a = new Object[5];
//			a[0] = 1;
//			a[1] = 1;
//			a[2] = 1;
//			a[3] = 1;
//			a[4] = 1;
//			model.addRow(a);
// 		}
		
		setLayout(new GridLayout(1,2));
		setBorder(BorderFactory.createTitledBorder("Fee List:"));
		
		Box leftTopBox = Box.createHorizontalBox();
		leftTopBox.add(comboBox1);
		leftTopBox.add(comboBox2);
		leftTopBox.add(comboBox3);
		leftJPanel.add(leftTopBox,BorderLayout.NORTH);
		leftJPanel.add(scrollPane);
		leftJPanel.add(refresh,BorderLayout.SOUTH);
		
		Box rightTopBox = Box.createHorizontalBox();
		rightTopBox.add(textField);
		rightTopBox.add(queryButton);
		rightJPanel.add(rightTopBox,BorderLayout.NORTH);
		rightJPanel.add(jScrollPane);
		rightJPanel.add(payment,BorderLayout.SOUTH);
		
		leftJPanel.setBorder(BorderFactory.createTitledBorder("搜索:"));
		rightJPanel.setBorder(BorderFactory.createTitledBorder("查询:"));
		
		add(leftJPanel);
		add(rightJPanel);
		
//		JFrame jFrame = new JFrame();
//		jFrame.pack();
//		jFrame.setVisible(true);
//		jFrame.add(this);
		backHireRefresh(0);
	}
	
	private void backHireRefresh(int limite) {
		String string = new String();
		Vector<String> persionalHire = new Vector<String>();
		List<RoomId> hireRoomList = new RoomQuery().hireAndOwenQuery(limite);
		System.out.println(hireRoomList==null?"y":"f");
		for (RoomId roomId : hireRoomList) {
			string = "租有或拥有的房间 ： 房间ID：" + roomId.getRoomId() + "    所属楼房：" + roomId.getRoombelong() + "    房间层数："
					+ roomId.getHeight();
			persionalHire.addElement(string);
		}
		
		list.setListData(persionalHire);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == comboBox3) {
			switch ((String) comboBox3.getSelectedItem()) {
			case "所有": {
				height = 0;
				backHireRefresh(height);
				break;
			}
			case "1楼": {
				height = 1;
				backHireRefresh(height);
				break;
			}
			case "2楼": {
				height = 2;
				backHireRefresh(height);
				break;
			}
			}
			System.out.println("t" + comboBox3.getSelectedItem());
			System.out.println(height);
		}
		
		if (e.getSource() == refresh) {
			backHireRefresh(height);
		}
		
		if (e.getSource() == queryButton) {
			boolean flag = new check().checkString(textField.getText());
			if (flag) {
				JOptionPane.showMessageDialog(null, "输入有误，请检查拼写！");
			}else {
				List<Fee> feelist = new FeeQuery().feeQuery(textField.getText().trim());
				if (model.getRowCount() > 0) {
					while (true) {
						model.removeRow(0);
						if (model.getRowCount() == 0) {
							break;
						}
					}
				}
				for (Fee fee : feelist) {
					Object[] object = new Object[5];
					object[0] = fee.getProperty_costs();
					object[1] = fee.getRent();
					object[2] = fee.getParking();
					object[3] = fee.getWater();
					object[4] = fee.getPlace();
					countfee = fee.feeCount();
					model.addRow(object);
				}
			}
		}
		if (e.getSource() == payment) {
			if (countfee[0]!=0.0) {
				JOptionPane.showMessageDialog(null, "本月费用总额为："+countfee[0]+",你已付清");
			}else {
				JOptionPane.showMessageDialog(null, "请先查询！");
			}
		}
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}
	
//	public static void main(String[] args) {
//		new feeListView();
//	}

}
