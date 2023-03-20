package plataforma1.email;

import javax.enterprise.inject.Alternative;

@Alternative
public class DummyEmailConfig implements EmailConfig {
    @Override
    public String getSmtpUser() {
        return null;
    }

    @Override
    public String getSmtpPassword() {
        return null;
    }

    @Override
    public String getSmtpHost() {
        return null;
    }

    @Override
    public int getSmtpPort() {
        return 0;
    }

    @Override
    public boolean isSmtpAuth() {
        return false;
    }

    @Override
    public boolean isDisableSmtpPlainAuth() {
        return false;
    }

    @Override
    public boolean isUseSSL() {
        return false;
    }

    @Override
    public boolean isUseStarttls() {
        return false;
    }
}
