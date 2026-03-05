package com.civic.smartcity.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.civic.smartcity.model.Grievance;
import com.civic.smartcity.model.User;
import com.civic.smartcity.repository.GrievanceRepository;
import com.civic.smartcity.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired private UserRepository userRepo;
    @Autowired private GrievanceRepository grievanceRepo;

    @GetMapping("/officers")
    public ResponseEntity<?> officers() {
        List<User> officers = userRepo.findByRole("OFFICER");
        List<Map<String,Object>> result = officers.stream().map(o -> {
            List<Grievance> assigned = grievanceRepo
                .findByAssignedOfficerOrderBySubmittedAtDesc(o.getUsername());
            Map<String,Object> m = new LinkedHashMap<>();
            m.put("username",     o.getUsername());
            m.put("email",        o.getEmail() != null ? o.getEmail() : "");
            m.put("totalAssigned", assigned.size());
            m.put("pending",      assigned.stream().filter(g -> "PENDING".equals(g.getStatus())).count());
            m.put("inProgress",   assigned.stream().filter(g -> "IN_PROGRESS".equals(g.getStatus())).count());
            m.put("resolved",     assigned.stream().filter(g -> "RESOLVED".equals(g.getStatus())).count());
            m.put("categories",   assigned.stream().map(Grievance::getCategory).distinct().collect(Collectors.toList()));
            return m;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/officers/{username}/grievances")
    public ResponseEntity<?> officerGrievances(@PathVariable String username) {
        return ResponseEntity.ok(
            grievanceRepo.findByAssignedOfficerOrderBySubmittedAtDesc(username)
                .stream().map(g -> {
                    Map<String,Object> m = new LinkedHashMap<>();
                    m.put("id",       g.getId());
                    m.put("title",    g.getTitle());
                    m.put("category", g.getCategory());
                    m.put("status",   g.getStatus());
                    m.put("priority", g.getPriority() != null ? g.getPriority() : "MEDIUM");
                    m.put("citizen",  g.getCitizenUsername());
                    m.put("deadline", g.getDeadline() != null ? g.getDeadline().toString() : "");
                    return m;
                }).collect(Collectors.toList())
        );
    }
}