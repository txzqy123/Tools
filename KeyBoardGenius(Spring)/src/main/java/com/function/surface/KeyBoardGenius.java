package com.function.surface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import com.function.ProgramMain;
import com.function.service.AtGroupAllUser;
import com.function.service.AtUserAllGroup;
import com.function.service.FileCompare;
import com.function.service.GetProxy;
import com.function.service.KeySprite;
import com.function.service.MailSender;
import com.function.service.ReadGroupInfo;
import com.function.service.UserGroupList;
import com.function.util.StaticParam;

@Component("keyBoardGenius")
public class KeyBoardGenius extends JFrame {

	private static final Logger logger = LoggerFactory.getLogger(ProgramMain.class);
	
	/**
	 * 群总数Lable
	 */
	private JLabel lbGroupCount;
	private JTextField txGroupCount;
	
	/**
	 * 程序运行一圈等待时间
	 */
	private JLabel lbOneCircleWaitTime;
	private JTextField txOneCircleWaitTime;
	
	/**
	 * 程序点击@后等待时间
	 */
	private JLabel lbAtWaitTime;
	private JTextField txAtWaitTime;
	
	/**
	 * 按键后等待时间
	 */
	private JLabel lbPressWaitTime;
	private JTextField txPressWaitTime;
	
	/**
	 * 读取群信息
	 */
	private JButton readGroupInfo;
	
	/**
	 * 获取用户所有群
	 */
	private JButton userGroupList;
	
	/**
	 * 群地址
	 */
	private JLabel lbGroupAddress;
	private JTextField txGroupAddress;
	
	/**
	 * 登录Cookie
	 */
	private JLabel lbLoginCookie;
	private JTextField txLoginCookie;
	
	/**
	 * AT所有人
	 */
	private JButton atAllGroupUser;
	
	/**
	 * QQ群号
	 */
	private JLabel lbQQGroupCode;
	private JTextField txQQGroupCode;
	
	/**
	 * 数据文件地址
	 */
	private JLabel lbDataAddress;
	private JTextField txDataAddress;
	
	/**
	 * AT所有群
	 */
	private JButton atAllGroup;
	
	/**
	 * 邮件发送
	 */
	private JButton btnMailSender;
	
	/**
	 * 获取代理IP
	 */
	private JButton btnGetProxy;
	
	/**
	 * 文件内容比较获取
	 */
	private JButton btnFileCompare;
	
	private JLabel zdLable;
	private JButton button1;
	private JButton button2;
	private JTextField zdStorage;
	private JComboBox box;
	private JMenuBar menuBar;
	private JSlider slider;
	private JSpinner spinner;
	private JToolBar toolBar;
	private JFileChooser chooser;
	
	@Autowired
	private TaskExecutor taskExecutor;
	
	@Autowired
	private JavaMailSenderImpl mailSender1;
	@Autowired
	private JavaMailSenderImpl mailSender2;
	@Autowired
	private JavaMailSenderImpl mailSender3;
	@Autowired
	private JavaMailSenderImpl mailSender4;
	@Autowired
	private JavaMailSenderImpl mailSender5;
	@Autowired
	private JavaMailSenderImpl mailSender6;
	@Autowired
	private JavaMailSenderImpl mailSender7;
	@Autowired
	private JavaMailSenderImpl mailSender8;
	@Autowired
	private JavaMailSenderImpl mailSender9;
	@Autowired
	private JavaMailSenderImpl mailSender10;
	@Autowired
	private JavaMailSenderImpl mailSender11;

	public KeyBoardGenius() {

		super();
		this.setSize(500, 600);
		this.getContentPane().setLayout(null);// 设置布局控制器
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
			System.exit(0);
			}
		});

		// this.getContentPane().setLayout(new FlowLayout());//���ò��ֿ�����

		// this.getContentPane().setLayout(new
		// GridLayout(1,2));//���ò��ֿ�����,��Ҫ����趨��������Ŀ

		// this.getContentPane().setLayout(new
		// BorderLayout());//���ò��ֿ�������North,South,West,East��4���ƿؼ�����

		// this.getContentPane().setLayout(new GridBagLayout());//���ò��ֿ�����
		//
		// this.add(this.getJdTextField(),null);//����ı���
		// this.add(this.getZdTextField(),null);//����ı���
		// //
		 this.add(this.getMailSenderButton(),null);//��Ӱ�ť
		 this.add(this.getButton2(),null);//��Ӱ�ť
		 this.add(this.getGroupCountLabel(),null);//��Ӱ�ť
		 this.add(this.getGroupCountTextField(),null);//��Ӱ�ť
		 this.add(this.getOneCircleWaitTimeLabel(),null);//��Ӱ�ť
		 this.add(this.getOneCircleWaitTimeTextField(),null);//��Ӱ�ť
		 this.add(this.getAtWaitTimeLabel(),null);//��Ӱ�ť
		 this.add(this.getAtWaitTimeTextField(),null);//��Ӱ�ť
		 this.add(this.getPressWaitTimeLabel(),null);//��Ӱ�ť
		 this.add(this.getPressWaitTimeTextField(),null);//��Ӱ�ť
		 this.add(this.ReadGroupInfoBtn(),null);
		 
		 this.add(this.getLoginCookieLabel(),null);
		 this.add(this.getLoginCookieTextField(),null);
		 
		 this.add(this.getGroupAddressLabel(),null);
		 this.add(this.getGroupAddressTextField(),null);
		 
		 this.add(this.GetAtAllGroupUserBtn(),null);
		 this.add(this.UserGroupListBtn(),null);
		 
		 this.add(this.getQQGroupCodeLabel(),null);
		 this.add(this.getQQGroupCodeTextField(),null);
		 
		 this.add(this.getDataAddressLabel(),null);
		 this.add(this.getDataAddressTextField(),null);
		 
		 this.add(this.GetatAllGroupButton(),null);
		 this.add(this.GetGetProxyBtn(),null);
		 
		 this.add(this.GetFileCompareBtn(),null);
		 
		 
		// //GetGetProxyBtn
		// this.add(this.getJdLabel(),null);//��ӱ�ǩ
		// this.add(this.getZdLabel(),null);//��ӱ�ǩ
		// //
		// // this.add(this.getBox(),null);//�����-�б��
		//
		// this.add(this.getChooser(),null);//����ļ�ѡ��

		// this.setJMenuBar(this.getMenu());//��Ӳ˵�

		// this.add(this.getSlider(),null);
		 
		// this.add(this.getSpinner(),null);
		// this.add(this.getToolBar(),null);
		this.setTitle("按键精灵");// ���ô��ڱ���
		//KeySprite.run();
		//Toolkit tk = Toolkit.getDefaultToolkit();
		//tk.addAWTEventListener(new KeyPressListener(), AWTEvent.KEY_EVENT_MASK); 
	}

	/**
	 * 文件选择
	 * @return
	 */
	private JFileChooser getChooser() {

		if (chooser == null) {

			chooser = new JFileChooser(new File("c:\\"));
			chooser.setBounds(0, 0, 500, 400);
			chooser.setVisible(true);
			// chooser.setAcceptAllFileFilterUsed(false);
			chooser.setMultiSelectionEnabled(true);
			chooser.setApproveButtonText("���뵽��ݿ�");
			// int returnValue = chooser.showOpenDialog(null);
			// if(returnValue == JFileChooser.APPROVE_OPTION){
			//	    			
			// File[] files = chooser.getSelectedFiles();
			// for(int i=0;i<files.length;i++){
			// log.error(String.format("�ļ�%s��ʼ����",
			// files[i].getAbsoluteFile()));
			// ExcelUtil excel = new ExcelUtil();
			// List<InputValue> listValue =
			// excel.ReadExcel(files[i].getAbsolutePath());
			// List<RoadLinkBean> roadLinkList = MathUtil.getConfigRoadLink();
			// DBWriter db = new DBWriter();
			// for(int j=0;j<roadLinkList.size();j++){
			// List<MonitorDetectData> decectData =
			// db.getMonitor(roadLinkList.get(j));
			// List<InputValue> inputValueList =
			// MathUtil.computeInputValue(decectData,roadLinkList.get(j));
			// inputValueList =
			// MathUtil.setVideoValue(listValue,inputValueList);
			// db.addInput(inputValueList);
			// }
			// }
			// System.out.print("�������");
			// log.error("�������");
			// }
			//chooser.addActionListener(new chooserListener());// Ϊ��-�б����Ӽ�������
		}
		return chooser;
	}

//	private class chooserListener implements ActionListener {
//		public void actionPerformed(ActionEvent e) {
//			File[] files = chooser.getSelectedFiles();
//			for (int i = 0; i < files.length; i++) {
//				ExcelUtil excel = new ExcelUtil();
//				List<InputValue> listValue = excel.ReadExcel(files[i]
//						.getAbsolutePath());
//				List<RoadLinkBean> roadLinkList = MathUtil.getConfigRoadLink();
//				DBWriter db = new DBWriter();
//				for (int j = 0; j < roadLinkList.size(); j++) {
//
//					List<MonitorDetectData> decectData = db
//							.getMonitor(roadLinkList.get(j));
//					List<InputValue> inputValueList = MathUtil
//							.computeInputValue(decectData, roadLinkList.get(j));
//					inputValueList = MathUtil.setVideoValue(listValue,
//							inputValueList);
//					db.addInput(inputValueList);
//				}
//			}
//			System.out.print("�������");
//		}
//	}

	private JToolBar getToolBar() {
		if (toolBar == null) {
			toolBar = new JToolBar();
			toolBar.setBounds(103, 260, 71, 20);
			toolBar.setFloatable(true);
		}
		return toolBar;
	}

	private JSpinner getSpinner() {
		if (spinner == null) {
			spinner = new JSpinner();
			spinner.setBounds(103, 220, 80, 20);
			spinner.setValue(100);
		}
		return spinner;
	}

	private JSlider getSlider() {
		if (slider == null) {
			slider = new JSlider();
			slider.setBounds(103, 200, 100, 20);
			slider.setMaximum(100);
			slider.setMinimum(0);
			slider.setOrientation(0);
			slider.setValue(0);
		}
		return slider;
	}

	/**
	 * �˵��ļ���JMenuBar->JMenu->JMenuItem ����1��n�Ĺ�ϵ
	 * �����Ӳ˵��õ�SetJMenuBar����
	 * 
	 * @return ��b�õĲ˵�
	 */
	private JMenuBar getMenu() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			JMenu m1 = new JMenu();
			m1.setText("�ļ�");
			JMenu m2 = new JMenu();
			m2.setText("�༭");
			JMenu m3 = new JMenu();
			m3.setText("����");

			JMenuItem item11 = new JMenuItem();
			item11.setText("��");
			JMenuItem item12 = new JMenuItem();
			item12.setText("����");
			JMenuItem item13 = new JMenuItem();
			item13.setText("�˳�");

			JMenuItem item21 = new JMenuItem();
			item21.setText("����");
			JMenuItem item22 = new JMenuItem();
			item22.setText("����");
			JMenuItem item23 = new JMenuItem();
			item23.setText("����");

			JMenuItem item31 = new JMenuItem();
			item31.setText("��ӭ");
			JMenuItem item32 = new JMenuItem();
			item32.setText("����");
			JMenuItem item33 = new JMenuItem();
			item33.setText("�汾��Ϣ");

			m1.add(item11);
			m1.add(item12);
			m1.add(item13);

			m2.add(item21);
			m2.add(item22);
			m2.add(item23);

			m3.add(item31);
			m3.add(item32);
			m3.add(item33);

			menuBar.add(m1);
			menuBar.add(m2);
			menuBar.add(m3);
		}
		return menuBar;
	}

	/**
	 * ������-�б��
	 * 
	 * @return
	 */
	private JComboBox getBox() {
		if (box == null) {
			box = new JComboBox();
			box.setBounds(103, 140, 71, 27);
			box.addItem("1");
			box.addItem("2");
			box.addActionListener(new comboxListener());// Ϊ��-�б����Ӽ�������

		}
		return box;
	}

	private class comboxListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();
			System.out.println(o.toString());
		}
	}

//	/**
//	 * ��������ʵ��ActionListener�ӿڣ���Ҫʵ��actionPerformed����
//	 * 
//	 * @author HZ20232
//	 * 
//	 */
//	private class ExcelPrintButton implements ActionListener {
//		public void actionPerformed(ActionEvent e) {
//			MathUtil.printExcelProcess(jdStorage.getText().trim(), zdStorage
//					.getText().trim());
//		}
//	}
	

	/**
	 * �趨�ı���
	 * 
	 * @return
	 */
	private JTextField getZdTextField() {
		if (zdStorage == null) {
			zdStorage = new JTextField();
			zdStorage.setBounds(340, 410, 120, 20);
			zdStorage.setText("2015/4/2 15:26:00");
		}
		return zdStorage;
	}
	
	/**
	 * ���ñ�ǩ
	 * 
	 * @return ���úõı�ǩ
	 */
	private JLabel getZdLabel() {
		if (zdLable == null) {
			zdLable = new JLabel();
			zdLable.setBounds(240, 410, 100, 20);
			zdLable.setText("������ʱ�䣺");
			zdLable.setToolTipText("������ʱ��");
		}
		return zdLable;
	}
	
	/**
	 * ��������ʵ��ActionListener�ӿڣ���Ҫʵ��actionPerformed����
	 * 
	 * @author HZ20232
	 * 
	 */
	private class StartButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			StaticParam.runFlg = 0;
			taskExecutor.execute(new KeySprite(Integer.parseInt(txGroupCount.getText()),Integer.parseInt(txOneCircleWaitTime.getText()),Integer.parseInt(txAtWaitTime.getText()),
					Integer.parseInt(txPressWaitTime.getText())));
		}
	}
	
	/**
	 * ��������ʵ��ActionListener�ӿڣ���Ҫʵ��actionPerformed����
	 * 
	 * @author HZ20232
	 * 
	 */
	private class EndButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			StaticParam.runFlg = 1;
		}
	}
	
	/**
	 * 邮件发送Listener
	 * 
	 * @author HZ20232
	 * 
	 */
	private class MailSenderButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			StaticParam.runFlg = 0;
			List<JavaMailSenderImpl> listMail = new ArrayList<JavaMailSenderImpl>();
			listMail.add(mailSender1);
			listMail.add(mailSender2);
			listMail.add(mailSender3);
			listMail.add(mailSender4);
			listMail.add(mailSender5);
			listMail.add(mailSender6);
			listMail.add(mailSender7);
			listMail.add(mailSender8);
			listMail.add(mailSender9);
			listMail.add(mailSender10);
			listMail.add(mailSender11);
			taskExecutor.execute(new MailSender(listMail,txDataAddress.getText(),txQQGroupCode.getText(),Integer.parseInt(txPressWaitTime.getText())));
		}
	}

	
	/**
	 * 读取群信息Listener
	 * 
	 * @author HZ20232
	 * 
	 */
	private class ReadGroupInfoButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			taskExecutor.execute(new ReadGroupInfo(txGroupAddress.getText(),txLoginCookie.getText(),txDataAddress.getText(),txQQGroupCode.getText()));
		}
	}
	
	/**
	 * 用户群列表Listener
	 * 
	 * @author HZ20232
	 * 
	 */
	private class UserGroupListButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			taskExecutor.execute(new UserGroupList(MessageFormat.format(txGroupAddress.getText(),txQQGroupCode.getText()),txLoginCookie.getText(),txDataAddress.getText(),txQQGroupCode.getText()));
		}
	}
	
	/**
	 * At所有用户Listener
	 * 
	 * @author HZ20232
	 * 
	 */
	private class AtAllUserBtn implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			taskExecutor.execute(new AtGroupAllUser(Integer.parseInt(txGroupCount.getText()),Integer.parseInt(txOneCircleWaitTime.getText()),Integer.parseInt(txAtWaitTime.getText()),Integer.parseInt(txPressWaitTime.getText()),MessageFormat.format("{0}//{1}.{2}",txDataAddress.getText(),txQQGroupCode.getText(),"txt")));
		}
	}
	
	/**
	 * At所有群Listener
	 * 
	 * @author HZ20232
	 * 
	 */
	private class AtAllGroupBtn implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			taskExecutor.execute(new AtUserAllGroup(Integer.parseInt(txGroupCount.getText()),Integer.parseInt(txOneCircleWaitTime.getText()),Integer.parseInt(txAtWaitTime.getText()),Integer.parseInt(txPressWaitTime.getText()),MessageFormat.format("{0}//{1}.{2}",txDataAddress.getText(),txQQGroupCode.getText(),"txt"),txQQGroupCode.getText()));
		}
	}
	
	/**
	 * At所有群Listener
	 * 
	 * @author HZ20232
	 * 
	 */
	private class GetGetProxyBtn implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			for(int i=1;i<=16;i++){
				
				taskExecutor.execute(new GetProxy(i,16));
			}
			
		}
	}
	
	/**
	 * 文件对比
	 * 
	 * @author HZ20232
	 * 
	 */
	private class GetFileCompareBtn implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
				taskExecutor.execute(new FileCompare("E:\\tmp\\1","sourceA.csv","sourceB.txt","destationName.txt"));
			
		}
	}
	
	/**
	 * 开始
	 * 
	 * @return 开始
	 */
	private JButton getMailSenderButton() {
		if (button1 == null) {
			button1 = new JButton();
			button1.setBounds(20, 20, 120, 30);
			button1.setText("发送邮件");
			button1.setToolTipText("发送邮件");
			button1.addActionListener(new MailSenderButton());// ��Ӽ������࣬����Ҫ����Ӧ���ɼ�������ķ���ʵ��

		}
		return button1;
	}
	
	/**
	 * 结束
	 * 
	 * @return 结束
	 */
	private JButton getButton2() {
		if (button2 == null) {
			button2 = new JButton();
			button2.setBounds(200, 20, 120, 30);
			button2.setText("结束");
			button2.setToolTipText("结束");
			button2.addActionListener(new EndButton());// ��Ӽ������࣬����Ҫ����Ӧ���ɼ�������ķ���ʵ��

		}
		return button2;
	}

	/**
	 * 群总数
	 * 
	 * @return 群总数
	 */
	private JLabel getGroupCountLabel() {
		if (lbGroupCount == null) {
			lbGroupCount = new JLabel();
			lbGroupCount.setBounds(20, 60, 150, 20);
			lbGroupCount.setText("群总数");
			lbGroupCount.setToolTipText("群总数");
		}
		return lbGroupCount;
	}
	
	/**
	 * 群总数
	 * 
	 * @return
	 */
	private JTextField getGroupCountTextField() {
		if (txGroupCount == null) {
			txGroupCount = new JTextField();
			txGroupCount.setBounds(160, 60, 120, 20);
			txGroupCount.setText("100");
		}
		return txGroupCount;
	}
	
	/**
	 * 程序运行一圈等待时间
	 * 
	 * @return 程序运行一圈等待时间
	 */
	private JLabel getOneCircleWaitTimeLabel() {
		if (lbOneCircleWaitTime == null) {
			lbOneCircleWaitTime = new JLabel();
			lbOneCircleWaitTime.setBounds(20, 100, 150, 20);
			lbOneCircleWaitTime.setText("程序运行一圈等待时间");
			lbOneCircleWaitTime.setToolTipText("程序运行一圈等待时间");
		}
		return lbOneCircleWaitTime;
	}
	
	/**
	 * 程序运行一圈等待时间
	 * 
	 * @return
	 */
	private JTextField getOneCircleWaitTimeTextField() {
		if (txOneCircleWaitTime == null) {
			txOneCircleWaitTime = new JTextField();
			txOneCircleWaitTime.setBounds(200, 100, 120, 20);
			txOneCircleWaitTime.setText("1000");
		}
		return txOneCircleWaitTime;
	}
	
	/**
	 * 程序点击@后等待时间
	 * 
	 * @return 程序点击@后等待时间
	 */
	private JLabel getAtWaitTimeLabel() {
		if (lbAtWaitTime == null) {
			lbAtWaitTime = new JLabel();
			lbAtWaitTime.setBounds(20, 140, 150, 20);
			lbAtWaitTime.setText("程序点击@后等待时间");
			lbAtWaitTime.setToolTipText("程序点击@后等待时间");
		}
		return lbAtWaitTime;
	}
	
	/**
	 * 程序点击@后等待时间
	 * 
	 * @return
	 */
	private JTextField getAtWaitTimeTextField() {
		if (txAtWaitTime == null) {
			txAtWaitTime = new JTextField();
			txAtWaitTime.setBounds(200, 140, 120, 20);
			txAtWaitTime.setText("20");
		}
		return txAtWaitTime;
	}
	
	/**
	 * 按键后等待时间
	 * 
	 * @return 按键后等待时间
	 */
	private JLabel getPressWaitTimeLabel() {
		if (lbPressWaitTime == null) {
			lbPressWaitTime = new JLabel();
			lbPressWaitTime.setBounds(20, 180, 150, 20);
			lbPressWaitTime.setText("按键后等待时间");
			lbPressWaitTime.setToolTipText("按键后等待时间");
		}
		return lbPressWaitTime;
	}
	
	/**
	 * 按键后等待时间
	 * 
	 * @return
	 */
	private JTextField getPressWaitTimeTextField() {
		if (txPressWaitTime == null) {
			txPressWaitTime = new JTextField();
			txPressWaitTime.setBounds(200, 180, 120, 20);
			txPressWaitTime.setText("10");
		}
		return txPressWaitTime;
	}
	
	/**
	 * 读取群信息
	 * 
	 * @return 读取群信息
	 */
	private JButton ReadGroupInfoBtn() {
		if (readGroupInfo == null) {
			readGroupInfo = new JButton();
			readGroupInfo.setBounds(20, 220, 120, 30);
			readGroupInfo.setText("批量读取群信息");
			readGroupInfo.setToolTipText("批量读取群信息");
			readGroupInfo.addActionListener(new ReadGroupInfoButton());
		}
		return readGroupInfo;
	}
	
	/**
	 * 用户群列表
	 * 
	 * @return 用户群列表
	 */
	private JButton UserGroupListBtn() {
		if (userGroupList == null) {
			userGroupList = new JButton();
			userGroupList.setBounds(200, 220, 120, 30);
			userGroupList.setText("用户群列表");
			userGroupList.setToolTipText("用户群列表");
			userGroupList.addActionListener(new UserGroupListButton());
		}
		return userGroupList;
	}
	
	/**
	 * 群地址
	 * 
	 * @return 按键后等待时间
	 */
	private JLabel getGroupAddressLabel() {
		if (lbGroupAddress == null) {
			lbGroupAddress = new JLabel();
			lbGroupAddress.setBounds(20, 260, 150, 20);
			lbGroupAddress.setText("群地址");
			lbGroupAddress.setToolTipText("群地址");
		}
		return lbGroupAddress;
	}
	
	/**
	 * 群地址
	 * 
	 * @return
	 */
	private JTextField getGroupAddressTextField() {
		if (txGroupAddress == null) {
			txGroupAddress = new JTextField();
			txGroupAddress.setBounds(200, 260, 120, 20);
			txGroupAddress.setText("http://qun.qzone.qq.com/cgi-bin/get_group_member?callbackFun=_GroupMember&uin=2932869028&groupid={0}&neednum=1&r=0.8846789575181901&g_tk=1147177503&ua=Mozilla%2F5.0%20(Windows%20NT%206.1)%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko)%20Chrome%2F42.0.2311.152%20Safari%2F537.36");
		}
		return txGroupAddress;
	}
	
	/**
	 * 登录Cookie
	 * 
	 * @return 按键后等待时间
	 */
	private JLabel getLoginCookieLabel() {
		if (lbLoginCookie == null) {
			lbLoginCookie = new JLabel();
			lbLoginCookie.setBounds(20, 300, 150, 20);
			lbLoginCookie.setText("登录Cookie");
			lbLoginCookie.setToolTipText("登录Cookie");
		}
		return lbLoginCookie;
	}
	
	/**
	 * 登录Cookie
	 * 
	 * @return
	 */
	private JTextField getLoginCookieTextField() {
		if (txLoginCookie == null) {
			txLoginCookie = new JTextField();
			txLoginCookie.setBounds(200, 300, 120, 20);
			txLoginCookie.setText("QZ_FE_WEBP_SUPPORT=1; cpu_performance_v8=32; RK=oLWDvMksto; __Q_w_s_hat_seed=1; pgv_pvi=4862216192; pgv_pvid=540850624; o_cookie=757912398; pgv_info=ssid=s109334912; pt2gguin=o2932869028; uin=o2932869028; skey=@ubZUjn6CC; ptisp=cm; ptcz=3bf5427b04748dce079c7595123a66e2b839436364480078b6eb5123be4a95ad");
		}
		return txLoginCookie;
	}
	
	/**
	 * QQ群号
	 * 
	 * @return 按键后等待时间
	 */
	private JLabel getQQGroupCodeLabel() {
		if (lbQQGroupCode == null) {
			lbQQGroupCode = new JLabel();
			lbQQGroupCode.setBounds(20, 340, 150, 20);
			lbQQGroupCode.setText("QQ群号");
			lbQQGroupCode.setToolTipText("QQ群号");
		}
		return lbQQGroupCode;
	}
	
	/**
	 * QQ群号
	 * 
	 * @return
	 */
	private JTextField getQQGroupCodeTextField() {
		if (txQQGroupCode == null) {
			txQQGroupCode = new JTextField();
			txQQGroupCode.setBounds(200, 340, 120, 20);
			txQQGroupCode.setText("");
		}
		return txQQGroupCode;
	}
	
	/**
	 * 数据文件目录
	 * 
	 * @return 按键后等待时间
	 */
	private JLabel getDataAddressLabel() {
		if (lbDataAddress == null) {
			lbDataAddress = new JLabel();
			lbDataAddress.setBounds(20, 380, 150, 20);
			lbDataAddress.setText("数据文件目录");
			lbDataAddress.setToolTipText("数据文件目录");
		}
		return lbDataAddress;
	}
	
	/**
	 * 数据文件目录
	 * 
	 * @return
	 */
	private JTextField getDataAddressTextField() {
		if (txDataAddress == null) {
			txDataAddress = new JTextField();
			txDataAddress.setBounds(200, 380, 120, 20);
			txDataAddress.setText("D:\\database");
		}
		return txDataAddress;
	}
	
	/**
	 * At所有人
	 * 
	 * @return 读取群信息
	 */
	private JButton GetAtAllGroupUserBtn() {
		if (atAllGroupUser == null) {
			atAllGroupUser = new JButton();
			atAllGroupUser.setBounds(20, 420, 120, 30);
			atAllGroupUser.setText("At所有人");
			atAllGroupUser.setToolTipText("At所有人");
			atAllGroupUser.addActionListener(new AtAllUserBtn());
		}
		return atAllGroupUser;
	}
	
	/**
	 * At所有群
	 * 
	 * @return 结束
	 */
	private JButton GetatAllGroupButton() {
		if (atAllGroup == null) {
			atAllGroup = new JButton();
			atAllGroup.setBounds(200, 420, 120, 30);
			atAllGroup.setText("At所有群");
			atAllGroup.setToolTipText("At所有群");
			atAllGroup.addActionListener(new AtAllGroupBtn());// ��Ӽ������࣬����Ҫ����Ӧ���ɼ�������ķ���ʵ��

		}
		return atAllGroup;
	}
	
	/**
	 * 获取代理IP
	 * 
	 * @return 获取代理IP
	 */
	private JButton GetGetProxyBtn() {
		if (btnGetProxy == null) {
			btnGetProxy = new JButton();
			btnGetProxy.setBounds(20, 460, 120, 30);
			btnGetProxy.setText("获取代理IP");
			btnGetProxy.setToolTipText("获取代理IP");
			btnGetProxy.addActionListener(new GetGetProxyBtn());
		}
		return btnGetProxy;
	}
	
	/**
	 * 获取文件比对
	 * 
	 * @return 获取代理IP
	 */
	private JButton GetFileCompareBtn() {
		if (btnFileCompare == null) {
			btnFileCompare = new JButton();
			btnFileCompare.setBounds(200, 460, 120, 30);
			btnFileCompare.setText("文件比较");
			btnFileCompare.setToolTipText("文件比较");
			btnFileCompare.addActionListener(new GetFileCompareBtn());
		}
		return btnFileCompare;
	}
}
