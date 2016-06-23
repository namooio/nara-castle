package namoo.nara.castle.sp.springweb;

import namoo.nara.stage.envoy.cp.pojo.EnvoyServicePojoLycler;
import namoo.nara.stage.envoy.service.DramaContextService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 18..
 */
@Controller
public class DefaultFrontView {
    //
    private DramaContextService dramaContextService;


    public DefaultFrontView() {
        //
        dramaContextService = new EnvoyServicePojoLycler().requestDramaContextService();
    }


    @RequestMapping("/")
    public ModelAndView main(@RequestParam("pavilionId") String pavilionId, @RequestParam("castingId") String castingId, @RequestParam("playerId") String playerId) {
        //
        Map<String, String> model = new HashMap<>();
        model.put("pavilionId", pavilionId);
        model.put("castingId", castingId);
        model.put("playerId", playerId);

        model.put("ctx", dramaContextService.getContextPath());

        return new ModelAndView("/WEB-INF/jsp/index.jsp", model);
    }

    @RequestMapping("/local")
    public ModelAndView localMain(@RequestParam("pavilionId") String pavilionId, @RequestParam("castingId") String castingId, @RequestParam("playerId") String playerId) {
        //
        Map<String, String> model = new HashMap<>();
        model.put("pavilionId", pavilionId);
        model.put("castingId", castingId);
        model.put("playerId", playerId);

        model.put("ctx", "");

        return new ModelAndView("/WEB-INF/jsp/index.jsp", model);
    }

}
