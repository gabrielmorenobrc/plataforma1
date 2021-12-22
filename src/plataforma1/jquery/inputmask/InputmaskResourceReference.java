package plataforma1.jquery.inputmask;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.resource.JQueryResourceReference;

import java.util.Arrays;

public class InputmaskResourceReference extends JavaScriptResourceReference {

    private static final InputmaskResourceReference instance = new InputmaskResourceReference();

    private InputmaskResourceReference() {
        super(InputmaskResourceReference.class, "dist/jquery.inputmask.bundle.js");
    }

    public static InputmaskResourceReference get() {
        return instance;
    }

    @Override
    public Iterable<? extends HeaderItem> getDependencies() {
        JavaScriptReferenceHeaderItem jquery = JavaScriptHeaderItem.forReference(JQueryResourceReference.get());
//        JavaScriptReferenceHeaderItem jquery = JavaScriptHeaderItem.forReference(JQuery3_1_1ResourceReference.get());
//        JavaScriptHeaderItem phone = JavaScriptHeaderItem.forReference(new JavaScriptResourceReference(InputmaskResourceReference.class, "dist/inputmask/phone-codes/phone.js"));
//        JavaScriptHeaderItem phone_be = JavaScriptHeaderItem.forReference(new JavaScriptResourceReference(InputmaskResourceReference.class, "dist/inputmask/phone-codes/phone-be.js"));
//        JavaScriptHeaderItem phone_ru = JavaScriptHeaderItem.forReference(new JavaScriptResourceReference(InputmaskResourceReference.class, "dist/inputmask/phone-codes/phone-ru.js"));
//        JavaScriptHeaderItem binding = JavaScriptHeaderItem.forReference(new JavaScriptResourceReference(InputmaskResourceReference.class, "dist/inputmask/bindings/inputmask.binding.js"));
        return Arrays.asList(jquery/*, phone, phone_be, phone_ru, binding*/);
    }
}
