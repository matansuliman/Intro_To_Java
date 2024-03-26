/* Name: Matan Suliman
 * Id: 322982620
 * Date: 29/10/2022
 */
import java.util.Scanner;

/* Class Name: Triangle2.
 * Input: the class gets three integers.
 * Output: the class prints whether the given numbers can form a triangle and if so states his type (common, right-angle, isosceles, equilateral).
 * The class uses the triangle inequality theorem to determine if three numbers can form a triangle.
 * The class uses the Pythagoras' theorem to determine if a triangle is a right-angle triangle.
 * The class checks equilateral triangle as a private case of isosceles triangle.
 */
public class Triangle2
{
    // Main Method:
    public static void main (String[]args)
    {
        //Gets 3 numbers from user
        Scanner scan = new Scanner(System.in);
        System.out.println("This program determines whether three numbers can form a triangle and if so states his type (common, right-angle, isosceles, equilateral). ");
        System.out.println("Please enter three numbers as you choose");
        int num1 = scan.nextInt();
        int num2 = scan.nextInt();
        int num3 = scan.nextInt();
        //checks Triangle Inequality theorem
        if ((num1 < num2 + num3) && (num2 < num1 + num3) && (num3 < num1 + num2))
        {
            //checks if isosceles
            if (num1 == num2 || num2 == num3 || num1 == num3)
            {
                //checks if equilateral - private case of isosceles
                if (num1 == num2 && num1 == num3) //means num1, num2, num3 are equal
                    System.out.println("The numbers: " + num1 + ", " + num2 + " and " + num3 + " represent an equilateral triangle");
                else
                    System.out.println("The numbers: " + num1 + ", " + num2 + " and " + num3 + " represent an isosceles triangle");
            }
            //not isosceles or equilateral
            //checks Pythagoras' theorem considering each time num1 or num2 or num3 as the hypotenuse
            else if ((Math.pow(num1,2) == Math.pow(num2,2) + Math.pow(num3,2)) || (Math.pow(num2,2) == Math.pow(num1,2) + Math.pow(num3,2)) || (Math.pow(num3,2) == Math.pow(num1,2) + Math.pow(num2,2)) )
                    System.out.println("The numbers: " + num1 + ", " + num2 + " and " + num3 + " represent a right-angle triangle");
            else //not isosceles, equilateral or right-angled
                System.out.println("The numbers: " + num1 + ", " + num2 + " and " + num3 + " represent a common triangle");
        }
        else //not a triangle
            System.out.println("The numbers: " + num1 + ", " + num2 + " and " + num3 + " cannot represent a triangle");
    }//end of method Main
}//end of class Triangle1
