package com.pluralsight.conferencedemo.controllers;

import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionController {
    @Autowired
    private SessionRepository sessionRepository;


    //Metoda ce returneaza toate Sesiunile
    @GetMapping
    public List<Session> list() {
        return sessionRepository.findAll();
    }

    //Metoda ce returneaza o singura sesiune
    @GetMapping
    @RequestMapping("{id}")
    public Session get(@PathVariable Long id) {
        //return sessionRepository.getOne(id);
        return sessionRepository.getReferenceById(id);
    }
    @PostMapping
    public Session create(@RequestBody final Session session) {
        return sessionRepository.saveAndFlush(session);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete (@PathVariable Long id) {
        //Also needs to check for children records before deleting
        sessionRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Session update(@PathVariable Long id, @RequestBody Session session) {
        Session existingSession = sessionRepository.getOne(id);
        //se ignora proprietatea ca sa nu copieze null peste primary_key-ul inregistrarii
        BeanUtils.copyProperties(session, existingSession, "session_id");
        return sessionRepository.saveAndFlush(existingSession);
    }




}
