package webservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import model.Articles;
import model.Gnews;
import model.Source;
import utils.ConsumerRestClient;


@Path("/wsremote")
public class WebServicesBlog {
	
	String loremIpsum="Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
	String urlImageLoremIpsum="https://tiposconcaracter.es/wp-content/uploads/2011/08/que-es-lorem-ipsum.jpg";

	
	@GET
	@Path("/external")
	@Produces("application/json")
	public Gnews getExternalGnewsData() {
		Gnews data=new Gnews();
		try {
			String url="https://gnews.io/api/";
			String method="v4/search";
			Map<String,String> params=new HashMap<String, String>();
			params.put("q", "watches");
			params.put("token", "c8227a07c24b9738e4a444a0121079f1");
			ConsumerRestClient cons=new ConsumerRestClient(url, method, params, "GET");
			data = cons.entity(Gnews.class);
			//data.setTotalArticles(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	@GET
	@Path("/remotedata")
	@Produces("application/json")
	public Gnews getRemoteData() {
		List<Articles> lstArticles=new ArrayList<Articles>();
		Source source=new Source("Remote+", "https://www.lipsum.com");
		for(int i=0; i<55; i++) {
			Articles article=new Articles("Test Title #".concat(String.valueOf(i)), "Text of Lorem ipsum", loremIpsum, "", urlImageLoremIpsum, "", source);
			lstArticles.add(article);
		}
		
		Gnews data=new Gnews(lstArticles.size(),lstArticles);
		
		return data;
	}
}
