package plataforma1.email;

/**
 * Especificación de interfase para el envío de emails
 */
public interface EmailSender {

    /**
     * Ejecuta de manera sinrónica el envío del mensaje descripto en emailMessage
     *
     * @param emailMessage descriptor del mensaje a enviar
     * @throws Exception
     */
    void send(EmailMessage emailMessage) throws Exception;

}
