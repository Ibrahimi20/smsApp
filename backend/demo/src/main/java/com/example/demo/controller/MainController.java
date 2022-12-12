package com.example.demo.controller;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.SMS;
import com.example.demo.repository.SmsRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MainController {
	
	@Autowired
	SmsRepository smsrepository;
	
	@GetMapping("/")
	public String hello() {
	    return "hello from home";
	  }
	
	@PostMapping("/savedata")
	public ResponseEntity<?> save(@RequestBody ArrayList<SMS> smss) {
		
		//numObjetAppe, String contactInfo, String nom, String prenom, String etat, Date dateEnvoie
		
		for(SMS sms : smss) {
			//SMS newitem = new SMS(sms.getNumObjetAppe(),sms.getContactInfo(),sms.getNom(),sms.getPrenom(),sms.getEtat(),sms.getDateEnvoie());
			
			smsrepository.save(sms);
		}
		
		
		return new ResponseEntity<>(smss,HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/getdate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> datefilter(@RequestParam Date startDate) {
		List<SMS> list = smsrepository.findByDateEnvoie(startDate);
	    return new ResponseEntity<>(list,HttpStatus.OK);
	  }
	
	@GetMapping(value = "/getall", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getall() {
		List<SMS> list = smsrepository.findAll();
	    return new ResponseEntity<>(list,HttpStatus.OK);
	  }

}
