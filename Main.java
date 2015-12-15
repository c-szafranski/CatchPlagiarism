import java.util.TreeMap;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.FileNotFoundException;

// -------------------------------------------------------------------------
/**
 * Main class to run the Plagarism catcher
 *
 * @author Chris Szafranski 
 * @version Dec 5, 2015
 */
public class Main
{
    public static int           nGram   = 0;
    public static String        path;
    public static List<Integer> Matches = new ArrayList<Integer>();
    public static int           thresh;


    // ----------------------------------------------------------
    /**
     * Main Method
     *
     * @param args {Path,nGram,threshold}
     * @throws FileNotFoundException
     */
    public static void main(String[] args)
        throws FileNotFoundException
    {
        if (args.length == 0)
            printWelcome();
        else
        {
            nGram = Integer.valueOf(args[1]);
            path = args[0];
            thresh = Integer.valueOf(args[2]);
        }

        System.out.println("nGram length: " + nGram);
        System.out.println("Path saved as: " + path);
        System.out.println("Running... ");

        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles == null)
        {
            System.out.println("Invalid Path!");
            again();
        }
        System.out.println("Adding documents to list...");

        printMap(findMatches(addFiles(listOfFiles)));
        again();

    }


// ----------------------------------------------------------
    /**
     * Runs the code. Function to be called from console
     *
     * @throws FileNotFoundException
     */
    public static void FileProcDemo(String[] s)
        throws FileNotFoundException
    {
        main(s);
    }
// ----------------------------------------------------------
    /**
     * Asks user if they would like to run program for another set
     *
     * @throws FileNotFoundException
     */

    private static void again()
        throws FileNotFoundException
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Run again?");
        System.out.println("Enter (Y) for yes");
        if (scan.next().equals("y") || scan.next().equals("Y"))
        {
            String[] s= {};
            main(s);
        }
        else
        {
            System.out.println("Thank you and goodbye");
            return;
        }
    }
// ----------------------------------------------------------
    /**
     * Prints solution
     */

    private static void printMap(Map<Integer, String> matches)
    {
        System.out.println();
        for (Map.Entry<Integer, String> entry : matches.entrySet())
        {
            System.out.println(entry.getValue() + " match: " + entry.getKey());
        }
        System.out.println("Done");

    }

// ----------------------------------------------------------
    /**
     * Solver finds matches between documents and saves them in a TreeMap
     * @param Array of DocNodes 
     *@return TreeMap<Integer,String> <Count,"Doc1 and Doc2">
     */
    private static TreeMap<Integer, String> findMatches(List<DocNode> docs)
    {
        TreeMap<Integer, String> matches = new TreeMap<Integer, String>(); //map to hold matches and match count
        System.out.println("Finding similarities...");
        for (int i = 0; i < docs.size(); i++)//going through all documents from start
        {
            Map<Integer, String> mapA = docs.get(i).getMap();//map of first doc

            for (int j = i + 1; j < docs.size(); j++)//comparing doc i with doc i+1
            {

                Iterator it = mapA.entrySet().iterator();
                Map<Integer, String> mapB = docs.get(j).getMap();//doc i+1 map
                int match = 0;

                while (it.hasNext())//comparing all hashkeys 
                {
                    Map.Entry pair = (Map.Entry)it.next();
                    if (mapB.containsKey(pair.getKey()))
                    {
                        match++;//count match
                    }

                }
                if (match > thresh)//if matches are greater than threshold add to treemap
                {
                    System.out.print(".");// indication of work being
                    String str =
                        docs.get(i).getName() + " and " + docs.get(j).getName();
                    matches.put(match, str);

                }

            }
        }

        return matches;
    }

// ----------------------------------------------------------
    /**
     * Adds all files in File[] to a new array of Document Nodes
     *@param File[] array of files 
     * @return List<DocNode> an array of DocNode objects
     */
    private static List<DocNode> addFiles(File[] listOfFiles)
        throws FileNotFoundException
    {
        int count = 0;
        List<DocNode> array = new ArrayList();
        for (int i = 1; i < listOfFiles.length; i++)
        {
            System.out.println();
            try
            {
                String name = listOfFiles[i].getName();
                System.out.print("Adding " + name + " ...");
                //creating new doc node
                DocNode doc = 
                    new DocNode(buildMap(listOfFiles[i], nGram), name);
                array.add(doc);//adding to list
                count++;
                System.out.print(" added");
            }
            catch (Exception e)
            {
                i++; //in some cases documents fail to get added
            }

        }
        System.out.println();
        System.out.println(count + " documents added");
        return array;

    }
// ----------------------------------------------------------
    /**
     * Welcome routine
     *
     */

    private static void printWelcome()
    {

        System.out.println("***************Plagiarism catcher****************");
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter path to files: ");
        path = scan.nextLine();
        System.out.println("Enter length of n-gram (n) : ");
        while (!scan.hasNextInt())//in case invalid input is entered
        {
            try
            {
                nGram = scan.nextInt();
            }
            catch (InputMismatchException e)
            {
                System.out.println("Type Error: please enter valid nGram");
                System.out.println();
                scan.next();
            }

        }
        nGram = scan.nextInt();
        System.out.println("Please enter threshold of matches as an integer: ");
        while (!scan.hasNextInt())//in case invalid input is entered
        {
            try
            {
                thresh = scan.nextInt();
            }
            catch (InputMismatchException e)
            {
                System.out.println("Type Error: please enter valid threshold");
                System.out.println();
                scan.next();
            }

        }
        thresh = scan.nextInt();
    }


    // ----------------------------------------------------------
    /**
     * Builds hashmap of document
     *
     * @param docPath
     *            name of document in question
     * @param x
     *            length of n-gram
     * @return hash map of document
     * @throws FileNotFoundException
     */
    public static Map<Integer, String> buildMap(File f, int x)
        throws FileNotFoundException
    {

        Map<Integer, String> map = new HashMap<Integer, String>();
        Scanner input = new Scanner(f);

        int count = 0;

        WordQueue<String> q = new WordQueue<String>(x);
        for (int i = 0; i < x; i++)
        {
            try
            {
                String s = input.next();
                q.enque(s); // enque the first x members
                count = 1;
            }
            catch (Exception e)
            {
                //in case an unhandled character is in document
                q.enque("char"); // enque the first x members
                count = 1;
            }
        }
        // at this point we have the first queue ready
        // we must add it to hash map

        map.put(q.toString().hashCode(), q.toString());

        while (input.hasNext())
        {

            q.deque();
            q.enque(input.next());
            map.put(q.toString().hashCode(), q.toString());
            count++;
        }

        return map;

    }
}
