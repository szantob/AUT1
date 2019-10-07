package hu.bme.aut.retelab2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.bme.aut.retelab2.repository.SubscriptionRepository;

@RestController
@RequestMapping("/api/subscribe")
public class SubscriptionController {

	@Autowired
	SubscriptionRepository repository;
}
