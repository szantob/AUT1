package hu.bme.aut.retelab2.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;


import hu.bme.aut.retelab2.SecretGenerator;

@Entity
public class Ad {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String title;
	
	private String desc;
	
	private int value;
	
	@CreationTimestamp
	private Date time;
	
	@OneToMany(mappedBy="ad")
	private List<Subscription> subscriptions;

	private String secret = SecretGenerator.generate();
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public void addSubscription(Subscription sc) {
		subscriptions.add(sc);
	}
}
