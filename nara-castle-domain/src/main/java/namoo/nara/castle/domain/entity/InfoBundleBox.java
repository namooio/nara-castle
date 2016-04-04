package namoo.nara.castle.domain.entity;

import namoo.nara.castle.domain.entity.contact.ContactBundle;
import namoo.nara.castle.domain.entity.history.HistoryBundle;

import java.util.HashMap;

public class InfoBundleBox {
    //
    private ContactBundle contactBundle;
    private HistoryBundle historyBundle;

    public InfoBundleBox() {
        //
    }

    public ContactBundle getContactBundle() {
        return contactBundle;
    }

    public void setContactBundle(ContactBundle contactBundle) {
        this.contactBundle = contactBundle;
    }

    public HistoryBundle getHistoryBundle() {
        return historyBundle;
    }

    public void setHistoryBundle(HistoryBundle historyBundle) {
        this.historyBundle = historyBundle;
    }
}