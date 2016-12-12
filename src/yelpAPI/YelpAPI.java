package yelpAPI;

import java.util.*;

import org.json.simple.JSONArray;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;
import com.beust.jcommander.Parameter;

import GUI.FileWrite;

/**
 * Code sample for accessing the Yelp API V2.
 *
 * This program demonstrates the capability of the Yelp API version 2.0 by using
 * the Search API to query for businesses by a search term and location, and the
 * Business API to query additional information about the top result from the
 * search query.
 *
 * <p>
 * See <a href="http://www.yelp.com/developers/documentation">Yelp
 * Documentation</a> for more info.
 */
public class YelpAPI {

	private static final String API_HOST = "api.yelp.com";
	private static final String SEARCH_PATH = "/v2/search";
	private static final String BUSINESS_PATH = "/v2/business";

	private static String DEFAULT_TERM = "restaurant";
	private static String DEFAULT_LOCATION = "Philadelphia, PA";
	private static int SEARCH_LIMIT = 3;

	/*
	 * Update OAuth credentials below from the Yelp Developers API site:
	 * http://www.yelp.com/developers/getting_started/api_access
	 */
	private static final String CONSUMER_KEY = "0QWMjmhS1iQXCoWdQhvM2w";
	private static final String CONSUMER_SECRET = "SE3lWpqgRUsJApFA48DtdtrPncs";
	private static final String TOKEN = "0FE1EeKtk9ZYj8XPlLrRjncK1DCH4ru2";
	private static final String TOKEN_SECRET = "4hvoeTJvQ-RNDEk7LgIARjt-ndI";

	OAuthService service;
	Token accessToken;

	/**
	 * Constructs YelpAPI according to user input
	 * 
	 * @param term
	 * @param location
	 * @param limit
	 */
	public YelpAPI(String term, String location, int limit) {
		this(CONSUMER_KEY, CONSUMER_SECRET, TOKEN, TOKEN_SECRET);

		if (term.trim() != "") {
			YelpAPI.DEFAULT_TERM = term.trim();
		}

		if (location.trim() != "") {
			YelpAPI.DEFAULT_LOCATION = location.trim();
		}

		if (limit > 0 && limit <= 10) {
			YelpAPI.SEARCH_LIMIT = limit;
		}
	}

	/**
	 * Setup the Yelp API OAuth credentials.
	 *
	 * @param consumerKey Consumer key           
	 * @param consumerSecret Consumer secret          
	 * @param token Token           
	 * @param tokenSecret Token secret
	 *            
	 */
	public YelpAPI(String consumerKey, String consumerSecret, String token, String tokenSecret) {
		this.service = new ServiceBuilder().provider(TwoStepOAuth.class).apiKey(consumerKey).apiSecret(consumerSecret)
				.build();
		this.accessToken = new Token(token, tokenSecret);
	}

	/**
	 * Creates and sends a request to the Search API by term and location.
	 * <p>
	 * See
	 * <a href="http://www.yelp.com/developers/documentation/v2/search_api">Yelp
	 * Search API V2</a> for more info.
	 *
	 * @param term <tt>String</tt> of the search term to be queried           
	 * @param location  <tt>String</tt> of the location         
	 * @return <tt>String</tt> JSON Response
	 */
	public String searchForBusinessesByLocation(String term, String location) {
		OAuthRequest request = createOAuthRequest(SEARCH_PATH);
		request.addQuerystringParameter("term", term);
		request.addQuerystringParameter("location", location);
		request.addQuerystringParameter("limit", String.valueOf(SEARCH_LIMIT));
		return sendRequestAndGetResponse(request);
	}

	/**
	 * Creates and sends a request to the Business API by business ID.
	 * <p>
	 * See
	 * <a href="http://www.yelp.com/developers/documentation/v2/business">Yelp
	 * Business API V2</a> for more info.
	 *
	 * @param businessID <tt>String</tt> business ID of the requested business          
	 * @return <tt>String</tt> JSON Response
	 */
	public String searchByBusinessId(String businessID) {
		OAuthRequest request = createOAuthRequest(BUSINESS_PATH + "/" + businessID);
		return sendRequestAndGetResponse(request);
	}

	/**
	 * Creates and returns an {@link OAuthRequest} based on the API endpoint
	 * specified.
	 *
	 * @param path  API endpoint to be queried        
	 * @return <tt>OAuthRequest</tt>
	 */
	private OAuthRequest createOAuthRequest(String path) {
		OAuthRequest request = new OAuthRequest(Verb.GET, "https://" + API_HOST + path);
		return request;
	}

	/**
	 * Sends an {@link OAuthRequest} and returns the {@link Response} body.
	 *
	 * @param request {@link OAuthRequest} corresponding to the API request         
	 * @return <tt>String</tt> body of API response
	 */
	private String sendRequestAndGetResponse(OAuthRequest request) {
		System.out.println("Querying " + request.getCompleteUrl() + " ...");
		this.service.signRequest(this.accessToken, request);
		Response response = request.send();
		return response.getBody();
	}

	/**
	 * Queries the Search API based on the user input arguments
	 * Returns all the JSON results in form of String stored in the ArrayList
	 *
	 * @param yelpApi <tt>YelpAPI</tt> service instance         
	 * @param yelpApiCli  <tt>YelpAPICLI</tt> command line arguments         
	 */
	private static ArrayList<String> queryAPI(YelpAPI yelpApi, YelpAPICLI yelpApiCli) {
		
		//using YELP search API
		String searchResponseJSON = yelpApi.searchForBusinessesByLocation(yelpApiCli.term, yelpApiCli.location);

		FileWrite f = new FileWrite();

		JSONParser parser = new JSONParser();
		JSONObject response = null;
		try {
			response = (JSONObject) parser.parse(searchResponseJSON);
		} catch (ParseException pe) {
			System.out.println("Error: could not parse JSON response:");
			System.out.println(searchResponseJSON);
			System.exit(1);
		}

		JSONArray businesses = (JSONArray) response.get("businesses");
		int resNum = businesses.size();

		ArrayList<String> output = new ArrayList<>();
		
		//using YELP business API-revised version
		//traverse to output each JSON result
		for (int i = 0; i < resNum; i++) {
			JSONObject b = (JSONObject) businesses.get(i);
			String bID = b.get("id").toString();
			String businessResponseJSON = yelpApi.searchByBusinessId(bID.toString());
			output.add(businessResponseJSON);
		}
		return output;
	}

	/**
	 * Command-line interface for the sample Yelp API runner.
	 */
	private static class YelpAPICLI {
		@Parameter(names = { "-q", "--term" }, description = "Search Query Term")
		public String term = DEFAULT_TERM;

		@Parameter(names = { "-l", "--location" }, description = "Location to be Queried")
		public String location = DEFAULT_LOCATION;

	}
	
	/**
	 * The newly added method for querying API based on user input of term, location and result limit
	 * Returns all query results in form of string stored in ArrayList
	 * @param term
	 * @param location
	 * @param limit
	 * @return ArrayList<String>
	 */
	public static ArrayList<String> search(String term, String location, int limit) {

		YelpAPI yelpApi = new YelpAPI(term, location, limit);
		YelpAPICLI yelpApiCli = new YelpAPICLI();

		return queryAPI(yelpApi, yelpApiCli);
	}
}
