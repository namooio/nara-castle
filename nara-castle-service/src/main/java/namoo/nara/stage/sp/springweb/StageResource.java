package namoo.nara.stage.sp.springweb;

import namoo.nara.stage.envoy.cp.pojo.EnvoyServicePojoLycler;
import namoo.nara.stage.envoy.entity.Player;
import namoo.nara.stage.envoy.service.PlayerService;
import namoo.nara.stage.poster.cp.pojo.PosterServicePojoLycler;
import namoo.nara.stage.poster.service.PosterService;
import namoo.nara.stage.rolebook.cp.pojo.RoleBookServicePojoLycler;
import namoo.nara.stage.rolebook.entity.Role;
import namoo.nara.stage.rolebook.entity.RoleBook;
import namoo.nara.stage.rolebook.entity.RolePlayer;
import namoo.nara.stage.rolebook.service.RoleBookService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
//        return playerService.findPlayers(castingId);

        List<Player> players = playerService.findPlayers(castingId);

        for(Player player : players) {
            if (player.getId().equals("01-002-1")) {
                player.setLeader(true);
            }
        }
        return players;
    }

    // RoleBook
    @RequestMapping(value = "rolebooks", method = RequestMethod.POST)
    public void saveRoleBook(@RequestBody RoleBook roleBook) {
        //
        roleBookService.saveRoleBook(roleBook);
    }

    @RequestMapping(value = "rolebooks/castingId/{castingId}", method = RequestMethod.GET)
    public RoleBook findRoleBook(@PathVariable("castingId") String castingId) {
        //
        try {
            return roleBookService.findRoleBook(castingId);
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(value = "roles", method = RequestMethod.GET)
    public List<Role> findRoles() {
        //
        return roleBookService.findRoles();
    }

    @RequestMapping(value = "roles/castingId/{castingId}/playerId/{playerId}", method = RequestMethod.GET)
    public List<Role> findRolesByPlayer(@PathVariable("castingId") String castingId, @PathVariable("playerId") String playerId) {
        //
        return roleBookService.findRoles(castingId, playerId);
    }



    private void initRoleBook() {
        //
        if (roleBookService.findRoles().size() > 0) {
            return;
        }

        RoleBook roleBook = new RoleBook();
        roleBook.setCastingId("01-002");

        List<RolePlayer> rolePlayers = new ArrayList<>();

        RolePlayer rolePlayer = new RolePlayer();
        rolePlayer.setPlayerId("01-002-3");
        rolePlayer.setName("hkkang");

        List<Role> roles = new ArrayList<Role>();

        Role role = new Role();
        role.setName("Admin");
        role.setDescription("Admin role");
        roles.add(role);

        role = new Role();
        role.setName("User");
        role.setDescription("User role");
        roles.add(role);

        rolePlayer.setRoles(roles);
        rolePlayers.add(rolePlayer);

        roleBook.setRolePlayers(rolePlayers);
        roleBookService.saveRoleBook(roleBook);
    }
}
