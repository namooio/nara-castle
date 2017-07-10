package namoo.nara.castle.da.mongo.document;

import namoo.nara.castle.domain.view.CastellanView;
import namoo.nara.share.domain.NameValueList;
import namoo.nara.share.domain.granule.AddressList;
import namoo.nara.share.domain.granule.EmailList;
import namoo.nara.share.domain.granule.NameList;
import namoo.nara.share.domain.granule.PhoneList;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.stream.Collectors;

@Document(collection = "CA_CASTELLAN")
public class CastellanViewDoc {
    //
    @Id
    private String id;

    private NameList names;
    private PhoneList phones;
    private EmailList emails;
    private AddressList addresses;
    private NameValueList attrNameValues;

    public CastellanViewDoc() {
        //
    }

    public static CastellanViewDoc toDocument(CastellanView castellanView) {
        //
        CastellanViewDoc castellanViewDoc = new CastellanViewDoc();
        BeanUtils.copyProperties(castellanView, castellanViewDoc);
        return castellanViewDoc;
    }

    public static List<CastellanView> toDomains(List<CastellanViewDoc> castellanViewDocs) {
        //
        return castellanViewDocs.stream().map(doc -> doc.toDomain()).collect(Collectors.toList());
    }

    public CastellanView toDomain() {
        //
        CastellanView castellanView = new CastellanView();
        BeanUtils.copyProperties(this, castellanView);
        return castellanView;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public NameList getNames() {
        return names;
    }

    public void setNames(NameList names) {
        this.names = names;
    }

    public PhoneList getPhones() {
        return phones;
    }

    public void setPhones(PhoneList phones) {
        this.phones = phones;
    }

    public EmailList getEmails() {
        return emails;
    }

    public void setEmails(EmailList emails) {
        this.emails = emails;
    }

    public AddressList getAddresses() {
        return addresses;
    }

    public void setAddresses(AddressList addresses) {
        this.addresses = addresses;
    }

    public NameValueList getAttrNameValues() {
        return attrNameValues;
    }

    public void setAttrNameValues(NameValueList attrNameValues) {
        this.attrNameValues = attrNameValues;
    }
}
