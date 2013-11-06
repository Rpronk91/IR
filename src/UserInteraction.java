import indexer.LineSplitter;
import util.StringNormalizer;

import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;

// TODO if nothing else is needed of this code, it can be deleted.
public class UserInteraction 
{
	private Hashtable<String, Frequency> invertedIndex = 
			new Hashtable<>();
	private StringNormalizer normalizer = new StringNormalizer();
		
	public UserInteraction(String[] args) throws IOException
	{	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input;
		if(args.length == 0){
			System.out.print("Enter query: ");
			input = br.readLine();
		} else
			input = args[0];
		parseInput(input);
		parseCollection();
		ArrayList<String> BMfiles;
		BMfiles = returnBestMatch(input);
		System.out.println("matches found (unranked): ");
		for(String file : BMfiles.toArray(new String[BMfiles.size()]))
		{
			System.out.println(file);
		}
	}
	
	public static void main(String[] args) throws IOException 
	{
		new UserInteraction(args);
	}
	
	public void parseInput(String input)
	{
		String token;
		LineSplitter tokenizer = new LineSplitter(input);
		token =  tokenizer.getToken();
		System.out.print("matching on: ");
		while ( token != null ) {
			if(!token.contentEquals("AND") && !token.contentEquals("OR") 
								&& !token.contentEquals("NOT")) {
				System.out.print(token+ " ");
				token = normalizer.normalize(token);
				invertedIndex.put(token, new Frequency());
			}
		    token =  tokenizer.getToken(); 
		}
		System.out.print("\n");
	}
	
	public void parseCollection() throws IOException
	{
		File[] files;
		File dir = new File("collection");
    	files = dir.listFiles(new FilenameFilter() { 
    	         public boolean accept(File dir, String filename)
    	              { return filename.endsWith(".txt"); }});
    	String filename;
		String line;
		String token;
		FileReader freader;
		LineNumberReader lnreader;
    	for(File file : files )
    	{
			freader = new FileReader(file);
			lnreader = new LineNumberReader(freader);
			line = lnreader.readLine();
			while(line != null)
			{
				LineSplitter tokenizer = new LineSplitter(line);
				token =  tokenizer.getToken();
				while ( token != null ) {
					token = normalizer.normalize(token);
				    if(invertedIndex.containsKey(token))
				    {
				    	filename = file.getName().substring(0, 17);
				    	invertedIndex.get(token).updateFrequency(filename);
				    }
				    token =  tokenizer.getToken();
				}
				line = lnreader.readLine();
			}
    	}
	}
	
	//return the files where all query tokens match
	public ArrayList<String> returnBestMatch(String input)
	{
		ArrayList<String> BMfiles = new ArrayList<String>();
		//find smallest inverted index
		double smallestTable = Double.POSITIVE_INFINITY;
		double TableLength;
		String bestStartKey = null;
		for(String queryWord : invertedIndex.keySet())
		{
			TableLength = invertedIndex.get(queryWord).getTableLength();
			if (TableLength < smallestTable)
			{
				bestStartKey = queryWord;
				smallestTable = TableLength;
			}
		}
		int sumWordCount = 0;
		//for all files containing a certain token (bestStartkey)
		for(String fileName : invertedIndex.get(bestStartKey).getKeySet())
		{
			for(String queryWord : invertedIndex.keySet())
			{
				sumWordCount += invertedIndex.get(queryWord).containsKey(fileName);
			}
			//all words in query are in the file
			if(sumWordCount == invertedIndex.keySet().size())
			{
				BMfiles.add(fileName);
			}
			sumWordCount = 0;
		}
		
		return BMfiles;
	}
	
}