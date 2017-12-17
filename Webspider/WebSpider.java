import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.net.URL;

/**
 * Program gets a URL and returns a count of all of its pages 
 * and external links.
 */
public class WebSpider 
{   
   /**
    * Crawls a web page separating the links on a page to external links
    * and pages in the domain to be visited. Each of the child pages are
    * visited until all the children have been visited.
    * 
    * @param args a URL string to be traversed
    * 
    * @return an array of integers for the number of pages traversed and
    * the number of external links
    * 
    * @throws MalformedURLException throws exception if the URL is improperly
    * constructed
    * 
    * @throws IOException Exception occurs if the website is unable to be 
    * visited
    */
    private static int[] webCrawler(String[] args) 
            throws MalformedURLException, IOException
    {
        HashSet<URL> pagesVisited = new HashSet<URL>();
        HashSet<URL> externalLinks = new HashSet<URL>();
        ArrayDeque<URL> pagesToVisit = new ArrayDeque<URL>();
        HTMLLinks page;
        URL pageURL;
        String domain = args[0];
        
        // Load the queue with the domain by adding to the beginning
        pagesToVisit.push(new URL(domain));
                
        //loops through the web pages in the domain
        while(!pagesToVisit.isEmpty())
        {
            // Remove the beginning item of the queue which is at the end
            pageURL = pagesToVisit.removeLast();
            
            // Use the HTMLLinks class to return a page of links
            page = new HTMLLinks(pageURL);
            
            // Add the Visited page to the pages visited HashSet
            pagesVisited.add(pageURL);
            
            // Iterates through links on a Web Page
            for(URL aURL : page.getLinks())
            {   
            	    
                // Determine if a link is in the domain or external
                if (aURL.toString().contains(domain)) 
                {
                    // Check to see if a repaired page name is in the 
                    // queue or HashSet of visited pages
                    if (!pagesToVisit.contains(aURL) 
             				&& !pagesVisited.contains(aURL))
                    {
                        // Add the URL to pagesToVisit HashSet
                        pagesToVisit.push(aURL);             
                    }                          
                }
                else 
                {
                    externalLinks.add(aURL);
                }
            }  
        }
        
        return new int[]{pagesVisited.size(), externalLinks.size()};
    }
    
    
   /**
    * Runs the web crawler from the command line terminal, accepting a
    * single argument of a complete http://xxxxxxxx.domain. Throws an
    * exception if the URL is malformed or broken.
    * 
    * @param args a complete URL to be visited
    */
    public static void main(String[] args)
    {
        int[] webCrawlerOutput = new int[2];
    		
        // Kills the program if there aren't enough website arguments
        if(args.length < 1)
        {
            System.err.print("Insufficient arguments");
            System.exit(-1);
        }
        
        // Try running Web Crawler and print the results to System.out
        try
        {
            webCrawlerOutput = webCrawler(args);
            System.out.println(webCrawlerOutput[0] + " Web Pages");
            System.out.println(webCrawlerOutput[1] + " External Links");
        }
        
        // Exception occurs if the URL is improperly formed
        catch(MalformedURLException e)
        {
            System.err.println("Illegal URL argument");
            System.exit(-1);
        }
        
        // Exception occurs if page cannot be accessed
        catch(IOException e)
        {
            System.err.println("Network Error");
            System.exit(-1);
        }   
    }    
}