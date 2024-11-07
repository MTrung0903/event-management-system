package hcmute.fit.event_management.service;

import hcmute.fit.event_management.entity.Employee;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {
    List<Employee> findAll();

    List<Employee> findAllById(Iterable<Integer> integers);

    <S extends Employee> List<S> saveAll(Iterable<S> entities);

    long count();

    void delete(Employee entity);

    void deleteAll();

    void deleteAllById(Iterable<? extends Integer> integers);

    void deleteById(Integer integer);

    <S extends Employee> S save(S entity);

    List<Employee> findAll(Sort sort);

    <S extends Employee> Optional<S> findOne(Example<S> example);
}
