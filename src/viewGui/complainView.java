package viewGui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import propertyClass.Complain;
import propertyClass.RoomId;
import querySql.RoomQuery;
import querySql.complainQuery;
import utils.*;

@SuppressWarnings("serial")
public class complainView extends JPanel implements ActionListener {
	
	LoginView loginView = null;

	JLabel label = null;
	JLabel hisJLabel = null;
	TextArea complainTextArea = null;
	JButton jButton = null;
	JList<String> list = null;
	JButton viewButton = null;

	GridLayout gridLayout = null;
	BorderLayout leftLayout = null;
	BorderLayout rightLayout = null;

	JPanel leftPanel = null;
	JPanel rightPanel = null;
	JScrollPane jScrollPane = null;

	boolean press = false;
	String a = null;
	long select;
	int count;
	public complainView() {

		gridLayout = new GridLayout(1, 2);
		leftLayout = new BorderLayout();
		rightLayout = new BorderLayout();

		leftPanel = new JPanel(leftLayout);
		rightPanel = new JPanel(rightLayout);
		list = new JList<String>();
		jScrollPane = new JScrollPane(list);

		// Box leftBox = Box.createVerticalBox();
		label = new JLabel("遇到问题?");
		hisJLabel = new JLabel("历史投诉:");
		complainTextArea = new TextArea("在这里描述你所遇到的问题...");
		jButton = new JButton("提交");
		viewButton = new JButton("查看此投诉的详细信息");

		new changeFont().changefontBold(label, hisJLabel);
		
		jButton.addActionListener(this);

		Box centerBox = Box.createVerticalBox();
		centerBox.add(complainTextArea);
		centerBox.add(jButton);

		leftPanel.add(label, BorderLayout.NORTH);
		leftPanel.add(centerBox);
		leftPanel.add(jButton, BorderLayout.SOUTH);

		rightPanel.add(hisJLabel, BorderLayout.NORTH);
		rightPanel.add(jScrollPane);
		rightPanel.add(viewButton, BorderLayout.SOUTH);

		// Container container = getParent();

		// list.addMouseListener((MouseListener) this);
//		list.addListSelectionListener(new ListSelectionListener() {
//			
//			@Override
//			public void valueChanged(ListSelectionEvent e) {
//				JFrame jFrame = new JFrame();
//				jFrame.setVisible(true);
//				jFrame.setSize(200,150);
//				jFrame.setLocationRelativeTo(new complainView());
//				Dialog dialog = new Dialog(jFrame);
//				dialog.setTitle("Tips:");
//			}
//		});

		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {			
				System.out.println(e.getValueIsAdjusting());
				if (!e.getValueIsAdjusting() && press == true) {
					press = false;
					return;
				}
				
				a = list.getSelectedValue();
				System.out.println(list.getSelectedValue());
				System.out.println(list.getVisibleRowCount());
				System.out.println(count);
				System.out.println("当前选中："+list.getSelectedValue().charAt(0));
				System.out.println(list.getSelectedValue().charAt(list.getSelectedValue().indexOf("：")+1));
			}
		});
		list.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				press = true;
			}
		});
		viewButton.addActionListener(this);

		setLayout(gridLayout);
//		add(label,BorderLayout.NORTH);
//		add(centerBox,BorderLayout.CENTER);
		add(leftPanel);
		add(rightPanel);

		leftPanel.setBorder(BorderFactory.createTitledBorder("投诉"));
		rightPanel.setBorder(BorderFactory.createTitledBorder("历史"));
		setBorder(BorderFactory.createTitledBorder("投诉:"));
		hisComplainReflash();
	}
	
	private void hisComplainReflash() {
		// TODO Auto-generated method stub
		String string = new String();
//		int i = 1;
		Vector<String> persionalHire = new Vector<String>();
		List<Complain> hireRoomList = new complainQuery().idComplainQuery();
		System.out.println(hireRoomList == null ? "y" : "f");
		for (Complain complain : hireRoomList) {
			string = complain.getId()+" 投诉时间："+complain.getDate()+"   是否解决："+complain.getIssolve();
			persionalHire.addElement(string);
		}
		//System.out.println(hireRoomList.get(list.getSelectedIndex()+1).getId());

		list.setListData(persionalHire);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		if (e.getSource() == list) {
//			System.out.println(list.getSelectedValue());
//			System.out.println("Success");
//		}
		if (e.getSource() == jButton) {
			boolean flag =  new complainQuery().submitComplain(complainTextArea.getText());
			if (flag) {
				JOptionPane.showMessageDialog(null, "投诉成功！物业将会尽快解决您的问题！");
			}else {
				JOptionPane.showMessageDialog(null, "投诉失败！请稍后再试！");
			}
		}
		
		JDialog jDialog = new JDialog(loginView,true);
		if (e.getSource() == viewButton) {
			System.out.println("Success");
			//JFrame jFrame = new JFrame();
			//System.out.println(list.getSelectedValue());
			//JDialog jDialog = new JDialog(loginView,"",true);
			
			if (list.getSelectedValue()==null) {
				jDialog.setTitle("Tips:");
				JLabel jLabel = new JLabel("To choose one and start view");
				jDialog.add(jLabel);
				jDialog.setSize(200,150);
			}else {
				Box box = Box.createVerticalBox();
				TextArea qtextArea = new TextArea();
				TextArea wTextArea = new TextArea();
//				qtextArea.setEditable(false);
//				wTextArea.setEditable(false);
				qtextArea.setFocusable(false);
				wTextArea.setFocusable(false);
				box.add(qtextArea);
				box.add(wTextArea);
	
				jDialog.add(box);
//				List<Complain> hireRoomList = new complainQuery().idComplainQuery();
				String[] aString = list.getSelectedValue().split(" ");
				List<Complain> context = new complainQuery().idQueryComplain(Integer.valueOf(aString[0]));
				qtextArea.setText(context.get(0).getContent());
				wTextArea.setText(context.get(0).getReply());

//				qtextArea.setText(hireRoomList.get(Integer.valueOf(String.valueOf(list.getSelectedValue().charAt(0)))-1).getContent());
//				wTextArea.setText(hireRoomList.get(Integer.valueOf(String.valueOf(list.getSelectedValue().charAt(0)))-1).getReply());
				//List<Complain> qlist = new complainQuery().idQueryComplain(list.getSelectedValue().charAt(list.getSelectedValue().indexOf("：")+1));
//				select = hireRoomList2.get(list.getSelectedIndex()+1).getId();
//				List<Complain> hireRoomList = new complainQuery().idQueryComplain(select);
//				System.out.println(hireRoomList == null ? "y" : "f");
////				long id = hireRoomList.get(list.getSelectedIndex()+1).getId();
//				qtextArea.setText(qlist.get(0).getContent());
//				wTextArea.setText(qlist.get(0).getReply());
				jDialog.setSize(375, 375);
				jDialog.setTitle("Details:");
			}

			jDialog.setLocationRelativeTo(null);
			jDialog.setVisible(true);
			
			//jDialog.pack();
		}
	}

//	@Override
//	public void valueChanged(ListSelectionEvent e) {
//		System.out.println(list.getSelectedValue());
//	}

//	class listListener implements ListSelectionListener{
//		boolean press = false;
//	@Override
//	public void valueChanged(ListSelectionEvent e) {
//		System.out.println(e.getValueIsAdjusting());
//		if (!e.getValueIsAdjusting() && press == true) {
//			press = false;
//			return;
//		}
//		
//	}
//
//		
//		
//	}

}
