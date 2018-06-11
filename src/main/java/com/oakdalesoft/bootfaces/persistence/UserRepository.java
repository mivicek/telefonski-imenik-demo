package com.oakdalesoft.bootfaces.persistence;

import com.oakdalesoft.bootfaces.domain.User;
import com.oakdalesoft.bootfaces.view.UserView;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Alex on 07/03/2015.
 */

public interface UserRepository extends JpaRepository<User, Long> {

    User findById(long id);
    
    void deleteAll();  //void deleteAllUsers();
    
    //List<User>  findByIme(String ime);
    
	//Object findByIme(String ime);
    

	//Object findByImeContaining();
	       
    
	//List<User> findUserByIme(String ime);

	//List<User> findByIme();
	
	//List<User> findByImeContaining();
	
		
	//void deleteUserById(long id);
				//
    
	//void doDeleteUser(User user);
	    //User findByIme(String ime);
         //void doUpdateUser(User user);
         //void doUpdateUser(User user);
         //void deleteUserById(long id);
        //void deleteUser(long id); 
        // ne radi moja ideja
      //List<UserView> findAllUsers();  //List<User> findAllUsers(); 
        
    //void deleteAllUsers();
         //public boolean isUserExist(User user);
    
}
