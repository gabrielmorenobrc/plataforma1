package plataforma1.wicket.semantic;

import com.vaynberg.wicket.select2.ApplicationSettings;
import org.apache.wicket.markup.head.*;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.resource.JQueryResourceReference;

import java.util.Arrays;
import java.util.List;

public class SemanticResourceReference extends JavaScriptResourceReference {

    private static final SemanticResourceReference instance = new SemanticResourceReference();

    private SemanticResourceReference() {
        super(SemanticResourceReference.class, "resources/semantic.min.js");
    }

    public static SemanticResourceReference get() {
        return instance;
    }

    @Override
    public List<HeaderItem> getDependencies() {
        JavaScriptReferenceHeaderItem jquery = JavaScriptHeaderItem.forReference(JQueryResourceReference.getV3());
        CssReferenceHeaderItem style = CssHeaderItem.forReference(new CssResourceReference(SemanticResourceReference.class, "resources/semantic.min.css"));
        CssReferenceHeaderItem semanticOverrides = CssHeaderItem.forReference(new CssResourceReference(SemanticResourceReference.class, "resources/semantic-overrides.css"));
        CssReferenceHeaderItem select2Style = CssHeaderItem.forReference(new CssResourceReference(ApplicationSettings.class, "res/select2.css"));
        CssReferenceHeaderItem select2Overrides = CssHeaderItem.forReference(new CssResourceReference(SemanticResourceReference.class, "resources/select2-overrides.css"));
        return Arrays.asList(jquery, style, semanticOverrides, select2Style, select2Overrides);
    }

}
