package com.example.departmentservice.service;

import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.entity.Department;
import com.example.departmentservice.repository.DepartmentRepo;
import com.example.departmentservice.utility.PaginationData;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepo departmentRepo;
    public final WebClient webClient;

    public DepartmentDto saveDepartment(DepartmentDto department) {
        Department dep = Department.builder()
                .departmentCode(department.getDepartmentCode())
                .departmentName(department.getDepartmentName())
                .departmentDescription(department.getDepartmentDescription())
                .build();

        Department dep1 = departmentRepo.save(dep);
        return Department.toData(dep1);
    }

    public PaginationData getDepartments(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Department> departments = departmentRepo.findAll(pageable);
        List<Department> listOfDepartments = departments.getContent();
        List<DepartmentDto> departmentDtoList = listOfDepartments.stream().map(Department::toData).collect(Collectors.toList());

        PaginationData content = new PaginationData();
        content.setContent(departmentDtoList);
        content.setPageNo(departments.getNumber() + 1);
        content.setPageSize(departments.getSize());
        content.setTotalElements(departments.getTotalElements());
        return content;

    }
    public DepartmentDto getByCode(String code) {
        Department department = departmentRepo.findByDepartmentCode(code);
        if (department == null) {
            throw new RuntimeException("Couldn't find department");
        } else {
            return Department.toData(department);
        }
    }
    public Department getDepById(Long id) {
        Optional<Department> department = departmentRepo.findById(id);
        if (department.isPresent()) {
            return department.get();
        }
        throw new RuntimeException("No department found for id " + id);
    }
    public void deleteDepartment(Long id) {
        Department department = getDepById(id);
        if (isDepartmentUsedByEmployee(department)) {
            throw new IllegalStateException("Department is associated with employees and cannot be deleted");
        }else {
            System.out.println("Deleted");
            departmentRepo.deleteById(id);
        }
    }
    private boolean isDepartmentUsedByEmployee(Department department) {
        // Use WebClient to check if any employees are associated with the department
        Mono<Boolean> result = webClient.get()
                .uri("http://localhost:8081/api/employee/code/" + department.getDepartmentCode())
                .retrieve()
                .bodyToMono(Boolean.class);
        return Boolean.TRUE.equals(result.block());
    }
}
