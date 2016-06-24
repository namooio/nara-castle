package namoo.nara.stage.sp.springweb;

import namoo.nara.stage.envoy.cp.pojo.EnvoyServicePojoLycler;
import namoo.nara.stage.envoy.entity.Player;
import namoo.nara.stage.envoy.service.PlayerService;
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
    private PlayerService playerService;
    private RoleBookService roleBookService;


    public StageResource() {
        //
        posterService = new PosterServicePojoLycler().requestPosterService();
        playerService = new EnvoyServicePojoLycler().requestPlayerService();
        roleBookService = new RoleBookServicePojoLycler().requestRoleBookService();
    }


    // Envoy
    @RequestMapping(value = "players", method = RequestMethod.GET)
    public List<Player> findPlayers(@RequestParam("castingId") String castingId) {
        //
        return playerService.findPlayers("01", castingId);
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
