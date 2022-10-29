public class Data {

    String email;
    String firstName;
    String lastName;
    String ccNumber;
    int namesCount;
    boolean hasNumber;

    public Data(String email, String firstName, String lastName)
    {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        namesCount = 1;
        hasNumber = false;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public void incrementNamesCount()
    {
        namesCount++;
    }

    public String toString()
    {
        return String.format("%s %s, %s, %s", firstName, lastName, ccNumber, email);
    }
}
