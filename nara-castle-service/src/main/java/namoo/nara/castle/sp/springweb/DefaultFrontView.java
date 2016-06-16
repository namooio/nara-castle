package namoo.nara.castle.sp.springweb;

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
    @RequestMapping("/")
    public ModelAndView main(@RequestParam("townId") String townId, @RequestParam("townerId") String townerId) {
        //
        System.out.println("townId: " + townId + ", townerId: " + townerId);

        Map<String, String> model = new HashMap<>();
        model.put("castingId", townId);
        model.put("playerId", townerId);

        return new ModelAndView("/WEB-INF/jsp/index.jsp", model);
//        return "/resources/index.html";
    }

}
