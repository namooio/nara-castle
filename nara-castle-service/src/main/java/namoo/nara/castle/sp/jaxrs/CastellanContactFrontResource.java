package namoo.nara.castle.sp.jaxrs;

import namoo.nara.castle.domain.service.CastleServiceLycler;
import namoo.nara.castle.front.dto.contact.AddressBookDto;
import namoo.nara.castle.front.dto.contact.EmailBookDto;
import namoo.nara.castle.front.dto.contact.NameBookDto;
import namoo.nara.castle.front.dto.contact.PhoneBookDto;
import namoo.nara.castle.front.dto.contact.UserAddressDto;
import namoo.nara.castle.front.logic.CastellanContactFrontServiceLogic;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
//@Controller
@Path("front/castellans/{id}/contacts")
public class CastellanContactFrontResource extends CastellanContactFrontServiceLogic {
    //
//    @Autowired
    public CastellanContactFrontResource(CastleServiceLycler serviceLycler) {
        super(serviceLycler);
    }

    @Override
    @Path("namebook")
    @POST
    public void attachNameBook(@PathParam("id") String castleId, NameBookDto nameBookDto) {
        super.attachNameBook(castleId, nameBookDto);
    }

    @Override
    @Path("namebook")
    @DELETE
    public void detachNameBook(@PathParam("id") String castleId) {
        super.detachNameBook(castleId);
    }

    @Override
    @Path("namebook")
    @GET
    public NameBookDto findNameBook(@PathParam("id") String castleId) {
        return super.findNameBook(castleId);
    }

    @Override
    @Path("emailbook")
    @POST
    public void attachEmailBook(@PathParam("id") String castleId, EmailBookDto emailBookDto) {
        super.attachEmailBook(castleId, emailBookDto);
    }

    @Override
    @Path("emailbook")
    @DELETE
    public void detachEmailBook(@PathParam("id") String castleId) {
        super.detachEmailBook(castleId);
    }

    @Override
    @Path("emailbook")
    @GET
    public EmailBookDto findEmailBook(@PathParam("id") String castleId) {
        return super.findEmailBook(castleId);
    }

    @Override
    @Path("phonebook")
    @POST
    public void attachPhoneBook(@PathParam("id") String castleId, PhoneBookDto phoneBookDto) {
        super.attachPhoneBook(castleId, phoneBookDto);
    }

    @Override
    @Path("phonebook")
    @DELETE
    public void detachPhoneBook(@PathParam("id") String castleId) {
        super.detachPhoneBook(castleId);
    }

    @Override
    @Path("phonebook")
    @GET
    public PhoneBookDto findPhoneBook(@PathParam("id") String castleId) {
        return super.findPhoneBook(castleId);
    }

    @Override
    @Path("addressbook")
    @POST
    public void attachAddressBook(@PathParam("id") String castleId, AddressBookDto addressBookDto) {
        super.attachAddressBook(castleId, addressBookDto);
    }

    @Override
    @Path("addressbook")
    @DELETE
    public void detachAddressBook(@PathParam("id") String castleId) {
        super.detachAddressBook(castleId);
    }

    @Override
    @Path("addresses")
    @POST
    public void addUserAddress(@PathParam("id") String castleId, UserAddressDto addressDto) {
        super.addUserAddress(castleId, addressDto);
    }

    @Override
    @Path("addresses/{title}")
    @DELETE
    public void removeUserAddress(@PathParam("id") String castleId, @PathParam("title") String addressTitle) {
        super.removeUserAddress(castleId, addressTitle);
    }

    @Override
    @Path("addresses")
    @PUT
    public void modifyUserAddress(@PathParam("id") String castleId, UserAddressDto addressDto) {
        super.modifyUserAddress(castleId, addressDto);
    }

    @Override
    @Path("addressbook")
    @GET
    public AddressBookDto findAddressBook(@PathParam("id") String castleId) {
        return super.findAddressBook(castleId);
    }
}
