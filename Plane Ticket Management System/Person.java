public class Person {
    private String Name;
    private String Surname;
    private String Email;

    //Constructor
    public Person(String Name, String Surname, String Email){
        this.Name = Name;
        this.Surname = Surname;
        this.Email = Email;
    }

    // Getters and Setters
    public String takeName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String takeSurname() {
        return Surname;
    }
    public void setSurname(String Surname) {
        this.Surname = Surname;
    }

    public String takeEmail () {
        return Email;
    }
    public void setEmail(String Email) {
        this.Email = Email;
    }

    // A method to print information
    public void Infor() {
        System.out.println("Name: " + Name);
        System.out.println("Surname: " + Surname);
        System.out.println("Email: " + Email);
    }
}
