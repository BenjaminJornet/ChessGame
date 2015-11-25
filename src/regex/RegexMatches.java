package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Coord;

public class RegexMatches
{
	public static void convert(String line,Coord a,Coord b) throws Exception{


		String pattern = "\\((\\d+),(\\d+)\\)\\s*->\\s*\\((\\d+),(\\d+)\\)";

		// Create a Pattern object
		Pattern r = Pattern.compile(pattern);

		// Now create matcher object.
		Matcher m = r.matcher(line);
		if (m.find( )) {
			a.x=Integer.valueOf( m.group(1) ).intValue();
			a.y=Integer.valueOf(m.group(2) ).intValue();
			b.x=Integer.valueOf(m.group(3) ).intValue();
			b.y=Integer.valueOf( m.group(4) ).intValue();
		} else {
			throw new Exception("NO MATCH");
		}
	}
	public static void main( String args[] ){
		Coord a = new Coord(-1,-1);
		Coord b = new Coord(-1,-1);
		String str = "(10,120)->(150,17)";
		try {
			convert(str,a,b);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(a);
		System.out.println(b);
	}
}