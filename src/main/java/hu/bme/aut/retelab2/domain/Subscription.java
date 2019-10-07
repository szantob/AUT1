package hu.bme.aut.retelab2.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Subscription {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String email;
	
	@ManyToOne
	private Ad ad;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Ad getAd() {
		return ad;
	}

	public void setAd(Ad ad) {
		this.ad = ad;
	}
}
