package hcmute.fit.event_management.service;

import hcmute.fit.event_management.entity.Employee;
import hcmute.fit.event_management.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements IEmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> findAllById(Iterable<Integer> integers) {
        return employeeRepository.findAllById(integers);
    }

    @Override
    public <S extends Employee> List<S> saveAll(Iterable<S> entities) {
        return employeeRepository.saveAll(entities);
    }

    @Override
    public long count() {
        return employeeRepository.count();
    }

    @Override
    public void delete(Employee entity) {
        employeeRepository.delete(entity);
    }

    @Override
    public void deleteAll() {
        employeeRepository.deleteAll();
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        employeeRepository.deleteAllById(integers);
    }

    @Override
    public void deleteById(Integer integer) {
        employeeRepository.deleteById(integer);
    }

    @Override
    public <S extends Employee> S save(S entity) {
        return employeeRepository.save(entity);
    }

    @Override
    public List<Employee> findAll(Sort sort) {
        return employeeRepository.findAll(sort);
    }

    @Override
    public <S extends Employee> Optional<S> findOne(Example<S> example) {
        return employeeRepository.findOne(example);
    }
}
