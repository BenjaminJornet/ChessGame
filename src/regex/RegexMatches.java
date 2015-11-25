package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatches
{
    public static void main( String args[] ){

      // String to be scanned to find the pattern.
      String line = "(10,120)->(150,17)";
      String pattern = "\\((\\d+),(\\d+)\\)\\s*->\\s*\\((\\d+),(\\d+)\\)";

      // Create a Pattern object
      Pattern r = Pattern.compile(pattern);

      // Now create matcher object.
      Matcher m = r.matcher(line);
      if (m.find( )) {
         System.out.println("Found value: " + m.group(1) );
         System.out.println("Found value: " + m.group(2) );
         System.out.println("Found value: " + m.group(3) );
         System.out.println("Found value: " + m.group(4) );
         System.out.println("finish");


      } else {
         System.out.println("NO MATCH");
      }
   }
}