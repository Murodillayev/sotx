package uz.pdp.sotx;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final TestDao dao;

    @GetMapping("/")
    public String homePage(Model model) {
        List<Test> tests = dao.getAll();
        model.addAttribute("tests", tests);
        return "home";
    }
}
