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
        //
        if(contactBundle == null) {
            this.contactBundle = new ContactBundle();
        }

        return contactBundle;
    }

    public void setContactBundle(ContactBundle contactBundle) {
        this.contactBundle = contactBundle;
    }

    public HistoryBundle getHistoryBundle() {
        //
        if(historyBundle == null) {
            this.historyBundle = new HistoryBundle();
        }

        return historyBundle;
    }

    public void setHistoryBundle(HistoryBundle historyBundle) {
        this.historyBundle = historyBundle;
    }
}