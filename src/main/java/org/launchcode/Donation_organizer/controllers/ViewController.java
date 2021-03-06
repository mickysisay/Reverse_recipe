package org.launchcode.Donation_organizer.controllers;

import org.launchcode.Donation_organizer.models.Recipe;
import org.launchcode.Donation_organizer.models.User;
import org.launchcode.Donation_organizer.models.data.RecipeRepository;
import org.launchcode.Donation_organizer.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RequestMapping("/view")
@Controller
public class ViewController {
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    UserRepository userRepository;
    @GetMapping("/recipe/{recipeid}")
    public String viewRecipe(@PathVariable int recipeid, Model model, HttpServletRequest request){
        //finding user
        Integer id = (Integer) request.getSession().getAttribute("reverseRecipeUser");
        Optional option =   userRepository.findById(id);
        User theUser = (User) option.get();
        model.addAttribute("user",theUser);
        //
        model.addAttribute("subUsers", userRepository.findAllById(theUser.getSubscription()));

        Recipe recipe = new Recipe();
        try {
            recipe = recipeRepository.findById(recipeid).get();
        }catch(Exception e){
            return "redirect:/store";
        }
       model.addAttribute("recipe",recipe);

       return "view/showRecipe";
    }
    @GetMapping("/profile/{userid}")
    public String viewProfile(@PathVariable int userid,Model model,HttpServletRequest request){
        //finding user
        Integer id = (Integer) request.getSession().getAttribute("reverseRecipeUser");
        Optional option =   userRepository.findById(id);
        User theUser = (User) option.get();
        model.addAttribute("user",theUser);
        //
        model.addAttribute("subUsers", userRepository.findAllById(theUser.getSubscription()));
        User user = new User();
        try {
             user = userRepository.findById(userid).get();
        }catch(Exception e){
        return "redirect:/store";
    }
        model.addAttribute("users",user);

        return "view/showProfile";
    }

}
