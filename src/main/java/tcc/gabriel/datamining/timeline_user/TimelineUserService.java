package tcc.gabriel.datamining.timeline_user;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import tcc.gabriel.datamining.sentiment.SenticNet;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Service
public class TimelineUserService {
	public String OAUTH_CONSUMER_KEY;
	public String OAUTH_CONSUMER_SECRET;
	public String OAUTH_ACCESS_TOKEN;
	public String OAUTH_ACCESS_TOKEN_SECRET;
	private ConfigurationBuilder cb;
	private TwitterFactory twitterFactory;
	private Twitter twitter;
	private List<TimelineUser> listaDeItensDaTimeLineUser = new ArrayList<>();

	@Autowired
	public TimelineUserService(@Value("${OAUTH_CONSUMER_KEY}") String OAUTH_CONSUMER_KEY,
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

	public List<TimelineUser> getAll(String id) throws TwitterException {
		/*
		 * Como o metodo .getUserTimeline() retorna apenas 20 resultados, foi criado uma
		 * pagina para passar como parametro onde nessa pagina eu especifico a
		 * quantidade de paginas existentes e a quantidade de resultados que serão
		 * apresentados logo como eu quero toda a informação, criei um contador para ir
		 * contando as paginas e populando minha lista
		 */

		List<Status> listaStatus = new ArrayList<>();
		listaDeItensDaTimeLineUser = new ArrayList<>();
		int contadorPaginas = 1;
		Paging paging = new Paging(contadorPaginas, 100);

		// while (twitter.getUserTimeline(id, paging).size() > 0) {
		// try {
		// paging.setPage(contadorPaginas);
		// listaStatus = twitter.getUserTimeline(id, paging);
		// System.out.println(listaStatus.size());
		// for (int i = 0; i < listaStatus.size(); i++) {
		// Status status = (Status) listaStatus.get(i);
		// TimelineUser timeline = new TimelineUser(status.getId(),
		// status.getUser().getId(),
		// status.getUser().getName(), status.getText(), status.getCreatedAt());
		// listaDeItensDaTimeLineUser.add(timeline);
		// }
		//
		// } catch (TwitterException e) {
		// }
		// contadorPaginas++;
		// }

		paging.setPage(contadorPaginas);
		listaStatus = twitter.getUserTimeline(id, paging);
		for (int i = 0; i < listaStatus.size(); i++) {
			Status status = (Status) listaStatus.get(i);
			TimelineUser timeline = new TimelineUser(status.getId(), status.getUser().getId(),
					status.getUser().getName(), status.getText(), status.getCreatedAt(), status.getLang());
			listaDeItensDaTimeLineUser.add(timeline);
		}
		analisarTexto(new SenticNet());
		return listaDeItensDaTimeLineUser;
	}

	private void analisarTexto(SenticNet senticnet) {
		for (TimelineUser t : listaDeItensDaTimeLineUser) {
			t.setPolaridade(senticnet.analisarTexto(t.getMensagem(), t.getLinguagem()));
		}
	}

}