package plataforma1.wicket.semantic;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

public class NotifierPanel extends Panel {

    private final WebMarkupContainer popup;
    private String title;
    private String message;
    private NotifierBehavior notifierBehavior;
    private String onShow;
    private long duration;

    public NotifierPanel(String id) {
        super(id);
        setOutputMarkupId(true);

        notifierBehavior = new NotifierBehavior();
        add(notifierBehavior);

        popup = new WebMarkupContainer("popup");
        popup.setOutputMarkupId(true);
        add(popup);
        popup.add(new Label("title", new PropertyModel<String>(this, "title")).setEscapeModelStrings(false));
        popup.add(new Label("message", new PropertyModel<String>(this, "message")).setEscapeModelStrings(false));
        duration = 10000;
    }

    public void notify(String title, String message) {
        notify(title, message, null);
    }

    public void notify(String title, String message, Component targetComponent) {
        this.title = title;
        this.message = message;
        AjaxRequestTarget ajaxRequestTarget = getRequestCycle().find(AjaxRequestTarget.class);
        ajaxRequestTarget.add(this);
        ajaxRequestTarget.appendJavaScript(
                "clearTimeout(window.notifierTimeout);\n;" +
                        "$('#" + popup.getMarkupId() + "').show();\n" +
                        "window.notifierTimeout = window.setTimeout(function(){ $('#" + notifierBehavior.getMarkupId() + "').find('.notifier').hide() }, " + duration + ");\n");
        if (targetComponent != null) {
            String js = "var top = document.getElementById('" + targetComponent.getMarkupId() + "').getBoundingClientRect().top ;\n"
                    + " var h = document.getElementById('" + popup.getMarkupId() + "').clientHeight;\n"
                    + " var offset = top - h; if (offset < 0) {offset = 0};\n"
                    + "document.getElementById('" + popup.getMarkupId() + "').style.top = offset + 'px';\n";
            ajaxRequestTarget.appendJavaScript(js);
        }
        if (onShow != null) {
            ajaxRequestTarget.appendJavaScript(onShow);
        }

    }

    public void setOnShow(String onShow) {
        this.onShow = onShow;
    }

    public String getOnShow() {
        return onShow;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
