package ie.gmit.sw.Connections;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import ie.gmit.sw.Calendar.CalendarValues;
import ie.gmit.sw.Modules.Module;
import ie.gmit.sw.Modules.ModuleDetails;
import ie.gmit.sw.Timetable.TimetableModule;

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
		BasicDBObject document = new BasicDBObject();
		document.put("Confirmation Code", code);

		DBCollection user = db.getCollection("User");
		user.remove(document);

		DBCollection user1 = db.getCollection("Calendar");
		user1.remove(document);

		DBCollection user2 = db.getCollection("Timetable");
		user2.remove(document);

		DBCollection user3 = db.getCollection("ToDo");
		user3.remove(document);
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
		document.put("StartTime", cal.getStartTime());
		document.put("EndTime", cal.getEndTime());
		document.put("Color", cal.getColor());
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
		document.put("StartTime", cal.getStartTime());
		document.put("EndTime", cal.getEndTime());
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
				String startTime = (String) dbObject.get("StartTime");
				String endTime = (String) dbObject.get("EndTime");
				String color = (String) dbObject.get("Color");
				String[] s = new String[6];
				s[0] = title;
				s[1] = start;
				s[2] = finish;
				s[3] = startTime;
				s[4] = endTime;
				s[5] = color;
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

	public void deleteToDo(String code, String title, String desc) {
		MongoClientURI uri = new MongoClientURI("mongodb://Chris:G00309429@ds055945.mlab.com:55945/heroku_nhl6qjlh");
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());
		DBCollection user = db.getCollection("ToDoCompleted");
		BasicDBObject document = new BasicDBObject();
		document.put("Confirmation Code", code);
		document.put("Title", title);
		document.put("Desc", desc);
		user.remove(document);
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
			}
		}
		client.close();
		return l;
	}

	public void taskCompleted(String code, String title, String desc) {
		MongoClientURI uri = new MongoClientURI("mongodb://Chris:G00309429@ds055945.mlab.com:55945/heroku_nhl6qjlh");
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());
		DBCollection user = db.getCollection("ToDo");
		BasicDBObject document = new BasicDBObject();
		document.put("Confirmation Code", code);
		document.put("Title", title);
		document.put("Desc", desc);
		user.remove(document);
		client.close();
	}

	public void setTaskCompleted(String code, String title, String desc) {
		MongoClientURI uri = new MongoClientURI("mongodb://Chris:G00309429@ds055945.mlab.com:55945/heroku_nhl6qjlh");
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());
		DBCollection user = db.getCollection("ToDoCompleted");
		BasicDBObject document = new BasicDBObject();
		document.put("Confirmation Code", code);
		document.put("Title", title);
		document.put("Desc", desc);
		user.insert(document);
		client.close();
	}

	public List getTaskCompleted(String code){
		MongoClientURI uri = new MongoClientURI("mongodb://Chris:G00309429@ds055945.mlab.com:55945/heroku_nhl6qjlh");
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());
		DBCollection user = db.getCollection("ToDoCompleted");
		BasicDBObject query = new BasicDBObject();
		query.put("Confirmation Code", code);
		DBCursor cursor = user.find(query);

		ArrayList<String[]> c = new ArrayList<String[]>();
		if(cursor.hasNext()) {
			for (DBObject dbObject : cursor) {
				String title = (String) dbObject.get("Title");
				String desc = (String) dbObject.get("Desc");
				String[] s = new String[2];
				s[0] = title;
				s[1] = desc;
				c.add(s);
			}
		}
		client.close();
		return c;
	}

	public void deleteCompletedTask(String code, String title, String description) {
		MongoClientURI uri = new MongoClientURI("mongodb://Chris:G00309429@ds055945.mlab.com:55945/heroku_nhl6qjlh");
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());
		DBCollection user = db.getCollection("ToDoCompleted");
		BasicDBObject document = new BasicDBObject();
		document.put("Confirmation Code", code);
		document.put("Title", title);
		document.put("Desc", description);
		user.remove(document);
		client.close();
	}

	//Timetable
	//=================================================
	public void setTimetable(String code, TimetableModule module) {
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
				String start = dbObject.get("Start").toString();
				String end = (String) dbObject.get("End").toString();
				String day = (String) dbObject.get("Day").toString();
				String room = (String) dbObject.get("Room");
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

	//Modules
	//=================================================
	public void setModule(String code, String title, String lecturer) {
		MongoClientURI uri = new MongoClientURI("mongodb://Chris:G00309429@ds055945.mlab.com:55945/heroku_nhl6qjlh");
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());
		DBCollection user = db.getCollection("Modules");
		BasicDBObject document = new BasicDBObject();
		document.put("Confirmation Code", code);
		document.put("Title", title);
		document.put("Lecturer", lecturer);
		
		List<BasicDBObject> moduleGrades = new ArrayList<BasicDBObject>();
		document.put("Grades", moduleGrades);
		user.insert(document);
		client.close();
	}

	public List getModules(String code) {
		List moduleList = new ArrayList<>();
		MongoClientURI uri = new MongoClientURI("mongodb://Chris:G00309429@ds055945.mlab.com:55945/heroku_nhl6qjlh");
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());
		DBCollection user = db.getCollection("Modules");
		BasicDBObject query = new BasicDBObject();
		query.put("Confirmation Code", code);
		DBCursor cursor = user.find(query);

		if(cursor.hasNext()) {
			for (DBObject dbObject : cursor) {
				String title = (String) dbObject.get("Title");
				String lecturer = (String) dbObject.get("Lecturer");
				String[] s = new String[3];
				s[0] = title;
				s[1] = lecturer;
				moduleList.add(s);
			}
		}
		return moduleList;
	}

	public void removeModule(String code, TimetableModule removeModule) {
		MongoClientURI uri = new MongoClientURI("mongodb://Chris:G00309429@ds055945.mlab.com:55945/heroku_nhl6qjlh");
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());
		DBCollection user = db.getCollection("Timetable");
		BasicDBObject document = new BasicDBObject();
		document.put("Confirmation Code", code);
		document.put("Title", removeModule.getTitle());
		document.put("Start", removeModule.getTimeStart());
		document.put("End", removeModule.getTimeEnd());
		document.put("Day", removeModule.getDay());
		document.put("Room", removeModule.getRoom());
		user.remove(document);
		client.close();
	}	

	public void setModuleGrades(String code, String title, String gradeTitle, String date, String value, String result){
		MongoClientURI uri = new MongoClientURI("mongodb://Chris:G00309429@ds055945.mlab.com:55945/heroku_nhl6qjlh");
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());
		DBCollection user = db.getCollection("Modules");
		
		BasicDBObject query = new BasicDBObject();
		query.put("Confirmation Code", code);
		query.put("Title", title);
		
		BasicDBObject grade = new BasicDBObject();
		grade.put("ModuleTitle", title);
		grade.put("Title", gradeTitle);
		grade.put("Date", date);
		grade.put("Value", value);
		grade.put("Result", result);
		
		user.update(query, new BasicDBObject("$push", new BasicDBObject("Grades", grade)), true, false);
		client.close();
		}

	public List getModuleGrades(String code) {
		List moduleGradesList = new ArrayList<>();
		MongoClientURI uri = new MongoClientURI("mongodb://Chris:G00309429@ds055945.mlab.com:55945/heroku_nhl6qjlh");
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());
		DBCollection user = db.getCollection("Modules");
		BasicDBObject query = new BasicDBObject();
		query.put("Confirmation Code", code);
		
		BasicDBObject fields = new BasicDBObject("Grades",1).append("_id",false);
		DBCursor cursor = user.find(query, fields);

		if(cursor.hasNext()) {
			for (DBObject dbObject : cursor) {
				
				 BasicDBList grades = (BasicDBList) dbObject.get("Grades");
				  BasicDBObject[] gradeArr = grades.toArray(new BasicDBObject[0]);
				  for(BasicDBObject dbObj : gradeArr) {
					
					String title = (String) dbObj.get("ModuleTitle");
				    String gradeTitle = (String) dbObj.get("Title");
				    String Date = (String) dbObj.get("Date");
				    String Value = (String) dbObj.get("Value");
				    String Result = (String) dbObj.get("Result");
				    String s[] = new String[6];
				   
				    s[0] = title;
					s[1] = gradeTitle;
					s[2] = Date;
					s[3] = Value;
					s[4] = Result;
					moduleGradesList.add(s);
					
				  }
			}
		}
		client.close();
		return moduleGradesList;
	}

	public void deleteModule(String code, Module module) {
		MongoClientURI uri = new MongoClientURI("mongodb://Chris:G00309429@ds055945.mlab.com:55945/heroku_nhl6qjlh");
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());
		DBCollection user = db.getCollection("Modules");
		BasicDBObject document = new BasicDBObject();
		document.put("Confirmation Code", code);
		document.put("Title", module.getTitle());
		document.put("Lecturer", module.getLecturer());
		user.remove(document);
		client.close();
	}
}