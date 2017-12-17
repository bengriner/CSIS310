import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class that receives a list of files or takes input from System.in and writes
 * a random character sequence of the specified argument length based upon the
 * seed length argument(s) given. Currently only outputs the data to system.out. 
 */
public class RandomWriter
{
   private static final int VALUE_EOF = -1;

   /**
    * Method that accepts a HashMap of seeds with a corresponding ArrayList of
    * characters that have followed the seed in the input.
    * 
    * TODO: Implement the printStream method to make the output more versatile
    * @param seedMap
    *     HashMap of seeds with an ArrayList of Characters that follow seed   
    * @param outputLength
    *     the number of Characters to output        
    * @throws IOException if there is an error when writing out
    */
    private static void randomWrite(
            HashMap<String, ArrayList<Character>> seedMap, int outputLength)
    {
       StringBuilder seed = new StringBuilder();
       String[] keys;
       char currentChar;
       int i = 0;

       // Add all of the keys to our keys ArrayList
       keys = seedMap.keySet().toArray(new String[0]);

       // Get the initial random seed
       seed.append(keys[(int) (Math.random() * keys.length)]);

       // Loop until we have printed outputLength number of characters
       while (i < outputLength)
       {
          // If the current seed exists in our collection of the seeds
          if (seedMap.get(seed.toString()) != null)
          {
             // Gets a random character from this seed's ArrayList of chars
             currentChar = seedMap.get(seed.toString()).get((int) 
            		  (Math.random() * seedMap.get(seed.toString()).size()));

             // If we have a null value for our currentChar
             if (Integer.valueOf(currentChar) != 0)
             {
                // Update the seed so the current char 
                seed.deleteCharAt(0);
                seed.append(currentChar);

                // Write out the current char.
                System.out.print(currentChar);

                // Iterate because we have just written out a character
                i++;
             }
          }
          // Else if the current seed doesn't exist in our collection
          else
          {
             // Get another random seed
             seed = new StringBuilder();
             seed.append(keys[(int) (Math.random() * keys.length)]);
          }
       }
    }
   
   
   /**
    * Method to add to the current seed and following char map passed as an
    * argument. Using the input source and seed length given in the arguments
    * 
    * @param seedAndFollowingCharMap
    *     the map of seeds and corresponding characters that can follow          
    * @param inputSource
    *     the source of input we will be reading from         
    * @param seedLength
    *     the size of the seeds we will derive from our input source to
    *     store in our map of seeds and corresponding characters
    * @return the updated map of seeds and corresponding characters
    * @throws IOException
    *     if an error occurs during the reading of the file
    */
    private static HashMap<String, ArrayList<Character>> buildCharMap(
            HashMap<String, ArrayList<Character>> seedMap,
            InputStream inputSource, Integer seedLength) throws IOException
    {
        // Set currentChar to the next character byte
        int currentChar = inputSource.read();
        StringBuilder currentSeed = new StringBuilder();
     
        // While we are not at the end of the file
        while (currentChar != VALUE_EOF && currentSeed.length() < seedLength)
        {
           // Get the first seedLength characters add currentChar to seed
           currentSeed.append((char) currentChar);

           // Set currentChar to the next character byte
           currentChar = inputSource.read();
        }

        // While at the end of the file (using the EOF character)
        while (currentChar != VALUE_EOF)
        {
           //CASE: doesn't have a current seed in the seedCharMap
           if (!seedMap.containsKey(currentSeed.toString()))
           {
              // Add the current seed to the seedMap
              seedMap.put(currentSeed.toString(), new ArrayList<Character>());
           }

           // Get the current seed from the seedMap and add to ArrayList
           seedMap.get(currentSeed.toString())
               .add(Character.valueOf((char) currentChar));

           // Update the current seed
           currentSeed = currentSeed.deleteCharAt(0);
           currentSeed = currentSeed.append((char) currentChar);
           currentChar = inputSource.read();
        }   
        return seedMap;
    }


   /**
    * Program that provides a random writing application based upon an integer
    * passed as an argument in args indicating the length of the seed to begin.
    * The output length is determined from the output length argument
    */   
    public static void main(String[] args)
    { 
       final int MIN_LENGTH_FILE_NAME = 2;
       final int MIN_LENGTH_SEED = 1;
       final int MIN_LENGTH_OUTPUT = 1;
	
       HashMap<String, ArrayList<Character>> seedMap = new HashMap<>();
       InputStream inputSource = new BufferedInputStream(System.in);
       int seedLength = 0;
       int outputLength = 0;
	
       // If the user did not provide enough arguments display error message
       if (args.length < (MIN_LENGTH_OUTPUT + MIN_LENGTH_SEED))
       {
           System.err.println("Not enough arguments provided. Two ints separated"
              + " by a space must be provided with optional file names");
           System.exit(1);
       }
       // If we have at least enough arguments
       else if (args.length >= MIN_LENGTH_FILE_NAME)
       {
          // Validate the seed length argument
          try
          {
             // Try to get the first arg as an integer
             seedLength = Integer.valueOf(args[0]);
	
             // Validate that the length is greater than MIN_LENGTH_SEED
             if (seedLength < MIN_LENGTH_SEED)
             {
                System.err.println("You do not have permission to access "
                   + "one or more of the requested files");
	            System.exit(1);
             }
          }
          catch (NumberFormatException e)
          {
             System.err.println("You do not have permission to access "
                + "one or more of the requested files");
             System.exit(1);
          } 
	      
          // Now validate the output length given
          try
          {
             // Try to get the second arg as an integer
             outputLength = Integer.valueOf(args[1]);
	
             // Validate that the length is greater than MIN_LENGTH_OUTPUT
             if (outputLength < MIN_LENGTH_OUTPUT)
             {
                System.err.println("You must enter an output length "
                   + "that is an integer greater than 0");
                System.exit(1);
             }
          }
          catch (NumberFormatException e)
          {
             System.err.println("You must enter an output length that"
                + " is an integer greater than 0");
             System.exit(1);
          }
	     
          // CASE: There are files to  be read
          if (args.length > MIN_LENGTH_FILE_NAME)
          {
             try
             {
                // Handles loading and reading multiple files
                for (int i = MIN_LENGTH_FILE_NAME; i < args.length; i++)
                {
                   // Open the file(s) given.
                   inputSource = new FileInputStream(new File(args[i]));

                   // Update the map of the seeds
                   seedMap = buildCharMap(seedMap, inputSource, seedLength);
                }
             }
             catch (FileNotFoundException e)
             {
                System.err.println("One or more of the files can not be found. "
                   + "Please verify that the files are in the correct location");
                System.exit(1);
             }
             catch (IOException e)
             {
                System.err.println("An error occured while reading the file");
                System.exit(1);
             }
          }

          // CASE: No files to read, use inputstream reader
          else if (args.length == MIN_LENGTH_FILE_NAME)
          {
             try
             {
                // Update the map of the seeds and the characters
                seedMap = buildCharMap(seedMap, inputSource, seedLength);
             }
             catch (IOException e)
             {
                System.err.println("An error occured while reading the file");
                System.exit(1);
             }
          }
          
          // Perform our random writing of length outputLength
          randomWrite(seedMap, outputLength);
       }
    }
}