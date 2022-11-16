package com.adamovich.it.controller;

import com.adamovich.it.dao.PersonDAO;
import com.adamovich.it.models.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/contacts")
public class PersonController {

    private final PersonDAO personDAO;

    public PersonController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model){
     List<Person> personList = new ArrayList<>(personDAO.index());
        Collections.sort(personList, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getFirstName().compareTo(o2.getFirstName());
            }
        });
        model.addAttribute("listPerson", personList);
        return "contacts/index";
    }

    @GetMapping("/new")
    public String create(@ModelAttribute("person") Person person){
        return "contacts/new";
    }

    @PostMapping
    public String save(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        System.out.println("FLAG 1");
        if(bindingResult.hasErrors()){
            System.out.println("FLAG 3");
            return "contacts/new";
        }
        System.out.println("FLAG 2");
        person.setId(personDAO.getIdCount());
        personDAO.save(person);
        return "redirect:/contacts";
    }

    @GetMapping("{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        return "contacts/info";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id){
        personDAO.delete(id);
        return "redirect:/contacts";
    }

    @GetMapping("{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personDAO.show(id));
        return "contacts/edit";
    }

    @PatchMapping("{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult, @PathVariable("id") int id){
         if(bindingResult.hasErrors()){
             return "contacts/edit";
         }
        personDAO.update(person, id);
        return "redirect:/contacts";
    }
}



