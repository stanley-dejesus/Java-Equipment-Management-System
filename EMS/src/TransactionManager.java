import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class TransactionManager {
	private String dirFileName;
	public ArrayList<Transaction> transactions;
	
	//3 Add transaction (objects with duplicate transaction id are not allowed; needs to add clone so UI cannot change object directly )
	//Adds new transaction objects to transactions
	public void addTransaction(Transaction transaction){
		boolean found = false;
		for (int x = 0; x < getTransactions().size(); x++){
			if((transactions.get(x).getTransactionId() == transaction.getTransactionId())){
				found = true;
				break;
			}
		}
		if (found == true){
			throw new InvalidOperationException(transaction.getTransactionId(), "adding", "it already exist");
		}
		else 
			transactions.add(transaction);
		save();
	}
	//Stores transactions into its data file
	private void save(){
		//String transXML = toString();
		String transXML = "";
		for(int x = 0; x < getTransactions().size(); x++){
			if(x < getTransactions().size()-1)
				transXML+=getTransactions().get(x).toString() + "\n";
			else
				transXML+=getTransactions().get(x).toString();
		}
			try{
				BufferedWriter out = new BufferedWriter(new FileWriter(dirFileName, false));
				out.write(transXML);
				out.close();
				
			}catch(IOException e){
				e.printStackTrace();
			}
	}
	//retrieves transactions data from file and loads it to ArrayList where all objects are stored.
	private void load()	{
		try	{
			//checks to see if file exist. if not throws an InvalidLoadException
			if (!(new File(dirFileName)).exists())					
				throw new InvalidLoadException(dirFileName);
			FileReader reader = new FileReader(dirFileName);
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line;
			Vector <String> t = new Vector<String>();			
			while((line = bufferedReader.readLine()) !=null){
				if(line == "")
					break;
				else
					t.add(line);
			}
			bufferedReader.close();
			reader.close();
			for (int x=0; x<t.size(); x++){
				addTransaction(new Transaction(t.get(x)));
			}
		}catch(IOException e){
			System.err.println(e.getClass().getSimpleName() + ": IOException error");
		}
	}
	//Sets transaction to complete status; locates transaction and invokes completeTransaction and sends
	//Locates Transaction from list and invokes its completeTransaction method. 
	//completeTransaction method ensures status is not set to complete/cancel (exception thrown if set) & sets transaction date.
	//invokes save method.	
	public void completeTransaction(int transactionId, double total){
		for (int x=0; x < getTransactions().size(); x++){
			if (transactions.get(x).getTransactionId() == transactionId){
				transactions.get(x).completeTransaction(total);
				break;
			}
		}
		save();
	}
	public TransactionManager(String dirFileName){
		transactions = new ArrayList<Transaction>();
		this.dirFileName = dirFileName;
		load();
	}
	//returns clones of each transaction object
	public  ArrayList <Transaction> getTransactions(){
		return transactions;
	}
	//4 Cancel transaction (cannot be cancelled if completed)
	//Sets transaction to cancel status; invoke transaction's cancelTransaction method() to set status to cancel.
	public void cancelTransaction(int transactionId){
		for (int x=0; x < getTransactions().size(); x++){
			if (transactions.get(x).getTransactionId() == transactionId){
				transactions.get(x).cancelTransaction();
				break;
			}
		}
		save();
	}
	//This method updates the transactionType by locating the transaction and changing the transaction type.
	public void updateTransactionType(int transactionId, int transactionType) {
//		Transaction uTrans;
		for (int x=0; x < transactions.size(); x++)
		{
			if (transactions.get(x).getTransactionId() == transactionId)
			{
				transactions.get(x).setTransactionType(transactionType);
				break;
			}
		}
		save();
	}
	//6 Add equipment to transaction (equipment with duplicate ids are not allowed; cannot add if transaction is completed or cancelled)
	//Adds new serial number to transaction by locating the ID and invoking the trans addEquipment method.
	public void addEquipment(int transactionId, String serialNo)	{
//		Transaction uTrans;
		for (int x=0; x < transactions.size(); x++)	{
			if (transactions.get(x).getTransactionId() == transactionId){
				transactions.get(x).addEquipment(serialNo);
				break;
			}
		}
		save();
	}
	//7 Remove equipment from transaction (cannot remove if transaction is completed or cancelled)
	//Removes serial from transaction by locating transaction ID and invoking transaction removeEquipment method.
	public void removeEquipment(int transactionId, String serialNo)	{
//		Transaction uTrans;
		for (int x=0; x < getTransactions().size(); x++){
			if (transactions.get(x).getTransactionId() == transactionId){
				transactions.get(x).removeEquipment(serialNo);
				break;
			}
		}
		save();
	}
	//Outputs each transaction in list on its own line
	public String toString(){

		String listString = "";
		
		for (Transaction t: transactions){
			listString+=(t + "\n");
		}
		return listString;
	}
	public TransactionManager clone() {
		return new TransactionManager(dirFileName);
	}
}

