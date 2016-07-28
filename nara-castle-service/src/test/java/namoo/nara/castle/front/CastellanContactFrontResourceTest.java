package namoo.nara.castle.front;

import namoo.nara.castle.AbstractCastleApplicationTests;
import namoo.nara.castle.front.dto.contact.AddressBookDto;
import namoo.nara.castle.front.dto.contact.UserAddressDto;
import namoo.nara.castle.rep.dto.CastleBuildDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;
import java.util.UUID;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class CastellanContactFrontResourceTest extends AbstractCastleApplicationTests {
    //
    private String castleId;

    @Before
    public void setupInitialData() {
        //
        this.castleId = UUID.randomUUID().toString();

        CastleBuildDto castleBuildDto = new CastleBuildDto();
        castleBuildDto.setName("Michael's Castle");
        castleBuildDto.setLocale(Locale.US);

        getCastleRepClient().buildCastle(castleId, castleBuildDto);
    }

    @Test
    public void testUserAddress() {
        //
        // Attach address book
        getCastellanContactFrontClient().attachAddressBook(this.castleId, new AddressBookDto());

        // Add address
        UserAddressDto userAddressDto = new UserAddressDto();
        userAddressDto.setStyle("Korean");
        userAddressDto.setTitle("우리집주소");
        userAddressDto.setZipCode("16876");

        getCastellanContactFrontClient().addUserAddress(this.castleId, userAddressDto);

        userAddressDto = new UserAddressDto();
        userAddressDto.setStyle("Korean");
        userAddressDto.setTitle("회사주소");
        userAddressDto.setZipCode("153-792");

        getCastellanContactFrontClient().addUserAddress(this.castleId, userAddressDto);

        // Assert
        AddressBookDto addressBookDto = getCastellanContactFrontClient().findAddressBook(this.castleId);
        Assert.assertEquals(2, addressBookDto.getAddresses().size());

        // Assert remove address
        getCastellanContactFrontClient().removeUserAddress(this.castleId, "회사주소");

        addressBookDto = getCastellanContactFrontClient().findAddressBook(this.castleId);
        Assert.assertEquals(1, addressBookDto.getAddresses().size());

        // Assert modify address
        userAddressDto = new UserAddressDto();
        userAddressDto.setStyle("Korean");
        userAddressDto.setTitle("우리집주소");
        userAddressDto.setZipCode("448-538");

        getCastellanContactFrontClient().modifyUserAddress(this.castleId, userAddressDto);

        addressBookDto = getCastellanContactFrontClient().findAddressBook(this.castleId);
        Assert.assertEquals("448-538", addressBookDto.getAddresses().get(0).getZipCode());
    }

}
