package plataforma1.reporting;

import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Utilidad para generar archcivos PDF a partir de XHTML
 */
public class XHTMLPDFConverter {

    /**
     * Tomando inputStream como entrada XHTML escribe la transformación a archivo PDF en outputStream
     *
     * @param inputStream
     * @param outputStream
     * @throws Exception
     */
    public void convert(InputStream inputStream, OutputStream outputStream) throws Exception {
        ITextRenderer renderer = new ITextRenderer();
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        org.w3c.dom.Document d = documentBuilder.parse(inputStream);
        renderer.setDocument(d, "");
        renderer.layout();
        renderer.createPDF(outputStream);
    }

}
