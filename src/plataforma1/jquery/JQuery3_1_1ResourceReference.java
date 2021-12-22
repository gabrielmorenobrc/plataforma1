package plataforma1.jquery;

import org.apache.wicket.request.resource.JavaScriptResourceReference;

public class JQuery3_1_1ResourceReference extends JavaScriptResourceReference {
    private static final long serialVersionUID = 1L;
    public static final String VERSION_1 = "jquery-3.1.1.min.js";
    private static final JQuery3_1_1ResourceReference INSTANCE = new JQuery3_1_1ResourceReference();

    public static JQuery3_1_1ResourceReference get() {
        return INSTANCE;
    }

    protected JQuery3_1_1ResourceReference() {
        super(JQuery3_1_1ResourceReference.class, "jquery-3.1.1.min.js");
    }
}
