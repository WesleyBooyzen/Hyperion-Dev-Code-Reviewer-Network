// import
import java.util.*;
import java.io.*;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
public class ProjectMain {

	// main class
    public static void main(String[] args) throws Exception {

    	// create ArrayList
        ArrayList<Project> ProjectArray = new ArrayList<Project>();
        ArrayList<Person> people = new ArrayList<Person>();
        String projectsContents;
		String peopleContents;
		Project projectToUpdate;
		String projectArgument;
		String personArgument;
        
        Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/poised?allowPublicKeyRetrieval=true&useSSL=false", "root",
				"BooyzenW589");

		// Create a direct line to the database for running our queries
		Statement statement = connection.createStatement();
		ResultSet activeProjects;
		ResultSet peopleList;
		int rowsAffected;

		// Create ArrayList of projects by reading from contents of PoisedPMS database
		peopleList = statement.executeQuery("SELECT * FROM person");

		while (peopleList.next()) {
			peopleContents = peopleList.getString("designation");
			peopleContents += ", " + peopleList.getString("name");
			peopleContents += ", " + peopleList.getString("number");
			peopleContents += ", " + peopleList.getString("email");
			peopleContents += ", " + peopleList.getString("address");

			people.add(new Person(peopleContents));
		}

		activeProjects = statement.executeQuery("SELECT * FROM projects");

		while (activeProjects.next()) {
			projectsContents = activeProjects.getString("projectnumber");
			projectsContents += ", " + activeProjects.getString("buildingtype");
			projectsContents += ", " + activeProjects.getString("customer");
			projectsContents += ", " + activeProjects.getString("projectname");
			projectsContents += ", " + activeProjects.getString("buildingaddress");
			projectsContents += ", " + activeProjects.getString("erfnumber");
			projectsContents += ", " + activeProjects.getBigDecimal("totalfee").toString();
			projectsContents += ", " + activeProjects.getBigDecimal("totalpaid").toString();
			projectsContents += ", " + activeProjects.getString("deadline");
			projectsContents += ", " + activeProjects.getString("architect");
			projectsContents += ", " + activeProjects.getString("projectmanager");
			projectsContents += ", " + activeProjects.getString("structuralengineer");
			projectsContents += ", " + activeProjects.getString("completed");

			ProjectArray.add(new Project(projectsContents, people));
		}

        // create Scanner for input
        Scanner input = new Scanner(System.in);

        while (true) {
        	// display menu to user to choose what user wants to do
            System.out.println("Please make a selection from the menu:" 
					        	+ "\n1. Create a new project:"
					            + "\n2. Change the due date of the project:" 
					        	+ "\n3. Change the total number of the fee paid:"
					            + "\n4. Update contractors contact details" 
					        	+ "\n5. View completed projects"
					            + "\n6. View uncompleted tasks" 
					        	+ "\n7. Exit:" 
					            + ">>>");

            // create object to handle user choice
            int choice = input.nextInt();
            double totalFee = 0.0;
            double amountPaidDate = 0.0;

            // if user chooses 1, proceed to create project
            if (choice == 1) {
            	

                int projectNumber = 0;
                while (true) {
                    try {
                    	// display relevant message
                        System.out.print("Enter Project Number:\n");
                        projectNumber = input.nextInt();
                        input.nextLine();//clear buffer after taking the inputs before taking the strings
                        break;
                        // catch block if user does not input integers
                    } catch (Exception e) {
                        System.out.println("Enter only numbers!");
                        input.next();
                    }
                }
                // display message to user to input project name
                System.out.println("Enter Project Name:");
                String projectName = input.nextLine();
                // display message to user to input building type
                System.out.println("Enter type of Building:");
                String buildingType = input.nextLine();
                // display message to user to input address of building
                System.out.println("Enter address of Project:");
                String addressOfProject = input.nextLine();

                while (true) {
                    try {
                    	// display message to user to input the total fee of the project
                        System.out.println("Enter total Fee:");
                        totalFee = input.nextDouble();
                        break;
                        // catch block to throw exception message if user does not input integers
                    } catch (Exception e) {
                    	// display error message to user
                        System.out.println("format not correct!!! please try again");
                        input.next();

                    }
                }

                while (true) {
                    try {
                    	// display message to user to input the amount paid by customer
                        System.out.println("Enter total Amount Paid To Date:");
                        amountPaidDate = input.nextDouble();
                        input.nextLine();//clear buffer after taking the inputs before taking the strings
                        break;
                        // catch block to throw exception message to user if user does not input integers
                    } catch (Exception e) {
                    	// display error message to user
                        System.out.println("Enter numbers only!");
                        input.next();
                    }
                }
                // display message to user to input the due date of the project
                System.out.println("Enter due date of the project:");
                String deadline = input.nextLine();
                // set the project completion automatically to "no"
                String projectCompleted = "No";
                // display message to user to input the Architects name
                System.out.println("Enter Architect Name and Surname:");
                String name = input.nextLine();
                int telephoneNumber = 0;
                while (true) {
                    try {
                    	// display message to input the Architect telephone number
                        System.out.println("Enter Architect phone number:");
                        telephoneNumber = input.nextInt();
                        input.nextLine();
                        break;
                        // catch block to throw out error message if user inputs strings rather than integers
                    } catch (Exception e) {
                        System.out.println("format not correct try again");
                        input.next();
                    }
                }
                // display message to user to input the Architect email address
                System.out.println("Enter Architect Email:");
                String emailAddress = (input.nextLine());
                // display message to user to input the Architect address
                System.out.println("Enter Architect address:");
                String physicalAddress = input.nextLine();
                // create a new person class called Architect
                Person Architect = new Person(name, telephoneNumber, emailAddress, physicalAddress);
                // display message to user to input the contractors name
                System.out.println("Enter Contractor Name and Surname");
                String contractorName = input.nextLine();
                int contractorNumber = 0;
                while (true) {
                    try {
                        System.out.println("Enter Contractor phone number");
                        contractorNumber = input.nextInt();
                        input.nextLine();//clear buffer after taking the inputs before taking the strings
                        break;
                    } catch (Exception e) {
                        System.out.println("format not correct try again !!");
                        input.next();
                    }
                }
                System.out.println("Enter Contractor Email:");
                String contractorEmail = input.nextLine();
                System.out.println("Enter Contractor address:");
                String contractorAddress = input.nextLine();
                //Create Contractor
                Person Contractor = new Person(contractorName, contractorNumber, contractorEmail, contractorAddress);
                System.out.println("Enter Customer Name and Surname:");
                String customerName = input.nextLine();
                int customerNumber = 0;
                while (true) {
                    try {
                        System.out.println("Enter Customer number:");
                        customerNumber = input.nextInt();
                        input.nextLine();//clear buffer after taking the inputs before taking the strings
                        break;
                    } catch (Exception e) {
                        System.out.println("Input numbers only!");
                        input.next();
                    }
                }
                System.out.println("Enter Customer Email Address:");
                String customerEmail = input.nextLine();
                System.out.println("Enter Customer address:");
                String customerAddress = input.nextLine();
                if (projectName == "") {
                    projectName = customerName;
                }
                //Create Customer
                Person Customer = new Person(customerName, customerNumber, customerEmail, customerAddress);
                Project newProject = new Project(projectNumber, projectName, buildingType, addressOfProject, totalFee, amountPaidDate, deadline, projectCompleted, Architect, Contractor, Customer);
                ProjectArray.add(newProject);
                System.out.println("\nNew project created!");
                System.out.println(newProject.toString());
                if (projectName.equals("")) {
                    newProject.setProjectName(buildingType + " " + customerName);
                }
                
            	Class.forName("com.mysql.jdbc.Driver");
    			Connection con = DriverManager.getConnection(
    					"jdbc:mysql://localhost:3306/poised","root","BooyzenW589");
    			Statement stmt = con.createStatement();
                
                ResultSet rs = stmt.executeQuery("SELECT*FROM projects");
                
                stmt.executeUpdate("INSERT INTO projects VALUE('"+projectNumber+"','"+projectName+"','"+buildingType+"','"+addressOfProject+"', '"+totalFee+"', '"+amountPaidDate+"', '"+deadline+"', '"+projectCompleted+"')");
                stmt.executeUpdate("INSERT INTO person VALUE('"+name+"', '"+telephoneNumber+"', '"+emailAddress+"', '"+physicalAddress+"', '"+contractorName+"', '"+contractorNumber+"', '"+contractorEmail+"', '"+contractorAddress+"', '"+customerName+"', '"+customerNumber+"', '"+customerEmail+"', '"+customerAddress+"')");
    			System.out.println("Values inserted successfully!");
            }
            if (choice == 2) {

                int number = 0;
                while (true)

                    try {
                        BufferedReader txt = new BufferedReader(new FileReader("Projects.txt"));
                        String str;
                        ArrayList<String> data = new ArrayList<String>();
                        while ((str = txt.readLine()) != null) {

                            data.add(str);
                        }

                        // close the file
                        txt.close();

                        // String lines = "";
                        // display message to user to input the project number he 
                        // wishes to make changes
                        System.out.println("enter index number of the project :");
                        number = input.nextInt();
                        number = number - 1;
                        input.nextLine();
                        // display message to user to input the new due date
                        System.out.println("Enter the new due date for the project:");
                        String due = input.nextLine();

                        // get the index number the user entered from the Array List
                        // to make changes to the specific project
                        String temp = data.get(number);
                        temp = temp.toString();
                        //  System.out.println(data);

                        List<String> temple = Arrays.asList(temp.split(","));
                        temple.set(5, due);

                        String project = temple.toString();
                        project = project.replace("[", "");
                        project = project.replace("]", "");


                        //  temp = temp.replace("No",due);

                        data.set(number, project);

                        //   System.out.println(data.size());
                        
                        Class.forName("com.mysql.jdbc.Driver");
            			Connection con = DriverManager.getConnection(
            					"jdbc:mysql://localhost:3306/poised","root","BooyzenW589");
            			Statement stmt = con.createStatement();
                        
                        ResultSet rs = stmt.executeQuery("SELECT*FROM projects");
                        
                        stmt.executeUpdate("UPDATE projects SET deadline = '"+due+"' WHERE projectNumber = '"+number+"')");
            			System.out.println("Values updated successfully!");


                        break;

                    } catch (Exception e) {

                        System.out.println("Please enter numbers only!");
                    }

                // if user chooses 3 from the menu, the program will continue to this lines
            } else if (choice == 3) {

                int number = 0;
                while (true)

                    try {
                        String str;
                        // cast projects into an Array List
                        ArrayList<String> data = new ArrayList<String>();

                        // String lines = "";
                        // int lineNo = 0;
                        // display relevant message to user to input the project number he wishes
                        // to make changes to
                        System.out.println("enter index number of the project :");
                        number = input.nextInt();
                        number = number - 1;
                        input.nextLine();
                        // display relevant message to user to input the new amount paid to date
                        System.out.println("Enter the new amount format  * 20.8 *");
                        String total = input.nextLine();

                        String temp = data.get(number);
                        // cast the file to a String
                        temp = temp.toString();
                        //  System.out.println(data);

                        List<String> temple = Arrays.asList(temp.split(","));
                        temple.set(4, total);
                        String project = temple.toString();
                        project = project.replace("[", "");
                        project = project.replace("]", "");
                        //  temp = temp.replace("No",due);
                        data.set(number, project);
                        //   System.out.println(data.size());
                        // write the data to the files
                        Class.forName("com.mysql.jdbc.Driver");
            			Connection con = DriverManager.getConnection(
            					"jdbc:mysql://localhost:3306/poised","root","BooyzenW589");
            			Statement stmt = con.createStatement();
                        
                        ResultSet rs = stmt.executeQuery("SELECT*FROM projects");
                        
                        stmt.executeUpdate("UPDATE projects SET totalPaid = '"+total+"' WHERE projectNumber = '"+number+"')");
            			System.out.println("Values updated successfully!");

                        break;

                        // catch block to display error message to user if user inputs
                        // the incorrect format of data
                    } catch (Exception e) {

                        System.out.println("Enter numbers only!");
                    }

                // if user chooses 4 from the menu, the program will continue to the following
            } else if (choice == 4) {

                int number = 0;
                while (true)

                    try {
                        String str;
                        // cast the data to a String
                        ArrayList<String> data = new ArrayList<String>();

                        // String lines = "";
                        // int lineNo = 0;
                        System.out.println("enter index number of the project :");
                        number = input.nextInt();
                        number = number - 1;
                        input.nextLine();
                        // display message to user to input the new telephone number for the
                        // contractor
                        System.out.println("Enter the new telephone number: ");
                        String total = input.nextLine();

                        String temp = data.get(number);
                        temp = temp.toString();
                        //  System.out.println(data);

                        List<String> temple = Arrays.asList(temp.split(","));
                        temple.set(14, total);
                        String project = temple.toString();
                        project = project.replace("[", "");
                        project = project.replace("]", "");
                        //  temp = temp.replace("No",due);
                        data.set(number, project);
                        
                        Class.forName("com.mysql.jdbc.Driver");
            			Connection con = DriverManager.getConnection(
            					"jdbc:mysql://localhost:3306/poised","root","BooyzenW589");
            			Statement stmt = con.createStatement();
                        
                        ResultSet rs = stmt.executeQuery("SELECT*FROM person");
                        
                        stmt.executeUpdate("UPDATE person SET number = '"+total+"' WHERE projectNumber = '"+number+"')");
            			System.out.println("Values updated successfully!");

                        break;

                        // catch block to display error message to user if user inputs incorrect format of data
                    } catch (Exception e) {

                        System.out.println("Enter numbers only!");
                    }

                // if user chooses 5 from the menu, the program will continue to the following
            } else if (choice == 5) {
            	
            	for (Project all : ProjectArray) {
            		System.out.println(all.toString());
            	}

                // if user chooses 6 from the menu, the program will continue to the following
            } else if (choice == 6) {
            	
            	for (Project all : ProjectArray) {
            		System.out.println(all.toString());
            	}
      
            	// if user chooses 9 from the menu, the program will exit
            }else if (choice == 7){
            	connection.close();
				input.close();
				System.out.println("Shutting down");
				break;
                }
            }
        }
    }

/*
 * References:
 * Implementing multiple try catch blocks in program, https://www.programiz.com/java-programming/try-catch
 * Accessed: 16 June 2022
 * Java Style Guide, https://google.github.io/styleguide/javaguide.html#s4-formatting
 * Accessed: 16 June 2022
 * Write classes to file, https://stackoverflow.com/questions/8662844/java-write-class-to-file
 * Accessed: 26 June 2022
 * Write getters and setters to file, https://stackoverflow.com/questions/19686444/getter-and-setter-methods-and-reading-a-txt-file
 * Accessed: 26 June 2022
 * Get index item in Array List, https://www.w3resource.com/java-tutorial/arraylist/arraylist_indexof.php
 * Accessed: 26 June 2022
 */