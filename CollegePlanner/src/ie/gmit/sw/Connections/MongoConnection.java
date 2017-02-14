package ie.gmit.sw.Connections;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoConnection {
	private String defaultImage = "https://www.barfoot.co.nz/images/noprofile-big.png";
	
	public void setNewUser(String code){
		final BasicDBObject[] data = createUserData(code, defaultImage);
		MongoClientURI uri  = new MongoClientURI("mongodb://Chris:G00309429@ds055945.mlab.com:55945/heroku_nhl6qjlh"); 
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());
		DBCollection user = db.getCollection("User");
		user.insert(data);
		client.close();
	}
	
	public void setUserDatabase(String code, String file){
		MongoClientURI uri  = new MongoClientURI("mongodb://Chris:G00309429@ds055945.mlab.com:55945/heroku_nhl6qjlh"); 
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());
		DBCollection user = db.getCollection("User");
		final BasicDBObject[] data = createUserData(code, file);
		BasicDBObject document = new BasicDBObject();
		document.put("Confirmation Code", code);
		user.remove(document);
		user.insert(data);
		client.close();
	}
	
	public BasicDBObject[] createUserData(String code, String file){

		BasicDBObject ImageDetails = new BasicDBObject();

		ImageDetails.put("Confirmation Code", code);
		ImageDetails.put("Image", file);

		final BasicDBObject[] data = {ImageDetails};

		return data;
	}
}