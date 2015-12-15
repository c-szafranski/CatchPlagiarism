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
Author: Chris Szafranski
/**********************************************************************
Brief: This package will detect plagiarism between .txt documents and return a list of most similar documents


Implementation: 
This Java program implements Hashmaps, TreeMaps, Queues, and ArrayList data structures to find n-Gram matches between .txt documents. 

Upon starting a welcome message will appear and the user will enter a path to a folder and an n-gram length (n) and a threshold for matches. 
The program will detect invalid paths and will detect a non-integer entry for the n-Gram length. 
Files are stored in DocNodes which consist of a HashMap and a doucment name.
The nodes are constructed as follows:
Using a queue of n length a hashmap is built where the Value is the n-Gram queue and the Key is the hashcode of the n-Gram string. 
In other words the hashmap maps <Integer,String>. 
Once all document nodes are built the program will introduce an iterator that takes the key of the hashmap in 
question and searches for that key in the other mashmaps of all the other nodes using .containsKey();
In the loop that checks if the key exists in the other maps, 
matches are counted and if the total count for a document pair is greater than the threshold that pair and it's match number
are added to a TreeMap which is later printed in order from least to greatest number of matches. 


I allowed for the code to be run in the console with the user entering paths and fields for a more user 
friendly approach as well as calling the FileProcDemo() method that will run the main class with given arguments. 
It allows for flexibility and will catch invalid paths and arguments. 

Running from command line: call function FileProcDemo({String pathToFiles,String nGram,String sensitivity}) in Main.java 



/**********************************************************************
 end 
 **********************************************************************/

