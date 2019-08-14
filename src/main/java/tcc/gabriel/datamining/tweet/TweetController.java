package tcc.gabriel.datamining.tweet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import twitter4j.TwitterException;

@RestController
@RequestMapping("/tweet")
public class TweetController {
	
	@Autowired
	TweetService service;
	
	@GetMapping("{busca}")
	public List<Tweet> getAllTweet(@PathVariable String busca) throws TwitterException {
		return service.getAllTweet(busca);
	}
	
}