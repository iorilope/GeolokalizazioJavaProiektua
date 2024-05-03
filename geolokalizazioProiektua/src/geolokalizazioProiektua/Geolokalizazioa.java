/*
 * 30 abr 2024
 * Ioritz Lopetegi
 */
package geolokalizazioProiektua;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

// TODO: Auto-generated Javadoc
/**
 * 
 * @author Ioritz Lopetegi
 */
public class Geolokalizazioa {

	/** The API_KEY. */
	private static final String API_KEY = "6ff8fb506a718128817359ac318588c7";

	/** The BASE_URL_HOME. */
	private static final String BASE_URL_HOME = "https://api.openweathermap.org/geo/1.0/";

	/** Hiria. */
	String hiria;

	/** latitudea string. */
	String latitudeaString;

	/** longitudea string. */
	String longitudeaString;

	/** url. */
	String url = BASE_URL_HOME + "direct?q=" + hiria + "&limit=0&appid=" + API_KEY;

	/** urlreversee. */
	String urlreversee = BASE_URL_HOME + "reverse?lat=" + hiria + "&lon=" + hiria + "limit=5&appid=" + API_KEY;

	/** lat node. */
	JsonNode latNode;

	/** lon node. */
	JsonNode lonNode;

	/**
	 * hiria izena koordenatu bidez lortzen duen Metodoa.
	 *
	 * @param lat , latitudea
	 * @param lon ,longitudea
	 * @return hiria izena koordenatu bidez
	 */
	// Koordenatu bidez izena bilatu
	public String getHiriaIzenaKoordenatuBidez(double lat, double lon) throws IOException {
	    try {
	        OkHttpClient client = new OkHttpClient();
	        urlreversee = BASE_URL_HOME + "reverse?lat=" + lat + "&lon=" + lon + "&limit=5&appid=" + API_KEY;

	        Request request = new Request.Builder().url(urlreversee).build();

	        Response response = client.newCall(request).execute();
	        if (response.isSuccessful()) {
	            String jsonData = response.body().string();

	            ObjectMapper mapper = new ObjectMapper();
	            JsonNode rootNode = mapper.readTree(jsonData);

	           
	            if (rootNode.isArray() && rootNode.size() > 0) {
	                
	                JsonNode firstCityNode = rootNode.get(0);

	               
	                JsonNode nameNode = firstCityNode.get("name");

	                
	                if (nameNode != null && nameNode.isTextual()) {
	                    System.out.println(nameNode.asText());
	                    return nameNode.asText();
	                } else {
	                    System.err.println("Error: Hiria aurkitzerakoan");
	                    return null;
	                }
	            } else {
	                System.err.println("Error: Apiaren Erantzunean");
	                return null;
	            }
	        } else {
	            System.err.println("Error: Ubikazioko datuen errorea - Errore Kodea: " + response.code());
	            return null;
	        }
	    } catch (IOException e) {
	        System.err.println("Error: HTTP: " + e.getMessage());
	        return null;
	    }
	}

	/**
	 * hiria izena lortzen du.
	 *
	 * @param hiria
	 * @return hiria izena
	 * @throws IOException.
	 */
	public String getHiriaIzena(String hiria) throws IOException {

		try {
			OkHttpClient client = new OkHttpClient();
			url = BASE_URL_HOME + "direct?q=" + hiria + "&limit=0&appid=" + API_KEY;

			Request request = new Request.Builder().url(url).build();

			Response response = client.newCall(request).execute();

			if (response.isSuccessful()) {
				String jsonData = response.body().string();

				ObjectMapper mapper = new ObjectMapper();
				try {
					JsonNode rootNode = mapper.readTree(jsonData);

					JsonNode firstCityNode = rootNode.get(0);

					JsonNode nameNode = firstCityNode.path("name");
					if (nameNode.isTextual()) {
						return nameNode.asText();
					} else {
						System.err.println("Error Hiria aurkitzerakoan");
						return null;
					}

				} catch (JsonProcessingException e) {
					System.err.println("Error parsing JSON data: " + e.getMessage());
					return null;
				}
			} else {
				System.err.println("Error fetching location data: " + response.code());
				return null;
			}

		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * Lortu latitudea.
	 *
	 * @param hiria
	 * @return lortutako latitudea
	 * @throws IOException.
	 */
	public String getLortuLatitudea(String hiria) throws IOException {
		try {
			String url = BASE_URL_HOME + "direct?q=" + hiria + "&limit=0&appid=" + API_KEY;

			OkHttpClient client = new OkHttpClient();
			Request request = new Request.Builder().url(url).build();

			Response response = client.newCall(request).execute();

			if (response.isSuccessful()) {
				String jsonData = response.body().string();
				ObjectMapper mapper = new ObjectMapper();
				try {
					JsonNode rootNode = mapper.readTree(jsonData);
					JsonNode firstCityNode = rootNode.get(0);

					JsonNode latNode = firstCityNode.path("lat");
					return latNode.asText();
				} catch (JsonProcessingException e) {
					System.err.println("Error parsing JSON data: " + e.getMessage());
					return null;
				}
			} else {
				System.err.println("Error fetching location data: " + response.code());
				return null;
			}
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * Lortu latitudeagradu.
	 *
	 * @param hiria
	 * @return lortutako latitudeagraduak
	 * @throws IOException.
	 */
	public String getLortuLatitudeagradu(String hiria) throws IOException {
		try {
			String url = BASE_URL_HOME + "direct?q=" + hiria + "&limit=0&appid=" + API_KEY;

			OkHttpClient client = new OkHttpClient();
			Request request = new Request.Builder().url(url).build();

			Response response = client.newCall(request).execute();

			if (response.isSuccessful()) {
				String jsonData = response.body().string();
				ObjectMapper mapper = new ObjectMapper();
				try {
					JsonNode rootNode = mapper.readTree(jsonData);
					JsonNode firstCityNode = rootNode.get(0);

					JsonNode latNode = firstCityNode.path("lat");
					double latitude = latNode.asDouble();

					int graduaklat = (int) latitude;
					double geratzendirenminutuaklat = (latitude - graduaklat) * 60;
					int minutuaklat = (int) geratzendirenminutuaklat;
					double segunduaklat = (geratzendirenminutuaklat - minutuaklat) * 60;

				
					String formattedLatitude = String.format("%d° %d' %.2f\"", graduaklat, minutuaklat, segunduaklat);
					return formattedLatitude;
				} catch (JsonProcessingException e) {
					System.err.println("Error parsing JSON data: " + e.getMessage());
					return null;
				}
			} else {
				System.err.println("Error fetching location data: " + response.code());
				return null;
			}
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * Lortu longitude.
	 *
	 * @param hiria
	 * @return lortutako longitudea
	 * @throws IOException
	 */
	public String getLortuLongitude(String hiria) throws IOException { 
		try {
			String url = BASE_URL_HOME + "direct?q=" + hiria + "&limit=0&appid=" + API_KEY;

			OkHttpClient client = new OkHttpClient();
			Request request = new Request.Builder().url(url).build();

			Response response = client.newCall(request).execute();

			if (response.isSuccessful()) {
				String jsonData = response.body().string();
				ObjectMapper mapper = new ObjectMapper();
				try {
					JsonNode rootNode = mapper.readTree(jsonData);
					JsonNode firstCityNode = rootNode.get(0);

					JsonNode lonNode = firstCityNode.path("lon");
					return lonNode.asText();
				} catch (JsonProcessingException e) {
					System.err.println("Error parsing JSON data: " + e.getMessage());
					return null;
				}
			} else {
				System.err.println("Error fetching location data: " + response.code());
				return null;
			}
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * Lortu longitudeagradu.
	 *
	 * @param hiria
	 * @return lortutako longitudeagradu
	 * @throws IOException.
	 */
	public String getLortuLongitudeagradu(String hiria) throws IOException {
		try {
			String url = BASE_URL_HOME + "direct?q=" + hiria + "&limit=0&appid=" + API_KEY;

			OkHttpClient client = new OkHttpClient();
			Request request = new Request.Builder().url(url).build();

			Response response = client.newCall(request).execute();

			if (response.isSuccessful()) {
				String jsonData = response.body().string();
				ObjectMapper mapper = new ObjectMapper();
				try {
					JsonNode rootNode = mapper.readTree(jsonData);
					JsonNode firstCityNode = rootNode.get(0);

					JsonNode longNode = firstCityNode.path("lon");
					double longitude = longNode.asDouble();

					int graduaklon = (int) longitude;
					double geratzendirenminutuaklon = (longitude - graduaklon) * 60;
					int minutuaklon = (int) geratzendirenminutuaklon;
					double segunduaklon = (geratzendirenminutuaklon - minutuaklon) * 60;

				
					String formattedLatitude = String.format("%d° %d' %.2f\"", graduaklon, minutuaklon, segunduaklon);
					return formattedLatitude;
				} catch (JsonProcessingException e) {
					System.err.println("Error parsing JSON data: " + e.getMessage());
					return null;
				}
			} else {
				System.err.println("Error fetching location data: " + response.code());
				return null;
			}
		} catch (IOException e) {
			throw e;
		}
	}

}
