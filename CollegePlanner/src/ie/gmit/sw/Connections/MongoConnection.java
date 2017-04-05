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
import ie.gmit.sw.Timetable.TimetableModule;

/**
 * @author Christopher Weir - G00309429, Gareth Lynskey - G00312651, Paul Dolan - G00297086, Patrick Griffin - G00314635
 * 
 * MongoConnection handles all the requests to and from the MongoDB database on heroku.
 *
 */
public class MongoConnection {
	//The default image to be used when a user registers
	private String defaultImage = "https://www.barfoot.co.nz/images/noprofile-big.png";
	//THe image string to hold the new image
	private String image;
	//The address of the server on heroku
	private String mongoAddress = "mongodb://Chris:G00309429@ds055945.mlab.com:55945/heroku_nhl6qjlh";

	//Register
	//=================================================
	/**
	 * setNewUser() creates a new entry in the database for a new user and assigns the default user image.
	 * 
	 * @param code
	 */
	public void setNewUser(String code) {
		//Create a new BasicDBOject by calling createUserData() with the session code and the default image
		final BasicDBObject[] data = createUserData(code, defaultImage);
		MongoClientURI uri = new MongoClientURI(mongoAddress);
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());
		DBCollection user = db.getCollection("User");
		user.insert(data);
		client.close();
	}

	//Profile
	//=================================================
	/**
	 * setUserData() removes a users image and replaces it with the a new image.
	 * 
	 * @param code
	 * @param file
	 */
	public void setUserData(String code, String file) {
		MongoClientURI uri = new MongoClientURI(mongoAddress);
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

	/**
	 * removeUserData() deletes all data belonging to a user from the database.
	 * 
	 * @param code
	 */
	public void removeUserData(String code) {
		MongoClientURI uri = new MongoClientURI(mongoAddress);
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
		
		DBCollection user4 = db.getCollection("Modules");
		user4.remove(document);
		
		DBCollection user5 = db.getCollection("ToDoCompleted");
		user5.remove(document);
		
		client.close();
	}

	/**
	 * getUserImage() retrieves the users image from the database.
	 * 
	 * @param code
	 * @return
	 */
	public String getUserImage(String code) {
		MongoClientURI uri = new MongoClientURI(mongoAddress);
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

	/**
	 * createUserData() creates a new BasicDBObject with the session code and image file.
	 * 
	 * @param code
	 * @param file
	 * @return
	 */
	private BasicDBObject[] createUserData(String code, String file) {
		BasicDBObject ImageDetails = new BasicDBObject();
		ImageDetails.put("Confirmation Code", code);
		ImageDetails.put("Image", file);
		final BasicDBObject[] data = { ImageDetails };
		return data;
	}

	//Calendar
	//=================================================
	/**
	 * setCalendar() adds a new Calendar event in the Calendar collection on the database.
	 * 
	 * @param code
	 * @param cal
	 */
	public void setCalendar(String code, CalendarValues cal) {
		MongoClientURI uri = new MongoClientURI(mongoAddress);
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

	/**
	 * deleteCalendar() removes a Calendar event from the Calendar collection on the database.
	 * 
	 * @param code
	 * @param cal
	 */
	public void deleteCalendar(String code, CalendarValues cal) {
		MongoClientURI uri = new MongoClientURI(mongoAddress);
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

	/**
	 * getCalendar() retrieves the Calendar events from the database.
	 * 
	 * @param code
	 * @return calendar events
	 */
	public List getCalender(String code) {
		MongoClientURI uri = new MongoClientURI(mongoAddress);
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());
		DBCollection user = db.getCollection("Calendar");
		BasicDBObject query = new BasicDBObject();
		query.put("Confirmation Code", code);
		DBCursor cursor = user.find(query);

		ArrayList<String[]> list = new ArrayList<String[]>();
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
				list.add(s);
			}
		}
		client.close();
		return list;
	}

	//ToDo List
	//=================================================
	/**
	 * 
	 * setTodoList() adds a Todo entry to the ToDo collection on the database.
	 * @param code
	 * @param title
	 * @param description
	 */
	public void setTodoList(String code, String title, String description) {
		MongoClientURI uri = new MongoClientURI(mongoAddress);
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

	/**
	 * deleteToDo() removes a Todo entry from the ToDo collection on the database.
	 * 
	 * @param code
	 * @param title
	 * @param desc
	 */
	public void deleteToDo(String code, String title, String desc) {
		MongoClientURI uri = new MongoClientURI(mongoAddress);
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

	/**
	 * getTodoList() gets the list of Todo entries from the database.
	 * 
	 * @param code
	 * @return todo list
	 */
	public List getTodoList(String code) {
		MongoClientURI uri = new MongoClientURI(mongoAddress);
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());
		DBCollection user = db.getCollection("ToDo");
		BasicDBObject query = new BasicDBObject();
		query.put("Confirmation Code", code);
		DBCursor cursor = user.find(query);

		ArrayList<String[]> list = new ArrayList<String[]>();
		if(cursor.hasNext()) {
			for (DBObject dbObject : cursor) {
				String title = (String) dbObject.get("Title");
				String desc = (String) dbObject.get("Desc");
				String[] s = new String[2];
				s[0] = title;
				s[1] = desc;
				list.add(s);
			}
		}
		client.close();
		return list;
	}

	/**
	 * taskCompleted() removes a Todo entry that has been completed, from the database.
	 * 
	 * @param code
	 * @param title
	 * @param desc
	 */
	public void taskCompleted(String code, String title, String desc) {
		MongoClientURI uri = new MongoClientURI(mongoAddress);
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

	/**
	 * setTaskCompleted() adds a completed Todo entry to the ToDoCompleted collection on the database.
	 * 
	 * @param code
	 * @param title
	 * @param desc
	 */
	public void setTaskCompleted(String code, String title, String desc) {
		MongoClientURI uri = new MongoClientURI(mongoAddress);
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

	/**
	 * getTaskCompleted() gets a list of all the completed Todo entries from the database.
	 * 
	 * @param code
	 * @return completed Todo list
	 */
	public List getTaskCompleted(String code){
		MongoClientURI uri = new MongoClientURI(mongoAddress);
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());
		DBCollection user = db.getCollection("ToDoCompleted");
		BasicDBObject query = new BasicDBObject();
		query.put("Confirmation Code", code);
		DBCursor cursor = user.find(query);

		ArrayList<String[]> list = new ArrayList<String[]>();
		if(cursor.hasNext()) {
			for (DBObject dbObject : cursor) {
				String title = (String) dbObject.get("Title");
				String desc = (String) dbObject.get("Desc");
				String[] s = new String[2];
				s[0] = title;
				s[1] = desc;
				list.add(s);
			}
		}
		client.close();
		return list;
	}

	/**
	 * deleteCompletedTask() removes the completed Todo entry from the database.
	 * 
	 * @param code
	 * @param title
	 * @param description
	 */
	public void deleteCompletedTask(String code, String title, String description) {
		MongoClientURI uri = new MongoClientURI(mongoAddress);
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
	/**
	 * setTimetable() adds a new timetable entry to the Timetable collection on the database.
	 * 
	 * @param code
	 * @param module
	 */
	public void setTimetable(String code, TimetableModule module) {
		MongoClientURI uri = new MongoClientURI(mongoAddress);
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
	
	/**
	 * getTimetable() returns a list of Timetable entries from the database.
	 * 
	 * @param code
	 * @return Timetable list
	 */
	public List getTimetable(String code) {
		MongoClientURI uri = new MongoClientURI(mongoAddress);
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());
		DBCollection user = db.getCollection("Timetable");
		BasicDBObject query = new BasicDBObject();
		query.put("Confirmation Code", code);
		DBCursor cursor = user.find(query);

		ArrayList<String[]> list = new ArrayList<String[]>();
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
				list.add(s);
			}
		}
		client.close();
		return list;
	}

	//Modules
	//=================================================
	/**
	 * setModule() adds a new module to the Modules collection on the database.
	 * 
	 * @param code
	 * @param title
	 * @param lecturer
	 */
	public void setModule(String code, String title, String lecturer) {
		MongoClientURI uri = new MongoClientURI(mongoAddress);
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());
		DBCollection user = db.getCollection("Modules");
		BasicDBObject document = new BasicDBObject();
		document.put("Confirmation Code", code);
		document.put("Title", title);
		document.put("Lecturer", lecturer);

		List<BasicDBObject> moduleGrades = new ArrayList<BasicDBObject>();
		document.put("Grades", moduleGrades);

		List<BasicDBObject> moduleAssignments = new ArrayList<BasicDBObject>();
		document.put("Assignments", moduleAssignments);

		user.insert(document);
		client.close();
	}

	/**
	 * getModules() returns a list of Modules from the database.
	 * 
	 * @param code
	 * @return Module list
	 */
	public List getModules(String code) {
		List moduleList = new ArrayList<>();
		MongoClientURI uri = new MongoClientURI(mongoAddress);
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
		client.close();
		return moduleList;
	}

	/**
	 * removeModule() removes a module from the Modules collection on the database.
	 * 
	 * @param code
	 * @param removeModule
	 */
	public void removeModule(String code, TimetableModule removeModule) {
		MongoClientURI uri = new MongoClientURI(mongoAddress);
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

	/**
	 * setModuleGrades() adds a new Grade entry subdocument to a Module on the database.
	 * 
	 * @param code
	 * @param title
	 * @param gradeTitle
	 * @param date
	 * @param value
	 * @param result
	 */
	public void setModuleGrades(String code, String title, String gradeTitle, String date, String value, String result){
		MongoClientURI uri = new MongoClientURI(mongoAddress);
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

	/**
	 * getModuleGrades() returns a list of Grades from each Module in the database.
	 * 
	 * @param code
	 * @return list of Grades
	 */
	public List getModuleGrades(String code) {
		List moduleGradesList = new ArrayList<>();
		MongoClientURI uri = new MongoClientURI(mongoAddress);
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

	/**
	 * getModuleAssignments() returns a list of Assignments from each Module in the database.
	 * 
	 * @param code
	 * @return list of assignments
	 */
	public List getModuleAssignments(String code) {
		List moduleAssignmentsList = new ArrayList<>();
		MongoClientURI uri = new MongoClientURI(mongoAddress);
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());
		DBCollection user = db.getCollection("Modules");
		BasicDBObject query = new BasicDBObject();
		query.put("Confirmation Code", code);

		BasicDBObject fields = new BasicDBObject("Assignments",1).append("_id",false);
		DBCursor cursor = user.find(query, fields);
		if(cursor.hasNext()) {
			for (DBObject dbObject : cursor) {
				BasicDBList assignments = (BasicDBList) dbObject.get("Assignments");
				BasicDBObject[] assignmentsArr = assignments.toArray(new BasicDBObject[0]);

				for(BasicDBObject dbObj : assignmentsArr) {
					String title = (String) dbObj.get("ModuleTitle");
					String assignmentsTitle = (String) dbObj.get("Title");
					String Date = (String) dbObj.get("Date");
					String Value = (String) dbObj.get("Value");
					String s[] = new String[6];

					s[0] = title;
					s[1] = assignmentsTitle;
					s[2] = Date;
					s[3] = Value;
					moduleAssignmentsList.add(s);
				}
			}
		}
		client.close();
		return moduleAssignmentsList;
	}

	/**
	 * deleteModule() removes a Module from the database.
	 * 
	 * @param code
	 * @param module
	 */
	public void deleteModule(String code, Module module) {
		MongoClientURI uri = new MongoClientURI(mongoAddress);
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

	/**
	 * deleteGrade() removes a Grade entry from a Module on the database.
	 * 
	 * @param code
	 * @param moduleTitle
	 * @param gradeTitle
	 * @param gradeDate
	 * @param gradeValue
	 * @param gradeResult
	 */
	public void deleteGrade(String code, String moduleTitle,String gradeTitle, String gradeDate, String gradeValue, String gradeResult) {
		MongoClientURI uri = new MongoClientURI(mongoAddress);
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());
		DBCollection user = db.getCollection("Modules");

		BasicDBObject query = new BasicDBObject();
		query.put("Confirmation Code", code);

		BasicDBObject grade = new BasicDBObject();
		grade.put("ModuleTitle", moduleTitle);
		grade.put("Title", gradeTitle);
		grade.put("Date", gradeDate);
		grade.put("Value", gradeValue);
		grade.put("Result", gradeResult);

		user.update(query, new BasicDBObject("$pull", new BasicDBObject("Grades", grade)), true, true);
		client.close();
	}

	/**
	 * setModuleAssignment() adds an Assignment entry subdocument to a Module on the database.
	 * 
	 * @param code
	 * @param moduleTitle
	 * @param title
	 * @param date
	 * @param value
	 */
	public void setModuleAssignment(String code, String moduleTitle, String title, String date, String value) {
		MongoClientURI uri = new MongoClientURI(mongoAddress);
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());
		DBCollection user = db.getCollection("Modules");

		BasicDBObject query = new BasicDBObject();
		query.put("Confirmation Code", code);
		query.put("Title", moduleTitle);

		BasicDBObject assignment = new BasicDBObject();
		assignment.put("ModuleTitle", moduleTitle);
		assignment.put("Title", title);
		assignment.put("Date", date);
		assignment.put("Value", value);

		user.update(query, new BasicDBObject("$push", new BasicDBObject("Assignments", assignment)), true, false);
		client.close();
	}

	/**
	 * deleteAssignment() removes and Assignment entry from a Module on the database.
	 * 
	 * @param code
	 * @param moduleTitle
	 * @param title
	 * @param date
	 * @param value
	 */
	public void deleteAssignment(String code, String moduleTitle, String title, String date, String value) {
		MongoClientURI uri = new MongoClientURI(mongoAddress);
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());
		DBCollection user = db.getCollection("Modules");

		BasicDBObject query = new BasicDBObject();
		query.put("Confirmation Code", code);

		BasicDBObject grade = new BasicDBObject();
		grade.put("ModuleTitle", moduleTitle);
		grade.put("Title", title);
		grade.put("Date", date);
		grade.put("Value", value);

		user.update(query, new BasicDBObject("$pull", new BasicDBObject("Assignments", grade)), true, true);
		client.close();
	}
}