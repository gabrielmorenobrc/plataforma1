package plataforma1.wicket.semantic;

import org.apache.wicket.Page;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashMap;

@SessionScoped
public class NotifierProvider implements Serializable {

    private HashMap<Integer, NotifierPanel> notifiersMap = new HashMap<Integer, NotifierPanel>();
    private Integer lastPageKeyUsed = 0;
    private String onShow;

    public void createNotifier(Page parentPage, String id) {
        Integer pageKey = generateAndAssignPageKey(parentPage);
        NotifierPanel notifierPanel = new NotifierPanel(id);
        notifierPanel.setOnShow(onShow);
        parentPage.add(notifierPanel);
        notifiersMap.put(pageKey, notifierPanel);
    }

    public NotifierPanel getNotifier(Page parentPage) {
        Integer pageKey = retrievePageKey(parentPage);
        NotifierPanel notifierpanel = notifiersMap.get(pageKey);
        return notifierpanel;
    }

    private Integer generateAndAssignPageKey(Page page) {
        Integer pageKey = getNextPageKey();
        page.setMetaData(NotifierProviderMetaDataKey.INSTANCE, pageKey);
        return pageKey;
    }

    private synchronized Integer getNextPageKey() {
        return ++lastPageKeyUsed;
    }

    private Integer retrievePageKey(Page page) {
        Integer pageKey = page.getMetaData(NotifierProviderMetaDataKey.INSTANCE);
        return pageKey;
    }

    public String getOnShow() {
        return onShow;
    }

    public void setOnShow(String onShow) {
        this.onShow = onShow;
    }
}
