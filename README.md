## Q1:
## explanation:
* This method gets a string of 0's and 1's and calculates the minimun number of swaps required to create an alternating sequence. 
* a uniqe property of alternating sequence is that the 0's are at even indexs or odd indexs. 
* so we count the number of zeros at even indexs, from this we can understand that the number of zeros
* in odd indexs is the number of total zeros which equal to s length divided by 2 minus the number of zeros at even indexs. 
* The minimum of these two numbers is the minimum number of swaps needed to alternate the sequence.
## complexity: 
* time complexity - O(n) n is the string length, we only go through a loop once. 
* space complexity - O(1) fixed number of variables. 


## Q2:
## what method from the question: 
* returns the biggest number of nearby indexs from a given array that their sum is even. 
* time complexity - O(n^3) n is the array length, 3 for loops inside each other each at max n times. 
* space complexity - O(1) fixed number of variables. 
## better what method: 
* first sum all the array, if its even then the answer in a.length, otherwise it odd and we need to "take out" one odd number to make the sum even. 
* if we "take out" a number from array we get two subarrays (i is the number index) - left[0,i-1] right[i+1,a.length-1]. 
* calculations: left.length = i-1-0+1 = i, right.length = a.length-1-i-1+1 = a.length-i-1; 
* then we get the max length by compering all subarrays length created by "takeing out" odd number so their sum is even. 
## complexity: 
* time complexity - O(n) n is the array length, at max iterate one loop n times. 
* space complexity - O(1) fixed number of variables. 

## Q3:
* This method gets an array of numbers and try to find a route. 
* a route is legal if it begins at index 0 and advancing left or right by the value at that index. 
* the route needs to end at the last index of the array. 
## complexity: 
* time complexity - O(2^n) - each cell has 2 ways. 
* space complexity - O(n) array visited length depend on n.
