/**
 * This class represents a Car object
 * 
 * @author Matan Suliman
 * @version 31/10/2022
 */
public class Car
{
    //instance variables
    private int _id; //licence number
    private char _type; //rating
    private String _brand; //manufacturer
    private boolean _isManual; //is the gear manual
    
    //constants
    private static final int MIN_ID = 1000000, DEFAULT_ID = 9999999;
    private static final char TYPE_A = 'A', TYPE_B = 'B', TYPE_C = 'C', TYPE_D = 'D';
    
    /**
     * Creates a new Car object
     * id should be a 7 digits number, otherwise set it to 9999999
     * type should be 'A','B','C' or 'D', otherwise set it to 'A'
     * @param id the id of the car (7 digits number)
     * @param type the type of the car ('A','B','C' or 'D')
     * @param brand the car's brand
     * @param isManual flag indicating if the car is manual
     */
    public Car(int id, char type, String brand, boolean isManual)
    {
        if (MIN_ID <= id && id <= DEFAULT_ID)// positive and has 7 digits (between 1,000,000 - 9,999,999)
            _id = id;
        else
            _id = DEFAULT_ID;
        if (type == TYPE_A || type == TYPE_B || type == TYPE_C || type == TYPE_D)// is one of the constant types
            _type = type;
        else
            _type = TYPE_A;
        _brand = brand;
        _isManual = isManual;
    }
    
    /**
     * Copy constructor
     * @param other the car to be copied
     */
    public Car(Car other)
    {
        _id = other.getId();
        _type = other.getType();
        _brand = other.getBrand();
        _isManual = other.isManual();
    }
    
    /**
     * Gets the id
     * @return the id
     */
    public int getId()
    {
        return _id;
    }
    
    /**
     * Gets the type
     * @return the type
     */
    public char getType()
    {
        return _type;
    }
    
    /**
     * Gets the brand
     * @return the brand
     */
    public String getBrand()
    {
        return _brand;
    }
    
    /**
     * Gets the isManual flag
     * @return the isManual flag
     */
    public boolean isManual()
    {
        return _isManual;
    }
    
    /**
     * Sets the id (only if the given id is valid)
     * @param the id value to be set
     */
    public void setId(int id)
    {
        if ((MIN_ID <= id && id <=  DEFAULT_ID))
            _id = id;
    }
    
    /**
     * Sets the type (only if the given type is valid)
     * @param the type value to be set
     */
    public void setType(char type)
    {
        if (type == TYPE_A || type == TYPE_B || type == TYPE_C || type == TYPE_D)
            _type = type;
    }
    
    /**
     * Sets the brand of the car
     * @param the brand value to be set
     */
    public void setBrand(String brand)
    {
        _brand = brand;
    }
    
    /**
     * Sets the isManual flag of the car
     * @param the isManual flag to be set
     */
    public void setIsManual(boolean manual)
    {
        _isManual = manual;
    }
    
    /**
     * Returns a String object that represents this car
     * @return String that represents this car in the following format:
     * id:1234567 type:B brand:Toyota gear:manual (or auto)
     */
    public String toString()
    {
        return "id:" + _id + " type:" + _type + " brand:" + _brand + " gear:" + ((_isManual) ? "manual" : "auto");
    }
    
    /**
     * Check if two cars are the same
     * Cars are considered the same if they have the same type, brand and gear
     * @param other the car to compare this car to
     * @return true if the cars are the same, otherwise false
     */
    public boolean equals (Car other)
    {
        return _type == other.getType() && _brand.equals(other.getBrand()) && _isManual == other.isManual();
    }
    
    /**
     * Check if this car is better than the other car
     * A car is considered better than another car if its type is higher or
     * If both cars have the same type, an automated car is better than a manual car.
     * @param other the car to compare this car to
     * @return true if this car is better than the other car, otherwise false
     */
    public boolean better(Car other)
    {
        return _type > other.getType() || (_type == other.getType() && _isManual == false && other.isManual());
    }

    /**
     * Check if this car is worse than the other car
     * @param other the car to compare this car to
     * @return true if this car is worse than the other car, otherwise false
     */
    public boolean worse(Car other)
    {
        return other.better(this);
    }
}
