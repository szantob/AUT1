package hu.bme.aut.retelab2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.bme.aut.retelab2.domain.Ad;
import hu.bme.aut.retelab2.repository.AdRepository;

@RestController
@RequestMapping("/api/ad")
public class AdController {

    @Autowired
    private AdRepository adRepository;


    @PostMapping
    public Ad create(@RequestBody Ad ad) {
        ad.setId(null);
        ad.setTime(null);
        return adRepository.save(ad);
    }

    @GetMapping
    public List<Ad> search(
    		@RequestParam(required = true) String keyword,
    		@RequestParam(required = false, defaultValue = "0") String minVal,
    		@RequestParam(required = false, defaultValue = "10000000") String maxVal
    		) {
    	int max = Integer.parseInt(maxVal);
    	int min = Integer.parseInt(minVal);
    	
    	List<Ad> result = adRepository.search(min, max, keyword);
    	for (Ad ad : result) {
			ad.setSecret(null);
		}
		return result;
    }
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Ad ad) {
        Ad a = adRepository.modify(ad);
        if (a == null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        return ResponseEntity.ok().build();
    }
}
