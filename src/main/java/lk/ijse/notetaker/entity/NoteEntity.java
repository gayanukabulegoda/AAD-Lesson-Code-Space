package lk.ijse.notetaker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "notes")
@Entity
public class NoteEntity implements SuperEntity {
    @Id
    private String id;
    private String noteTitle;
    private String noteDesc;
    private String priorityLevel;
    private String createDate;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private UserEntity user;
}
