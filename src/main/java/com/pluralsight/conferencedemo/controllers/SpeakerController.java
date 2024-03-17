package com.pluralsight.conferencedemo.controllers;

import com.pluralsight.conferencedemo.models.Speaker;
import com.pluralsight.conferencedemo.repositories.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakerController {
    @Autowired
    private SpeakerRepository speakerRepository;

    //Metoda GET ce returneaza toti lectorii
    @GetMapping
    public List<Speaker> list() {
        return speakerRepository.findAll();
    }

    //Metoda GET ce returneaza un singur lector
    @GetMapping
    @RequestMapping("{id}")
    public Speaker get(@PathVariable Long id ){
        return speakerRepository.getReferenceById(id);
    }

    //Metoda POST ce creeaza un lector
    @PostMapping
    public Speaker create(@RequestBody final Speaker speaker) {
        return speakerRepository.saveAndFlush(speaker);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        speakerRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Speaker update(@PathVariable long id, @RequestBody Speaker speaker) {
        Speaker existentSpeaker = speakerRepository.getOne(id);
        BeanUtils.copyProperties(speaker, existentSpeaker, "speaker_id");
        return speakerRepository.saveAndFlush(existentSpeaker);
    }

}
