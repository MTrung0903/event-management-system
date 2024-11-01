package hcmute.fit.event_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "subtask")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubTask {
    @Id
    @GeneratedValue
    @Column(name = "subtask_id")
    private int subTaskId;

    @Column(name = "subtask_name")
    private String subTaskName;
    @Column(name = "subtask_desc")
    private String subTaskDesc;
    @Column(name = "subtask_deadline")
    private String subTaskDeadline;
    @Column(name = "subtask_status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
