package ie.gmit.sw.Connections;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import ie.gmit.sw.Calendar.CalendarValues;
import ie.gmit.sw.Timetable.Module;

public class MongoConnection {
	private String defaultImage = "https://www.barfoot.co.nz/images/noprofile-big.png";
	private String image;

	//Register
	//=================================================
	public void setNewUser(String code) {
		final BasicDBObject[] data = createUserData(code, defaultImage);
		MongoClientURI uri = new MongoClientURI("mongodb://Chris:G00309429@ds055945.mlab.com:55945/heroku_nhl6qjlh");
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());
		DBCollection user = db.getCollection("User");
		user.insert(data);
		client.close();
	}

	//Profile
	//=================================================
	public void setUserData(String code, String file) {
		MongoClientURI uri = new MongoClientURI("mongodb://Chris:G00309429@ds055945.mlab.com:55945/heroku_nhl6qjlh");
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

	public void removeUserData(String code) {
		MongoClientURI uri = new MongoClientURI("mongodb://Chris:G00309429@ds055945.mlab.com:55945/heroku_nhl6qjlh");
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());
		DBCollection user = db.getCollection("User");
		BasicDBObject document = new BasicDBObject();
		document.put("Confirmation Code", code);
		user.remove(document);
		client.close();
	}

	public String getUserImage(String code) {
		MongoClientURI uri = new MongoClientURI("mongodb://Chris:G00309429@ds055945.mlab.com:55945/heroku_nhl6qjlh");
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());
		DBCollection user = db.getCollection("User");
		BasicDBObject query = new BasicDBObject();
		query.put("Confirmation Code", code);
		DBCursor cursor = user.find(query);
		while (cursor.hasNext()) {
			image = (String) cursor.next().get("Image");
		}
		client.close();
		return image;
	}

	private BasicDBObject[] createUserData(String code, String file) {
		BasicDBObject ImageDetails = new BasicDBObject();
		ImageDetails.put("Confirmation Code", code);
		ImageDetails.put("Image", file);
		final BasicDBObject[] data = { ImageDetails };
		return data;
	}

	//Calendar
	//=================================================
	public void setCalendar(String code, CalendarValues cal) {
		MongoClientURI uri = new MongoClientURI("mongodb://Chris:G00309429@ds055945.mlab.com:55945/heroku_nhl6qjlh");
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());
		DBCollection user = db.getCollection("Calendar");
		BasicDBObject document = new BasicDBObject();
		document.put("Confirmation Code", code);
		document.put("Title", cal.getTitle());
		document.put("Start", cal.getStart());
		document.put("Finish", cal.getEnd());
		user.insert(document);
		client.close();
	}

	public void deleteCalendar(String code, CalendarValues cal) {
		MongoClientURI uri = new MongoClientURI("mongodb://Chris:G00309429@ds055945.mlab.com:55945/heroku_nhl6qjlh");
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());
		DBCollection user = db.getCollection("Calendar");
		BasicDBObject document = new BasicDBObject();
		document.put("Confirmation Code", code);
		document.put("Title", cal.getTitle());
		document.put("Start", cal.getStart());
		document.put("Finish", cal.getEnd());
		user.remove(document);
		client.close();
	}

	public List getCalender(String code) {
		MongoClientURI uri = new MongoClientURI("mongodb://Chris:G00309429@ds055945.mlab.com:55945/heroku_nhl6qjlh");
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());
		DBCollection user = db.getCollection("Calendar");
		BasicDBObject query = new BasicDBObject();
		query.put("Confirmation Code", code);
		DBCursor cursor = user.find(query);

		ArrayList<String[]> l = new ArrayList<String[]>();
		if(cursor.hasNext()) {
			for (DBObject dbObject : cursor) {
				String title = (String) dbObject.get("Title");
				String start = (String) dbObject.get("Start");
				String finish = (String) dbObject.get("Finish");
				String[] s = new String[3];
				s[0] = title;
				s[1] = start;
				s[2] = finish;
				l.add(s);
			}
		}
		client.close();
		return l;
	}

	//ToDo List
	//=================================================
	public void setTodoList(String code, String title, String description) {
		MongoClientURI uri = new MongoClientURI("mongodb://Chris:G00309429@ds055945.mlab.com:55945/heroku_nhl6qjlh");
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());
		DBCollection user = db.getCollection("ToDo");
		BasicDBObject document = new BasicDBObject();
		document.put("Confirmation Code", code);
		document.put("Title", title);
		document.put("Desc", description);
		user.insert(document);
		client.close();
	}

	public List getTodoList(String code) {
		MongoClientURI uri = new MongoClientURI("mongodb://Chris:G00309429@ds055945.mlab.com:55945/heroku_nhl6qjlh");
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());
		DBCollection user = db.getCollection("ToDo");
		BasicDBObject query = new BasicDBObject();
		query.put("Confirmation Code", code);
		DBCursor cursor = user.find(query);

		ArrayList<String[]> l = new ArrayList<String[]>();
		if(cursor.hasNext()) {
			for (DBObject dbObject : cursor) {
				String title = (String) dbObject.get("Title");
				String desc = (String) dbObject.get("Desc");
				String[] s = new String[2];
				s[0] = title;
				s[1] = desc;
				l.add(s);
				System.out.println(title +" " + desc);
			}
		}
		client.close();
		return l;
	}
//Timetable
//=================================================
	public void setTimetable(String code, Module module) {
		MongoClientURI uri = new MongoClientURI("mongodb://Chris:G00309429@ds055945.mlab.com:55945/heroku_nhl6qjlh");
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());
		DBCollection user = db.getCollection("Timetable");
		BasicDBObject document = new BasicDBObject();
		document.put("Confirmation Code", code);
		document.put("Title", module.getTitle());
		document.put("Start", module.getTimeStart());
		document.put("End", module.getTimeEnd());
		document.put("Day", module.getDay());
		document.put("Room", module.getRoom());
		user.insert(document);
		client.close();
	}
	public List getTimetable(String code) {
		MongoClientURI uri = new MongoClientURI("mongodb://Chris:G00309429@ds055945.mlab.com:55945/heroku_nhl6qjlh");
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());
		DBCollection user = db.getCollection("Timetable");
		BasicDBObject query = new BasicDBObject();
		query.put("Confirmation Code", code);
		DBCursor cursor = user.find(query);

		ArrayList<String[]> l = new ArrayList<String[]>();
		if(cursor.hasNext()) {
			for (DBObject dbObject : cursor) {
				String title = (String) dbObject.get("Title");
				System.out.println(title);
				String start = dbObject.get("Start").toString();
				System.out.println(start);
				String end = (String) dbObject.get("End").toString();
				System.out.println(end);
				String day = (String) dbObject.get("Day").toString();
				System.out.println(day);
				String room = (String) dbObject.get("Room");
				System.out.println(room);
				String[] s = new String[5];
				s[0] = title;
				s[1] = start;
				s[2] = end;
				s[3] = day;
				s[4] = room;
				l.add(s);
			}
		}
		client.close();
		return l;
	}
}