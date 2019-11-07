package plataforma1.reporting;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.logging.Logger;

public class ReporteTabularPdfWriter {

    private static final Logger logger = Logger.getLogger(ReporteTabularPdfWriter.class.getName());

    public void writeReporteTabular(ReporteTabularDTO dto, OutputStream outputStream) throws Exception {
        writeReporteTabular(dto, outputStream, getClass(), "reporte-tabular.html");
    }

    public void writeReporteTabular(ReporteTabularDTO dto, OutputStream outputStream, Class resourceBaseClass, String templateFileName) throws Exception {
        ByteArrayOutputStream htmlOutputStream = new ByteArrayOutputStream();
        new SimpleTemplateWriter().writeDocument(dto, resourceBaseClass, templateFileName, htmlOutputStream);
        htmlOutputStream.flush();
        ByteArrayInputStream htmlInputStream = new ByteArrayInputStream(htmlOutputStream.toByteArray());
        new XHTMLPDFConverter().convert(htmlInputStream, outputStream);
        outputStream.flush();
    }

}
