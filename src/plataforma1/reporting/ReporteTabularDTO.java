package plataforma1.reporting;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>Descriptor de reporte para infraestructura de reportes tabulares</p>
 * <p>Permite describir título, columnas, renglones y totalizadores proveyendo formateo automático de tipos de datos comunes</p>
 * Ejemplo
 * <p/>
 * <pre>
 *     <code>
 * ReporteTabularDTO reporteTabularDTO = new ReporteTabularDTO();
 * reporteTabularDTO.setTitle("Pagos");  // establece el título del reporte
 * reporteTabularDTO.parseCols("Fecha, Descripción, Importe");  // define 3 columnas con sus respectivos títulos
 *
 * reporteTabularDTO.getColumns().get(1).setMinWidth(100); //establece un mínimo de 100px para la segunda columna
 *
 * for (Pago pago : pagos) {
 *
 *      reporteTabularDTO.addField(pago.getFecha(), DATE); // define el contenido de la primera columna para el renglón a agregar
 *      reporteTabularDTO.addField(pago.getDescripcion(), STRING); // define el contenido de la segunda columna para el renglón a agregar
 *      reporteTabularDTO.addField(pago.getImporte(), DECIMAL); // define el contenido de la tercera columna para el renglón a agregar
 *      reporteTabularDTO.addRow(); // agrega el registro al reporte
 * }
 *
 * reporteTabularDTO.addTotal(pagos.size(), INTEGER); // agrega totalizador con la cantidad de registros
 * reporteTabularDTO.addEmptyTotal();  // agrega totalizador vacío
 * reporteTabularDTO.addTotal(totalPagos, DECIMAL); // agrega totalizador con la suma de los valores de la tercera columna
 * return reporteTabularDTO;
 *     </code>
 * </pre>
 */

public class ReporteTabularDTO implements Serializable {
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    private final DecimalFormat percentFormat = new DecimalFormat("##0.00");
    private final DecimalFormat decimalFormat = new DecimalFormat("0.00");
    private String title;
    private final List<Column> columns = new ArrayList<Column>();
    private final List<FieldValue[]> rows = new ArrayList<FieldValue[]>();
    private final List<FieldValue> totals = new ArrayList<FieldValue>();
    private List<FieldValue> fields;

    /**
     * @return descriptores ordenados e las columnas definidas
     */
    public List<Column> getColumns() {
        return columns;
    }

    /**
     * @return descriptores de los renglones incorporados
     */
    public List<FieldValue[]> getRows() {
        return rows;
    }

    /**
     * @return descriptores de los totalizadores
     */
    public List<FieldValue> getTotals() {
        return totals;
    }

    /**
     * Método de conveniencia que permite definir las columnas indicando una lista de títulos separados por comas
     *
     * @param line lista de títulos de columans separados por comas
     */
    public void parseCols(String line) {
        columns.clear();
        String[] cols = line.split(",");
        for (String title : cols) {
            Column column = new Column();
            column.setTitle(title);
            columns.add(column);
        }
    }

    /**
     * Método de conveniencia que permite definir el ancho relativo de todas las columnas especificando la representación decimal de los valores separados por especios
     *
     * @param line
     */
    public void parseWidthFactors(String line) {
        String[] cols = line.split(" ");
        for (int i = 0; i < cols.length; i++) {
            String col = cols[i];
            columns.get(i).setWeight(Integer.valueOf(col));
        }
    }

    /**
     * Incorpora un nuevo registro cuyas celdas contienen los datos previamente descriptos en invocaciones a addField
     */
    public void addRow() {
        rows.add(fields.toArray(new FieldValue[0]));
        fields = null;
    }

    /**
     * Incorpora una celda al próximo registro. La canitdad de celdas del registro debe coincidir con la cantidad de columans definidas.
     *
     * @param value     si el valor corresponde a alguno de los tipos soportados por ValueType se utiliza el formato automático, en caso contrario se utilizará toString para la salida del reporte.
     * @param fieldType
     */
    public void addField(Object value, ValueType fieldType) {
        if (fields == null) {
            fields = new ArrayList<FieldValue>();
        }
        FieldValue fieldValue = new FieldValue(fieldType, value);
        fieldValue.setColumn(columns.get(fields.size()));
        fields.add(fieldValue);
    }

    public String getTitle() {
        return title;
    }

    /**
     * @param title el título del reporte
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Incorpora un totalizador. La cantidad de totalizadores debe coincidir con la cantidad de columnas
     *
     * @param value
     * @param fieldType
     */
    public void addTotal(Object value, ValueType fieldType) {
        totals.add(new FieldValue(fieldType, value));
    }

    /**
     * Incorpora un totalizador vacío
     */
    public void addEmptyTotal() {
        totals.add(null);
    }

    public enum ValueType {STRING, INTEGER, DECIMAL, PERCENT, TIME, DATE, DATETIME}

    ;

    public class FieldValue implements Serializable {
        private final ValueType valueType;
        private final Object value;
        private Column column;

        public FieldValue(ValueType fieldType, Object value) {
            this.valueType = fieldType;
            this.value = value;
        }

        public ValueType getValueType() {
            return valueType;
        }

        public Object getValue() {
            return value;
        }

        public String getString() {
            if (value == null) {
                return null;
            } else if (valueType.equals(ValueType.DATE)) {
                return dateFormat.format((Date) value);
            } else if (valueType.equals(ValueType.DATETIME)) {
                return dateFormat.format((Date) value) + " " + timeFormat.format((Date) value);
            } else if (valueType.equals(ValueType.TIME)) {
                return timeFormat.format((Date) value);
            } else if (valueType.equals(ValueType.DECIMAL)) {
                return decimalFormat.format(((BigDecimal) value).doubleValue());
            } else if (valueType.equals(ValueType.PERCENT)) {
                return percentFormat.format((Double) value);
            } else {
                return value.toString();
            }
        }

        public String getAlign() {
            if (valueType.equals(ValueType.DATE)) {
                return "center";
            } else if (valueType.equals(ValueType.DATETIME)) {
                return "center";
            } else if (valueType.equals(ValueType.TIME)) {
                return "center";
            } else if (valueType.equals(ValueType.DECIMAL)) {
                return "right";
            } else if (valueType.equals(ValueType.INTEGER)) {
                return "right";
            } else if (valueType.equals(ValueType.PERCENT)) {
                return "right";
            } else {
                return "left";
            }
        }

        public String getWrapAsStyle() {
            return getColumn().getFieldWrapAsStyle();
        }

        public String getWhiteSpaceAsStyle() {
            return column.getFieldWhiteSpaceAsStyle();
        }

        public String getWidthAsStyle() {
            return column.getWidthAsStyle();
        }

        public Column getColumn() {
            return column;
        }

        public void setColumn(Column column) {
            this.column = column;
        }
    }

    public class Column implements Serializable {
        private String title;
        private Integer weight;
        private Integer minWidth;
        private boolean fieldBreakWord = false;
        private boolean fieldWrap = true;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public String getWidthAsStyle() {
            if (minWidth != null) {
                return "min-width: " + minWidth + "px;";
            } else if (weight != null) {
                double d = 100.0 / (double) columns.size();
                int w = (int) (d * (double) weight);
                return "min-width: " + w + "%;";
            } else {
                return "wdth: auto;";
            }
        }

        public boolean isFieldBreakWord() {
            return fieldBreakWord;
        }

        public void setFieldBreakWord(boolean fieldBreakWord) {
            this.fieldBreakWord = fieldBreakWord;
        }

        public String getFieldWrapAsStyle() {
            if (fieldBreakWord) {
                return "word-wrap: break-word;";
            } else {
                return "";
            }
        }

        public boolean isFieldWrap() {
            return fieldWrap;
        }

        public void setFieldWrap(boolean fieldWrap) {
            this.fieldWrap = fieldWrap;
        }

        public String getFieldWhiteSpaceAsStyle() {
            if (fieldWrap) {
                return "";
            } else {
                return " white-space: nowrap;";
            }
        }

        public Integer getMinWidth() {
            return minWidth;
        }

        public void setMinWidth(Integer minWidth) {
            this.minWidth = minWidth;
        }
    }

}
