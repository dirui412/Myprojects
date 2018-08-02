package lj.generator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import lj.global.PlatformFun;
import lj.global.PlatformVar;
import lj.util.SpringUtils;
import lj.util.XmlUtils;
import java.awt.Font;

public class GeneratorMain extends JFrame implements ActionListener {
	
	private JPanel headPanel = new JPanel();
	private JPanel tailPanel = new JPanel();
	private JLabel labelMessage = new JLabel("");
	private JLabel labelPackageName = new JLabel("模型包名:");
	private JLabel labelTableName = new JLabel("模型表(视图):");
	private JLabel labelIndexName = new JLabel("主键名称");
	private JTextField txtPackageName = new JTextField("");
	private JTextField txtTableName = new JTextField("");
	private JTextField txtIndexName = new JTextField("");

	private JLabel labelCore = new JLabel("core项目路径:");
	private JTextField txtDirCore = new JTextField();// TextField 目录的路径
	private JButton btnDirCore = new JButton("...");// 选择
	private JLabel labelWeb = new JLabel("web项目路径:");
	private JTextField txtDirWeb = new JTextField();// TextField 目录的路径
	private JButton btnDirWeb = new JButton("...");
	private JFileChooser fileChooser = new JFileChooser();// 文件选择器
	
	private JLabel labelQueryFields=new JLabel("查询字段(;)");
	private JTextField txtQueryFields=new JTextField();
	
	private JLabel labelGenerate=new JLabel("生成文件:");
	private ButtonGroup  cbGroup=new ButtonGroup();
	private JCheckBox cbModel=new JCheckBox("Model",true);
	private JCheckBox cbIDao=new JCheckBox("IDao",true);
	private JCheckBox cbDao =new JCheckBox("Dao",true);
	private JCheckBox cbService=new JCheckBox("Service",true);
	private JCheckBox cbController=new JCheckBox("Controller",true);
	private JCheckBox cbPage=new JCheckBox("Page",true);
	
	private JButton btnGenerate = new JButton("生成");
	private JButton btnExit = new JButton("退出");
	


	/**
	 * 窗口按钮事件处理
	 */
	public void actionPerformed(ActionEvent e) {
		// 1-退出
		if (e.getSource().equals(btnExit) == true) {
			System.out.println("正确退出应用程序");
			System.exit(0);
		}
		// 2-生成
		else if (e.getSource().equals(btnGenerate) == true) {
			String packageName = txtPackageName.getText();
			String tableName = txtTableName.getText();
			String indexName = txtIndexName.getText();
			String dirCore=txtDirCore.getText();
			String dirWeb=txtDirWeb.getText();
			String queryFields=txtQueryFields.getText();
			GeneratorModel model=new GeneratorModel(packageName, tableName, indexName, 
					dirCore, dirWeb,queryFields,cbModel.isSelected(),cbIDao.isSelected(),
					cbDao.isSelected(),cbService.isSelected(),cbController.isSelected(),cbPage.isSelected());
			String FILE_PATH_MODEL=PlatformVar.platformPath + File.separator + "GeneratorModel.xml";
			XmlUtils.saveToFile(FILE_PATH_MODEL, model);
			CodeGenerator.generate(model);
		}
		// 3-选择Core目录
		else if (e.getSource().equals(btnDirCore) == true) {
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);// 设定只能选择到文件
			int state = fileChooser.showOpenDialog(null);// 此句是打开文件选择器界面的触发语句
			if (state == 1) {
				return;// 撤销则返回
			} else {
				File f = fileChooser.getSelectedFile();// f为选择到的文件
				txtDirCore.setText(f.getAbsolutePath());
			}
		}
		// 4-选择Web目录
		else if (e.getSource().equals(btnDirWeb) == true) {
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);// 设定只能选择到文件
			int state = fileChooser.showOpenDialog(null);// 此句是打开文件选择器界面的触发语句
			if (state == 1) {
				return;// 撤销则返回
			} else {
				File f = fileChooser.getSelectedFile();// f为选择到的文件
				txtDirWeb.setText(f.getAbsolutePath());
			}
		}
	}

	/**
	 * 构造方法
	 */
	public GeneratorMain() {
		// 1-初始化UI
		JRootPane paneRoot = this.getRootPane();
		paneRoot.setLayout(new BorderLayout());
		labelMessage.setFont(new java.awt.Font("Dialog", 1, 20));
		labelMessage.setForeground(Color.cyan);
		headPanel.add(labelMessage);
		paneRoot.add(headPanel, BorderLayout.NORTH);

		tailPanel.setLayout(null);
		

		labelPackageName.setBounds(30, 0, 100, 20);
		labelTableName.setBounds(30, 30, 100, 20);
		labelIndexName.setBounds(30, 60, 100, 20);
		labelQueryFields.setBounds(30, 90, 100, 20);
		labelCore.setBounds(30, 120, 100, 20);
		labelWeb.setBounds(30, 150, 100, 20);
		labelGenerate.setBounds(30, 180,100,20);

		txtPackageName.setBounds(120, 0, 200, 20);
		txtTableName.setBounds(120, 30, 200, 20);
		txtIndexName.setBounds(120, 60, 200, 20);
		txtQueryFields.setBounds(120, 90, 200, 20);
		
		txtDirCore.setBounds(120, 120, 200, 20);
		btnDirCore.setBounds(320, 120, 30, 20);
		txtDirWeb.setBounds(120, 150, 200, 20);
		btnDirWeb.setBounds(320, 150, 30, 20);
		
		cbModel.setBounds(120, 180, 60, 20);
		cbIDao.setBounds(180, 180, 60, 20);
		cbDao.setBounds(240, 180, 60, 20);
		cbService.setBounds(120, 195, 60, 20);
		cbController.setBounds(180, 195, 60, 20);
		cbPage.setBounds(240, 195, 100, 20);

		btnGenerate.setBounds(120, 220, 80, 20);
		btnExit.setBounds(210, 220, 80, 20);
		
		
		tailPanel.add(labelPackageName);
		tailPanel.add(labelTableName);
		tailPanel.add(labelIndexName);
		tailPanel.add(labelQueryFields);
		tailPanel.add(labelCore);
		tailPanel.add(labelWeb);
		tailPanel.add(labelGenerate);
		tailPanel.add(txtPackageName);
		tailPanel.add(txtTableName);
		tailPanel.add(txtIndexName);
		tailPanel.add(txtQueryFields);
		tailPanel.add(txtDirCore);
		tailPanel.add(txtDirWeb);
		tailPanel.add(btnDirCore);
		tailPanel.add(btnDirWeb);
		tailPanel.add(cbModel);
		tailPanel.add(cbIDao);
		tailPanel.add(cbDao);
		tailPanel.add(cbService);
		tailPanel.add(cbController);
		tailPanel.add(cbPage);
		tailPanel.add(btnGenerate);
		tailPanel.add(btnExit);

		paneRoot.add(tailPanel, BorderLayout.CENTER);

		getContentPane().setLayout(new GridBagLayout());
		this.pack();
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				dispose();
				System.out.println("正确退出应用程序");
				System.exit(0);
			}

		});
		btnExit.addActionListener(this);
		btnGenerate.addActionListener(this);
		btnDirCore.addActionListener(this);
		btnDirWeb.addActionListener(this);
		String FILE_PATH_MODEL=PlatformVar.platformPath + File.separator + "GeneratorModel.xml";
		
		GeneratorModel model=(GeneratorModel)XmlUtils.loadFromFile(FILE_PATH_MODEL);
		txtPackageName.setText(model.getPackageName());
		txtTableName.setText(model.getTableName());
		txtIndexName.setText(model.getIndexName());
		txtQueryFields.setText(model.getQueryFields());
		txtDirCore.setText(model.getDirCore());
		txtDirWeb.setText(model.getDirWeb());
		
	}

	public static void showUI() {
		// 2-显示窗口(取消窗口)
		GeneratorMain frame = new GeneratorMain();
		frame.setSize(400, 300);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int x = (int) (toolkit.getScreenSize().getWidth() - frame.getWidth()) / 2;
		int y = (int) (toolkit.getScreenSize().getHeight() - frame.getHeight()) / 2;
		frame.setLocation(x, y);
		frame.setTitle("模块代码生成");
		frame.setVisible(true);
		//frame.pack();
		UIManager.put("Button.font", new java.awt.Font("宋体", 0, 12));  
	}

	public static void main(String[] args) {
		SpringUtils.getBeanFactory();
		PlatformFun.preLoad();
		showUI();
	}

}
