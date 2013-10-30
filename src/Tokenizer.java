import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Tokenizer {

	private Matcher m;
	private Pattern p;
	String line;
	
	public Tokenizer(String line)
	{
		this.p = Pattern.compile("[\\w']+");
		this.m = p.matcher(line);
		this.line = line;
	}
	
	public String getToken()
	{
		if(m.find()) 
		    return line.substring(m.start(), m.end());
		return null;
	}
}
