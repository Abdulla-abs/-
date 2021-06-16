package viewGui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import querySql.*;
//import servers.RemeUse;
import utils.NamAndPasRoo;

@SuppressWarnings("serial")
public class LoginView extends JFrame implements ActionListener,ItemListener{
	
	JFrame frame = null;
	
	JMenu menu,switchSkin;
	JMenuItem mainMenuItem,countChange,metalFeel,windowFeel,nimbusFell,classicWindowFeel;
	
	CardLayout centerCarder;
	
	personnelAdministration pAdminWindow;
	LoginWindow logingWindow;
	mainLoginView mainView;

	JPanel centerJPanel;
	
	LoginView() {
		
		super("Login...");
		frame = new JFrame();
		
		JMenuBar jMenuBar = new JMenuBar();
		menu = new JMenu("²Ëµ¥");
		switchSkin = new JMenu("´°¿Ú·ç¸ñ");
		
		
		mainMenuItem = new JMenuItem("ÕËºÅ×¢²á");
		countChange = new JMenuItem("ÇÐ»»µÇÂ½");
		metalFeel = new JMenuItem("Ä¬ÈÏ·ç¸ñ");
		windowFeel = new JMenuItem("Widows·ç¸ñ");
		nimbusFell = new JMenuItem("Ë®¾§·ç¸ñ");
		classicWindowFeel = new JMenuItem("¾­µäWindows·ç");
		
//		mainMenuItem.addItemListener(this);
		mainMenuItem.addActionListener(this);
		countChange.addActionListener(this);
		metalFeel.addActionListener(this);
		windowFeel.addActionListener(this);
		nimbusFell.addActionListener(this);
		classicWindowFeel.addActionListener(this);
		
		switchSkin.setVisible(false);
		countChange.setVisible(false);
//		personlAddItem.setVisible();
		menu.add(mainMenuItem);
		menu.add(countChange);
		
		switchSkin.add(metalFeel);
		switchSkin.add(windowFeel);
		switchSkin.add(nimbusFell);
		switchSkin.add(classicWindowFeel);
//		personlAddItem.addActionListener(this);
		jMenuBar.add(menu);
		jMenuBar.add(switchSkin);

		
		centerCarder = new CardLayout();
		centerJPanel = new JPanel(centerCarder);
		
		logingWindow = new LoginWindow();
		pAdminWindow = new personnelAdministration();
		
		
		centerJPanel.add("LoginWindow", logingWindow);
		//centerJPanel.add("personnelAdministration", pAdminWindow);
//		centerJPanel.add("mainLoginView",mainView);

		
		add(centerJPanel,BorderLayout.CENTER);
		add(jMenuBar,BorderLayout.NORTH);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);;
		setLocationRelativeTo(null);
		setBounds(150, 50, 1200, 700);
	}
	
	class LoginWindow extends JPanel implements ActionListener{
		
		JLabel welcomLabel;
		JLabel nameLabel;
		JLabel passLabel;
		JTextField idField;
		JTextField passField;
		JButton loginButton;
		
		public LoginWindow() {
			welcomLabel = new JLabel("»¶Ó­µÇÂ¼...",JLabel.CENTER);
			nameLabel = new JLabel("                   ÓÃ»§ID:");
			passLabel = new JLabel("                   ÃÜÂë:");
			welcomLabel.setFont(new Font("»¶Ó­µÇÂ¼", Font.BOLD+Font.ITALIC, 22));
			nameLabel.setFont(new Font("name", Font.BOLD, 18));
			passLabel.setFont(new Font("name", Font.BOLD, 18));
			idField = new JTextField("00000002",20);
			passField = new JPasswordField("12005280323",20);
			loginButton = new JButton("µÇÂ¼");

			Box leftBox = Box.createVerticalBox();
			leftBox.add(Box.createVerticalStrut(85));
			leftBox.add(nameLabel);
			leftBox.add(Box.createVerticalStrut(85));
			leftBox.add(passLabel);
			leftBox.setPreferredSize(new Dimension(200, getComponentCount()));
			
			Box namefeildBox = Box.createHorizontalBox();
			namefeildBox.add(idField);
			Box passfeildBox = Box.createHorizontalBox();
			passfeildBox.add(passField);
			Box centerBox = Box.createVerticalBox();
			centerBox.add(Box.createVerticalStrut(85));
			centerBox.add(namefeildBox);
			centerBox.add(Box.createVerticalStrut(85));
			centerBox.add(passfeildBox);
			centerBox.add(Box.createVerticalStrut(45));
			centerBox.add(loginButton);

			JPanel jPanel = new JPanel();
			jPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			jPanel.add(centerBox,FlowLayout.LEFT);
			
			setLayout(new BorderLayout());
			add(leftBox,BorderLayout.WEST);
			add(jPanel);
			add(welcomLabel,BorderLayout.NORTH);
			loginButton.addActionListener(this);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			//int root = 0;
			if (e.getSource() == loginButton) {
//				System.out.println("Success");
//				centerCarder.show(centerJPanel,"mainLoginView" );
//				personlAddItem.setVisible(true);
				ResultSet s = new UserQuery().login(idField.getText(),passField.getText());

				if (s!=null) {
					//NamAndPasRoo perRe = new NamAndPasRoo();
					try {
						while (s.next()) {
							//root = s.getInt("rootid");
							NamAndPasRoo.userid = s.getString("userid");
							NamAndPasRoo.password = s.getString("password");
							NamAndPasRoo.rootid = s.getInt("rootid");
							NamAndPasRoo.username = s.getString("username");
							NamAndPasRoo.sex = s.getString("sex");
							mainView = new mainLoginView();
							countChange.setVisible(true);
							switchSkin.setVisible(true);
							nimbusFell.setVisible(false);
							mainMenuItem.setVisible(false);
							countChange.setVisible(true);
							centerJPanel.add("mainLoginView",mainView);
							centerCarder.show(centerJPanel,"mainLoginView" );
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
			
		}
		
	}

	public static void main(String[] args) {
		new LoginView();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String lookAndFeel;

		if (e.getSource() == metalFeel) {
			System.out.println("true");
			lookAndFeel = "javax.swing.plaf.metal.MetalLookAndFeel";
			try {
				UIManager.setLookAndFeel(lookAndFeel);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedLookAndFeelException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			SwingUtilities.updateComponentTreeUI(this);
		}
		if (e.getSource() == windowFeel) {
			lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
			try {
				UIManager.setLookAndFeel(lookAndFeel);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedLookAndFeelException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			SwingUtilities.updateComponentTreeUI(this);
		}
		if (e.getSource() == nimbusFell) {
			lookAndFeel = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
			try {
				UIManager.setLookAndFeel(lookAndFeel);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedLookAndFeelException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			SwingUtilities.updateComponentTreeUI(this);
		}
		if (e.getSource() == classicWindowFeel) {
			lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel";
			try {
				UIManager.setLookAndFeel(lookAndFeel);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedLookAndFeelException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			SwingUtilities.updateComponentTreeUI(this);
		}
		if (e.getSource() == countChange) {
			new LoginView();
			setVisible(false);
//			System.exit(0);
		}
		if (e.getSource() == mainMenuItem) {
			
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		System.out.println("s");
		
	}
	
}
