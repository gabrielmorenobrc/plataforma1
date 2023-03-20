package plataforma1.email;

import javax.activation.DataHandler;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.mail.Address;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Implementación de EmailSender basada en SMTP
 */
@Alternative
@ApplicationScoped
public class SmtpEmailSender implements EmailSender {

    private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    private Session session;
    @Inject
    private EmailConfig emailConfig;

    public synchronized void send(EmailMessage emailMessage) throws Exception {
        checkSession();
        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setSubject(emailMessage.getSubject());
        mimeMessage.setSentDate(new Date());
        if (emailMessage.getReplyTo() == null) {
            mimeMessage.setFrom(new InternetAddress(emailConfig.getSmtpUser()));
        } else {
            mimeMessage.setFrom(new InternetAddress(emailMessage.getReplyTo()));
        }
        List<String> recipients = emailMessage.getRecipients();
        InternetAddress[] addresses = new InternetAddress[recipients.size()];
        for (int i = 0; i < recipients.size(); i++) {
            String recipient = recipients.get(i);
            addresses[i] = new InternetAddress(recipient);
        }
        mimeMessage.setRecipients(javax.mail.Message.RecipientType.TO, addresses);
        String replyTo = emailMessage.getReplyTo();
        if (replyTo != null) {
            mimeMessage.setReplyTo(new Address[]{new InternetAddress(emailMessage.getReplyTo())});
        }
        MimeBodyPart bodyPart = new MimeBodyPart();
        if (emailMessage.getContentType() == null) {
            bodyPart.setText(emailMessage.getBody());
        } else {
            bodyPart.setContent(emailMessage.getBody(), emailMessage.getContentType());
        }
        Multipart multipart = new MimeMultipart("related");
        multipart.addBodyPart(bodyPart);
        for (EmailAttachment attachment : emailMessage.getAttachments()) {
            bodyPart = new MimeBodyPart();
            bodyPart.setDataHandler(new DataHandler(attachment.getDataSource()));
            bodyPart.setHeader("Content-ID", attachment.getContentId());
            bodyPart.setHeader("Content-Type", attachment.getContentType());
            if (attachment.getFileName() == null) {
                bodyPart.setHeader("Content-Disposition", attachment.getContentDisposition().toString());
            } else {
                bodyPart.setHeader("Content-Disposition", attachment.getContentDisposition().toString()
                        + "; filename=" + attachment.getFileName() + ";");
            }
            multipart.addBodyPart(bodyPart);
        }

        mimeMessage.setContent(multipart);
        Transport.send(mimeMessage, emailConfig.getSmtpUser(), emailConfig.getSmtpPassword());
    }

    private void checkSession() {
        if (session == null) {
            Properties properties = new Properties();
            if (this.emailConfig.isUseStarttls()) {
                properties.put("mail.smtp.starttls.enable", "true");
                properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
            }

            properties.put("mail.smtp.host", emailConfig.getSmtpHost());
            properties.put("mail.smtp.port", emailConfig.getSmtpPort());
            if (emailConfig.isUseSSL()) {
                properties.put("mail.smtp.socketFactory.class", SSL_FACTORY);
                properties.put("mail.smtp.auth", emailConfig.isSmtpAuth());
            } else {
                properties.put("mail.smtp.auth", emailConfig.isSmtpAuth());
                properties.put("mail.smtp.auth.plain.disable", emailConfig.isDisableSmtpPlainAuth());
            }
            session = Session.getInstance(properties);
        }
    }

    public void setEmailConfig(EmailConfig emailConfig) {
        this.emailConfig = emailConfig;
    }
}
