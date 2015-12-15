# CatchPlagiarism


/**********************************************************************
 *  README.txt                                                   
 *  Plagiarism Catcher
 *  Classes:
 *  -Main.java
 *  -DocNode.java
 *  -WordQueue.java
 *  
 **********************************************************************/
<Chris Szafranski>
/**********************************************************************
Running from command line: call function FileProcDemo({String pathToFiles,String nGram,String sensitivity}) in Main.java 

This Java program implements Hashmaps, TreeMaps, Queues, and ArrayList data structures to find n-Gram matches between .txt documents. 
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
matches are counted and if the total count for a document pair is greater than the threshold that pair and it's match number
are added to a TreeMap which is later printed in order from least to greatest number of matches. 


I allowed for the code to be run in the console with the user entering paths and fields for a more user 
friendly approach as well as calling the FileProcDemo() method that will run the main class with given arguments. 
It allows for flexibility and will catch invalid paths and arguments. 



/**********************************************************************
 
 **********************************************************************/

