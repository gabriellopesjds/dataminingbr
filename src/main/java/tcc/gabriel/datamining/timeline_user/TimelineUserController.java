package tcc.gabriel.datamining.timeline_user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import twitter4j.TwitterException;

@RestController
@RequestMapping("/user")
public class TimelineUserController {
	
	@Autowired
	TimelineUserService service;
	
	@GetMapping("{id}")
	public List<TimelineUser> getAll(@PathVariable String id) throws TwitterException {
		return service.getAll(id);
	}
}