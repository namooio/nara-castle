package namoo.nara.castle.sp.jaxrs;

import namoo.nara.castle.domain.service.CastleServiceLycler;
import namoo.nara.castle.front.dto.history.AccountBookDto;
import namoo.nara.castle.front.dto.history.CastleStateBookDto;
import namoo.nara.castle.front.dto.history.MetroBookDto;
import namoo.nara.castle.front.logic.CastleHistoryFrontServiceLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
@Controller
@Path("front/castles/{id}/histories")
public class CastleHistoryFrontResource extends CastleHistoryFrontServiceLogic {

    @Autowired
    public CastleHistoryFrontResource(CastleServiceLycler serviceLycler) {
        super(serviceLycler);
    }

    @Override
    @Path("accountbook")
    @POST
    public void attachAccountBook(@PathParam("id") String castleId, AccountBookDto accountBookDto) {
        super.attachAccountBook(castleId, accountBookDto);
    }

    @Override
    @Path("accountbook")
    @DELETE
    public void detachAccountBook(@PathParam("id") String castleId) {
        super.detachAccountBook(castleId);
    }

    @Override
    @Path("accountbook")
    @GET
    public AccountBookDto findAccountBook(@PathParam("id") String castleId) {
        return super.findAccountBook(castleId);
    }

    @Override
    @Path("statebook")
    @POST
    public void attachCastleStateBook(@PathParam("id") String castleId, CastleStateBookDto castleStateBookDto) {
        super.attachCastleStateBook(castleId, castleStateBookDto);
    }

    @Override
    @Path("statebook")
    @DELETE
    public void detachCastleStateBook(@PathParam("id") String castleId) {
        super.detachCastleStateBook(castleId);
    }

    @Override
    @Path("statebook")
    @GET
    public CastleStateBookDto findCastleStateBook(@PathParam("id") String castleId) {
        return super.findCastleStateBook(castleId);
    }

    @Override
    @Path("metrobook")
    @POST
    public void attachMetroBook(@PathParam("id") String castleId, MetroBookDto metroBookDto) {
        super.attachMetroBook(castleId, metroBookDto);
    }

    @Override
    @Path("metrobook")
    @DELETE
    public void detatchMetroBook(@PathParam("id") String castleId) {
        super.detatchMetroBook(castleId);
    }

    @Override
    @Path("metrobook")
    @GET
    public MetroBookDto findMetroBook(@PathParam("id") String castleId) {
        return super.findMetroBook(castleId);
    }
}
