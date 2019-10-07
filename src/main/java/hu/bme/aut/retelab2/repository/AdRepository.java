package hu.bme.aut.retelab2.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import hu.bme.aut.retelab2.domain.Ad;

@Repository
public class AdRepository {

    @PersistenceContext
    private EntityManager em;

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
        	return newAd;
        }
        return null;
    }
}
