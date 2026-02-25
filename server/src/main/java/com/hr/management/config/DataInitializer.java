package com.hr.management.config;

import com.hr.management.model.Permission;
import com.hr.management.model.Role;
import com.hr.management.repository.PermissionRepository;
import com.hr.management.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        // 1. Create permissions
        Permission tenantManage = createPermissionIfNotFound("SYSTEM_TENANT_MANAGE", "Quản lý doanh nghiệp", "SYSTEM");
        Permission planManage = createPermissionIfNotFound("SYSTEM_PLAN_MANAGE", "Quản lý gói dịch vụ", "SYSTEM");

        // Organization Management
        createPermissionIfNotFound("ORG_STRUCTURE_MANAGE", "Quản lý cơ cấu tổ chức", "ORGANIZATION");
        createPermissionIfNotFound("ORG_RBAC_MANAGE", "Quản lý phân quyền nội bộ", "ORGANIZATION");

        // HRM
        createPermissionIfNotFound("HRM_EMPLOYEE_VIEW", "Xem hồ sơ nhân viên", "HRM");
        createPermissionIfNotFound("HRM_EMPLOYEE_MANAGE", "Cập nhật hồ sơ nhân viên", "HRM");
        createPermissionIfNotFound("HRM_CONTRACT_MANAGE", "Quản lý hợp đồng lao động", "HRM");

        // Attendance
        createPermissionIfNotFound("ATTENDANCE_CHECK", "Thực hiện chấm công", "ATTENDANCE");
        createPermissionIfNotFound("ATTENDANCE_APPROVE", "Duyệt đơn từ chấm công", "ATTENDANCE");

        // Payroll
        createPermissionIfNotFound("PAYROLL_MANAGE", "Quản lý bảng lương", "PAYROLL");
        createPermissionIfNotFound("PAYROLL_VIEW", "Xem phiếu lương cá nhân", "PAYROLL");

        // Recruitment
        createPermissionIfNotFound("RECRUIT_MANAGE", "Quản lý tuyển dụng", "RECRUITMENT");

        // 2. Create System Roles (Tenant null)
        Set<Permission> superAdminPerms = new HashSet<>();
        superAdminPerms.add(tenantManage);
        superAdminPerms.add(planManage);
        createRoleIfNotFound("SUPER_ADMIN", "Quản trị viên tối cao hệ thống", superAdminPerms);

        System.out.println("Data initialization completed.");
    }

    private Permission createPermissionIfNotFound(String name, String description, String module) {
        var optPermission = permissionRepository.findByName(name);
        if (optPermission.isEmpty()) {
            Permission permission = Permission.builder()
                    .name(name)
                    .description(description)
                    .module(module)
                    .build();
            return permissionRepository.save(permission);
        }
        return optPermission.get();
    }

    private void createRoleIfNotFound(String name, String description, Set<Permission> permissions) {
        var optRole = roleRepository.findByNameAndTenant(name, null);
        if (optRole.isEmpty()) {
            Role role = Role.builder()
                    .name(name)
                    .description(description)
                    .permissions(permissions)
                    .tenant(null) // Global role
                    .build();
            roleRepository.save(role);
        }
    }
}
