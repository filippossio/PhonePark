package siokouros.filippos.phonepark.Model;


public class Car {

    private enum Colour {
        Black, White, Red, Green,
        Blue, Yellow, Silver,
        Gray, Orange, Gold
            }

    private String make;
    private String model;
    private int year;
    private Colour colour;
    private String RegNumber;

    public Car(String make, String model, int year, Colour colour, String regNumber) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.colour = colour;
        RegNumber = regNumber;
    }

    public Car() {

    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

    public String getRegNumber() {
        return RegNumber;
    }

    public void setRegNumber(String regNumber) {
        RegNumber = regNumber;
    }
}
