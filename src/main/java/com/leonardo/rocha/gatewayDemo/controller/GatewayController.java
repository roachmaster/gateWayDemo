package com.leonardo.rocha.gatewayDemo.controller;

import com.leonardo.rocha.gatewayDemo.data.User;
import com.leonardo.rocha.gatewayDemo.data.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.StreamSupport;

@CrossOrigin
@RestController
@RequestMapping("/")
public class GatewayController {
	private static final Logger logger = LoggerFactory.getLogger(GatewayController.class);

	private final UserRepository userRepository;

	@Autowired
	public GatewayController(UserRepository userRepository){
		this.userRepository = userRepository;
	}

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public ResponseEntity<String> getTest() {
		String response = "Hello, World!";
    	logger.info("Response: {}", response);
		return new ResponseEntity<>(response, HttpStatus.OK);
    }

	@RequestMapping(value = "users", method = RequestMethod.GET)
	public ResponseEntity<String> getUsers() {
		logger.info("Getting Users from DB");
		Iterable<User> users = this.userRepository.findAll();
		StringBuilder stringBuilder = new StringBuilder();
		for (User user: users){
			stringBuilder.append(user.toString()).append("\n");
		}
		return new ResponseEntity<>(stringBuilder.toString(), HttpStatus.OK);
	}

	@RequestMapping(value = "user/add/name/{name}/age/{age}", method = RequestMethod.POST)
	public ResponseEntity<String> addUser(@PathVariable String name, @PathVariable int age) {
		User newUser = new User(name,age);
		logger.info("Adding User: {}", newUser);
		User savedUser = this.userRepository.save(newUser);
		return new ResponseEntity<>(savedUser.toString(), HttpStatus.OK);
	}

	@RequestMapping(value = "users/clear", method = RequestMethod.GET)
	public ResponseEntity<String> deleteUsers() {
		this.userRepository.deleteAll();
		Iterable<User> users = this.userRepository.findAll();
		long usersSize = getUsersSize(users);
		if(usersSize != 0){
			logger.error("Users should have been deleted but size is : {}", usersSize);
			return new ResponseEntity<>(String.format("Users should have been deleted but size is : %s",usersSize), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(String.format("Users have been deleted, size is : %s",usersSize), HttpStatus.OK);
	}

	private long getUsersSize(Iterable<User> users){
		return StreamSupport.stream(users.spliterator(), false).count();
	}

}
