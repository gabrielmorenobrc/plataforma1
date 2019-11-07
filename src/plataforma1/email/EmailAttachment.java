package plataforma1.email;


import javax.activation.DataSource;
import java.io.Serializable;

public class EmailAttachment implements Serializable {
    private String contentId;
    private DataSource dataSource;
    private String contentType;
    private String fileName;
    private EmailAttachmentContentDisposition contentDisposition;

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public EmailAttachmentContentDisposition getContentDisposition() {
        return contentDisposition;
    }

    public void setContentDisposition(EmailAttachmentContentDisposition contentDisposition) {
        this.contentDisposition = contentDisposition;
    }
}
