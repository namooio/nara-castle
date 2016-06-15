package namoo.nara.castle.sp.jaxrs;

import namoo.nara.castle.domain.service.CastleServiceLycler;
import namoo.nara.castle.front.dto.CastleFindDto;
import namoo.nara.castle.front.logic.CastleFrontServiceLogic;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;
import java.util.Locale;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 19..
 */
//@Controller
@Path("front/castles")
public class CastleFrontResource extends CastleFrontServiceLogic {
    //
//    @Autowired
    public CastleFrontResource(CastleServiceLycler castleServiceLycler) {
        super(castleServiceLycler);
    }

    @Override
    @Path("{id}/suspend")
    @PUT
    public void suspendCastle(@PathParam("id") String id, String remarks) {
        super.suspendCastle(id, remarks);
    }

    @Override
    @Path("{id}/reopen")
    @PUT
    public void reopenCastle(@PathParam("id") String id, String remarks) {
        super.reopenCastle(id, remarks);
    }

    @Override
    @Path("{id}/name")
    @PUT
    public void modifyName(@PathParam("id") String id, String name) {
        super.modifyName(id, name);
    }

    @Override
    @Path("{id}/locale")
    @PUT
    public void modifyLocale(@PathParam("id") String id, Locale locale) {
        super.modifyLocale(id, locale);
    }

    @Override
    @Path("{id}")
    @GET
    public CastleFindDto findCastle(@PathParam("id") String id) {
        return super.findCastle(id);
    }

    @Override
    @GET
    public List<CastleFindDto> findAllCastles() {
        return super.findAllCastles();
    }
}
