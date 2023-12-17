package SpringJDBC_Notes.TryJDBC2.Controllers;

import SpringJDBC_Notes.TryJDBC2.Models.Note;
import SpringJDBC_Notes.TryJDBC2.Repositories.NoteRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping(path = "/note")
public class NoteController {
    @Autowired
    private NoteRepository noteRepo;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewNote(@RequestParam String avtor, @RequestParam String text) {
        noteRepo.save(new Note(avtor, text, LocalDateTime.now()));
        return "Saved";
    }

    @GetMapping(path = "/getAll")
    public @ResponseBody Iterable<Note> getAllNotes() {
        return noteRepo.findAll();
    }

    @DeleteMapping(path = "/delleteById")
    public @ResponseBody String deleteById(@RequestParam Integer id){
        noteRepo.deleteById(id);
        return "Delleted";
    }

    @PutMapping(path = "/update")
    public @ResponseBody Note updateNote(Integer id, String newText){
        Note note = noteRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note not exist with id = " + id));
        note.setText(newText);
        note.setDateTime(LocalDateTime.now());
        noteRepo.save(note);
        return note;
    }

    @PatchMapping(path = "/partialUpdate")
    public @ResponseBody Note partialUpdate(Integer id, String newText){
        Note note = noteRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note not exist with id = " + id));
        note.setText(newText);
        noteRepo.save(note);
        return note;
    }
}
