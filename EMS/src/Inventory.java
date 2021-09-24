import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class Inventory {
	private ArrayList<Equipment> inventory;
	private String dirFileName;
	//1 Add equipment object to the inventory (objects with duplicate serial numbers are not allowed; needs to add clone so UI cannot change object directly)
	//adds equipment to inventory
	public void addEquipment(Equipment eqmt)
	{
		boolean found = false;
		for (int x = 0; x < getInventory().size(); x++){
			if((getInventory().get(x).getSerialNo().equals(eqmt.getSerialNo()))){
				found = true;
				break;
			}
		}
		if (found == true){
			throw new InvalidOperationException(eqmt.getSerialNo(), "adding", "already exist in inventory");
		}else{
			inventory.add(eqmt);
		}
		save();	
	}
	//2 Remove equipment object from the inventory using equipment serial (cannot be removed if status is not available)
	//removes equipment from inventory
	public void removeEquipment(String serialNo)
	{
		int index = -1;
		for (int x = 0; x < getInventory().size(); x++){
			if(getInventory().get(x).getSerialNo().equals(serialNo)){
				index = x;
				break;
			}
		}
		if (index == -1){
			throw new InvalidOperationException(serialNo, "removing",  "it does not exist in inventory");
		}
		inventory.remove(index);
	}
	//saves inventory to file
	private void save()
	{
		String invXML = "";
		for (int x = 0; x < getInventory().size(); x++){
			if (x < getInventory().size()-1)
				invXML+=getInventory().get(x).toString() +"\n";
			else
				invXML+=getInventory().get(x).toString();
		}
		try{
			BufferedWriter out = new BufferedWriter(new FileWriter(dirFileName, false));
			//out.write("\n"+eqmtXML);
			out.write(invXML);
			out.close();
		}catch(IOException e){
			throw new InvalidLoadException(dirFileName);
		}		
	}
	//loads data from file to inventory ArrayList
	private void load()
	{
		try	{
			//checks to see if file exist. if not throws an InvalidLoadException
			if (!(new File(dirFileName)).exists())					
				throw new InvalidLoadException(dirFileName);				
			FileReader reader = new FileReader(dirFileName);
			BufferedReader bufferedReader = new BufferedReader(reader);			
			String line;
			Vector <String> s = new Vector<String>();
			while((line = bufferedReader.readLine()) != null){
				s.add(line);
			}
			bufferedReader.close();
			reader.close();
			for(int x = 0; x<s.size();x++){
				if(s.get(x).startsWith("<treadmill>"))
					addEquipment(new Treadmill(s.get(x)));
				else if (s.get(x).startsWith("<stepper>"))
					addEquipment(new Stepper(s.get(x)));

				else if (s.get(x).startsWith("<stationarybike>"))
					addEquipment(new StationaryBike(s.get(x)));
				else
					throw new InvalidLoadException("data parsing ", s.get(x));
			}
	
		}catch(IOException e){
			System.err.println(e.getClass().getSimpleName() + ": IOException error1");
		}
	}
	public double calculateEquipmentShipping(String serialNo)
	{
		for(int x=0; x<inventory.size();x++)
		{
			if(inventory.get(x).getSerialNo() == serialNo)
				return inventory.get(x).calculateShipping();
		}
		throw new InvalidEquipmentException(serialNo,"does not exist in inventory");
	}
	public Inventory(String dirFileName)
	{
		inventory = new ArrayList<Equipment>();
		this.dirFileName = dirFileName;
		load();
	}
	
	public ArrayList <Equipment> getInventory()
	{
		return inventory;
	}
	
	public String toString()
	{
		String listString = "";
		for(Equipment e: inventory)	{
			listString+=(e +"\n");
		}
		return listString;
	}
	public Inventory clone()
	{
		Inventory inv = new Inventory(dirFileName);
		return inv;
	}

}
