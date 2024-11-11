package hcmute.fit.event_management.repository;

import hcmute.fit.event_management.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("select e from  Employee e where e.manager.manID = :id")
    List<Employee> findByManagerId(@Param("id") Integer id);

    @Query("select e from Employee e where e.team.teamId = :id")
    List<Employee> findByTeamId(@Param("id") Integer id);


}
