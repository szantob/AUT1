package hu.bme.aut.retelab2.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import hu.bme.aut.retelab2.domain.Ad;
import hu.bme.aut.retelab2.domain.Subscription;

@Repository
public class SubscriptionRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Subscription save(Subscription sc) {
        return em.merge(sc);
    }
    
    @Transactional
    public List<Subscription> getSubscriptionsByAd(Ad ad) {
    	String query = "SELECT a FROM Subscription a WHERE Ad == ?1;";
		return em.createQuery(query, Subscription.class)
        		.setParameter(1, ad)
        		.getResultList();
    	
    }
}
