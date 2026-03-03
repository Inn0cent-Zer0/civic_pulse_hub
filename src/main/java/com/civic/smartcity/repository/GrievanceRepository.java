package com.civic.smartcity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.civic.smartcity.model.Grievance;

public interface GrievanceRepository extends JpaRepository<Grievance, Long> {
    List<Grievance> findByCitizenUsernameOrderBySubmittedAtDesc(String citizenUsername);
    List<Grievance> findAllByOrderBySubmittedAtDesc();
    List<Grievance> findByStatusOrderBySubmittedAtDesc(String status);
    long countByStatus(String status);
    List<Grievance> findByAssignedOfficerOrderBySubmittedAtDesc(String officer);
}
