package plataforma1.email;

/**
 * Especificación de configuración para implementación basada en SMTP de EmailSender
 */
public interface EmailConfig {

    String getSmtpUser();

    String getSmtpPassword();

    String getSmtpHost();

    int getSmtpPort();

    boolean isSmtpAuth();

    boolean isDisableSmtpPlainAuth();

    boolean isUseSSL();
}
