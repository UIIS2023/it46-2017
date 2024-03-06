package logsParsing;

import java.awt.Color;
import java.util.Arrays;

public class LogParserUtils {
	public static Color createColorFromString(String s) {
		String[] RGBs = s.split("[-\\[\\]]"); // Regex to match values
		RGBs = Arrays.copyOfRange(RGBs, 1, 4); // Remove white space before values
		System.out.println("LogParserUtils->createColorFromString()->RGB" + " "+ RGBs.toString());

		return new Color(Integer.parseInt(RGBs[0]), Integer.parseInt(RGBs[1]), Integer.parseInt(RGBs[2]));
	}
	
	
	public static String creteColorString(String s) {
		String secondPart = s.split("\\[")[1].toLowerCase();
		System.out.println("LogParserUtils->createColorString->SecondPart je" + " " + secondPart);
		String r = secondPart.split(",")[0].toLowerCase();
		
		r=r.replace("=","");
		r=r.replace("r","");
		System.out.println("r je" + r);
		
		
		
		String g = secondPart.split(",")[1].toLowerCase();
		g=g.replace("g=", "");
		System.out.println("g je"+  " "+ g);
		
		
		String b = secondPart.split(",")[2].toLowerCase();
		b=b.replace("b=", "");
		b=b.split("\\]")[0].toLowerCase();
		System.out.println("b je" + " " + b);
		System.out.println("LogParserUtils->creteColorString()->RGB-moj" + " " +"[" + r + "-" + g + "-" + b + "]");
		return "[" + r + "-" + g + "-" + b + "]";
	}
}
