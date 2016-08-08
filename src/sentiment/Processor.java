package sentiment;

import java.util.LinkedList;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/*
 * Main class
 */
public class Processor {

	// Alter those three parameters for testing:
	static String main_folder = "resources/";		// the path to the "resources" folder 
	static String test_dataset = "Liebherr";						// available options for demo: goethe, Liebherr, Cisco
	static boolean useSlidingWindowForTraining = false;				// if set to "true", only the last 1,000 documents will be used for the training of the ensemble classifier
	
	public static void main(String[] args) throws Exception {
		
		// get all test set elements
		LinkedList<String> lt = getData(test_dataset);				
		
		SentimentAnalyser analyser = new SentimentAnalyser(main_folder, useSlidingWindowForTraining, test_dataset);
		
		/*
		for (int i=0; i<lt.size(); i++){
			System.out.println(analyser.getPolarity(lt.get(i)));	// any text may be passed as an argument here
			//System.out.println(i+"\t"+out);
		}
		*/
		
		// test case
		LinkedList<String> ltc = new LinkedList<String>();

		ltc.add("Pokemon Go Surpasses 100 Million Downloads On Google Play");
		ltc.add("I JUST HATCHED A 5K EGG AND THIS IS WHAT WAS INSIDE, I AM SO ANGRY #PokemonGO");
		ltc.add("Disappointed with both evolutions #PokemonGO #TeamMystic");
		ltc.add("Twitch Is Now Helping #PokémonGO Catch All the Cheaters http://www.themarysue.com/twitch-pokemon-go-cheating-crackdown/");
		ltc.add("Hang in there, trainers! Many reports are coming in that the servers are offline. #PokemonGO");
		ltc.add("20-year-old baseball star was killed Saturday night while Playing #PokemonGo. http://l.gamespot.com/6018B07wA");
		ltc.add("I CAUGHT THEM ALL NOW WHAT DO I DO WITH MY LIFE?! #PokemonGO");
		ltc.add("Through the drama we made it to 3.3k I love each and every one of you #PokemonGO");
		ltc.add("Giving away 30,200 Coins to 3 random people who Retweet and Follow @TrendsTube_ ! Ends in 24 hours! Good luck! #PokemonGO");
		
		for (int i=0; i < ltc.size(); i++){
			System.out.println(analyser.getPolarity(ltc.get(i)));	// any text may be passed as an argument here
		}
	}
	
	private static LinkedList<String> getData(String f){
		LinkedList<String> all_tweets = new LinkedList<String>();
		DataSource ds;
		Instances data = null;
		
		try {
			System.out.println("getData: " + f);
			// file path: resources/test_sets/Liebherr.arff
			ds = new DataSource(main_folder+"test_sets/"+f+".arff");
			data =  ds.getDataSet();
		} catch (Exception e) {
			System.out.println("File not found.");
		}
		
		// add the test set into list of all_tweets (only for text attribute)
		for (int i = 0; i < data.numInstances(); i++) {
			all_tweets.add(data.get(i).stringValue(0));
		}
		
		return all_tweets;
	}
}