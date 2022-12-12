package com.example.demo.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.SMS;

@Repository
public interface SmsRepository extends JpaRepository<SMS, Long> {
	
	List<SMS> findByDateEnvoie(Date startDate );

}
