import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

// -------------------------------------------------------------------------
/**
 * This class will all the information for a document A hashmap of integer forms
 * of the document
 *
 * @author Chris
 * @version Dec 5, 2015
 */
public class DocNode
{
    private Map<Integer, String> map;
    private String               name;


    // ----------------------------------------------------------
    /**
     * The map passed in should be a hashmap
     *
     * @param name
     *            of document
     */
    public DocNode(String name)
    {
        this.name = name;
    }


// ----------------------------------------------------------
    /**
     * The map passed in should be a hashmap
     *
     * @param map
     * @param name
     */
    public DocNode(Map<Integer, String> map, String name)
    {
        this.map = map;
        this.name = name;
    }


    // ----------------------------------------------------------
    /**
     * Returns name of document
     *
     * @return name of document
     */
    public String getName()
    {
        return name;
    }


    // ----------------------------------------------------------
    /**
     * Returns map of node
     *
     * @return hash map
     */
    public Map<Integer, String> getMap()
    {
        return this.map;
    }


// ----------------------------------------------------------
    /**
     * This method will print the key and value of the map
     */
    public void printMap()
    {
        System.out.println(this.map.toString());

    }

}
