import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class Transaction {
	private final int transactionId; //8 digits
	private int transactionType; //1 for buy, 2 for rent
	private double cost = 0;
	private String transactionDate; //changed to string
	private int status; //0 - draft, 1 - complete, 2 - cancel
	private Vector <String> serialNums;

	//add am Equipment serial to transaction
	public void addEquipment(String serialNo){
		boolean found = false;
		for (int x = 0; x < getSerialNums().size(); x++){
			if (getSerialNums().get(x) == serialNo){
				found = true;
				break;
			}
		}
		if(found == true){
			throw new InvalidOperationException(serialNo, getTransactionId(), "adding", "equipment already exist in transaction");
		}	
		serialNums.add(serialNo);
	}
	//Remove an Equipment serial from transaction
	public void removeEquipment(String serialNo){ 
		int index = -1;

		for (int x = 0; x < getSerialNums().size(); x++){
			if(getSerialNums().get(x).equals(serialNo)){
				index = x;
				break;

			}
		}
		if (index == -1){
			throw new InvalidOperationException(serialNo, getTransactionId(), "removing", "equipment does not exist in transaction");
		}
		getSerialNums().remove(index);
	}
	//sets status to complete and adds a date 
	public void completeTransaction(double total) {
		if (status ==1 || status==2)
			throw new InvalidCompletionException(transactionId,"transaction set is already set to complete or cancel");
		else {
			status = 1;
			cost = total;
			setTransactionDate();
			
		}

	}
	//sets status to cancel and adds a date
	public void cancelTransaction()	{		
		if (status ==1 || status==2)
			throw new InvalidCompletionException(transactionId,"transaction set is already set to complete or cancel");
		else {
			status=2;
		}

	}
	public Transaction(int id, int type, String serialNos)
	{
		transactionId=id;
		transactionType = type;
		serialNums = new Vector<String>();
		String s[] = serialNos.split(" ");
		for(int x=0; x < s.length; x++)
		{
			if(!s[x].isEmpty())
			{
				serialNums.add(s[x]);
			}
		}
	}
	public Transaction(String line){
		String sNums = "";
		transactionId =  Integer.parseInt(line.substring(line.indexOf("<id>") + 4, line.indexOf("</id>")));
		transactionType = Integer.parseInt(line.substring(line.indexOf("<type>") + 6, line.indexOf("</type>")));
		cost = Double.parseDouble(line.substring(line.indexOf("<cost>") + 6, line.indexOf("</cost>")));
		transactionDate = line.substring(line.indexOf("<date>") + 6, line.indexOf("</date>"));
		status = Integer.parseInt(line.substring(line.indexOf("<status>") + 8, line.indexOf("</status>")));
		sNums = line.substring(line.indexOf("<serialnums>") + 12, line.indexOf("</serialnums>"));
		serialNums = new Vector<String>();
		String s[] = sNums.split(" ");
			for (int x = 0; x < s.length; x++){
				if(!(s[x].isEmpty()))
					serialNums.add(s[x]);
			}
			
	}
	public int getTransactionId() {
		return transactionId;
	}
	public int getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(int transactionType) {
		this.transactionType = transactionType;
	}
	public double getCost() {
		return cost;
	}
	public String getTransactionDate() {
		try {
			if (!transactionDate.equals(null))
				return transactionDate;
		}catch (NullPointerException e){
			return "";
		}
		return null;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Vector<String> getSerialNums() {
		return serialNums;
	}
	public String toString(){
		String XML =  "<transaction>"
				+ "<id>" + getTransactionId() + "</id>" 
				+ "<type>" + getTransactionType() + "</type>"
				+ "<cost>" + String.format("%.2f", getCost()) + "</cost>"
				+ "<date>" + getTransactionDate() + "</date>"
				+ "<status>" + getStatus() + "</status>"
				+ "<serialnums>";
		for (int x = 0; x < getSerialNums().size(); x++){
			if (x < getSerialNums().size()-1 ){
				XML+=getSerialNums().get(x) + " ";
			}else 
				XML+=getSerialNums().get(x);
		}
		XML +="</serialnums></transaction>";
		return XML;
	}
	public Transaction clone(){
 		return new Transaction(this.toString());
	}
	private void setTransactionDate(){
		String pattern = "MM-dd-yyyy HH:mm:ss";
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		transactionDate = dateFormat.format(new Date());
	}
}
