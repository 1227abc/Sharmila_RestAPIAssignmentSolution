package com.gl.employeeMgmt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gl.employeeMgmt.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
