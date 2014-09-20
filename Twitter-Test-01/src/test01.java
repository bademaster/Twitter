import java.util.ArrayList;
import java.util.List;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class test01 {

	static ArrayList<ConfigurationBuilder> accounts = new ArrayList<ConfigurationBuilder>();

	final static int maxLoops = 10;

	static int accountsArraySize = 0;
	static int currentAccount = 0;
	static boolean twErr = false;

	static long lowestStatusId = Long.MAX_VALUE;
	static Twitter twitter;
	static String searchString = "apple";
	// static ArrayList<Status> tweets = new ArrayList<Status>();
	static List<Status> tweets;
	static String lang = "de";

	static int currentTweet = 0;
	static int counter = 0;

	public static void main(String[] args) {

		setup();

		refreshTweets();

	}

	static void setup() {

		fillAccounts();

		accountsArraySize = accounts.size();

		switchAccount();

	}

	void draw() {

	}

	static void getNewTweets() {
		
			try {
				if (twErr) {
					switchAccount();
					twErr = false;
				}
				
				Query query = new Query(searchString);
				query.lang(lang);
				query.setCount(100);
				query.setMaxId(lowestStatusId - 1);

				QueryResult result = twitter.search(query);

				tweets = result.getTweets();
				currentTweet = currentTweet + 1;
				
				System.out.println("Tweets updated");
				
			} catch (TwitterException te) {
				// System.out.println("Failed to search tweets: " +
				// te.getMessage());
				// System.exit(-1);

				System.out.println("Message: " + te.getMessage());
				twErr = true;
				getNewTweets();
			}
		}

	

	static void refreshTweets() // Umgehen der 100 - Sperre
	{
		while (counter < (maxLoops * 100)) {
			
			getNewTweets();
			System.err.println("Current Account: " + currentAccount);

//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException ex) {
//				Thread.currentThread().interrupt();
//			}

			for (Status status : tweets) {
				System.out.println("Current: " + counter);
				// System.out.println(tweets.get(currentTweet));
				// Status status = tweets.get(currentTweet);
				System.out.println("Time: " + status.getCreatedAt());
				System.out.println("User: " + status.getUser());
				System.out.println("Text: " + status.getText());
				System.out.println("Lang: " + status.getLang());
				System.out.println("ID: " + status.getId());
				System.out.println("------------------------------");

				lowestStatusId = Math.min(status.getId(), lowestStatusId);
				counter++;
			}

		}

	}

	static void switchAccount() {

		if (currentAccount == accountsArraySize-1) {
			currentAccount = 0;
		}
		
		System.err.println("--- Switching Account from " + currentAccount + " to " + (currentAccount + 1) + " ---");
		
		ConfigurationBuilder cb = accounts.get(currentAccount);
		TwitterFactory tf = new TwitterFactory(cb.build());
		twitter = tf.getInstance();
		
		currentAccount++;
		System.err.println("Current Account: " + accounts.get(currentAccount).toString());
		
		// Debugging
		try {
			Thread.sleep(2000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		
	}

	static void fillAccounts() {

		ConfigurationBuilder cb01 = new ConfigurationBuilder();
		cb01.setOAuthConsumerKey("AJLqSHhaldpDxmnPce0z9o48a");
		cb01.setOAuthConsumerSecret("K2DpubTcJ2m5iLxIv1IExWX3No4mh8UCu7dWgasqZeO3Uji7EL");
		cb01.setOAuthAccessToken("2573572759-znUZR1OdN4tL0wp66Lll3d759AfJXpiWnF2OeFG");
		cb01.setOAuthAccessTokenSecret("5i6L7vUP22FUmdv3pknhxumeCaPl5RKziXRQHeCEeI4jP");

		ConfigurationBuilder cb02 = new ConfigurationBuilder();
		cb02.setOAuthConsumerKey("Vnv5LnIQEL6OvUzGPsJsiiiUa");
		cb02.setOAuthConsumerSecret("cZXD7p7BYwBjurnAjsEDGGADWR7dr8ENOMrEq5L3viEVpoVCZZ");
		cb02.setOAuthAccessToken("2573579448-wEaQDwWk3qzMIpBLefVNuSU6IOOwvmYXXKNV2Vr");
		cb02.setOAuthAccessTokenSecret("dOqvLeYeocwh5Xl6QQeGNIUZdMxQr6LvFTKtR96PtLqgk");

		ConfigurationBuilder cb03 = new ConfigurationBuilder();
		cb03.setOAuthConsumerKey("epLdMr7gJ3NvGLWHHS5LU335J");
		cb03.setOAuthConsumerSecret("JG6Pc0VUEx5ROG6qOiqh8pZ2k7KUaNvBaUeqbVh5qog7Ajx19R");
		cb03.setOAuthAccessToken("2573617292-NYiUm7I4QfCYdfO8u29I3okGB2iBLKfOrvLoo92");
		cb03.setOAuthAccessTokenSecret("UhxL0SkyhrGVNsEweypDdTxcw2exii21tFssf4D6xLaEK");

		ConfigurationBuilder cb04 = new ConfigurationBuilder();
		cb04.setOAuthConsumerKey("RIjwj93h3tGFpPIuh7uPjtexN");
		cb04.setOAuthConsumerSecret("E75OxMpIHIU0z6VK3ebmZoVSuUlsWqyOZpknhnWAqWl7tFmAw9");
		cb04.setOAuthAccessToken("2573641333-Zk8TFdv89hXgxaDE897DbtDc3eXA686q3WzqtJr");
		cb04.setOAuthAccessTokenSecret("GhTN9nFNupLQY6cRwNDAjPfnCnGExP4hDMU2UWga5hUTu");

		ConfigurationBuilder cb05 = new ConfigurationBuilder();
		cb05.setOAuthConsumerKey("0rYFcPwm6ELrXN8WUiJTD54wb");
		cb05.setOAuthConsumerSecret("fnrMSiDRTr4BnWpZqTWibmLwlhlS43BxxcphKOoWPKmyuO6Qds");
		cb05.setOAuthAccessToken("2573608194-IwxV9oets460yTKRyBCBNd7nCuhmMT86EmGJV1T");
		cb05.setOAuthAccessTokenSecret("hEv6qlfvHGy5PEBFnRTVi3PMohyUMzERnCut7EOIklq1d");

		ConfigurationBuilder cb06 = new ConfigurationBuilder();
		cb06.setOAuthConsumerKey("ccqNw0ggdbFc8gD4iwm00pSMf");
		cb06.setOAuthConsumerSecret("uZ9gA8scjIcx14jKTvtQzfKDFnP1jWXX5lC7w9MnzRNJkLvsNI");
		cb06.setOAuthAccessToken("2573613096-JFPSXvvh4r5OeTdnawoNJw0d5HWtolx9dNja5WV");
		cb06.setOAuthAccessTokenSecret("el1iozJeZ3rymFDokYwlljcswFcM1JoklzrRYE5btXgPg");
		
		ConfigurationBuilder cb07 = new ConfigurationBuilder();
		cb07.setOAuthConsumerKey("sGtjMi52m6PflY6lgsdKycZhV");
		cb07.setOAuthConsumerSecret("lb1jxzksy4wPrHIVvlLHtXvCCWVBFGT1tqpEQljdJCZA3BdPZq");
		cb07.setOAuthAccessToken("2573617356-wYTU5nGmT2B7SSARAEeowwPpQnCCriaJr8yXoI8");
		cb07.setOAuthAccessTokenSecret("LU8iqhW2ciSuiQmSsOfJ1X4HO0gj1upRL1HiIIYAUCeOB");
		
		ConfigurationBuilder cb08 = new ConfigurationBuilder();
		cb08.setOAuthConsumerKey("nJAfNXj3goOXZiO6rkuaYSI3L");
		cb08.setOAuthConsumerSecret("ZoRXJCRlZWSnbo6M7xH8mriXDbyFlSplso8gv54ha8FjqbfyOj");
		cb08.setOAuthAccessToken("35970-W2WeLz3sPAgHsFWDpJ1RTdDr55xbGoL1RyMQYUc");
		cb08.setOAuthAccessTokenSecret("IOJkCQpFal8QVGNcPa931mqlMh1gy0YcHOzE8dJL0Jipw");
		
		ConfigurationBuilder cb09 = new ConfigurationBuilder();
		cb09.setOAuthConsumerKey("7C1Wef6jym8CcDAuOhbLomTcb");
		cb09.setOAuthConsumerSecret("zvv9V2Ce3CDYFWRRdQBrGJce7b2o1w5RnLR3zkKoMGxaewtQfa");
		cb09.setOAuthAccessToken("2573643732-SDVVB9nMApipNlmcQkXJ4TWs2MrVXTSbXIrGj2g");
		cb09.setOAuthAccessTokenSecret("O20kfruZpL7tHrnDyoOvMoX8T8VB1RmZAVKgaXFh5p4sJ");
		
		ConfigurationBuilder cb10 = new ConfigurationBuilder();
		cb10.setOAuthConsumerKey("ChWWJml2cY41twNVso7JB1f7x");
		cb10.setOAuthConsumerSecret("VePF4hCtyVjbmX3wDdKaBkdPJpeJtmqilEJp9QmG85SnP3eD3h");
		cb10.setOAuthAccessToken("2573666306-SDT0itxIOLXGSyGOdmdUywYXHgDOn2D2otyxJUx");
		cb10.setOAuthAccessTokenSecret("OMbqGltxOO5o0M1u8JGvPwTILN4M8XOH3EQb2NIqUfpUT");
		

		accounts.add(cb01);
		accounts.add(cb02);
		accounts.add(cb03);
		accounts.add(cb04);
		accounts.add(cb05);
		accounts.add(cb06);
		accounts.add(cb07);
		accounts.add(cb08);
		accounts.add(cb09);
		accounts.add(cb10);

	}
}
