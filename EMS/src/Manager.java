
import java.util.ArrayList;


public class Manager {
	
	private final String inventoryFile = "equipment-inventory.txt";
	private final String transactionsFile = "transactions.txt";
	private String directory;
	private Inventory inventory;
	private TransactionManager transactionsList;
	
	private void open()
	{
		inventory = new Inventory(getDirectory()+inventoryFile);
		transactionsList = new TransactionManager(getDirectory()+transactionsFile);
	}
	//8. Retrieve transaction from Manager using transaction id (finds and returns transaction clone that matches the transaction id)
	public Transaction retrieveTransactionById(int transactionId) // returns a clone
	{
		Transaction trans = null;
		for(int x = 0; x< transactionsList.getTransactions().size(); x++)
		{
			if (transactionsList.getTransactions().get(x).getTransactionId() == transactionId)
			{
				trans =  transactionsList.getTransactions().get(x).clone();
			}
		}
		return trans;	
	}
	//9. Retrieve transactions from Manager using serial id (return clones; finds all transactions with that equipment and returns in ArrayList<Transaction>)
	public ArrayList<Transaction> retrieveTransactionBySerial(String serialNo)
	{
		ArrayList<Transaction> s = new ArrayList<Transaction>();
		for(int x = 0; x < transactionsList.getTransactions().size(); x++)
		{
			for(int y = 0; y < transactionsList.getTransactions().get(x).getSerialNums().size(); y++)
			{
				if (transactionsList.getTransactions().get(x).getSerialNums().get(y).equals(serialNo))
				{
					s.add(transactionsList.getTransactions().get(x).clone());
					
				}
			}
		}
		return s;
	}
	//10. Retrieve a list of Transaction objects from Manager that match the given type (return clones)
	public ArrayList<Transaction> transactionsByType(int type)
	{
		ArrayList<Transaction> trans = new ArrayList<Transaction>();
		for (int x = 0; x<transactionsList.getTransactions().size(); x++)
		{
			if (transactionsList.getTransactions().get(x).getTransactionType() == 2)
			{
				trans.add(transactionsList.getTransactions().get(x).clone());
			}
		}

		return trans;
	}
	//11. Retrieve equipment object from manager using serial number (return clone)
	public Equipment retrieveEquipmentBySerial(String serialNo)
	{
		for (int x = 0; x < getInventory().getInventory().size();  x++)
		{
			if (getInventory().getInventory().get(x).getSerialNo().equals(serialNo))
					return getInventory().getInventory().get(x).clone();
		}
		return null;
		
	}
	//12. Calculates Transaction Cost; it goes finds the transaction and loops through each transaction equipment 
	//and confirms that it exist in inventory. If it does, it takes the price and shipping cost and adds it to 
	//total based on type status. Else, it will throw an InvalidOperationException
	public String calculateTransactionCost(int transId) {
		double total = 0;
		int index=-1;
		for(int x=0; x<transactionsList.getTransactions().size();x++)
		{
			if(transactionsList.getTransactions().get(x).getTransactionId() == transId) {
				index=x;
			}
		}
		if(index == -1)
			if (index == -1)
				throw new InvalidOperationException(transId, "computing cost", "it could not be found");
		for(int x = 0; x< transactionsList.getTransactions().size();x++) {
			for(int y=0; y < inventory.getInventory().size(); y++)
			{
				if(transactionsList.getTransactions().get(index).getSerialNums().get(x).equals(inventory.getInventory().get(y).getSerialNo())) {
					if(transactionsList.getTransactions().get(index).getTransactionType() == 1)
						total+=(inventory.getInventory().get(y).getPrice() + inventory.getInventory().get(y).calculateShipping());
					else if (transactionsList.getTransactions().get(index).getTransactionType()==2)
						total+=((.15*inventory.getInventory().get(y).getPrice()) + inventory.calculateEquipmentShipping(inventory.getInventory().get(y).getSerialNo()));
					else
						throw new InvalidOperationException(inventory.getInventory().get(index).getSerialNo(), transId, "computing cost", "transaction type is invalid");
				}
			}
		}
		return String.format("$%,.2f", total);
	}
	//13. Calculate and return equipment shipping cost for equipment with specific serial number
	public double calculateEquipmentShippingCostBySerial(String serialNo)
	{
//		Equipment e = retrieveEquipmentBySerial(serialNo);
//		if (e == null)
//		{
//			throw new InvalidEquipmentException(serialNo,"it does not exist");
//		}
//		return retrieveEquipmentBySerial(serialNo).calculateShipping();
		return inventory.calculateEquipmentShipping(serialNo);
	}

	//#5 Complete transaction using the transaction id (cannot be completed if already completed or cancelled or the equipment is not in the inventory or the equipment is not available)
	//Complete transaction using the transaction id (cannot be completed if already completed 
	//or cancelled or the equipment is not in the inventory or the equipment is not available)
	public void completeTransaction(int transactionId)
	{
		double total = 0;
		int index = -1;
		//Find transaction in transactionsList and returns index
		for (int x=0; x<transactionsList.getTransactions().size();x++)
		{
			if (transactionsList.getTransactions().get(x).getTransactionId()==transactionId)
			{
				index=x;
			}
		}
		if(index == -1)
		{
			throw new InvalidCompletionException(transactionId, "it does not exist");
		}
		//Check transaction status; if 1 or 2 throw exception
		if(transactionsList.getTransactions().get(index).getStatus() == 1 || transactionsList.getTransactions().get(index).getStatus() == 2)
		{
				if (transactionsList.getTransactions().get(index).getStatus() == 1)
					throw new InvalidCompletionException(transactionId,"its status is already set to completed");
				else
					throw new InvalidCompletionException(transactionId, "its status is already set to cancelled");
		}
		//confirms whether transaction serial is empty
		if (inventory.getInventory().size() == 0)
		{
			throw new InvalidCompletionException(transactionId, "equipment is not in inventory.");
		}
		if(!(transactionsList.getTransactions().get(index).getSerialNums().size() == 1 && transactionsList.getTransactions().get(index).getSerialNums().equals(""))){
			//System.out.println("getSerialNum: " + transactionsList.getTransactions().get(index).getSerialNums().size()+ " " +  transactionsList.getTransactions().get(index).getSerialNums() );
			//finds serial Nums in inventory, changes their status to complete, and sums up total.
			for(int x=0; x<transactionsList.getTransactions().get(index).getSerialNums().size();x++)
			{
				for(int y=0; y< inventory.getInventory().size(); y++)
				{
					if(inventory.getInventory().get(y).getSerialNo().equals(transactionsList.getTransactions().get(index).getSerialNums().get(x)))
					{

						if(transactionsList.getTransactions().get(index).getTransactionType() == 1)
						{
							Equipment e = inventory.getInventory().get(y).clone();
							inventory.removeEquipment(e.getSerialNo());
							e.setStatus(1);
							inventory.addEquipment(e);
				
							total+=inventory.getInventory().get(y).getPrice() + inventory.getInventory().get(y).calculateShipping();
						}
						else if(transactionsList.getTransactions().get(y).getTransactionType() == 2)
						{
							Equipment e = inventory.getInventory().get(y).clone();
							inventory.removeEquipment(e.getSerialNo());
							e.setStatus(2);
							inventory.addEquipment(e);
							total+=(.15 *inventory.getInventory().get(y).getPrice()) + inventory.getInventory().get(y).calculateShipping();
						}
						else
							inventory.getInventory().get(y).setStatus(0);
					}
					else
					{
						throw new InvalidCompletionException(transactionId, " equipment is not in equipment inventory.");
						
					}
				}

			}

		}
		transactionsList.completeTransaction(transactionId, total);
	}
	public Manager(String directory){
		this.directory = directory;
		open();
	}
	public String getDirectory()
	{
		return directory;
	}

	public TransactionManager getTransactions()
	{
		return transactionsList;
	}
	public Inventory getInventory()
	{
		return inventory;
	}
}
