package simpleshop.shop.controller.view;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import simpleshop.shop.domain.Item;
import simpleshop.shop.domain.User;
import simpleshop.shop.service.ItemService;

import java.util.List;
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class HomeController {
    private final ItemService itemService;
    @GetMapping()
    public String home(Model model) {
        User user = (User)model.getAttribute("user");
        List<Item> items = itemService.getAllItems();
        model.addAttribute("items", items);
        model.addAttribute("user",user);
        return "home";
    }
}
