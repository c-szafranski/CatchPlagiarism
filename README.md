# CatchPlagiarism


/**********************************************************************
 *  README.txt                                                   
 *  Plagiarism Catcher
 **********************************************************************/
<Chris Szafranski>
/**********************************************************************
* Approximate hours to complete assignment?
***********************************************************************/

Many days. Collectively 10 hours

/**********************************************************************
* In the space below, describe the data structure and algorithm(s) you
* used in your solution. Please be detailed--explain to me how your
* program works!
***********************************************************************/
This Java program implements Hashmaps, Queues, and ArrayList data structures to find n-Gram matches between documents. 
Given a path to a file folder the program will read the files and build document nodes with a field for a hashmap and a file name. 
Those files are placed into an ArrayList for later processing. 
Upon starting a welcome message will appear and the user will enter a path to a folder and an n-gram length (n) and a threshold for matches. 
The program will detect invalid paths and will detect a non-integer entry for the n-Gram length. 
The nodes are constructed as follows:
Using a queue of n length a hashmap is built where the Value is the n-Gram queue and the Key is the hashcode of the n-Gram string. 
In other word the hashmap maps <Integer,String>. 
Once all document nodes are built the program will introduce an iterator that takes the key of the hashmap in 
question and searches for that key in the other mashmaps of all the other nodes using .containsKey();
In the loop that checks if the key exists in the other maps, 
matches are counted and if the total count for a document pair is greater than the threshold it will be printed.
This program does not sort the solution becasue the user controls the threshold. If they want a more refined
solution they can place a higher or lower threshhold. Also even with the large set a threshold of 100 results in only a few
questionable documents. 

/**********************************************************************
* How did you deal with the trade-off between space and time efficiency
* in implementing your solution?
***********************************************************************/
Using hash maps saves on time and space. I tried using BinarySearchTrees and using my implementation was a lot faster 
and I imagine it uses a lot less space. If I had more time I would filter out punctuation which would make my solution 
run much quicker. 

/**********************************************************************
* Describe any further extensions to made to this assignment.
***********************************************************************/

I allowed for the code to be run in the console with the user entering paths and fields for a more user 
friendly approach as well as calling the FileProcDemo() method that will run the main class with given arguments. 
It allows for flexibility and when running in console the code will catch invalid paths and arguments. 

/**********************************************************************
 * Describe any serious problems you encountered in this assignment.
 * What was hard, or what should we warn students about in the future?                    
 **********************************************************************/

My biggest issue was starting the project. I didn’t even know how to read files and there was minimal guidance from the instructor. 
After a good deal of research and testing I got the ball rolling. 
Another issue I encountered was that of using incorrect/inefficient data structures, mainly binary trees. 
After discovering hashing, life was a lot easier. It was only a matter of loading the data efficiently.



/**********************************************************************
 * List any other comments here. Feel free to provide any feedback   
 * on what you learned from doing the assignment, whether you enjoyed    
 * doing it, etc.
 **********************************************************************/

Group project next time. We would end with a much more polished code and a lot less stress lines.

/**********************************************************************
 
 **********************************************************************/

