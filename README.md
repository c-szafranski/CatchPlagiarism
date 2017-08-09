# CatchPlagiarism


#Documents
 *  README.md                                                   
 *  -Main.java
 *  -DocNode.java
 *  -WordQueue.java
 *  
#License 
Free to use and distribute modify and enhance with due credit given to it's author Christopher Szafranski 
#Author Chris Szafranski Dec. 5th 2015


#Brief
This program takes a string Path to a directory in which you would like to compare .txt files.
The second parameter is an nGram where 'n' is the word sample size. 
This integer number should be between 5-9 for best results
The last parameter is an integer threshold which indicates at how many nGram matches to flag a document as a match

#Run
#Mac OS 
As of OS X 10.2 the java compiler is native to the OS. Assuming you have at least OS X 10.2 installed to compile this project open up Terminal and navigate into the project directory. 
Run 
>javac Main.java DocNode.java WordQueue.java 
>java Main [String Path, int nGram, int threshold]

or run without parameters to be guided through each step by the program i.e
>java Main

#Windows
Same deal as Mac OS but you might have to download the java sdk and place it in your enviorment variables before continuing on to run the commands listed above. 


#Implementation
This Java program implements Hashmaps, TreeMaps, Queues, and ArrayList data structures to find n-Gram matches between .txt documents. These nGrams are essentially an 'n' is how many consecutive words are sampled for comparison. 
This allows a certain measure for tollerance in detecting plagerism. The third parameter is a threshold of how many matches two documents must have before they get flagged as plagerised. The first parameter is a path to the folder 
/directory to the .txt files to be tested.

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



#Future Feartures
* Acceptance of various file types (docx,doc, md, exc.)
* UI interface (maybe)
* Scaling for larger data sets 
