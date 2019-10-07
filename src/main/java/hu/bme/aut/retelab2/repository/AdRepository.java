package hu.bme.aut.retelab2.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import hu.bme.aut.retelab2.domain.Ad;
import hu.bme.aut.retelab2.domain.Subscription;

@Repository
public class AdRepository {

	@Autowired
	private SubscriptionRepository subRepo;
	
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    private JavaMailSender javaMailSender;

    @Transactional
    public Ad save(Ad ad) {
        return em.merge(ad);
    }

    @Transactional
    public List<Ad> search(int valueMin, int valueMax, String containsStr) {
    	String query = "SELECT a FROM Ad a WHERE ("
    							+ "value < ?1 AND "
    							+ "value > ?2 AND "
    							+ "title LIKE ?3)";
    	
        return em.createQuery(query, Ad.class)
        		.setParameter(1, valueMax)
        		.setParameter(2, valueMin)
        		.setParameter(3, containsStr)
        		.getResultList();
    } 

    @Transactional
    public Ad modify(Ad newAd) {
        Ad oldAd = em.find(Ad.class, newAd.getId());
        if(oldAd.getSecret().equals(newAd.getSecret())) {
        	em.merge(newAd);
        	List<Subscription> subList = subRepo.getSubscriptionsByAd(newAd);
        	for (Subscription subscription : subList) {
        		SimpleMailMessage msg = new SimpleMailMessage();
        		msg.setFrom("username@gmail.com");
        		msg.setTo(subscription.getEmail());
        		msg.setSubject(newAd.getTitle());
        		msg.setText(newAd.getDesc());
        		javaMailSender.send(msg);

			}
        	return newAd;
        }
        return null;
    }

	
    public Ad findById(long id) {
        return em.find(Ad.class, id);
    }
}
