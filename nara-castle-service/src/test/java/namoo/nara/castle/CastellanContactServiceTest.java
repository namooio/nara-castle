package namoo.nara.castle;

import namoo.nara.castle.adapter.dto.CastleBuildDto;
import namoo.nara.castle.adapter.dto.contact.AddressBookDto;
import namoo.nara.castle.adapter.dto.contact.UserAddressDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;
import java.util.UUID;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class CastellanContactServiceTest extends AbstractCastleServiceApplicationTests  {
    //
    private String id;

    @Before
    public void setupInitialData() {
        //
        id = UUID.randomUUID().toString();
        CastleBuildDto castleBuildDto = new CastleBuildDto();
        castleBuildDto.setName("Michael's Castle");
        castleBuildDto.setMetroId("99");
        castleBuildDto.setLocale(Locale.US);
        getCastleClient().buildCastle(id, castleBuildDto);
    }

    @Test
    public void testUserAddress() {
        //
        getCastellanContactClient().attachAddressBook(id, new AddressBookDto());

        UserAddressDto userAddressDto = new UserAddressDto();
        userAddressDto.setStyle("Korean");
        userAddressDto.setTitle("우리집주소");
        userAddressDto.setZipCode("16876");
        getCastellanContactClient().addUserAddress(id, userAddressDto);

        userAddressDto = new UserAddressDto();
        userAddressDto.setStyle("Korean");
        userAddressDto.setTitle("회사주소");
        userAddressDto.setZipCode("153-792");
        getCastellanContactClient().addUserAddress(id, userAddressDto);

        AddressBookDto addressBookDto = getCastellanContactClient().findAddressBook(id);
        Assert.assertEquals(2, addressBookDto.getAddresses().size());

        getCastellanContactClient().removeUserAddress(id, "회사주소");
        addressBookDto = getCastellanContactClient().findAddressBook(id);
        Assert.assertEquals(1, addressBookDto.getAddresses().size());

        userAddressDto = new UserAddressDto();
        userAddressDto.setStyle("Korean");
        userAddressDto.setTitle("우리집주소");
        userAddressDto.setZipCode("448-538");
        getCastellanContactClient().modifyUserAddress(id, userAddressDto);
        addressBookDto = getCastellanContactClient().findAddressBook(id);
        Assert.assertEquals("448-538", addressBookDto.getAddresses().get(0).getZipCode());
    }

}
