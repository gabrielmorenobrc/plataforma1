package plataforma1.wicket.semantic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Validation {
    private String formId;
    private boolean onBlur;
    private final Map<String, List<String>> fieldMap = new HashMap<>();

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public void add(String fieldName, String rule) {
        List<String> list = fieldMap.get(fieldName);
        if (list == null) {
            list = new ArrayList<>();
            fieldMap.put(fieldName, list);
        }
        list.add(rule);
    }

    public String toScript() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("$('#").append(formId).append("').form({");
        if (onBlur) {
            stringBuilder.append("on: 'blur', ");
        }
        stringBuilder.append("fields : {");
        boolean first = true;
        for (String key : fieldMap.keySet()) {
            if (first) {
                first = false;
            } else {
                stringBuilder.append(",");
            }
            stringBuilder.append(key);
            stringBuilder.append(" : {identifier : '").append(key).append("',");
            stringBuilder.append("rules : [");
            boolean firstRule = true;
            for (String rule : fieldMap.get(key)) {
                if (firstRule) {
                    firstRule = false;
                } else {
                    stringBuilder.append(",");
                }
                stringBuilder.append("{").append(rule).append("}");
            }
            stringBuilder.append("]}");
        }
        stringBuilder.append("}});");
        return stringBuilder.toString();
    }

    public boolean isOnBlur() {
        return onBlur;
    }

    public void setOnBlur(boolean onBlur) {
        this.onBlur = onBlur;
    }
}
