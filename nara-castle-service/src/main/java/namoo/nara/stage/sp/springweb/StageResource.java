package namoo.nara.stage.sp.springweb;

import namoo.nara.envoy.rep.EnvoyRepService;
import namoo.nara.envoy.rep.dto.PlayerDto;
import namoo.nara.stage.envoy.EnvoyLycler;
import namoo.nara.stage.poster.cp.pojo.PosterServicePojoLycler;
import namoo.nara.stage.poster.service.PosterService;
import namoo.nara.stage.rolebook.cp.pojo.RoleBookServicePojoLycler;
import namoo.nara.stage.rolebook.entity.Role;
import namoo.nara.stage.rolebook.entity.RoleBook;
import namoo.nara.stage.rolebook.service.RoleBookService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by hkkang on 2016-06-14.
 */
@RestController
@RequestMapping("stage")
public class StageResource {
    //
    private PosterService posterService;
    private RoleBookService roleBookService;
    private EnvoyRepService envoyRepService;


    public StageResource() {
        //
        posterService = new PosterServicePojoLycler().requestPosterService();
        roleBookService = new RoleBookServicePojoLycler().requestRoleBookService();
        envoyRepService = EnvoyLycler.getInstance().requestEnvoyService();
    }


    // Envoy
    @RequestMapping(value = "players", method = RequestMethod.GET)
    public List<PlayerDto> findPlayers(@RequestParam("pavilionId") String pavilionId, @RequestParam("castingId") String castingId) {
        //
        return envoyRepService.findPlayers(pavilionId, castingId);
    }

    // RoleBook
    @RequestMapping(value = "rolebook", method = RequestMethod.POST)
    public void saveRoleBook(@RequestBody RoleBook roleBook) {
        //
        roleBookService.saveRoleBook(roleBook);
    }

    @RequestMapping(value = "rolebooks/castingId/{castingId}", method = RequestMethod.GET)
    public RoleBook findRoleBook(@PathVariable("castingId") String castingId) {
        //
        return roleBookService.findRoleBook(castingId);
    }

    @RequestMapping(value = "roles", method = RequestMethod.GET)
    public List<Role> findRoles() {
        //
        return roleBookService.findRoles();
    }
    @RequestMapping(value = "rolebook/players/{playerId}/roles", method = RequestMethod.GET)
    public List<Role> findRolesByPlayer(@RequestParam("castingId") String castingId, @PathVariable("playerId") String playerId) {
        //
        return roleBookService.findRoles(castingId, playerId);
    }

}
