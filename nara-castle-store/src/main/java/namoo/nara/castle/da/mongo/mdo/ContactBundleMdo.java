package namoo.nara.castle.da.mongo.mdo;

import namoo.nara.castle.da.mongo.mdo.contact.AddressBookMdo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 6..
 */
@Document(collection = "ContactBundle")
public class ContactBundleMdo {
    //
    @Id
    private String id;

    private AddressBookMdo addressBookMdo;

    public ContactBundleMdo() {
        //
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AddressBookMdo getAddressBookMdo() {
        return addressBookMdo;
    }

    public void setAddressBookMdo(AddressBookMdo addressBookMdo) {
        this.addressBookMdo = addressBookMdo;
    }
}
