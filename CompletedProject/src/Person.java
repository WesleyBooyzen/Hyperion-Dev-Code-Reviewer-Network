public class Person {

    /**
     * Creating person class
     */
    String name;
    int telephoneNumber;
    String emailAddress;
    String physicalAddress;

    /**
     * Creating constructor
     */
    public Person(String name, int telephoneNumber, String emailAddress, String physicalAddress) {

        this.name = name;
        this.telephoneNumber = telephoneNumber;
        this.emailAddress = emailAddress;
        this.physicalAddress = physicalAddress;

    }
    /**
     * Creating setters for editing purposes
     */
    public void setTelephoneNumber (int telephoneNumber) {

        this.telephoneNumber = telephoneNumber       ;
    }
    public void setEmailAddress (String emailAddress){

        this.emailAddress = emailAddress;

    }

    /**
     * Creating toString method
     */
    public String toString() {
        String output = "";
        output += "\nName: " + name;
        output += "\nTelephone Number: " + telephoneNumber;
        output += "\nEmail Address: " + emailAddress;
        output += "\nPhysical Address: " + physicalAddress;
        return output;
    }
}