package viewGui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import propertyClass.Complain;
import querySql.complainQuery;

@SuppressWarnings("serial")
public class complainManager extends JPanel implements ActionListener {

	JLabel leftJLabel = null;
	JList<String> leftList = null;
	JScrollPane leftJScrollPane = null;

	JLabel rightJLabel = null;
	JComboBox<String> yearBox = null;
	JComboBox<String> sesonBox = null;
	JList<String> rightJList = null;
	JScrollPane rightJScrollPane = null;

	JButton refreshButton = null;

	LoginView loginView;

	boolean press = false;

	public complainManager() {
		// TODO Auto-generated constructor stub
		leftJLabel = new JLabel("未通过的投诉：");
		leftList = new JList<String>();
		leftJScrollPane = new JScrollPane(leftList);
		leftList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				System.out.println(e.getValueIsAdjusting());
				if (!e.getValueIsAdjusting() && press == true) {
					press = false;
					JDialog jDialog = new JDialog(loginView, true);
					Box box = Box.createVerticalBox();
					TextArea qtextArea = new TextArea();
					TextArea wTextArea = new TextArea();
					JButton jButton = new JButton("通过此投诉");
					qtextArea.setFocusable(false);
//					wTextArea.setFocusable(false);
					box.add(qtextArea);
					box.add(wTextArea);
					box.add(jButton);

					jDialog.add(box);

//					List<Complain> nopassList = new complainQuery().noPassComplainQuery();
					String[] get = leftList.getSelectedValue().split(" ");
					List<Complain> complainslist = new complainQuery().idQueryComplain(Integer.valueOf(get[0]));
					qtextArea.setText(complainslist.get(0).getContent());

					jButton.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							boolean flag = new complainQuery().submitComplainQuery(wTextArea.getText(), Integer.valueOf(get[0]));
							if (flag) {
								JOptionPane.showMessageDialog(null, "此投诉已通过");
							}else {
								JOptionPane.showMessageDialog(null, "投诉通过失败，检查系统");
							}
						}
					});
//					wTextArea.setText(complainslist.get(0));
					jDialog.setSize(475, 400);
					jDialog.setTitle("Details:");
					jDialog.setLocationRelativeTo(null);
					jDialog.setVisible(true);
					return;
				}

//				a = list.getSelectedValue();
//				System.out.println(list.getSelectedValue());
//				System.out.println(list.getVisibleRowCount());
//				System.out.println(count);
//				System.out.println("当前选中："+list.getSelectedValue().charAt(0));
//				System.out.println(list.getSelectedValue().charAt(list.getSelectedValue().indexOf("：")+1));
			}
		});
		leftList.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				press = true;
			}
		});

		rightJLabel = new JLabel("查询已通过的投诉：");
		rightJList = new JList<String>();
		rightJScrollPane = new JScrollPane(rightJList);

		yearBox = new JComboBox<String>();
		sesonBox = new JComboBox<String>();
		yearBox.addItem("2020 年");
		yearBox.addItem("2021 年");
		sesonBox.addItem("7 月");
		sesonBox.addItem("8 月");

		refreshButton = new JButton("刷新");
		refreshButton.addActionListener(this);

		JPanel comBox = new JPanel();
		comBox.add(yearBox);
		comBox.add(sesonBox);
		comBox.setBorder(BorderFactory.createLineBorder(Color.black));

		Box leftBox = Box.createVerticalBox();
		leftBox.add(leftJLabel);
		leftBox.add(leftJScrollPane);

		Box rightBox = Box.createVerticalBox();
		rightBox.add(rightJLabel);
		rightBox.add(comBox);
		rightBox.add(rightJScrollPane);
		rightJScrollPane.setPreferredSize(new Dimension(450, 420));

		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder("ComplainManange"));
		add(leftBox);
		add(rightBox, BorderLayout.EAST);
		add(refreshButton, BorderLayout.SOUTH);
	}

	private void complainRefresh() {
		// TODO Auto-generated method stub
		Vector<String> complainVector = new Vector<String>();
		List<Complain> complainslist = new complainQuery().noPassComplainQuery();
		for (Complain complain : complainslist) {
			complainVector.addElement(String.valueOf(complain.getId()) + " 投诉来自ID：" + complain.getFromid() + " 投诉时间"
					+ complain.getDate());
		}
		leftList.setListData(complainVector);
	}
	
	private void queryHisComRe(int year,int month) {
		// TODO Auto-generated method stub
		Vector<String> complainVector = new Vector<String>();
		List<Complain> complainslist = new complainQuery().dateQueryPassComplains(year, month);
		for (Complain complain : complainslist) {
			complainVector.addElement(String.valueOf(complain.getId()) + " 投诉来自ID：" + complain.getFromid() + " 投诉时间"
					+ complain.getDate());
		}
		rightJList.setListData(complainVector);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == refreshButton) {
			complainRefresh();
			Object year = yearBox.getSelectedItem();
			String[] yearStrings = year.toString().split(" ");
//			System.out.println(yearStrings[0]);
			Object month = sesonBox.getSelectedItem();
			String[] monthStrings = month.toString().split(" ");
			queryHisComRe(Integer.valueOf(yearStrings[0]), Integer.valueOf(monthStrings[0]));
//			System.out.println(year);
		}
		
	}

}
