package com.prj.issuetracker.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.prj.issuetracker.model.EmpInfo;


@Repository
public interface EmpRepository extends JpaRepository<EmpInfo, Integer> {
	Optional<EmpInfo> findByEmpid(Integer empid);
}
