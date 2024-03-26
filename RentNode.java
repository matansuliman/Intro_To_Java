/**
 * RentNode class 
 *
 * @author Matan Suliman
 * @version 27/01/2022
 */
public class RentNode
{
    //instance variables
    private Rent _rent;
    private RentNode _next;
    
    /**
     * Creates a new RentNode object
     * @param r rent
     */
    public RentNode(Rent r)
    {
        _rent = new Rent(r);
        _next = null;
    }
    
    /**
     * Creates a new RentNode object
     * @param r rent
     * @param next next RentNode object
     */
    public RentNode(Rent r, RentNode next)
    {
        _rent = new Rent(r);
        _next = next;//aliasing
    }
    
    /**
     * Copy constructor
     * @param other RentNode to be copied
     */
    public RentNode(RentNode other)
    {
        _rent = other.getRent();//no aliasing from getRent function
        _next = other.getNext();
    }
    
    /**
     * Gets the rent
     * @return the rent
     */
    public Rent getRent()
    {
        return new Rent(_rent);
    }
    
    /**
     * Gets the next rent node
     * @return next
     */
    public RentNode getNext()
    {
        return _next;//aliasing
    }
    
    /**
     * Sets the rent
     * @param r rent to be set
     */
    public void setRent(Rent r)
    {
        _rent = new Rent(r);
    }
    
    /**
     * Sets the rent
     * @param next RentNode to be set as next
     */
    public void setNext(RentNode next)
    {
        _next = next;//aliasing
    }
}
