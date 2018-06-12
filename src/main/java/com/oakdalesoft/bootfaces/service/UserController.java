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

        User user = userRepository.findOne(id);  //findById(id)
        if (user == null) {
 
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
 
     //@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    //public ResponseEntity<User> getUser(@PathVariable("id") long id) {
    //, @RequestBody MediaType APPLICATION_JSON_VALUE
    
   //consumes = MediaType.APPLICATION_JSON_VALUE
    
    
    //novi kontakt
    @RequestMapping(value = "/service/user/create/", method = RequestMethod.POST)
    public void insertUser(@RequestBody User user, UriComponentsBuilder ucBuilder, MediaType APPLICATION_JSON_UTF8){
        userRepository.save(user); // userRepository.save(user);
    }
  
    	
  
    /*
    @RequestMapping(value = "/service/user/{id}/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody User user, UriComponentsBuilder ucBuilder) {
    	   	
        //logger.info("Updating User with id {}", id);
    	User oldUser = userRepository.findOne(id);
    	System.out.println("Update REST START");
        //User updated = userRepository.findOne(id);
        User updated = new User();
        
        
        updated.setId(id);   //onemoguciti promjenu
        updated.setIme(user.getIme());
        updated.setMobilni(user.getMobilni());
        updated.setPoslovni(user.getPoslovni());
        updated.setPrivatni(user.getPrivatni());
        updated.setFiksni(user.getFiksni());
        
        
        //User updated = this.userRepository.save(updated);

        if (updated == null) {
            //logger.error("Unable to update. User with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        
        userRepository.save(updated);           
        return new ResponseEntity<User>(updated, HttpStatus.OK);
    }
    
    */
    
     
    
    // pobrisi kontakt
    @RequestMapping(value = "/service/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
        User user = userRepository.findOne(id);  //findById(id);
        System.out.println("FINDING to delete");
        if (user == null) {
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
}	
  
    
    
    