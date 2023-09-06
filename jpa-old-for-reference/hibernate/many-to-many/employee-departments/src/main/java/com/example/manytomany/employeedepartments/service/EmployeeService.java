package com.example.manytomany.employeedepartments.service;

import com.example.manytomany.employeedepartments.entity.Employee;
import com.example.manytomany.employeedepartments.model.EmployeeData;
import com.example.manytomany.employeedepartments.model.EmployeeInfo;
import com.example.manytomany.employeedepartments.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void createEmployee(EmployeeInfo employeeInfo) {
        Employee employee = new Employee();
        employee.setId(employeeInfo.getId());
        employee.setFirstName(employeeInfo.getFirstName());
        employee.setLastName(employeeInfo.getLastName());

        employeeRepository.save(employee);
    }

    public EmployeeData getEmployee(Long employeeId) {
        Optional<Employee> thatEmployee = employeeRepository.findById(employeeId);

        if (thatEmployee.isPresent()) {
            EmployeeData employeeData = new EmployeeData();
            Employee foundEmployee = thatEmployee.get();
            employeeData.setId(foundEmployee.getId());
            employeeData.setFirstName(foundEmployee.getFirstName());
            employeeData.setLastName(foundEmployee.getLastName());
            employeeData.setDepartments(foundEmployee.getDepartments());

            return employeeData;
        }

        else return null; // try writing a custom exception
    }

    public void updateEmployee(Long employeeId, EmployeeData employeeData) {
        Optional<Employee> thatEmployee = employeeRepository.findById(employeeId);
        if (thatEmployee.isPresent()) {
            Employee employee = new Employee();
            employee.setId(thatEmployee.get().getId());
            employeeData.setFirstName(thatEmployee.get().getFirstName());
            employeeData.setLastName(thatEmployee.get().getLastName());
            employeeData.setDepartments(thatEmployee.get().getDepartments());

        }
    }

    public void deleteEmployee(Long employeeId) {
        Optional<Employee> thatEmployee = employeeRepository.findById(employeeId);

        if (thatEmployee.isPresent()) employeeRepository.delete(thatEmployee.get());
    }
}
