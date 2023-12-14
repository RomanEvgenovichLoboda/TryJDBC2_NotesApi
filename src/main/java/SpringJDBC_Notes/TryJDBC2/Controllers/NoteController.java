package SpringJDBC_Notes.TryJDBC2.Controllers;

import SpringJDBC_Notes.TryJDBC2.Models.Note;
import SpringJDBC_Notes.TryJDBC2.Repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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
}
