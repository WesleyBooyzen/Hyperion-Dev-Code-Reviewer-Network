import java.util.Date;
public class Project {

    /**
     * Creating attributes and defining them
     */
    int projectNumber;
    String projectName;
    String buildingType;
    String physicalAddress;
    Double totalFee;
    Double amountPaid;
    String deadline;
    String projectCompleted;
    Date dateCompleted;

    /**
     * Adding people to add them in the project
     */
    Person Architect;
    Person Customer;
    Person Contractor;

    /**
     * Creating constructor
     */
    public Project(int projectNumber, String projectName, String buildingType,String physicalAddress ,Double totalFee,Double amountPaid, String deadline, String projectCompleted,Person Architect, Person Customer, Person Contractor) {

        this.projectNumber = projectNumber;
        this.projectName = projectName;
        this.buildingType = buildingType;
        this.physicalAddress = physicalAddress;
        this.totalFee = totalFee;
        this.amountPaid = amountPaid;
        this.deadline = deadline;
        this.projectCompleted = projectCompleted;
        this.Architect = Architect;
        this.Customer = Customer;
        this.Contractor = Contractor;

    }

    /**
     * Create getters and setters to edit
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public void setProjectCompleted(String projectCompleted) {
        this.projectCompleted = projectCompleted;
    }
    public void setDateCompleted (Date dateCompleted ) {
        this.dateCompleted = dateCompleted;
    }
    //create getters and setter to get project details
    public int getProjectNumber() {

        return projectNumber;
    }
    //Mutator
    public void setProjectNumber(int projectNumber) {

        this.projectNumber = projectNumber;
    }


    // Create a toString method

    public String toString() {
        String output = "";
        output += "\nProject Number: " + projectNumber;
        output += "\nProject Name: " + projectName;
        output += "\nBuilding Type: " + buildingType;
        output += "\nPhysical Address: " + physicalAddress;
        output += "\nTotal Fee: " + totalFee;
        output += "\nAmount Paid: " + amountPaid;
        output += "\nDeadline: " + deadline;
        output += "\nIs Project Completed: " + projectCompleted;
        output += "\nCompleted Date: " + dateCompleted;
        return output;
    }


}