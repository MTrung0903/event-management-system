package hcmute.fit.event_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "task")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private int taskId;
    @Column(name = "task_name")
    private String taskName;
    @Column(name = "task_desc")
    private String taskDesc;
    @Column(name = "task_deadline")
    private String taskDl;
    @Column(name = "task_status")
    private String taskStatus;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
