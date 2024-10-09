package lk.ijse.SpringBootNoteTaker.repository;

import lk.ijse.SpringBootNoteTaker.entity.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<NoteEntity, String> {
}
