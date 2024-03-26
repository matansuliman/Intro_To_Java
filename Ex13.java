/**
 * Ex13 class 
 *
 * @author Matan Suliman
 * @version 24/12/2022
 */
import java.util.*;
public class Ex13
{
    /**
     * Q1:
     * This method gets a string of 0's and 1's and calculates the minimun number of swaps required to create an alternating sequence. 
     * a uniqe property of alternating sequence is that the 0's are at even indexs or odd indexs. 
     * so we count the number of zeros at even indexs, from this we can understand that the number of zeros
     * in odd indexs is the number of total zeros which equal to s length divided by 2 minus the number of zeros at even indexs. 
     * The minimum of these two numbers is the minimum number of swaps needed to alternate the sequence. 
     * 
     * time complexity - O(n) n is the string length, we only go through a loop once. 
     * space complexity - O(1) fixed number of variables. 
     *
     * @param s the given string sequence
     * @return the minimun number of swaps
     */
    public static int alternating(String s)
    {
        final char ZERO = '0';
        int even_0 = 0;//counter
        for (int i = 0; i < s.length(); i+=2)//iterate through even indexs
        {
            if (s.charAt(i) == ZERO)//detect zero in string
                    even_0++;//counter++
        }
        return Math.min(even_0, s.length()/2 - even_0);//min value between zero chars and one chars
    }
    
    
    /**
     * Q2
     * what method from the question: 
     * returns the biggest number of nearby indexs from a given array that their sum is even. 
     * time complexity - O(n^3) n is the array length, 3 for loops inside each other each at max n times. 
     * space complexity - O(1) fixed number of variables. 
     *
     * better what method: 
     * first sum all the array, if its even then the answer in a.length, otherwise it odd and we need to "take out" one odd number to make the sum even. 
     * if we "take out" a number from array we get two subarrays (i is the number index) - left[0,i-1] right[i+1,a.length-1]. 
     * calculations: left.length = i-1-0+1 = i, right.length = a.length-1-i-1+1 = a.length-i-1; 
     * then we get the max length by compering all subarrays length created by "takeing out" odd number so their sum is even. 
     * 
     * time complexity - O(n) n is the array length, at max iterate one loop n times. 
     * space complexity - O(1) fixed number of variables. 
     * 
     * @param a array of numbers
     * @return the biggest number of nearby indexs that their sum is even
     */
    public static int what(int[] a)
    {
        int sum = 0;//init
        for (int i = 0; i < a.length; i++)//sum all the array
        {
            sum += a[i];
        }
        if (sum % 2 == 0)//if even
            return a.length;//return the length of array
        //otherwise sum is odd
        int maxLen = 0;//init
        for (int i = 0; i < a.length; i++)//iterate the array
        {
            if (a[i] % 2 == 1)//find odd value
                maxLen = Math.max(maxLen, Math.max(i, a.length - i - 1));//get the max length of subarrays from left and right and store in len
        }
        return maxLen;//return the max length
    }
    
    
    /**
     * Q3:
     * This method gets an array of numbers and try to find a route. 
     * a route is legal if it begins at index 0 and advancing left or right by the value at that index. 
     * the route needs to end at the last index of the array. 
     * 
     * time complexity - O(2^n) - each cell has 2 ways. 
     * space complexity - O(n) array visited length depend on n. 
     *
     * @param a the given array 
     * @return true if there is a route from index 0 to the last index, otherwise false 
     */
    public static boolean isWay(int[] a)
    {
        if(a.length == 1)//if we got 1 "cell" in the array
        {
            return true;
        }//else we need to find a way
        boolean[] visited = new boolean[a.length];
        visited = fillArrayFalse(visited, visited.length - 1);
        return R_isWay(a, 0, visited);
    }
    
    /*
     * This is a help method.
     * here we recursively initialize the array to false values.
     * time complexity - O(n)
     * space complexity - O(1)
     * @param a the given array
     * @return true if there is a route from index 0 to the last index, otherwise false.
     */
    private static boolean[] fillArrayFalse(boolean[] v, int i)
    {
        if (i < 0)//base case:
            return v;
        //recursion:
        v[i] = false;
        return fillArrayFalse(v, --i);
    }
    
    /*
     * This the recursive method of isWay.
     * stop conditions:
     * 1) got to the last index.
     * 2) got to invalid index - means out of bounds of array or already seen.
     * otherwise:
     * mark current position as visited.
     * return if either the next jump to right or left is true.
     * 
     * time complexity - O(2^n) each cell has 2 ways.
     * space complexity - O(1) fixed number of variables.
     *
     * @param a the given array
     * @param currentIndex the current index
     * @param visited array of index already visited
     * @return true if there is a route from index 0 to the last index, otherwise false.
     */
    private static boolean R_isWay(int[] a, int currentIndex, boolean[] visited)
    {
        if (currentIndex == a.length - 1)
            return true;
        if (isNotValid(currentIndex, visited))//not valid
            return false;
        //valid - inside array and not seen yet:
        visited[currentIndex] = true;
        return R_isWay(a, currentIndex - a[currentIndex], visited) || R_isWay(a, currentIndex + a[currentIndex], visited);
    }
    
    /*
     * This is a help method.
     * checking if index is in array and not seen yet.
     * time complexity - O(1).
     * space complexity - O(1).
     * @param a the given array
     * @param v the given visited array
     * @param indexToGo the index
     * @return true if can jump, false if cant.
     */
    private static boolean isNotValid(int indexToCheck, boolean[] v)
    {
        return !(0 <= indexToCheck && indexToCheck < v.length && !v[indexToCheck]);
    }
    
    
    /**
     * Q4:
     * This method returns the shortest path length from price to evil if possible. The method is givan map with evil inside and strting position prince. 
     * 
     * time complexity - O(4^(n^2)) each cell has 4 ways. 
     * space complexity - O(n^2). 
     *
     * @param drm the given map
     * @param i starting x coordinate of prince
     * @param j starting y coordinate of prince
     * @return the shortest path length to evil, if there is no path return -1;
     */
    public static int prince(int[][] drm, int i, int j)
    {
        int path = R_prince(drm, i, j);//call the recursive method
        if (path == Integer.MAX_VALUE)//if got an impossibale value there is no path;
            return -1;
        return path;//else return it
    }
    
    /*
     * This the recursive method of prince.
     * stop conditions:
     * 1) reached evil (value -1).
     * 2) got to a deadend - cant go anywere.
     * otherwise:
     * mark current position as visited (value -2).
     * get the values from going to four directions.
     * untrack the visited position.
     * return the minimun value.
     * 
     * time complexity - O(4^(n^2)) worst case is jumping all 4 directions.
     * space complexity - O(n^2).
     *
     * @param drm the given map
     * @param i x current coordinate of prince
     * @param j y current coordinate of prince
     * @param visited the position visited tracking array
     * @return the shortest path length to evil, if there is no path return -1;
     */
    private static int R_prince(int[][] drm, int i, int j)
    {
        if (drm[i][j] == -1)//reached evil
            return 1;
        //check if can go to directions
        boolean canDown = isValid(drm, i + 1, j, drm[i][j]);
        boolean canUp = isValid(drm, i - 1, j, drm[i][j]);
        boolean canRight = isValid(drm, i, j + 1, drm[i][j]);
        boolean canLeft = isValid(drm, i, j - 1, drm[i][j]);
        boolean isDeadEnd = !(canDown || canUp || canRight || canLeft);//only if all false than its a deadend
        if (isDeadEnd)//cant go any diraction
          return Integer.MAX_VALUE;
        int temp = drm[i][j];//store to untrack later
        final int VISITED = -2;//visited mark value
        drm[i][j] = VISITED;//mark as visited
        int down = Integer.MAX_VALUE, up = Integer.MAX_VALUE, right = Integer.MAX_VALUE, left = Integer.MAX_VALUE;//assume maximum value possible
        if(canDown)//down
            down = R_prince(drm, i + 1, j);
        if(canUp)//up
            up = R_prince(drm, i - 1, j);
        if(canRight)//right
            right = R_prince(drm, i, j + 1);
        if(canLeft)//left
            left = R_prince(drm, i, j - 1);
        drm[i][j] = temp;//backtracking
        int minPath = Math.min(Math.min(down, up),Math.min(right, left));//calc min path
        if (minPath == Integer.MAX_VALUE)//if its max value just return without adding 1;
            return Integer.MAX_VALUE;
        return minPath + 1;//else return shortest value (path) + 1
    }
    
    /*
     * This is a help method.
     * here we check if an (x,y) can be reached - in bounds of array, and not visited
     * if its the evil the hieght diffrance dosent matter.
     * if its not the evil check if at most 1 up or 2 down.
     * time complexity - O(1)
     * space complexity - O(1)
     * @param a the given array
     * @return true if there is a route from index 0 to the last index, otherwise false.
     */
    private static boolean isValid(int[][] drm, int i, int j, int currValue)
    {      
        final int VISITED = -2;
        if (0 <= i && i < drm.length && 0 <= j && j < drm.length && drm[i][j] != VISITED)//in bounds of array and not visited
        {
                int nextValue = drm[i][j];
                if (nextValue == -1)//is Evil - so the hight diff dosent matter
                    return true;
                if (currValue - 2 <= nextValue && nextValue <= currValue + 1)//can climb up 1 and climb down 2
                    return true;
                return false;//else cant climb
        }
        return false;//not in bounds or visited
    }
}
