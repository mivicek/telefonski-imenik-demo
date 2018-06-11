package com.oakdalesoft.bootfaces.view;

import com.oakdalesoft.bootfaces.domain.User;
import com.oakdalesoft.bootfaces.persistence.UserRepository;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;
//import org.springframework.web.bind.annotation.*;   //za svaki sluaj
//import org.springframework.web.bind.annotation.*;   //za svaki sluaj
//import org.springframework.data.repository.CrudRepository; //za svaki slucaj
//import org.springframework.data.jpa.repository.*;



/**
 * Created by Alex on 07/03/2015.
 */
@ManagedBean(name = "model", eager = true)
@RequestScoped
public class UserModel {

    public void setUser(UserView user) {
        this.user = user;
    }

    public UserView getUser() {
        return user;
    }
    
    @ManagedProperty(value = "#{user}")
    private UserView user;

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ManagedProperty(value = "#{userRepository}")
    UserRepository userRepository;
    
    public String doCreateUser() {
    	System.out.println("doCreateUser START");
        User created = new User();
        created.setId(this.user.getId());
        created.setIme(this.user.getIme());
        created.setMobilni(this.user.getMobilni());
        created.setPoslovni(this.user.getPoslovni());
        created.setPrivatni(this.user.getPrivatni());
        created.setFiksni(this.user.getFiksni());
        User newUser = this.userRepository.save(created);

        FacesContext.getCurrentInstance().addMessage("errors",
            new FacesMessage(FacesMessage.SEVERITY_INFO, "User created",
                "Kontakt za " + created.getIme() + " je kreiran s ID=" + newUser.getId()));

        this.user.setIme("");
        this.user.setMobilni("");
        this.user.setPoslovni("");
        this.user.setPrivatni("");
        this.user.setFiksni("");
        System.out.println("doCreateUser END");
        return "index.xhtml";
    
    }

    
    public String doUpdateUser() {
    	System.out.println("doUpdateUser START");
        User updated = new User();
        updated.setId(this.user.getId());
        updated.setIme(this.user.getIme());
        updated.setMobilni(this.user.getMobilni());
        updated.setPoslovni(this.user.getPoslovni());
        updated.setPrivatni(this.user.getPrivatni());
        updated.setFiksni(this.user.getFiksni());
        User newUser = this.userRepository.save(updated);

        FacesContext.getCurrentInstance().addMessage("errors",
            new FacesMessage(FacesMessage.SEVERITY_INFO, "User created",
                "Kontakt za " + updated.getIme() + " je izmjenjen, ID=" + newUser.getId()));
      
        System.out.println("doUpdateUser END");
        return "index.xhtml";
    
    }
 
    
    public void doFindUserById() {
        User found = userRepository.findOne(this.user.getId());
        this.user.setId(found.getId());
        this.user.setIme(found.getIme());
        this.user.setMobilni(found.getMobilni());
        this.user.setPoslovni(found.getPoslovni());
        this.user.setPrivatni(found.getPrivatni());
        this.user.setFiksni(found.getFiksni());
    }

    // moj prvi Java search algoritam  :)
    public List<UserView> findUserBySve() {
    	System.out.println("findUserByIme START i:" + this.user.getIme());
    	String keyword = this.user.getIme().toString().toLowerCase();
    	List<UserView> users = new ArrayList<UserView>();    	
    	
    	System.out.println("findUserByIme za keyword: " + keyword);
    	
    	for(User entity : this.userRepository.findAll()) {
    	System.out.println("listanje");
        UserView view = new UserView();
         
        System.out.println("entity.getIme(): " + entity.getIme() + "  , mobilni: " + entity.getMobilni() + " poslovni; " + entity.getPoslovni());

          
        String searchIme = "";
        String searchMobilni = "";
        String searchPoslovni = "";
        String searchPrivatni = "";
        String searchFiksni = "";
          
          
		if (entity.getIme() != null) {
        	searchIme = entity.getIme().toString(); 
		} else {
			searchIme = "";
		}
		
		if (entity.getMobilni() != null) {
        	searchMobilni = entity.getMobilni().toString(); 
		} else {
			searchMobilni = "";
		}
		
		if (entity.getPoslovni() != null) {
        	searchPoslovni = entity.getPoslovni().toString(); 
		} else {
			searchPoslovni = "";
		}		
		
		if (entity.getPrivatni() != null) {
        	searchPrivatni = entity.getPrivatni().toString(); 
		} else {
			searchPrivatni = "";
		}		
		
		if (entity.getFiksni() != null) {
        	searchFiksni = entity.getFiksni().toString(); 
		} else {
			searchFiksni = "";
		}		
		
		
		/*
        searchMobilni = entity.getMobilni().toString();
        searchPoslovni = entity.getPoslovni().toString();
        searchPrivatni = entity.getPrivatni().toString();
        searchFiksni = entity.getFiksni().toString();
        */
		
        System.out.println(searchIme + " " + searchMobilni + " " + searchPoslovni + "  " +  searchPrivatni + " " + searchFiksni 
          + " tip: " + searchMobilni.getClass().getName() + "  " +  keyword.getClass().getName());
          
          
        //if (((searchIme != null) && (searchMobilni != null) && (searchPoslovni != null) &&  (searchPrivatni != null) && (searchFiksni != null)) && 
        if ((searchIme.toLowerCase().contains(keyword)) || (searchMobilni.toLowerCase().contains(keyword)) || 
        		(searchPoslovni.toLowerCase().contains(keyword)) || (searchPrivatni.toLowerCase().contains(keyword)) || (searchFiksni.toLowerCase().contains(keyword)))
        {
        	  
        System.out.println("keyword: " + keyword + " , searchIme: " + searchIme + "key i mob"   + keyword.equals(searchMobilni) + "  isti tip: " +searchMobilni.getClass().getName() + "  " + keyword + "  " +  keyword.getClass().getName()); 
        //if (ime.equals(entity2) ) {
        //System.out.println("entity.getIme(): " + entity.getIme() + "  , ime= "+ keyword );
          
        view.setId(entity.getId());
        view.setIme(entity.getIme());
        view.setMobilni(entity.getMobilni());
        view.setPoslovni(entity.getPoslovni());
        view.setPrivatni(entity.getPrivatni());
        view.setFiksni(entity.getFiksni());
        users.add(view);
        System.out.println("nemame");
        }
      }
    	System.out.println("findUserByIme KRAJ");
    	System.out.println("users: " + users);
      return users;
    }
    
    
    
    
    //radi
    public List<UserView> findUserByIme() {
    	System.out.println("findUserByIme START");
    	
    	List<UserView> users = new ArrayList<UserView>();
    
        //List<UserView> users = userRepository.findByIme();
    	//List<User> users = userRepository.findByIme();
        //List<UserView> users = new ArrayList<UserView>();
    	
    	//User found = userRepository.findOne(this.user.getIme());
    	
    	String ime = this.user.getIme();
    	System.out.println("keyword ime: " + ime);
    	
    	System.out.println("findUserByIme za=" + ime);
    	
    	for(User entity : this.userRepository.findAll()) {
    		
    		
    	System.out.println("listanje");
        UserView view = new UserView();
         
        System.out.println("entity.getIme(): " + entity.getIme() + "  , ime: "+ ime + " pa ispada: " + (entity.getIme() == ime ));
          
        String entity2 = entity.getIme().toString();
          
        System.out.println(ime.equals(entity2) + "     " + "entity2: " + entity2 + "  " + entity2.getClass().getName() + "  " + ime + "  " +  ime.getClass().getName()); 
          
        //if (ime.equals(entity2) ) {
          
        if (entity2.toLowerCase().contains(ime.toLowerCase())) {
        	  
        System.out.println("entity.getIme(): " + entity.getIme() + "  , ime= "+ ime );
          
        view.setId(entity.getId());
        view.setIme(entity.getIme());
        view.setMobilni(entity.getMobilni());
        view.setPoslovni(entity.getPoslovni());
        view.setPrivatni(entity.getPrivatni());
        view.setFiksni(entity.getFiksni());
        users.add(view);
        System.out.println("nemame");
          }
        }
    System.out.println("findUserByIme KRAJ");
    System.out.println("users: " + users);
    return users;
    }
    
    
  
    public List<UserView> findAllUsers() {
        List<UserView> users = new ArrayList<UserView>();
        for(User entity : this.userRepository.findAll()) {
            UserView view = new UserView();
            view.setId(entity.getId());
            view.setIme(entity.getIme());
            view.setMobilni(entity.getMobilni());
            view.setPoslovni(entity.getPoslovni());
            view.setPrivatni(entity.getPrivatni());
            view.setFiksni(entity.getFiksni());
            users.add(view);
        }
        return users;
    }
    
    
    
    public String deleteAction(User user) {
    	System.out.println("deleteAction START");
    	userRepository.delete(user);
    	System.out.println("deleteAction END");
    	return "index.xhtml";
    }
    
    

    public String doDeleteUser(long id) {
    	
    	System.out.println("deleteAction START");
    	//found = userRepository.findOne(id);
    	//User created = findOne(this.user.getId())();
    	//User found = userRepository.findOne(this.user.getId());
    	
    	//userRepository.deleteById(id);
    	
    	//deleteUser(found);
    	
    	//this.user.delete(id);
    	
    	User user = userRepository.findOne(id);
    	userRepository.delete(user);
    	System.out.println("deleteAction END");
    	return "sviKontakti.xhtml";

    }
    
    
    public String deleteUserById() {
    	
    	//User updated = new User();
    	
    	System.out.println("deleteAction START");
    	
    	    	
        User deleted = new User();
        deleted.setId(this.user.getId());
        this.userRepository.delete(deleted);
        
        
    	//User user = userRepository.findOne(id);
    	//userRepository.delete(user);
    	System.out.println("deleteAction END");
    	
        FacesContext.getCurrentInstance().addMessage("errors",
                new FacesMessage(FacesMessage.SEVERITY_INFO, "User created",
                    "Kontakt je izbrisan, ID=" + deleted.getId()));
        
    	return "sviKontakti.xhtml";
    	
    }
  
    /*
    
    //tek testiram
    public List<UserView> findUserByIme() {
    	System.out.println("findUserByIme START");
    	
    	List<UserView> users = new ArrayList<UserView>();
    
      //List<UserView> users = userRepository.findByIme();
  //List<User> users = userRepository.findByIme();
     //List<UserView> users = new ArrayList<UserView>();
    	
    	//User found = userRepository.findOne(this.user.getIme());
    	
    	String ime = this.user.getIme();
    	
    	System.out.println("findUserByIme za=" + ime);
    	
    	for(User entity : this.userRepository.findByIme(ime)) {
    		
    		
    		System.out.println("listanje");
          UserView view = new UserView();
          view.setId(entity.getId());
          view.setIme(entity.getIme());
          view.setMobilni(entity.getMobilni());
          view.setPoslovni(entity.getPoslovni());
          view.setPrivatni(entity.getPrivatni());
          view.setFiksni(entity.getFiksni());
          users.add(view);
          System.out.println("nemame");
      }
    	System.out.println("findUserByIme KRAJ");
      return users;
    }
    
    */
     

    

}
