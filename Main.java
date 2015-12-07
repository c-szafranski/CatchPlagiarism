
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
 * @author Chris Szafranski ID:877380293
 * @version Dec 5, 2015
 */
public class Main
{ // C:\\Users\\Chris\\Desktop\\small_set
    public static int           nGram   = 0;
    public static String        path;
    public static List<Integer> Matches = new ArrayList<Integer>();
    public static int           thresh;


    // ----------------------------------------------------------
    /**
     * Main Method
     *
     * @param args
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

        List<DocNode> docs = addFiles(listOfFiles);

        Map<Integer, String> matches = findMatches(docs);

        printMap(matches);
        again();

    }


// ----------------------------------------------------------
    /**
     * Runs the code
     *
     * @throws FileNotFoundException
     */
    public static void FileProcDemo(String[] s)
        throws FileNotFoundException
    {
        main(s);
    }


    private static void again()
        throws FileNotFoundException
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Run again?");
        System.out.println("Enter (Y) for yes");
        if (scan.next().equals("y") || scan.next().equals("Y"))
        {
            main(null);
        }
        else
        {
            System.out.println("Thank you and goodbye");
            return;
        }
    }


    private static void printMap(Map<Integer, String> matches)
    {
        System.out.println("Done");

    }


    private static Map<Integer, String> findMatches(List<DocNode> docs)
    {
        Map<Integer, String> matches = new HashMap<Integer, String>();
        System.out.println("Finding similarities...");
        for (int i = 0; i < docs.size(); i++)
        {
            Map<Integer, String> mapA = docs.get(i).getMap();

            for (int j = i + 1; j < docs.size(); j++)
            {

                Iterator it = mapA.entrySet().iterator();
                Map<Integer, String> mapB = docs.get(j).getMap();
                int match = 0;
/*
 * System.out.println("New doc is " + docs.get(j).getName() +
 * " Matches reset to " + match);
 */
                while (it.hasNext())
                // for (Integer k : mapA.keySet())
                {
                    // Map.Entry pair = (Map.Entry)it.next();
                    // System.out.print("Key in question: " + pair.getKey());
                    Map.Entry pair = (Map.Entry)it.next();
                    if (mapB.containsKey(pair.getKey()))
                    {
                        match++;
                    }

                }
                if (match > thresh)
                {

                    System.out.println(docs.get(i).getName() + " and "
                        + docs.get(j).getName() + " match: " + match);
                    Matches.add(match);

                }

                String str =
                    docs.get(i).getName() + " and " + docs.get(j).getName();
                matches.put(match, str);
            }
        }

        return matches;
    }


    private static List<DocNode> addFiles(File[] listOfFiles)
        throws FileNotFoundException
    {
        int count = 0;
        List<DocNode> array = new ArrayList();
        for (int i = 1; i < listOfFiles.length; i++)
        {
            try
            {
                String name = listOfFiles[i].getName();
                System.out.println("Adding " + name);

                DocNode doc =
                    new DocNode(buildMap(listOfFiles[i], nGram), name);
                array.add(doc);
                count++;
            }
            catch (Exception e)
            {
                i++;
            }
        }
        System.out.println(count + " documents added");
        return array;

    }


    private static void printWelcome()
    {

        System.out.println("***************Plagiarism catcher****************");
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter path to files: ");
        path = scan.nextLine();
        System.out.println("Enter length of n-gram (n) : ");
        while (!scan.hasNextInt())
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
        while (!scan.hasNextInt())
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
                // System.out.println("Wierd ass character detected!");
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
