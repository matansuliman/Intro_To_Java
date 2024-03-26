/** 
 * This class represents a Rent object
 * 
 * @author Matan Suliman
 * @version 31/10/2022
 */
public class Rent
{
    //instance variables
    private String  _name;
    private Car _car;
    private Date _pickDate;
    private Date _returnDate;
    
    //constants
    private static final char TYPE_A = 'A', TYPE_B = 'B', TYPE_C = 'C', TYPE_D = 'D';
    private static final int TYPE_A_PRICE = 100, TYPE_B_PRICE = 150, TYPE_C_PRICE = 180, TYPE_D_PRICE = 240;
    private static final int DAYS_IN_WEEK = 7;
    private static final double DISCOUNT = 0.1;
    
    /**
     * Creates a new Rent object
     * The return date must be at least one day after the pickup date, otherwise set it to one day after the pick up date.
     * @param name the client's name
     * @param car the rented car
     * @param pick the pickup date
     * @param ret the return date 
     */
    public Rent(String name, Car car, Date pick, Date ret)
    {
        _name = name;
        _car = car;
        _pickDate = pick;
        if (pick.before(ret))
            _returnDate = ret;
        else
            _returnDate = pick.tomorrow();
    }

    /**
     * Copy constructor
     * @param other the rent to be copied
     */
    public Rent(Rent other)
    {
        _name = other.getName();
        _car = other.getCar();
        _pickDate = other.getPickDate();
        _returnDate = other.getReturnDate();
    }
    
    /**
     * Gets the name
     * @return the name
     */
    public String getName()
    {
        return _name;
    }
    
    /**
     * Gets the car
     * @return the car
     */
    public Car getCar()
    {
        return _car;
    }
    
    /**
     * Gets the pick up date
     * @return the pick up date
     */
    public Date getPickDate()
    {
        return _pickDate;
    }
    
    /**
     * Gets the return date
     * @return the return date
     */
    public Date getReturnDate()
    {
        return _returnDate;
    }
    
    /**
     * Sets the client name
     * @param name the client name (assume that the name is a valid name)
     */
    public void setName(String name)
    {
        _name = name;
    }
    
    /**
     * Sets the rented car
     * @param car the rented car (assume that car is not null)
     */
    public void setCar(Car car)
    {
        _car = new Car(car);
    }
    
    /**
     * Sets the pick up date
     * The pick up date must be at least one day before the return date, otherwise - don't change the pick up date
     * @param pickDate the pick up date (assume that pick up date is not null)
     */
    public void setPickDate(Date pickDate)
    {
        if (pickDate.before(_returnDate))
            _pickDate = new Date(pickDate);
    }
    
    /**
     * Sets the return date
     * The return date must be at least one day after the return date, otherwise - don't change the return date
     * @param returnDate the return date (assume that pick up date is not null)
     */
    public void setReturnDate(Date returnDate)
    {
        if (returnDate.after(_pickDate))
            _returnDate = new Date(returnDate);
    }
    
    /**
     * Check if 2 rents are the same
     * @param other the rent to compare this rent to
     * @return true if the rents are the same
     */
    public boolean equals(Rent other)
    {
        return _name.equals(other.getName()) && _car.equals(other.getCar()) && _pickDate.equals(other.getPickDate()) && _returnDate.equals(other.getReturnDate());
    }
    
    /**
     * Returns the number of rent days
     * @return number of rent days
     */
    public int howManyDays()
    {
        return _pickDate.difference(_returnDate);
    }
    
    /**
     * Returns the rent total price
     * @return rent total price
     */
    public int getPrice()
    {
        int days = howManyDays();
        int pricePerDay = 0;
        switch (_car.getType())
        {
            case TYPE_A:
                pricePerDay = TYPE_A_PRICE;
                break;
            case TYPE_B:
                pricePerDay = TYPE_B_PRICE;
                break;
            case TYPE_C:
                pricePerDay = TYPE_C_PRICE;
                break;
            case TYPE_D:
                pricePerDay = TYPE_D_PRICE;
                break;
        }
        return (days / DAYS_IN_WEEK) * (int)(DAYS_IN_WEEK * pricePerDay *  (1 - DISCOUNT)) + ((days % DAYS_IN_WEEK) * pricePerDay);
    }
    
    /**
     * Try to upgrade the car to a better car
     * If the given car is better than the current car of the rent, upgrade it and return the upgrade additional cost, otherwise - don't upgrade
     * @param car the car to upgrade to
     * @return the upgrade cost
     */
    public int upgrade(Car newCar)
    {
        if (newCar.better(_car))
        {
            int oldPrice = getPrice();
            _car = new Car(newCar);
            return getPrice() - oldPrice;
        }
        else
            return 0;
    }
    
    /**
     * Check if there is a double listing of a rent for the same person and car with an overlap in the rental days
     * If there is - return a new rent object with the unified dates, otherwise - return null.@param car - the car to upgrade to
     * @param other the other rent
     * @return the unified rent or null
     */
    public Rent overlap(Rent other)
    {
        if (!_name.equals(other.getName()) || !_car.equals(other.getCar()))
            return null;
        else
        {
            if (_pickDate.equals(other.getPickDate()))
            {
                if (_returnDate.before(other.getReturnDate()))
                    return new Rent(_name, _car, _pickDate, other.getReturnDate());
                else //_returnDate is after or equal to other.getReturnDate()
                    return new Rent(_name, _car, _pickDate, _returnDate);
            }
            else if (_pickDate.before(other.getPickDate()))
            {
                if (_returnDate.before(other.getPickDate()))
                    return null;
                else if (_returnDate.after(other.getReturnDate()))
                    return new Rent(_name, _car, _pickDate, _returnDate);
                else //_returnDate is between other.getPickDate() to other.getReturnDate() included
                    return new Rent(_name, _car, _pickDate, other.getReturnDate());
            }
            else //_pickDate is after other.getPickDate() 
            {
                if (other.getReturnDate().before(_pickDate))
                    return null;
                else if (other.getReturnDate().after(_returnDate))
                    return new Rent(_name, _car, other.getReturnDate(), other.getReturnDate());
                else //_returnDate is between _pickDate to _returnDate included
                    return new Rent(_name, _car, other.getReturnDate(), _returnDate);
            }
        }
    }
    
    /**
     * Returns a String that represents this rent
     * @return a String that represents this rent
     */
    public String toString()
    {
        return "Name:" + _name + " From:" + _pickDate.toString() + " To:" + _returnDate.toString() + " Type:" + _car.getType() + " Days:" + howManyDays() + " Price:" + getPrice();
    }
}
