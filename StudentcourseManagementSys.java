package MyPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class StudentcourseManagementSys {
	
	private static final int student_id = 0;	
	private static String course_code = null;

	public static Connection getConnection() {
	 String url = "jdbc:mysql://localhost:3306/details";
	 String username = "root";
	 String password = "hellodeb@2024";
	 Connection connection = null;
	 try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         connection = DriverManager.getConnection(url, username, password);
     } catch (ClassNotFoundException e) {
         System.out.println(e.getMessage());
     }catch(SQLException e1) {
    	 System.out.println(e1.getMessage());
     }
     return connection;
 }


	private static void listCourses() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            String query = "SELECT * FROM course";
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("Available Courses:");

            while (resultSet.next()) {

                System.out.println("Course Code: " + resultSet.getString("course_code"));
                System.out.println("Title: " + resultSet.getString("title"));
                System.out.println("Description: " + resultSet.getString("description"));
                System.out.println("Capacity: " + resultSet.getInt("capacity"));
                System.out.println("Schedule: " + resultSet.getString("schedule"));
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	private static void registration(int student_id,String course_code) throws SQLException {
		try(Connection connection = getConnection()){
			String checkCourse = "select capacity, (select count(*) from registration where course_code = ?) as registration from course where course_code = ?";
			PreparedStatement checkStatement = connection.prepareStatement(checkCourse);
            checkStatement.setString(1, course_code);
            checkStatement.setString(2, course_code);
            ResultSet courseResult = checkStatement.executeQuery();
            if (courseResult.next()) {
                int capacity = courseResult.getInt("capacity");
                int registration = courseResult.getInt("registration");

                if (registration < capacity) {
                    // Register the student
                    String registerQuery = "INSERT INTO registration (student_id, course_code) VALUES (?, ?)";
                    PreparedStatement registerStatement = connection.prepareStatement(registerQuery);
                    registerStatement.setInt(1, student_id);
                    registerStatement.setString(2, course_code);
                    registerStatement.executeUpdate();

                    System.out.println("Student registered successfully!");
                } else {
                    System.out.println("No slots available for this course.");
                }
            } else {
                System.out.println("Course not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
    private static void removeCourse(int student_id, String course_code) {
        try (Connection connection = getConnection()) {

            String removeQuery = "DELETE FROM registration WHERE student_id=? AND course_code =?";
            PreparedStatement removeStatement = connection.prepareStatement(removeQuery);
            removeStatement.setInt(1, student_id);
            removeStatement.setString(2, course_code);
            int rowsAffected = removeStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Course dropped successfully!");
            } else {
                System.out.println("No registration found for the given course.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void listStudentRegistrations() {
        try (Connection connection = getConnection();
                PreparedStatement listStatement = connection.prepareStatement("SELECT * FROM registration;")) {
 
            
           try( ResultSet resultSet = listStatement.executeQuery()){

            System.out.println("Students registered for id " + student_id + ":");
            System.out.println("-------------------------------");
            System.out.println("| Student ID | Course Code |");
            System.out.println("-------------------------------");       	       

            while (resultSet.next()) {
                System.out.println("| " + resultSet.getInt("student_id") + " | " + resultSet.getString("course_code") + " |");
            }

                System.out.println("-------------------------------");
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }catch(SQLException e) {
    	System.out.println("error prearing statement" + e.getMessage());
    }
 }
    

    public static void main(String[] args) throws SQLException {
    	Scanner sc = new Scanner(System.in);
    	int student_id;
    	int choice;
    	
    	do {
    		 System.out.println("\n--- Course Management System ---");
             System.out.println("1. List Available Courses");
             System.out.println("2. Register Student for a Course");
             System.out.println("3. Remove Course Registration");
             System.out.println("4. List of student registered.");
             System.out.println("5.Exit");
             System.out.print("Enter your choice: ");
             System.out.println();
             choice = sc.nextInt();
    	
             switch(choice) {
                 case 1:
            	 listCourses();
            	 break;
                 case 2:
                     System.out.print("Enter Student ID: ");
                      student_id = sc.nextInt();
                     System.out.print("Enter Course Code: ");
                     String course_code = sc.next();
                     registration(student_id,course_code);
                     break;

                 case 3:
                     System.out.print("Enter Student ID: ");
                     student_id = sc.nextInt();
                     System.out.print("Enter Course Code: ");
                     course_code = sc.next();
                     removeCourse(student_id, course_code);
                     break;
                     
                 case 4:    
                                                        	
                    listStudentRegistrations();
               	    break;
               	    
                 case 5:
                     System.out.println("Exiting...");
                     break;
                 
                 default:
                     System.out.println("Invalid choice.try again");
            	    }   	
    }while(choice != 5);
    		 }
}


	

