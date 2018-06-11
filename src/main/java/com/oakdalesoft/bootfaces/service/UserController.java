package com.oakdalesoft.bootfaces.service;

import com.oakdalesoft.bootfaces.domain.User;
import com.oakdalesoft.bootfaces.persistence.UserRepository;
import com.oakdalesoft.bootfaces.util.CustomErrorType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository; //kasnije
import com.oakdalesoft.bootfaces.view.UserModel;  //jos kasn


@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    // svi kontakti
    @RequestMapping(value = "/service/user/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userRepository.findAll();  //findAllUsers()
        if (users.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
 
    // pojedinacni kontakt
    @RequestMapping(value = "/service/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("id") long id) {
        //logger.info("Fetching User with id {}", id);
        User user = userRepository.findOne(id);  //findById(id)
        if (user == null) {
            //logger.error("User with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("User with id " + id 
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
 
    // napravi novi kontakt
    @RequestMapping(value = "/service/user/", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        //logger.info("Creating User : {}", user);
 
    	
        if (user == null) {
            //logger.error("Unable to create. A User with name {} already exist", user.getName());
            return new ResponseEntity(new CustomErrorType("Kontakt " + 
            user.getIme() + " vec postoji."),HttpStatus.CONFLICT);
        }
        userRepository.save(user);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
 
    // izmjeni kontakt
    @RequestMapping(value = "/service/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        //logger.info("Updating User with id {}", id);
 
        User currentUser = userRepository.findOne(id);
 
        if (currentUser == null) {
            //logger.error("Unable to update. User with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        currentUser.setIme(user.getIme());
        currentUser.setMobilni(user.getMobilni());
        currentUser.setPoslovni(user.getPoslovni());
        currentUser.setMobilni(user.getMobilni());
        currentUser.setPoslovni(user.getPoslovni());
        userRepository.save(currentUser);          //user rep dodan mozda userRepository.save(currentUser);     org userRepository.doUpdateUser(currentUser); 
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }
 
    
    
    // pobrisi kontakt
    @RequestMapping(value = "/service/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
        //logger.info("Fetching & Deleting User with id {}", id);
 
        User user = userRepository.findOne(id);  //findById(id);
        System.out.println("FINDING to delete");
        if (user == null) {
            //logger.error("Unable to delete. User with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Kontakt nije pobrisan, " + id + " nije pronadjen."),
                    HttpStatus.NOT_FOUND);
        }
        userRepository.delete(user); // deleteUserById
        System.out.println("deleted");
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
 
 
    // pobrisi sve kontakte
 
    
    @RequestMapping(value = "/service/user/", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteAllUsers() {
        //logger.info("Deleting All Users");
 
        userRepository.deleteAll(); //deleteAllUsers();
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
  
    
    
    }
	
};    


    
    
    
    
    
    
    
    /* 
    //------------------- Update a User --------------------------------------------------------
     
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        System.out.println("Updating User " + id);
         
        User currentUser = userRepository.findById(id);
         
        if (currentUser==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
 
        currentUser.setIme(user.getIme());
        currentUser.setMobilni(user.getMobilni());
        currentUser.setPoslovni(user.getPoslovni());
        currentUser.setMobilni(user.getMobilni());
        currentUser.setPoslovni(user.getPoslovni());

        
         
        userRepository.updateUser(currentUser);
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }

	
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/service/users")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return this.userRepository.findAll();
    }
    
    @RequestMapping(value="/service/user/{id}", method=RequestMethod.GET)
    public @ResponseBody 
    User getUserById(@PathVariable Long id) {
    	//if (method="RequestMethod.GET")
    	System.out.println("FINDING");
        return this.userRepository.findOne(id);
        
    }
    
    
    //wohooo moj prvi api
    @RequestMapping(value="/service/user/{id}", method=RequestMethod.DELETE)
    public @ResponseBody 
    User deleteUserById(@PathVariable Long id) {
    	this.userRepository.delete(id);
    	System.out.println("DELETING");
        //return "sdfasdfadsf";
		return this.user(HttpStatus.NO_CONTENT);
        
    }
    
    
    /*
    //wohooo moj prvi api
    @RequestMapping(value="/service/user/{id}", method=RequestMethod.DELETE)
    public @ResponseBody 
    User deleteUserById(@PathVariable Long id) {
    	//this.userRepository.delete(id);
    	//System.out.println("DELETING");
        return this.deleteUserById(id);
		//return "System.out.println("deleting")";
        
    }

    
    
    
    
    //do tud radi
    

    //------------------- Delete a User --------------------------------------------------------
    
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting User with id " + id);
 
        User user = userService.findById(id);
        if (user == null) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
 
        userService.deleteUserById(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
    */
 
    /*
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        User user = userService.findById(id);
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
    
    */
    /*-------------------Retrieve All Users--------------------------------------------------------
    
    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userService.findAllUsers();
        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
    */

