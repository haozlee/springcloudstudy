package me.leehao.multidbsource.controller;

import me.leehao.multidbsource.service.PersonService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class PersonController {
    @Resource
    private PersonService personService;

    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public String errMsg(@RequestParam(value="db", required=true)String db,
                         @RequestParam(value="id", required=true)Integer id) {
        return personService.getPerson(db, id);
    }
}
