package plataforma1.email;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

@Alternative
@ApplicationScoped
public class MockEmailSender implements EmailSender {
    private static final Logger logger = Logger.getLogger(MockEmailSender.class.getName());

    @Override
    public void send(EmailMessage emailMessage) throws Exception {
        logger.log(Level.INFO, MessageFormat.format("{0} -> {1}", emailMessage.getSubject(), emailMessage.getRecipients()));
    }
}
