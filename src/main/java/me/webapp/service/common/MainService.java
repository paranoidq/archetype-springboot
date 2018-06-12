package me.webapp.service.common;

import me.webapp.exception.ServiceException;
import me.webapp.service.LogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Service
public class MainService implements LogicService {

    @Autowired
    private JavaMailSender javaMailSender;


    /**
     * 发送邮件
     * @param from
     * @param to
     * @param subject
     * @param content
     * @throws ServiceException
     */
    public void sendMail(String from, String to, String subject, String content) throws ServiceException  {
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setFrom(from);
            mail.setTo(to);
            mail.setSubject(subject);
            mail.setText(content);
            javaMailSender.send(mail);
        } catch (MailException e) {
            throw new ServiceException("发送email失败", e);
        }
    }
}
