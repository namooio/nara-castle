package namoo.nara.castle.sp.jaxrs;

import namoo.nara.castle.domain.service.CastleServiceLycler;
import namoo.nara.castle.front.dto.CastellanFindDto;
import namoo.nara.castle.front.logic.CastellanFrontServiceLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
@Controller
@Path("front/castellans/{id}")
public class CastellanFrontResource extends CastellanFrontServiceLogic {

    @Autowired
    public CastellanFrontResource(CastleServiceLycler castleServiceLycler) {
        super(castleServiceLycler);
    }

    @Override
    @GET
    public CastellanFindDto findCastellan(@PathParam("id") String id) {
        //
        return super.findCastellan(id);
    }

    @Override
    @Path("displayname")
    @PUT
    public void modifyDisplayName(@PathParam("id") String id, String displayName) {
        //
        super.modifyDisplayName(id, displayName);
    }

    @Override
    @Path("photo")
    @PUT
    public void modifyPhoto(@PathParam("id") String id, String photoId) {
        //
        super.modifyPhoto(id, photoId);
    }

    @Override
    @Path("primaryemail")
    @PUT
    public void modifyPrimaryEmail(@PathParam("id") String id, String email) {
        //
        super.modifyPrimaryEmail(id, email);
    }

    @Override
    @Path("primaryphone")
    @PUT
    public void modifyPrimaryPhone(@PathParam("id") String id, String phoneNumber) {
        //
        super.modifyPrimaryPhone(id, phoneNumber);
    }
}
