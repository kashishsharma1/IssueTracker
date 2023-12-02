package com.prj.issuetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prj.issuetracker.model.SupportMember;

@Repository
public interface SupportRepository extends JpaRepository<SupportMember, Integer> {
	@Query(value = "select empid from supportinfo where speciality = :type order by no_of_tickets limit 1;", nativeQuery = true)
	Integer findSupIdByType(String type);
	@Query(value="select noOfTickets from supportinfo where empid=:empid", nativeQuery = true)
	Integer findNoOfTickets(Integer empid);
	@Query(value="select * from supportinfo where empid=:empid", nativeQuery = true)
	SupportMember findSupportMemberById(Integer empid);
}