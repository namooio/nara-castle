package namoo.nara.castle.sp.jaxrs;

import namoo.nara.castle.domain.service.CastleServiceLycler;
import namoo.nara.castle.rep.dto.CastleBuildDto;
import namoo.nara.castle.rep.logic.CastleRepServiceLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * Created by kchuh@nextree.co.kr on 2016. 5. 9..
 */
@Controller
@Path("rep/castles")
public class CastleRepResource extends CastleRepServiceLogic {
    //
    @Autowired
    public CastleRepResource(CastleServiceLycler castleServiceLycler) {
        //
        super(castleServiceLycler);
    }

    @Override
    @Path("{id}")
    @POST
    public void buildCastle(@PathParam("id") String castleId, CastleBuildDto castleBuildDto) {
        super.buildCastle(castleId, castleBuildDto);
    }

    @Override
    @Path("{id}/metro/{metroId}")
    @POST
    public void addMetro(@PathParam("id") String castleId, @PathParam("metroId") String metroId) {
        super.addMetro(castleId, metroId);
    }
}
