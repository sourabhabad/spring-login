package com.gpch.login.controller;


import com.gpch.login.exception.ResourceNotFoundException;
import com.gpch.login.model.Note;
import com.gpch.login.model.User;
import com.gpch.login.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    NoteRepository noteRepository;

    @GetMapping("/notes")
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

//    @PostMapping("/notes")
//    public Note createNote(@Valid @RequestBody Note note) {
//        return noteRepository.save(note);
//    }

    @GetMapping("/notes/{id}")
    public Note getNoteById(@PathVariable(value = "id") Long noteId) {
        return noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
    }


    @PutMapping("/notes/{id}")
    public Note updateNote(@PathVariable(value = "id") Long noteId,
                           @Valid @RequestBody Note noteDetails) {

        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());

        Note updatedNote = noteRepository.save(note);
        return updatedNote;
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        noteRepository.delete(note);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/notes", method = RequestMethod.POST)
    public ModelAndView createNewNotes(@Valid Note note, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        noteRepository.save(note);
        modelAndView.addObject("note", new Note());
        modelAndView.setViewName("notes");
        return modelAndView;
    }
}