package namoo.nara.castle;

import namoo.nara.castle.rep.dto.CastleBuildDto;

import java.util.Locale;
import java.util.UUID;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class CastellanContactFrontResourceTest { // extends AbstractCastleServiceApplicationTests  {
    //
    private String id;

//    @Before
    public void setupInitialData() {
        //
        id = UUID.randomUUID().toString();
        CastleBuildDto castleBuildDto = new CastleBuildDto();
        castleBuildDto.setName("Michael's Castle");
        castleBuildDto.setLocale(Locale.US);
//        getCastleRepClient().buildCastle(id, castleBuildDto);
    }

    // FIXME: 수정할 것.
    /*
    @Test
    public void testUserAddress() {
        //
        getCastellanContactFrontClient().attachAddressBook(id, new AddressBookDto());

        UserAddressDto userAddressDto = new UserAddressDto();
        userAddressDto.setStyle("Korean");
        userAddressDto.setTitle("우리집주소");
        userAddressDto.setZipCode("16876");
        getCastellanContactFrontClient().addUserAddress(id, userAddressDto);

        userAddressDto = new UserAddressDto();
        userAddressDto.setStyle("Korean");
        userAddressDto.setTitle("회사주소");
        userAddressDto.setZipCode("153-792");
        getCastellanContactFrontClient().addUserAddress(id, userAddressDto);

        AddressBookDto addressBookDto = getCastellanContactFrontClient().findAddressBook(id);
        Assert.assertEquals(2, addressBookDto.getAddresses().size());

        getCastellanContactFrontClient().removeUserAddress(id, "회사주소");
        addressBookDto = getCastellanContactFrontClient().findAddressBook(id);
        Assert.assertEquals(1, addressBookDto.getAddresses().size());

        userAddressDto = new UserAddressDto();
        userAddressDto.setStyle("Korean");
        userAddressDto.setTitle("우리집주소");
        userAddressDto.setZipCode("448-538");
        getCastellanContactFrontClient().modifyUserAddress(id, userAddressDto);
        addressBookDto = getCastellanContactFrontClient().findAddressBook(id);
        Assert.assertEquals("448-538", addressBookDto.getAddresses().get(0).getZipCode());
    }
    */

}
