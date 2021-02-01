package Nik.Controllers;

import Nik.Animals.Animal;
import Nik.Animals.AnimalsList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Zoo")
public class MenuController {
    @Autowired
    AnimalsList animalsList;
    @GetMapping("/Zoo")
    public String Animals(Model model)
    {
        model.addAttribute("animals", animalsList.getAnimals());
        return "Zoo/Animals";
    }
    @GetMapping("/Buy")
    public String Buy(Model model)
    {
        model.addAttribute("anim", new Animal());
        return "Zoo/Buy";
    }
    @GetMapping("/Ready")
    public String New(@RequestParam(name="name", required = false) String name,
                      @RequestParam(name="cost", required = false) int cost)
    {
        animalsList.save(new Animal(cost, name));
        return "redirect: Zoo";
    }
    @GetMapping("/Sell")
    public String Sell(Model model)
    {
        model.addAttribute("anim", new Animal());
        return "Zoo/Sell";
    }
    @GetMapping("/Delete")
    public String Delete(@RequestParam(name="name", required = false) String name)
    {
        animalsList.delete(name);
        return "redirect: Zoo";
    }
}
