package com.hr.management.repository;

import com.hr.management.model.Role;
import com.hr.management.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByNameAndTenant(String name, Tenant tenant);

    List<Role> findByTenantIsNull(); // Get system roles

    List<Role> findByTenant(Tenant tenant);
}
