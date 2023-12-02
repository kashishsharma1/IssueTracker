package com.prj.issuetracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prj.issuetracker.model.Tickets;

@Repository
public interface TicketsRepository extends JpaRepository<Tickets, Integer>{
	@Query(value = "SELECT * FROM tickets WHERE emp_id = :employeeId and status = 'pending' ORDER BY creation_time", nativeQuery = true)
	List<Tickets> findByEmpidPending(Integer employeeId);
	@Query(value = "SELECT * FROM tickets WHERE emp_id = :employeeId and status = 'resolved' ORDER BY creation_time", nativeQuery = true)
	List<Tickets> findByEmpidResolved(Integer employeeId);
	
	@Query(value = "SELECT * FROM tickets WHERE sup_id = :employeeId and status = 'resolved' ORDER BY creation_time", nativeQuery = true)
	List<Tickets> findTicketsBySupidResolved(Integer employeeId);
	@Query(value = "SELECT * FROM tickets WHERE sup_id = :employeeId and status = 'pending' ORDER BY creation_time", nativeQuery = true)
	List<Tickets> findTicketsBySupidPending(Integer employeeId);
	@Query(value = "select count(*) from tickets where sup_id=:employeeId and status='pending'", nativeQuery = true)
	Integer countByEmpIdAndStatusPending(Integer employeeId);
	@Query(value = "select count(*) from tickets where sup_id=:employeeId and status='resolved'", nativeQuery = true)
	Integer countByEmpIdAndStatusResolved(Integer employeeId);
	@Query(value = "select count(*) from tickets where sup_id=:employeeId ", nativeQuery = true)
	Integer countByEmpId(Integer employeeId);
}
