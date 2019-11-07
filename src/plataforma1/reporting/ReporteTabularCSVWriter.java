package plataforma1.reporting;


import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Escribe un archivo CSV dado un {@link toolkit.reporting.ReporteTabularDTO} utilizando {@link toolkit.reporting.CSVWriter}
 */
public class ReporteTabularCSVWriter {

    /**
     * @param dto          Datos de entrada
     * @param outputStream salida en formato CSV, juego de caracteres ISO-8859-1
     * @throws IOException
     */
    public void writeCSV(ReporteTabularDTO dto, OutputStream outputStream) throws IOException {
        Writer writer = new OutputStreamWriter(outputStream, "ISO-8859-1");
        CSVWriter cSVWriter = new CSVWriter(writer);
        boolean first = true;
        for (ReporteTabularDTO.Column column : dto.getColumns()) {
            String titulo = column.getTitle();
            if (first) {
                first = false;
            } else {
                cSVWriter.writeSeparator();
            }
            cSVWriter.write(titulo);
        }
        cSVWriter.writeLineEnd();
        cSVWriter.writeLineEnd();
        for (ReporteTabularDTO.FieldValue[] values : dto.getRows()) {
            first = true;
            for (ReporteTabularDTO.FieldValue fieldValue : values) {
                if (first) {
                    first = false;
                } else {
                    cSVWriter.writeSeparator();
                }
                if (fieldValue.getValueType().equals(ReporteTabularDTO.ValueType.DATE)) {
                    cSVWriter.writeDate((Date) fieldValue.getValue());
                } else if (fieldValue.getValueType().equals(ReporteTabularDTO.ValueType.DATETIME)) {
                    cSVWriter.writeDateTime((Date) fieldValue.getValue());
                } else if (fieldValue.getValueType().equals(ReporteTabularDTO.ValueType.DECIMAL)) {
                    cSVWriter.write((BigDecimal) fieldValue.getValue());
                } else if (fieldValue.getValueType().equals(ReporteTabularDTO.ValueType.INTEGER)) {
                    cSVWriter.write(fieldValue.getValue().toString());
                } else if (fieldValue.getValueType().equals(ReporteTabularDTO.ValueType.PERCENT)) {
                    cSVWriter.writePercent((Double) fieldValue.getValue());
                } else if (fieldValue.getValueType().equals(ReporteTabularDTO.ValueType.STRING)) {
                    cSVWriter.write((String) fieldValue.getValue());
                } else if (fieldValue.getValueType().equals(ReporteTabularDTO.ValueType.TIME)) {
                    cSVWriter.writeTime((Date) fieldValue.getValue());
                }
            }
            cSVWriter.writeLineEnd();
        }
        writer.flush();
    }

}
