package hcmute.fit.event_management.repository;

import hcmute.fit.event_management.entity.SubTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubtaskRepository extends JpaRepository<SubTask, Integer> {
    @Query("select sb from SubTask sb where sb.task.taskId = :taskId")
    List<SubTask> findByTaskId(int taskId);

    @Query("select sb from SubTask sb where sb.task.team.teamId = :teamId")
    List<SubTask> findByTeamId(int teamId);

    @Query("select sb from SubTask sb where sb.task.event.eventID = :eventId")
    List<SubTask> findByEventId(int eventId);

    @Query("select sb from SubTask sb where sb.employee.id = :employeeId ")
    List<SubTask> findByEmployeeId(int employeeId);

    @Query("select sb from SubTask sb where sb.employee.id = :employeeId and sb.task.taskId = :taskId")
    List<SubTask> getListSubtaskById(@Param("employeeId") int employeeId, @Param("taskId")int taskId);
    @Query("SELECT st FROM SubTask st " +
            "JOIN st.task t " +
            "JOIN t.team tm " +
            "WHERE tm.teamId = :teamId AND st.employee.id = :employeeId")
    List<SubTask> findSubTasksByTeamAndEmployee(@Param("teamId") int teamId,
                                                @Param("employeeId") int employeeId);

}
