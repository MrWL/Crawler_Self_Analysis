package com.peng.spider;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.jvnet.substance.skin.SubstanceRavenGraphiteLookAndFeel;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class FetchUrlByHtmlUnit extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//������ͼ��
	private ImageIcon img;
	private JLabel userName=new JLabel("�ʺ�:");
	private JTextField userInput=new JTextField();
	private JLabel pwd=new JLabel("����:");
	private JPasswordField pwdInput=new JPasswordField();
	private JButton jLogin=new JButton("��¼");
	//�Դ��ڽ�������
	public void initWindow(){
		setIconImage(img.getImage());
		setTitle("��������");
		setSize(230,170);
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	//���ô��ڿɼ�
	public void setWindowVisible(){
		setVisible(true);
	}
	//���Ӵ������
	public void addComponets(){
		setLayout(null);
		Font font=new Font("Dialog",1,13);
		userName.setFont(font);
		userName.setBounds(10, 22, 37, 20);
		add(userName);
		userInput.setBounds(47, 20, 165, 25);
		userInput.setFont(font);
		add(userInput);
		pwd.setFont(font);
		pwd.setBounds(10,63,37,20);
		add(pwd);
		pwdInput.setBounds(47, 60, 165, 25);
		pwdInput.setFont(font);
		add(pwdInput);
		jLogin.setBounds(72, 100, 80, 25);
		add(jLogin);
		jLogin.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				String user=userInput.getText();
				String pwd=new String(pwdInput.getPassword());
				try {
					System.out.println("���ڵ�¼");
					webFetch(user,pwd);
					System.out.println("��¼����");
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
		
		});
	}
	//���ô��ھ���
	public void setWindowCenter(){
		Dimension size=Toolkit.getDefaultToolkit().getScreenSize();
		int width=getSize().width;
		int height=getSize().height;
		setLocation(new Point((size.width-width)/2,(size.height-height)/2));
	}
	public FetchUrlByHtmlUnit(){
		URL imgUrl=getClass().getResource("xiaomi.jpg");
		img=new ImageIcon(imgUrl);
		initWindow();
		addComponets();
		setWindowCenter();
		setWindowVisible();
	}
	public void webFetch(String userInput,String pwdInput) throws Exception{
		final WebClient webClient=new WebClient();
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(false);
		final HtmlPage page=webClient.getPage("https://account.xiaomi.com/pass/serviceLogin");
		//��ȡ��
		final HtmlForm form=(HtmlForm)page.getElementById("loginForm");
		//��ȡ�ύ�ť
		final HtmlSubmitInput button=form.getInputByValue("��¼");
		HtmlTextInput userId=form.getInputByName("user");
		HtmlPasswordInput pwd=form.getInputByName("pwd");
		userId.setText(userInput);
		pwd.setText(pwdInput);
		final HtmlPage next=button.click();
		System.out.println(next.asText());
		webClient.closeAllWindows();
	}
	public static void main(String[] args) throws Exception {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {		
				try {
					UIManager.setLookAndFeel( new  SubstanceRavenGraphiteLookAndFeel());
				} catch (Exception e) {
					e.printStackTrace();
				}
				new FetchUrlByHtmlUnit();
			}
		});
	}
}
