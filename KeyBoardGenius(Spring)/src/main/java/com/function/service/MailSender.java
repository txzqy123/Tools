package com.function.service;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.function.util.Constants;
import com.function.util.FileUtil;

/**
 * 邮件发送
 * @author zqy
 *
 */
public class MailSender  implements Runnable{
	private static final Logger logger = LoggerFactory.getLogger(MailSender.class);
	private List<JavaMailSenderImpl> mailSenderList;
	private String databaseAddress;
	private String groupId;
	private int mailWaitTime;
	public MailSender(List<JavaMailSenderImpl> mailSender,String databaseAddress,String groupId,int mailWaitTime){
		
		this.mailSenderList = mailSender;
		this.databaseAddress = databaseAddress;
		this.groupId = groupId;
		this.mailWaitTime = mailWaitTime;
	}
	public void run(){
		
		String email = "";
		try{
			
				
			List<String> fileList = FileUtil.getAllFileName(this.databaseAddress + "\\email");
			List<String> proxyList = FileUtil.readFileByLines(MessageFormat.format("{0}\\{1}\\{2}.txt",this.databaseAddress,"GetProxy","proxy"));
			List<String> emailContent = FileUtil.readFileByLines(MessageFormat.format("{0}\\{1}\\{2}.txt",this.databaseAddress,"email","emailContent"));
			Random random=new Random(new Date().getTime());
			Random randomMail = new Random(new Date().getTime());
			for(int fileNo =0;fileNo < fileList.size();fileNo ++){
				logger.info("当前文件为：" + fileList.get(fileNo));
				if(!(fileList.get(fileNo).indexOf("_send")>0 || fileList.get(fileNo).indexOf("emailContent") >0)){
					
					List<String> allUser = FileUtil.readFileByLines(fileList.get(fileNo));
					List<String> hasSend = FileUtil.readFileByLines(fileList.get(fileNo).replace(".txt", "_send.txt"));
					
					if(emailContent.size() >= 2){
						
						for(int i=0;i<allUser.size();i++){
							boolean blHasSend = false;
							JavaMailSenderImpl mailSender = mailSenderList.get(randomMail.nextInt(mailSenderList.size()));
							MimeMessage message = mailSender.createMimeMessage();
							for(int j=0;j<hasSend.size();j++){
								if(allUser.get(i).split(Constants.SPLIT_SPACE).length > 3 && hasSend.get(j).split(Constants.SPLIT_SPACE).length >3){
									
									if(allUser.get(i).split(Constants.SPLIT_SPACE)[3].equals(hasSend.get(j).split(Constants.SPLIT_SPACE)[3])){
										
										blHasSend = true;
										break;
									}
								}
							}
							
							if(!blHasSend){
								
								String[] items = allUser.get(i).split(Constants.SPLIT_SPACE);
								if(items.length > 3){
									int randomNo = random.nextInt(proxyList.size());
									String ip= proxyList.get(randomNo).split(":")[0];
									String port= proxyList.get(randomNo).split(":")[1];
									try{
										//设置代理
//										System.getProperties().put("proxySet", true); 
//										System.getProperties().put("http.proxyHost", ip); 
//										System.getProperties().put("http.proxyPort", port); 
//										System.getProperties().put("socksProxySet", true); 
//										System.getProperties().put("socksProxyHost", ip); 
//										System.getProperties().put("socksProxyPort", port); 
										
										MimeMessageHelper smm = new MimeMessageHelper(message,true,"GBK");
										email = items[3] + "@qq.com";
										smm.setFrom(mailSender.getUsername());
										smm.setTo(email);
										smm.setSubject(emailContent.get(0));
										smm.setText(emailContent.get(1),true);
										mailSender.getJavaMailProperties().setProperty("mail.smtp.socks.host", ip);
										mailSender.getJavaMailProperties().setProperty("mail.smtp.socks.port", port);
										//mailSender.getJavaMailProperties().setProperty("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
										logger.info("代理IP为："+ ip+":"+port+"邮件发送地址为：" + ip + port+",收件人为：" + email+"发件人为:" + mailSender.getUsername());
										// 发送邮件
										mailSender.send(message);
										FileUtil.appendMethod(fileList.get(fileNo).replace(".txt", "_send.txt"), allUser.get(i));
										FileUtil.appendMethod(fileList.get(fileNo).replace(".txt", "_send.txt"), "\n");
										Thread.sleep(mailWaitTime);
									} catch(Exception ex){
										if(ex.toString().indexOf("550 Connection frequency limited") > 0){
											
											logger.error("该IP发送数量过多："+ex);
											FileUtil.appendMethod(fileList.get(fileNo).replace(".txt", "_send_error.txt"), allUser.get(i));
											FileUtil.appendMethod(fileList.get(fileNo).replace(".txt", "_send_error.txt"), "\n");
										} else if(ex.toString().indexOf("550 Mailbox not found or access denied") > 0){
											
											logger.error("地址有问题" +ex);
											FileUtil.appendMethod(fileList.get(fileNo).replace(".txt", "_send.txt"), allUser.get(i));
											FileUtil.appendMethod(fileList.get(fileNo).replace(".txt", "_send.txt"), "\n");
										} else {
											
											logger.error("发送邮件错误:" + ex);
											FileUtil.appendMethod(fileList.get(fileNo).replace(".txt", "_send_error.txt"), allUser.get(i));
											FileUtil.appendMethod(fileList.get(fileNo).replace(".txt", "_send_error.txt"), "\n");
										}
									}
									
								}
							}
							
						}
					} else {
						
						logger.warn("请查看邮件内容配置文件是否正确！");
					}
				}
			}
			
			
		} catch(Exception ex){
			
			logger.error("发送邮件错误:" + ex);
		}
		
	}
}
