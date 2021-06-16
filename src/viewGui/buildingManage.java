package viewGui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class buildingManage extends JPanel implements ActionListener{
	
	JComboBox<String> comboBox = null;
	JList<String> list = null;
	JScrollPane scrollPane = null;
	JButton changeButton = null;
	JButton addButton = null;
	
	public buildingManage() {
		// TODO Auto-generated constructor stub
		comboBox = new JComboBox<String>();
		list = new JList<String>();
		scrollPane = new JScrollPane();
		changeButton = new JButton("信息有误？更改");
		addButton = new JButton("新增？");
		
		comboBox.addItem("1号楼");
		scrollPane.add(list);
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.black));
		Box box = Box.createHorizontalBox();
		box.add(changeButton);
		box.add(addButton);
		
		changeButton.setPreferredSize(new Dimension(550, 35));
		
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder("楼房管理："));
		add(comboBox,BorderLayout.NORTH);
		add(scrollPane);
		add(box,BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
