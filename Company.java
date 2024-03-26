/**
 * Company class 
 *
 * @author Matan Suliman
 * @version 27/01/2022
 */
public class Company
{
    //instance variables
    private RentNode _head;
    
    /**
     * Creates a new Company object
     * head is null
     */
    public Company()
    {
        _head = null;
    }
    
    /**
     * add rent to company in right order by pick up date
     * if the rent exsist do nothing
     * if rent has same pick up date put in order by number of days
     * 
     * @param name the name of the rent
     * @param car the car of the rent
     * @param pick the pick up date of the car rent
     * @param ret the return date of the car rent
     * @return true if added, false if didn't
     */
    public boolean addRent(String name, Car car, Date pick, Date ret)
    {
        RentNode newRentNode = new RentNode(new Rent(name, car, pick, ret));
        if(_head == null)//list is empty
        {
            _head = newRentNode;//put at start
            return true;
        }
        //newRent rent already exsists at head
        if (_head.getRent().equals(newRentNode.getRent()))
        {
            return false;
        }
        //newRent date is before the head date
        if(newRentNode.getRent().getPickDate().before(_head.getRent().getPickDate()))
        {
            newRentNode.setNext(_head);//point to head
            _head = newRentNode;//put at start
            return true;
        }
        //maybe dates equal, newRent date equal head date
        if (newRentNode.getRent().getPickDate().equals(_head.getRent().getPickDate()))
        {
            //newRent rent is longer then head rent
            if((newRentNode.getRent().howManyDays() > _head.getRent().howManyDays()))
            {
                newRentNode.setNext(_head);//point to head
                _head = newRentNode;//put at start   
                return true;
            }
        }
        RentNode curr = _head;
        //newRent need to be after curr
        //while currNext isn't null
        while (curr.getNext() != null)
        {
            RentNode curr_Next = curr.getNext();
            Rent newRentNode_Rent = newRentNode.getRent();
            Rent currNext_Rent = curr_Next.getRent();
            //newRent rent already exsists at currNext
            if (newRentNode_Rent.equals(currNext_Rent))
            {
                return false;
            }
            //newRent date is before the currNext date
            if(newRentNode_Rent.getPickDate().before(currNext_Rent.getPickDate()))
            {
                newRentNode.setNext(curr_Next);
                curr.setNext(newRentNode);
                return true;
            }
            //maybe dates equal, newRent date equal currNext date
            if (newRentNode_Rent.getPickDate().equals(currNext_Rent.getPickDate()))
            {
                //newRent rent is longer then head rent
                if((newRentNode_Rent.howManyDays() > currNext_Rent.howManyDays()))
                {
                    newRentNode.setNext(curr_Next);
                    curr.setNext(newRentNode);
                    return true;
                }
            }
            curr = curr.getNext();
        }
        curr.setNext(newRentNode);//add to end
        return true;
    }
    
    /**
     * Returns a String object that represents this Company
     * if the company has no rents, return a spesific string.
     * 
     * @return a string that represents this Company rents
     */
    public String toString()
    {
        if (getNumOfRents() == 0)//list is empty
        {
            return "The company has 0 rents.";
        }
        String str = "The company has " + getNumOfRents() + " rents:\n";
        for(RentNode curr = _head; curr != null; curr = curr.getNext())
        {
            str += curr.getRent() + "\n";
        }
        return str;
    }
    
    /**
     * Remove a rent by given date
     * if multiple rents have this date, remove the first one
     * 
     * @param d date of rent to remove
     * @return true if removed a rent, false if didn't
     */
    public boolean removeRent(Date d)
    {
        if(_head == null)//list is empty
        {
            return false;
        }
        if (_head.getRent().getReturnDate().equals(d))//head retDate equal to d
        {
            _head = _head.getNext();//remove head by pointing to next
            return true;
        }
        RentNode curr = _head;
        while (curr.getNext() != null)
        {
            if (curr.getNext().getRent().getReturnDate().equals(d))//curr retDate equal to d
            {
                curr.setNext(curr.getNext().getNext());//remove currNext by pointing to next
                return true;
            }
            curr = curr.getNext();
        }
        return false;//nothing happened
    }
    
    /**
     * Return the number of rent in the company
     * 
     * @return number of rents in company
     */
    public int getNumOfRents()
    {
        int count = 0;
        for(RentNode curr = _head; curr != null; curr = curr.getNext())
        {
            count++;//counting rent equal to counting nodes
        }
        return count;
    }
    
    /**
     * Return the sum of prices of rents in the company
     * 
     * @return sum of prices of rents in the company
     */
    public int getSumOfPrices()
    {
        int sum = 0;
        for(RentNode curr = _head; curr != null; curr = curr.getNext())
        {
            sum += curr.getRent().getPrice();//adding prices
        }
        return sum;
    }
    
    /**
     * Return the sum of days of rents in the company
     * 
     * @return sum of days of rents in the company
     */
    public int getSumOfDays()
    {
        int sum = 0;
        for(RentNode curr = _head; curr != null; curr = curr.getNext())
        {
            sum += curr.getRent().howManyDays();//adding days
        }
        return sum;
    }
    
    /**
     * Return the average days length of rents in the company
     * 
     * @return average days length of rents in the company
     */
    public double averageRent()
    {
        if(getNumOfRents() == 0)//if empty
        {
            return 0;//average is 0
        }
        return (double)getSumOfDays() / (double)getNumOfRents();//total days/total rent= average length of rent
    }
    
    /**
     * Return the last car to get back of all rents in the company
     * 
     * @return last car to get back from all rents
     */
    public Car lastCarRent()
    {
        if(_head == null)//is empty
        {
            return null;
        }
        RentNode lastCar_RentNode = _head;//keep the rentNode of the last car
        for(RentNode curr = _head.getNext(); curr != null; curr = curr.getNext())
        {
            //if curr return date is after
            if(curr.getRent().getReturnDate().after(lastCar_RentNode.getRent().getReturnDate()))
            {
                lastCar_RentNode = curr;//keep to return
            }
        }
        return lastCar_RentNode.getRent().getCar();//return the car
    }
    
    /**
     * Return the longest rent in the company
     * 
     * @return the longest rent in the company
     */
    public Rent longestRent()
    {
        if(_head == null)//is empty
        {
            return null;
        }
        RentNode longestRent_RentNode = _head;//keep the rentNode of the longest rent
        for(RentNode curr = _head.getNext(); curr != null; curr = curr.getNext())
        {
            //if curr is longer
            if (curr.getRent().howManyDays() > longestRent_RentNode.getRent().howManyDays())
            {
                longestRent_RentNode = curr;//keep to return
            }
        }
        return longestRent_RentNode.getRent();//return the rent
    }
    
    /**
     * Return the common rate of cars of all rents in the company
     * 
     * @return the common rate of cars of all rents in the company
     */
    public char mostCommonRate()
    {
        if(_head == null)//is empty
        {
            return 'N';
        }
        final char TYPE_A = 'A', TYPE_B = 'B', TYPE_C = 'C', TYPE_D = 'D';//types of rates
        int count_A = 0, count_B = 0, count_C = 0, count_D = 0;//init counters
        for(RentNode curr = _head; curr != null; curr = curr.getNext())
        {
            //count the types
            switch (curr.getRent().getCar().getType())
            {
                case TYPE_A:
                    count_A++;
                    break;
                case TYPE_B:
                    count_B++;
                    break;
                case TYPE_C:
                    count_C++;
                    break;
                case TYPE_D:
                    count_D++;
                    break;
            }
        }
        //if 'D' is most common return it
        if(count_D >= count_C && count_D >= count_B && count_D >= count_A)
        {
            return 'D';
        }
        //if 'C' is most common return it
        if (count_C >= count_B && count_C >= count_A)
        {
            return 'C';
        }
        //if 'B' is most common return it
        if (count_B >= count_A)
        {
            return 'B';
        }
        //'A' is most common so return it
        return 'A';
    }
    
    /**
     * check if other compnay is contained in this company
     * 
     * @param other other company
     * @return true if other compnay contained in this company, false if doesn't
     */
    public boolean includes(Company other)
    {
        if(other.getNumOfRents() == 0)//if other company is empty
        {
            return true;
        }
        if(getNumOfRents() == 0)//if this company is empty
        {
            return false;
        }
        //iterate throgh other company
        for(RentNode curr_1 = other._head; curr_1 != null; curr_1 = curr_1.getNext())
        {
            boolean exsist = false;//assume curr_1 not exsist
            //iterate throgh this company
            for(RentNode curr_2 = _head; curr_2 != null; curr_2 = curr_2.getNext())
            {
                //if rents equal then curr_1 exsist in this company
                if(curr_1.getRent().equals(curr_2.getRent()))
                {
                    exsist = true;
                    break;
                }
            }
            if(!exsist)//if not exsist return false
            {
                return false;
            }
            //else continue to iterate
        }
        return true;//other compnay is contained in this company
    }
    
    /**
     * merege other company to this company by order
     * adding all rents from other company to this company
     * addRent insures that this compnay is ordered by date
     * 
     * @param other other company
     */
    public void merge(Company other)
    {
        for(RentNode curr = other._head; curr != null; curr = curr.getNext())
        {
            Rent curr_Rent = curr.getRent();
            addRent(curr_Rent.getName(), curr_Rent.getCar(), curr_Rent.getPickDate(), curr_Rent.getReturnDate());
        }
    }
}
