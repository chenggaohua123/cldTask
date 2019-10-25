package com.fhtpay.task.service.emailservice;

import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.fhtpay.common.exception.BusinessException;
import com.fhtpay.email.model.EmailEntity;
import com.fhtpay.email.model.EmailInfo;
import com.fhtpay.transaction.dao.TransactionManageDao;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class EmailSendImpl  {
	
	private static Log logger = LogFactory.getLog(EmailSendImpl.class);
	
	private JavaMailSenderImpl mailSender;
	private FreeMarkerConfigurer freeMarkerConfigurer;
	private TaskExecutor taskExecutor;
	private TransactionManageDao transactionManageDao;
	/**
	 * 实现：利用spring多线程机制发送邮件
	 * @param entity 发送邮件配置信息
	 * @param context 邮件的动态内容参数
	 */
	public void threadSendEmail(final EmailEntity entity, final Map<String,String> context) {
		
		taskExecutor.execute(new Runnable(){
			public void run(){
				try {
					sendEmail(entity, context);
				} catch(BusinessException e) {
					e.printStackTrace();
					logger.info(e.getMessage());
				}
			}
		});
	}
	
	/**
	 * 实现：发送邮件
	 * @param1 entity 发送邮件参数
	 * @param2 context 邮件发送的内容参数
	 * @return 发送结果
	 */
	private void sendEmail(EmailEntity email, Map<String,String> context) throws BusinessException{
		try {
			mailSender.setUsername(email.getEmailAcount());
			mailSender.setPassword(email.getEmailPassword());
			mailSender.setHost(email.getEmailHost());
			mailSender.setPort(Integer.valueOf(email.getEmailPort()));
			Template emailTemp = freeMarkerConfigurer.getConfiguration().getTemplate(email.getTemplateName());
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(emailTemp, context);
			MimeMessage msg=mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg,true,"utf-8");
			helper.setText(html,true);
			helper.setFrom(email.getEmailAcount());
			helper.setTo(email.getEmailSendTo());
			helper.setSubject(email.getEmailSubject());
			if(email.getBcc()!=null) {
				if(email.getBcc().length>0) {
					helper.setBcc(email.getBcc());
				}
			}
			mailSender.send(msg);
		} catch (IOException e) {
			e.printStackTrace();
			logger.info("邮件发送时失败！抛IOException异常，异常内容=="+e.getStackTrace());
			throw new BusinessException("邮件发送失败！");
		} catch (TemplateException e) {
			logger.info("邮件发送时失败！抛TemplateException异常，异常内容=="+e.getStackTrace());
			throw new BusinessException("邮件发送失败！");
		} catch (MessagingException e) {
			logger.info("邮件发送时失败！抛MessagingException异常，异常内容=="+e.getStackTrace());
			throw new BusinessException("邮件发送失败！");
		}
	}
	
	public EmailInfo queryEmailInfo(){
		return transactionManageDao.queryEmailInfo();
	}
	/***
	 * 更新邮件发送次数
	 * */
	public int updateEmailSendCountById(EmailInfo info){
		return transactionManageDao.updateEmailSendCountById(info);
	}
	public TaskExecutor getTaskExecutor() {
		return taskExecutor;
	}
	public void setTaskExecutor(TaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}

	public JavaMailSenderImpl getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}

	public FreeMarkerConfigurer getFreeMarkerConfigurer() {
		return freeMarkerConfigurer;
	}

	public void setFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer) {
		this.freeMarkerConfigurer = freeMarkerConfigurer;
	}

	public TransactionManageDao getTransactionManageDao() {
		return transactionManageDao;
	}

	public void setTransactionManageDao(TransactionManageDao transactionManageDao) {
		this.transactionManageDao = transactionManageDao;
	}
	
	
	
	
}
