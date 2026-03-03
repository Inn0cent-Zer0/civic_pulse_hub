package com.civic.smartcity.controller;

import com.civic.smartcity.dto.AdminUpdateRequest;
import com.civic.smartcity.model.Grievance;
import com.civic.smartcity.model.User;
import com.civic.smartcity.repository.GrievanceRepository;
import com.civic.smartcity.repository.UserRepository;
import com.civic.smartcity.security.JwtUtil;
import com.civic.smartcity.service.GrievanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired private UserRepository userRepository;
    @Autowired private GrievanceRepository grievanceRepository;
    @Autowired private GrievanceService grievanceService;
    @Autowired private JwtUtil jwtUtil;

    private String extractToken(String h) { return h.substring(7); }
    private void requireAdmin(String h) {
        if (!jwtUtil.getRoleFromToken(extractToken(h)).equals("ADMIN"))
            throw new SecurityException("Admin only.");
    }

    @GetMapping("/officers")
    public ResponseEntity<?> getOfficers(@RequestHeader("Authorization") String auth) {
        try {
            requireAdmin(auth);
            List<User> officers = userRepository.findByRole("OFFICER");
            List<Map<String, Object>> result = officers.stream().map(o -> {
                List<Grievance> assigned = grievanceRepository
                    .findByAssignedOfficerOrderBySubmittedAtDesc(o.getUsername());
                long active   = assigned.stream().filter(g -> "IN_PROGRESS".equals(g.getStatus())).count();
                long pending  = assigned.stream().filter(g -> "PENDING".equals(g.getStatus())).count();
                long resolved = assigned.stream().filter(g -> "RESOLVED".equals(g.getStatus())).count();
                List<String> categories = assigned.stream()
                    .map(Grievance::getCategory).distinct().collect(Collectors.toList());
                Map<String, Object> m = new LinkedHashMap<>();
                m.put("username",   o.getUsername());
                m.put("email",      o.getEmail() != null ? o.getEmail() : "");
                m.put("phone",      o.getPhone() != null ? o.getPhone() : "");
                m.put("total",      assigned.size());
                m.put("active",     active);
                m.put("pending",    pending);
                m.put("resolved",   resolved);
                m.put("categories", categories);
                return m;
            }).collect(Collectors.toList());
            return ResponseEntity.ok(result);
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/officers/{username}/grievances")
    public ResponseEntity<?> getOfficerGrievances(@PathVariable String username,
                                                   @RequestHeader("Authorization") String auth) {
        try {
            requireAdmin(auth);
            return ResponseEntity.ok(
                grievanceRepository.findByAssignedOfficerOrderBySubmittedAtDesc(username));
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/grievances/{id}/assign")
    public ResponseEntity<?> assignGrievance(@PathVariable Long id,
                                              @RequestBody Map<String, String> body,
                                              @RequestHeader("Authorization") String auth) {
        try {
            requireAdmin(auth);
            AdminUpdateRequest req = new AdminUpdateRequest();
            req.setAssignedOfficer(body.get("officerUsername"));
            req.setStatus("IN_PROGRESS");
            return ResponseEntity.ok(grievanceService.adminUpdate(id, req, extractToken(auth)));
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", e.getMessage()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
        }
    }
}