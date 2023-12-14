package SpringJDBC_Notes.TryJDBC2.Repositories;

import SpringJDBC_Notes.TryJDBC2.Models.Note;
import org.springframework.data.repository.CrudRepository;

public interface NoteRepository extends CrudRepository<Note, Integer> {
}
