package com.oakdalesoft.bootfaces.domain;

import javax.persistence.*;

/**
 * Created by Alex on 07/03/2015.
 */

@Entity
@Table(schema = "users.public", name = "User")
public class User {
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
    @Column(nullable = false)
	private String ime;
    @Column(nullable = true)
	private String mobilni;
    @Column(nullable = true)
	private String poslovni;
    @Column(nullable = true)
	private String privatni;
    @Column(nullable = true)
	private String fiksni;


    /*
    public User(int id, String ime, String mobilni, String poslovni, String privatni, String fiksni) {
		super();
		this.id = id;
		this.ime = ime;
		this.mobilni = mobilni;
		this.poslovni = poslovni;
		this.privatni = privatni;
		this.fiksni = fiksni;
	}
	*/
	
	public long getId() {
		return id;
	}

	public void setId(long l) {
		this.id = l;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}
	
	
	public String getMobilni() {
		return mobilni;
	}

	public void setMobilni(String mobilni) {
		this.mobilni = mobilni;
	}

	public String getPoslovni() {
		return poslovni;
	}

	public void setPoslovni(String poslovni) {
		this.poslovni = poslovni;
	}

	
	public String getPrivatni() {
		return privatni;
	}

	public void setPrivatni(String privatni) {
		this.privatni = privatni;
	}
		
	
	public String getFiksni() {
		return fiksni;
	}

	public void setFiksni(String fiksni) {
		this.fiksni = fiksni;
	}

}	
	
/*
@Entity
@Table(schema = "users.public", name = "User")
public class User {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getnbofpage() {
        return nbofpage;
    }

    public void setnbofpage(Integer nbOfPage) {
        this.nbofpage = nbOfPage;
    }

    public Boolean getIllustrations() {
        return illustrations;
    }

    public void setIllustrations(Boolean illustrations) {
        this.illustrations = illustrations;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = true)
    private Float price;
    @Column(nullable = true)
    private String description;
    @Column(nullable = true)
    private Integer nbofpage;
    @Column(nullable = true)
    private Boolean illustrations;

}
*/