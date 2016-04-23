package namoo.nara.castle.pr.springweb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 18..
 */
@Controller
public class DefaultViewController {
    //
    @RequestMapping("view")
    public String main() {

        return "index.html";
    }

    @RequestMapping("test")
    public String test() {
        return "test";
    }
}
