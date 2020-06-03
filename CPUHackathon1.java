import java.io.*;
import java.util.*;
import java.text.DecimalFormat;
import org.json.simple.JSONObject; 
import org.json.simple.JSONArray; 

public class CPUHackathon1 {

	public static void main(String[] args) {
		

		FileReader fr = new FileReader("D:\\Hackathon\\Requirement1\\CPU.txt");

 
   		int lines=0;

   
  
  		 BufferedReader br = new BufferedReader(fr);
  		 String s;              
  	    while((s=br.readLine())!=null)    
   		   {
   	      lines++;          
            
    		  }

   		fr.close();

		String text = "";
		


		ArrayList<Double> values=new ArrayList<Double>();

		try {
			FileReader readfile = new FileReader("D:\\Hackathon\\Requirement1\\CPU.txt");
			BufferedReader readbuffer = new BufferedReader(readfile);
			
			for (int i = 1; i <= lines; i++) {
				text = readbuffer.readLine() + "\n";
				text = text.replaceAll("( )+", " ");
				String[] str=text.split(" ");
				String value=str[8].toString();
				
				values.add(Double.parseDouble(value));	
			}
			
			readbuffer.close();
		}
		catch(FileNotFoundException e){e.printStackTrace();}
		catch(IOException e){e.printStackTrace();}
		

		double avg=0;
		double max=0;
		
		DecimalFormat df = new DecimalFormat("#0.0"); 
		DecimalFormat df1 = new DecimalFormat("#0.00"); 
		

		for(int i=0;i<values.size();i++)
		{
			avg=avg+values.get(i);
			if(values.get(i)>max)
			{
				max=values.get(i);
			}
		}
		max = Double.valueOf(df1.format(max));
		avg = avg/values.size();
		avg = Double.valueOf(df1.format(avg));
		
		
		JSONObject obj = new JSONObject();
		JSONObject obj1 = new JSONObject();
		
		
		Map m = new LinkedHashMap(values.size()); 
	       
		for(int i=0;i<values.size();i++)
		{	
			 m.put((i+1)+"s", values.get(i)); 
		}
		obj1.put("values", m);
		obj1.put("maxcpu", max);
		obj1.put("avgcpu", avg);
		
		obj.put("sampletransaction", obj1);
		
		JSONArray ja = new JSONArray(); 
		
		ja.add(obj);
		
		
		
		try(FileWriter file = new FileWriter("output1.json"))
		{
			file.write(ja.toString());
			file.flush();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		
		System.out.println(ja);
		
	}


}