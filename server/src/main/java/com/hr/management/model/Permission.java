package com.hr.management.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "permissions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name; // e.g., "USER_VIEW", "USER_CREATE", "PAYROLL_APPROVE"

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String module; // e.g., "HRM", "PAYROLL", "SYSTEM"
}
