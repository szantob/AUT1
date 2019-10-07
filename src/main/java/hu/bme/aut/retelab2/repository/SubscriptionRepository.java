package hu.bme.aut.retelab2.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import hu.bme.aut.retelab2.domain.Subscription;

@Repository
public class SubscriptionRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Subscription save(Subscription sc) {
        return em.merge(sc);
    }
}
