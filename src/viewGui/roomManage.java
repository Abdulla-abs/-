package viewGui;

import java.awt.BorderLayout;
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
public class roomManage extends JPanel implements ActionListener{
	
	JComboBox<String> buildBox = null;
	JComboBox<String> floorBox = null;
	JComboBox<String> roomBox = null;
	JList<String> list = null;
	JScrollPane scrollPane = null;
	JButton changeButton = null;
	JButton addButton = null;
	
	public roomManage() {
		// TODO Auto-generated constructor stub
		buildBox = new JComboBox<String>();
		floorBox = new JComboBox<String>();
		roomBox = new JComboBox<String>();
		list = new JList<String>();
		scrollPane = new JScrollPane();
		changeButton = new JButton("信息有误？更改");
		addButton = new JButton("新增？");
		
		buildBox.addItem("一栋");
		floorBox.addItem("1楼");
		roomBox.addItem("101");
		
		Box topBox = Box.createHorizontalBox();
		topBox.add(buildBox);
		topBox.add(floorBox);
		topBox.add(roomBox);
		scrollPane.add(list);
		Box bottomBox = Box.createHorizontalBox();
		bottomBox.add(changeButton);
		bottomBox.add(addButton);
		
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder("房间管理："));
		add(topBox,BorderLayout.NORTH);
		add(scrollPane);
		add(bottomBox,BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
