# Final Year Project

## Group Members:
### Christopher Weir - G00309429
### Gareth Lynskey - G00312651
### Patrick Griffin - G00314635
### Paul Dolan - G00297086

## Introduction
Throughout our three years of studying at Galway-Mayo Institute of Technology, we were embraced with the pressure of multiple Projects, Assessments, with struggled deadlines and trying to keep track of a list of different day to day tasks to be completed, whether college orientated or not. We have concluded that students need a more mobile and more accessible online platform for their academic organisation. Rather than having sheets of paper with lists or timetables we have created a system that incorporates numerous services for all students ranging from leaving cert level to third level students. <br>
In order to get a sense of what we should incorporate into our system, we looked at what websites and mobile applications are currently available that offer college organisational support for students to use. Based on our findings we created a plan for our system. We used what we found to be the most suitable features from these websites and mobiles applications, along with introducing our own features that we believe would enhance the user experience of our system.<br>
We wanted to create a way for students to track events, manage their college timetable, and keep track of assignments. We also understood that it is important for students to keep track of their college performance along with their overall Grade Point Average (GPA). Our plan was to have six key features which were a Calendar, Timetable, To-do List, Assignments Tracker, and a Modules Tracker to track your current progress within each Module. <br>
Upon planning what we each wished to achieve from the creation of this system, we settle on gaining greater knowledge of the Java programming language. After further deliberation from our meetings we then decided that this system should be created as a web application developed in J2EE which is Java 2 Platform, Enterprise Edition. J2EE provides the types of services that are necessary to build large scale, distributed, component based, multi-tier applications. Learning J2EE was new to us in the sense of using JSP, Servlets and incorporating other technologies to build this web application. <br>

## Context
The general context of this project is a system that provides several different but relative services to students to help them through their time at college. Students will be able to create an account through a simple registration process and they will even be able to customise their own profile. Student can create event on the calendar to help plan projects, etc. These events can be customised by colour coding for example and events can be displayed on three different views: Monthly, Weekly, and Daily. Students can create their own unique timetable for as we know personally, new timetables can be difficult to remember and adjust to. When a student has activities they must do, whether that be homework or extracurricular activities, they can keep track of these on their todo list and simply mark them off as they are completed. Students will be able to track their current academic progress by creating their modules and adding their results from projects or assessments to their respective modules. By doing this the students current progress for each module is calculated, along with their total Grade Point Average (GPA). Student can also monitor their assignments for each module that they have created. The students’ data is securely store on MLabs MongoDB and PostgreSQL databases which are hosted on Heroku. The application is secured using authentication codes allowing for no outside access to the application unless you are logged in.

## Technologies Used
- Development Environment
  - Eclipse EE
  - Heroku CLI
  - GitHub
  - JUnit
  - Selenium
- Java EE (Java Platform, Enterprise Edition)
  - JSP (JavaServer Pages)
  - Servlets
  - XML (Extensible Markup Language)
  - Apache Tomcat
- Heroku
  - MongoDB
  - PostgreSQL
- Bootstrap
- JQuery
- JavaScript
- CSS (Cascading Style Sheets)

## Deployment
**Deployment in web browser:** <br>
Navigate to http://collegeplannerfyp.herokuapp.com

**Deployment in Eclipse:**
- Make sure you have [Eclipse Jee](http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/keplersr2) downloaded.
- You may need to import the latest version of [Apache Tomcat](http://tomcat.apache.org/) into the project on Eclipse to run the server.
- Download the project from GitHub.
- Import the project into Eclipse.
- Run the Server.
- Run any JSP page and it will navigate you to the web application.

## Example User
**User:** TestUser <br>
**Password:** password

