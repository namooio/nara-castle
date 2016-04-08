package namoo.nara.castle.domain.entity;

import namoo.nara.castle.domain.entity.contact.ContactBundle;
import namoo.nara.castle.domain.entity.history.HistoryBundle;

public class InfoBundleBox {
    //
    private String castleId;

    private ContactBundle contactBundle;
    private HistoryBundle historyBundle;

    public InfoBundleBox(String castleId) {
        //
        this.castleId = castleId;
    }

    public ContactBundle getContactBundle() {
        //
        if(contactBundle == null) {
            this.contactBundle = ContactBundle.newInstance(castleId);
        }

        return contactBundle;
    }

    public void setContactBundle(ContactBundle contactBundle) {
        this.contactBundle = contactBundle;
    }

    public HistoryBundle getHistoryBundle() {
        //
        if(historyBundle == null) {
            this.historyBundle = HistoryBundle.newInstance(castleId);
        }

        return historyBundle;
    }

    public void setHistoryBundle(HistoryBundle historyBundle) {
        this.historyBundle = historyBundle;
    }
}