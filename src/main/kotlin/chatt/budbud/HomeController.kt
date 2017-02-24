package chatt.budbud

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {

    @GetMapping("/", "/home")
    fun home() = modelView("home") {



    }


}