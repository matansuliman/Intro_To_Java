/* Name: Matan Suliman
 * Id: 322982620
 * Date: 29/10/2022
 */
import java.util.Scanner;

/* Class Name: Triangle1
 * Input: the class gets three side lengths of triangle.
 * Output: thr class prints the perimeter and the area of the triangle.
 * The class uses the Heron's formula to calculate area of triangle from given side lengths
 */
public class Triangle1
{
    // Main Method:
    public static void main (String[]args)
    {
        //Gets 3 side lengths from user
        Scanner scan = new Scanner(System.in);
        System.out.println("This program calculates the area and the perimeter of a given triangle. ");
        System.out.println("Please enter the three lengths of the triangle's sides"); 
        int a = scan.nextInt();
        int b = scan.nextInt();
        int c = scan.nextInt();
        int perimeter = a + b + c; // the perimeter equals the sum of all side lengths.
        double halfPerimeter = (double)perimeter / 2; // this variable is made for Heron's formula.
        // Heron's formula:
        double area = Math.sqrt(halfPerimeter * (halfPerimeter - a) * (halfPerimeter - b) * (halfPerimeter - c));
        // prints side lengths, perimeter and area of triangle:
        System.out.println("The lengths of the triangle are: " + a + "," + b + "," + c + "."); 
        System.out.println("The perimeter of the triangle is: " + perimeter); 
        System.out.println("The area of the triangle is: " + area);
    }//end of method Main
}//end of class Triangle1
