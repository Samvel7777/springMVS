package am.itspace.springdemo.controller;

import am.itspace.springdemo.repository.BookRepository;
import am.itspace.springdemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;
    private final BookRepository bookRepository;

    @GetMapping("/")
    public String homePage(@AuthenticationPrincipal Principal principal, ModelMap modelMap, @RequestParam(value = "msg", required = false) String msg) {
        String username = null;
        if (principal != null) {
            username = principal.getName();
        }
        modelMap.addAttribute("users", userService.findAll());
        modelMap.addAttribute("books", bookRepository.findAll());
        modelMap.addAttribute("msg", msg);
        return "home";
    }

    @GetMapping("/about")
    public String aboutUsPage() {
        return "about";
    }

}
