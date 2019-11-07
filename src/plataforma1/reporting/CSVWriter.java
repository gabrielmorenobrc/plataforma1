package plataforma1.reporting;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>Utilidad para escritura de archivos en formato CSV</p>
 * <p>Encapsula formato de escritura para distintos tipos de datos</p>
 */
public class CSVWriter {
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    private final DecimalFormat percentFormat = new DecimalFormat("##0.00");
    private Writer writer;

    /**
     * @param writer Writer al que se direcciona la salida del CSVWriter
     */
    public CSVWriter(Writer writer) {
        this.writer = writer;
        DecimalFormatSymbols decimalFormatSymbols = percentFormat.getDecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator('.');
        percentFormat.setDecimalFormatSymbols(decimalFormatSymbols);
    }

    /**
     * Escribe value al archivo CSV en formato yyyy-MM-dd
     *
     * @param value
     * @throws IOException
     */
    public void writeDate(Date value) throws IOException {
        write(value == null ? "" : dateFormat.format(value));
    }

    /**
     * Escribe value al archivo CSV en formato HH:mm:ss
     *
     * @param value
     * @throws IOException
     */
    public void writeTime(Date value) throws IOException {
        write(value == null ? "" : timeFormat.format(value));
    }

    /**
     * Escribe value al archivo CSV en formato yyyy-MM-dd HH:mm:ss
     *
     * @param value
     * @throws IOException
     */
    public void writeDateTime(Date value) throws IOException {
        write(value == null ? "" : dateFormat.format(value) + " " + timeFormat.format(value));
    }

    /**
     * Escribe value al archivo CSV en formato #0.00
     *
     * @param value
     * @throws IOException
     */
    public void writePercent(Double value) throws IOException {
        write(value == null ? "" : percentFormat.format(value));
    }

    /**
     * Escribe value al archivo CSV en formato #0.00
     *
     * @param value
     * @throws IOException
     */
    public void write(BigDecimal value) throws IOException {
        write(value == null ? "" : value.toString());
    }

    /**
     * Escribe la representación decimal de value al archivo CSV
     *
     * @param value
     * @throws IOException
     */
    public void write(Long value) throws IOException {
        write(value == null ? "" : value.toString());
    }

    /**
     * Escribe la representación decimal de value al archivo CSV
     *
     * @param value
     * @throws IOException
     */
    public void write(Integer value) throws IOException {
        write(value == null ? "" : value.toString());
    }

    /**
     * Escribo value al archivo CSV codificando los caracteres reservados
     *
     * @param value
     * @throws IOException
     */
    public void write(String value) throws IOException {
        if (value == null) {
            writer.write("");
        } else {
            value = value.replaceAll("\"", "\\\"");
            if (value.indexOf(",") > -1) {
                value = "\"" + value + "\"";
            }
            writer.write(value);
        }
    }

    /**
     * Escribe separador de campo al archivo CSV
     *
     * @throws IOException
     */
    public void writeSeparator() throws IOException {
        writer.write(",");
    }

    /**
     * Escribe separador de línea al archivo CSV
     *
     * @throws IOException
     */
    public void writeLineEnd() throws IOException {
        writer.write("\r\n");
    }

}
