package com.jitendra.tweety.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import twitter4j.TwitterException;

@RestController
@RequestMapping(path="/api/tweety/")
public class TweeterController {

	
	@GetMapping()
	public ResponseEntity<?> fetchTimeLine(@RequestHeader(name="access") String accessToken) {
		try {
			return new ResponseEntity<List<String>>(TweeterService.getTimeLine(false,accessToken),HttpStatus.OK) ;
		} catch (TwitterException e) {
			return new ResponseEntity<Exception>(e,HttpStatus.INTERNAL_SERVER_ERROR) ;
		}

	}
	
	@GetMapping(path="self")
	public ResponseEntity<?> fetchTimeLine() {
		try {
			return new ResponseEntity<List<String>>(TweeterService.getTimeLine(true,null),HttpStatus.OK) ;
		} catch (TwitterException e) {
			return new ResponseEntity<Exception>(e,HttpStatus.INTERNAL_SERVER_ERROR) ;
		}

	}
	
	

}
