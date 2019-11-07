package plataforma1.wicket;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.handler.resource.ResourceStreamRequestHandler;
import org.apache.wicket.request.resource.ContentDisposition;
import org.apache.wicket.util.resource.AbstractResourceStreamWriter;

import java.io.OutputStream;
import java.io.Serializable;

/**
 * Created by gmoreno on 15/02/2017.
 */
public abstract class AsyncAjaxDownload extends AbstractAjaxBehavior implements Serializable {

    private ContentDisposition contentDisposition;

    public AsyncAjaxDownload() {
        setContentDisposition(ContentDisposition.ATTACHMENT);
    }

    public void scheduleDownload() {
        String url = getCallbackUrl().toString();
        url = url + (url.contains("?") ? "&" : "?");
        url = url + "antiCache=" + System.currentTimeMillis();
        AjaxRequestTarget ajaxRequestTarget = RequestCycle.get().find(AjaxRequestTarget.class);
        ajaxRequestTarget.appendJavaScript("setTimeout(\"window.location.href='" + url + "'\", 100);");
    }

    @Override
    public void onRequest() {
        AbstractResourceStreamWriter resourceStreamWriter = createResourceStreamWriter();
        ResourceStreamRequestHandler resourceStreamRequestHandler = new ResourceStreamRequestHandler(resourceStreamWriter);
        resourceStreamRequestHandler.setContentDisposition(getContentDisposition());
        resourceStreamRequestHandler.setFileName(getFileName());
        RequestCycle.get().scheduleRequestHandlerAfterCurrent(resourceStreamRequestHandler);
    }

    protected abstract String getFileName();

    protected ContentDisposition getContentDisposition() {
        return contentDisposition;
    }

    protected void setContentDisposition(ContentDisposition contentDisposition) {
        this.contentDisposition = contentDisposition;
    }

    protected AbstractResourceStreamWriter createResourceStreamWriter() {
        AbstractResourceStreamWriter resourceStreamWriter = new AbstractResourceStreamWriter() {

            @Override
            public void write(OutputStream outputStream) {
                try {
                    doWrite(outputStream);
                    outputStream.flush();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }

            @Override
            public String getContentType() {
                return doGetContentType();
            }
        };
        return resourceStreamWriter;
    }

    protected abstract String doGetContentType();

    protected abstract void doWrite(OutputStream outputStream) throws Exception;

}