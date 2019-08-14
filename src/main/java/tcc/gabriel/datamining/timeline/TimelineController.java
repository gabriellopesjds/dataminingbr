package tcc.gabriel.datamining.timeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import twitter4j.TwitterException;

@RestController
@RequestMapping("/timeline")
public class TimelineController {
	
	@Autowired
	TimelineService service;
	
	@GetMapping()
	public List<Timeline> getAll() throws TwitterException {
		return service.getAll();
	}

}