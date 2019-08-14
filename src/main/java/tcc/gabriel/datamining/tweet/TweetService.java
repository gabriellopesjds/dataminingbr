package tcc.gabriel.datamining.tweet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import tcc.gabriel.datamining.sentiment.SenticNet;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Service
public class TweetService {
	public String OAUTH_CONSUMER_KEY;
	public String OAUTH_CONSUMER_SECRET;
	public String OAUTH_ACCESS_TOKEN;
	public String OAUTH_ACCESS_TOKEN_SECRET;
	private ConfigurationBuilder cb;
	private TwitterFactory twitterFactory;
	private Twitter twitter;
	private List<Tweet> listaDeItensTweets = new ArrayList<>();

	@Autowired
	public TweetService(@Value("${OAUTH_CONSUMER_KEY}") String OAUTH_CONSUMER_KEY,
			@Value("${OAUTH_CONSUMER_SECRET}") String OAUTH_CONSUMER_SECRET,
			@Value("${OAUTH_ACCESS_TOKEN}") String OAUTH_ACCESS_TOKEN,
			@Value("${OAUTH_ACCESS_TOKEN_SECRET}") String OAUTH_ACCESS_TOKEN_SECRET) {
		this.OAUTH_CONSUMER_KEY = OAUTH_CONSUMER_KEY;
		this.OAUTH_CONSUMER_SECRET = OAUTH_CONSUMER_SECRET;
		this.OAUTH_ACCESS_TOKEN = OAUTH_ACCESS_TOKEN;
		this.OAUTH_ACCESS_TOKEN_SECRET = OAUTH_ACCESS_TOKEN_SECRET;
		configurarOAUTH();
	}


	public void configurarOAUTH() {
		cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey(OAUTH_CONSUMER_KEY).setOAuthConsumerSecret(OAUTH_CONSUMER_SECRET)
				.setOAuthAccessToken(OAUTH_ACCESS_TOKEN).setOAuthAccessTokenSecret(OAUTH_ACCESS_TOKEN_SECRET);
		twitterFactory = new TwitterFactory(cb.build());
		twitter = twitterFactory.getInstance();
	}

	public List<Tweet> getAllTweet(String busca) throws TwitterException {
		Query query = new Query("#" + busca);
		query.count(2000);
        
		QueryResult result = twitter.search(query);
		List<Status> listaStatus = result.getTweets();
		listaDeItensTweets = new ArrayList<>();
		for (Status status : listaStatus) {
			Tweet tweet = new Tweet(status.getId(), status.getUser().getScreenName(), status.getText(), status.getLang());
			listaDeItensTweets.add(tweet);
		}
		String resourcePath = "";
		analisarTexto(new SenticNet());
		return listaDeItensTweets;
	}
	
	private void analisarTexto(SenticNet senticnet) {
		for (Tweet t : listaDeItensTweets) {
			t.setPolaridade(senticnet.analisarTexto(t.getMensagem(), t.getLinguagem()));
		}
	}
	
}
