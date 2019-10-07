package hu.bme.aut.retelab2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.bme.aut.retelab2.domain.Ad;
import hu.bme.aut.retelab2.domain.EmailDto;
import hu.bme.aut.retelab2.domain.Subscription;
import hu.bme.aut.retelab2.repository.AdRepository;
import hu.bme.aut.retelab2.repository.SubscriptionRepository;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

	@Autowired
	SubscriptionRepository subRepository;
	
	@Autowired
	AdRepository adRepository;
	
	@PostMapping("{id}")
    public ResponseEntity<Subscription> getById(@PathVariable long id, @RequestParam EmailDto email) {
        Ad ad = adRepository.findById(id);
        
		Subscription sc = new Subscription();
        sc.setEmail(email.getEmail());
        sc.setAd(ad);
        ad.addSubscription(sc);
        
        adRepository.save(ad);
        subRepository.save(sc);
        
		return ResponseEntity.ok(sc);
    }
}
