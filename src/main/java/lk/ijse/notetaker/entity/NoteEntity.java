package lk.ijse.notetaker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "notes")
@Entity
public class NoteEntity {
    @Id
    private String id;
    private String noteTitle;
    private String noteDesc;
    private String priorityLevel;
    private String createDate;
}
