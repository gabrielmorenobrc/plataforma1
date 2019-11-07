package plataforma1.email;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Descriptor del mensaje a enviar
 */
public class EmailMessage implements Serializable {
    private String subject;
    private String contentType;
    private String body;
    private String replyTo;
    private final List<String> recipients = new ArrayList<String>();
    private final List<EmailAttachment> attachments = new ArrayList<>();

    public String getSubject() {
        return subject;
    }

    /**
     * Establece el asunto del mensaje
     *
     * @param subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContentType() {
        return contentType;
    }

    /**
     * Establece el tipo MIME del mensaje
     *
     * @param contentType
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getBody() {
        return body;
    }

    /**
     * Establece el contenido del mensaje. La codificación del contenido debe ser consistente con contentType
     *
     * @param body
     */
    public void setBody(String body) {
        this.body = body;
    }

    public String getReplyTo() {
        return replyTo;
    }

    /**
     * Estable la dirección de email que figurará como remitente
     *
     * @param replyTo
     */
    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    public List<String> getRecipients() {
        return recipients;
    }

    /**
     * Agrega una dirección de email a la lista de destinatarios
     *
     * @param recipientAddress
     */
    public void addRecipient(String recipientAddress) {
        recipients.add(recipientAddress);
    }

    public List<EmailAttachment> getAttachments() {
        return attachments;
    }
}
