package carRentalApp.Messages;

public class Message
{
    /*private String from;
    private String text;

    public Message() {}

    public Message(String from,String text)
    {
        this.from = from;
        this.text = text;
    }

    public String getFrom()
    {
        return from;
    }

    public void setFrom(String from)
    {
        this.from = from;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }*/

    private String identityCardNumber;
    private String doctorName;
    private String date;

    public Message() {
    }

    public Message(String identityCardNumber, String doctorName, String date) {
        this.identityCardNumber = identityCardNumber;
        this.doctorName = doctorName;
        this.date = date;
    }

    public String getIdentityCardNumber() {
        return identityCardNumber;
    }

    public void setIdentityCardNumber(String identityCardNumber) {
        this.identityCardNumber = identityCardNumber;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
