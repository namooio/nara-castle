package namoo.nara.castle.sp.spring.web;

import namoo.nara.stage.support.context.DramaContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class PlayFrontView {
    //
    @RequestMapping(value = "castle-play", method = RequestMethod.GET)
    public ModelAndView play(@RequestParam("pavilionId") String pavilionId, @RequestParam("castingId") String castingId, @RequestParam("playerId") String playerId) {
        //
        Map<String, String> model = new HashMap<>();
        model.put("ctx", DramaContext.getInstance().getContextPath(castingId));

        model.put("pavilionId", pavilionId);
        model.put("castingId", castingId);
        model.put("playerId", playerId);

        return new ModelAndView("index", model);
    }

    @RequestMapping(value = "castle-local-play", method = RequestMethod.GET)
    public ModelAndView localPlay(@RequestParam("pavilionId") String pavilionId, @RequestParam("castingId") String castingId, @RequestParam("playerId") String playerId) {
        //
        Map<String, String> model = new HashMap<>();
        model.put("ctx", "");

        model.put("pavilionId", pavilionId);
        model.put("castingId", castingId);
        model.put("playerId", playerId);

        return new ModelAndView("index", model);
    }

}
