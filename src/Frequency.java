import java.util.Hashtable;
import java.util.Set;

public class Frequency {
	
	private Hashtable<String, Double> frequencies = 
			new Hashtable<String, Double>();
	
	public Frequency(){		
	}
	
	public double getFrequency(String ObjectID)
	{
		if (frequencies.containsKey(ObjectID))
			return frequencies.get(ObjectID);
		else
			return 0.0;
	}
	
	public int getTableLength()
	{
		return frequencies.size();
	}
	
	public int containsKey(String key)
	{
		if(frequencies.containsKey(key))
			return 1;
		return 0;
	}
	
	public Set<String> getKeySet()
	{
		return frequencies.keySet();
	}
	
	public void updateFrequency(String ObjectID)
	{
		if (frequencies.containsKey(ObjectID))
			frequencies.put(ObjectID, frequencies.get(ObjectID)+1);
		else
			frequencies.put(ObjectID, 1.0);
	}
	
}
