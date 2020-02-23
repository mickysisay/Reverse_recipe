package org.launchcode.Donation_organizer.controllers;

import org.launchcode.Donation_organizer.models.Ingredient;
import org.launchcode.Donation_organizer.models.IngredientList;
import org.launchcode.Donation_organizer.models.data.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.expression.Lists;

import java.util.ArrayList;
import java.util.List;
@RequestMapping("look")
@RestController
public class ItemController {
    @Autowired
    IngredientRepository ingredientRepository;
    @GetMapping("searchItem")
    public List<Ingredient> addItem(@RequestParam String searchWord){
      List<Ingredient> allFound= ingredientRepository.findAll();
     List<Ingredient> found = new ArrayList<>();
        if(!searchWord.isEmpty()) {
     for(int i=0;i<allFound.size();i++){
       if(allFound.get(i).toString().toLowerCase().contains(searchWord.toLowerCase())){
           found.add(allFound.get(i));
       }
       if(found.size()>10){
           break;
       }
     }}
     return found;
    }
    @PostMapping("addItem")
    public String addIngRest(@RequestParam String ingredientName){
        if(ingredientName.trim().length()>3){
           Ingredient ingredient = new Ingredient();
           ingredient.setName(ingredientName);
           ingredientRepository.save(ingredient);
           return "ingredient added succesfully";
        }else{
            return "there was a problem adding ingredient";
        }
    }


}
