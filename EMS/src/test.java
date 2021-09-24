import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;



public class test {

	public static void main(String[] args) {
		scenario1A();
		scenario1B();
		scenario1D();
		scenario2();
		scenario3A();
		scenario3B();
		scenario4A();
		scenario4B();
		scenario5A();
		scenario5B();
		scenario6();
		scenario7();
		scenario8();
		scenario9();
		scenario10A();
		scenario10B();
		scenario10C();
		scenario10D();
		scenario11A();
		scenario11B();
		scenario11C();
		scenario12();
		scenario13();	
	}
	
	public static void scenario1A(){
		System.out.println("Scenario 1A: tests exception when equipment inventory file not exist.");
		try{
			Manager m =  new Manager("");
		}
		catch(InvalidLoadException e){
			System.out.println(e);
			System.out.println("Scenario1A Complete\n-------------------");
			return;
		}
		System.out.println("Scenario1A failed\n-------------------");
	}
	public static void scenario1B(){
		System.out.println("Scenario 1B: tests exception when transactions file not exist.");
		File f= new File("equipment-inventory.txt");
		try {
			f.createNewFile();
		} catch (IOException e) {
			System.out.println(e + e.getMessage());
		}
		try {
			Manager m =  new Manager("");
		}catch(InvalidLoadException e){
			System.out.println(e);
			System.out.println("Scenario1B Complete\n-------------------");
			return;
		}finally{
			f.delete();
		}
		
		System.out.println("Scenario1B failed");
	}
	public static void scenario1D(){
		System.out.println("Scenario 1D: tests exception when Equipment data is invalid for serial value.");
		try{
			StationaryBike s = new StationaryBike("AABBCCDD", "Brand", "Model", 12.89, 12, 60);	
		}catch (InvalidEquipmentException e){
			System.out.println(e);
			System.out.println("Scenario1D Complete\n-------------------");
			return;
		}
		System.out.println("Scenario1D Failed");
	}
	//Scenario 2: tests that files are loaded correctly
	public static void scenario2(){
		System.out.println("Scenario 2: tests that files are loaded correctly");
		File i= new File("equipment-inventory.txt");
		File t = new File("transactions.txt");
		try {
			i.createNewFile();
			t.createNewFile();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		Manager m = new Manager("");
		System.out.println("Inventory - " + m.getInventory());
		System.out.println("Transaction - " + m.getTransactions());
		i.delete();
		t.delete();
		System.out.println( "Scenario2 Complete\n-------------------");
	}
	//Scenario 3A: tests that manager correctly adds equipment to the inventory and updates file
	public static void scenario3A(){
		System.out.println("Scenario 3A: tests that manager correctly adds equipment to the inventory and updates file.");
		File i= new File("equipment-inventory.txt");
		File t = new File("transactions.txt");
		try {
			i.createNewFile();
			t.createNewFile();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		Manager m = new Manager("");
		m.getInventory().addEquipment(new Treadmill	("TH000001", "XTERRA", "TR150", 391.53, 12)); //Create treadmill object and add it to inventory
		m.getInventory().addEquipment(new Stepper	("ST000004", "Sunny Health Stepping Ecliptical", "SF-E3919", 499.99, false, 59)); //Create Stepper object and add it to inventory
		m.getInventory().addEquipment(new StationaryBike ("SB000010", "Schwinn", "130 Model", 549.00, 12, 70)); //Create StationaryBike object and add it to inventory
		System.out.println(m.getInventory()); //Gets inventory and print it out
		
		i.delete();
		t.delete();
		System.out.println("Scenario3A Complete\n-------------------");
	}
	//Scenario 3B: tests that manager correctly fails on adding duplicate equipment to the inventory
	public static void scenario3B() {
		System.out.println("Scenario 3B: tests that manager correctly fails on adding duplicate equipment to the inventory.");
		File i= new File("equipment-inventory.txt");
		File t = new File("transactions.txt");
		try {
			i.createNewFile();
			t.createNewFile();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		Manager m = new Manager("");
		m.getInventory().addEquipment(new Treadmill	("TH000001", "XTERRA", "TR150", 391.53, 12)); //Create treadmill object and add it to inventory
		try{
			m.getInventory().addEquipment(new Treadmill	("TH000001", "XTERRA Fitness", "TRX3500", 816.46, 14)); //Create treadmill object and add it to inventory
		}catch (InvalidOperationException eq){
			System.out.println(eq);
			System.out.println( "Scenario3B Complete\n-------------------");
			return;
		}finally{
			i.delete();
			t.delete();
		}
		System.out.println("Scenario3B failed\n-------------------");
	}
	public static void scenario4A(){
		System.out.println("Scenario 4A: tests that manager correctly adds transaction and updates file.");
		File i= new File("equipment-inventory.txt");
		File t = new File("transactions.txt");
		try {
			i.createNewFile();
			t.createNewFile();
		}catch(IOException e) {
			System.out.println(e);
			System.out.println("Scenario4A Failed");
		}
		Manager m = new Manager("");
		Transaction tran = new Transaction(12345678,1,"");
		m.getTransactions().addTransaction(tran);
		System.out.println(m.getTransactions());
		i.delete();
		t.delete();
		System.out.println("Scenario4A Complete\n-------------------");
	}
	public static void scenario4B(){
		System.out.println("Scenario 4B: tests that manager correctly fails on adding duplicate transaction");
		File i= new File("equipment-inventory.txt");
		File t = new File("transactions.txt");
		try {
			i.createNewFile();
			t.createNewFile();
		}catch(IOException e) {
			e.printStackTrace();
		}
		Manager m = new Manager("");
		Transaction tran = new Transaction(12345678,1,"");
		m.getTransactions().addTransaction(tran);
		try{
			m.getTransactions().addTransaction(new Transaction(12345678,2,""));
		}catch(InvalidOperationException e)
		{
			System.out.println(e);
			System.out.println("Scenario4B Complete\n-------------------");
			return;
		}finally{
			i.delete();
			t.delete();
		}
		System.out.println("Scenario4B failed\n-------------------");
	}
	//Scenario 5A: tests that manager correctly removes equipment from inventory and updates file.
	public static void scenario5A() {
		System.out.println("Scenario 5A: tests that manager correctly removes equipment from inventory and updates file");
		File i= new File("equipment-inventory.txt");
		File t = new File("transactions.txt");
		try {
			i.createNewFile();
			t.createNewFile();
		}catch(IOException e) {
			e.printStackTrace();
		}
		Manager m = new Manager("");
		Stepper st = new Stepper("ST000004", "Sunny Health Stepping Ecliptical", "SF-E3919", 499.99, false, 59);
		m.getInventory().addEquipment(st);
		System.out.println("Inventory List before removing\n" + m.getInventory());
		m.getInventory().removeEquipment("ST000004");
		System.out.println("Inventory List after removing\n" + m.getInventory());
		i.delete();
		t.delete();
		System.out.println("Scenario5A Complete\n-------------------");
	}
	//Scenario 5B: tests that manager correctly throws exception on remove equipment from inventory when not exist.
	public static void scenario5B()
	{
		System.out.println("Scenario 5B: tests that manager correctly throws exception on remove equipment from inventory when not exist.");
		File i= new File("equipment-inventory.txt");
		File t = new File("transactions.txt");
		try {
			i.createNewFile();
			t.createNewFile();
		}catch(IOException e) {
			e.printStackTrace();
		}
		Manager m = new Manager("");
		try
		{
			m.getInventory().removeEquipment("SB123456");
		}catch (InvalidOperationException e)
		{
			System.out.println(e);
			System.out.println("Scenario5B Complete\n-------------------");
			return;
		}finally {
			i.delete();
			t.delete();
		}
		System.out.println("Scenario5B Failed\n-------------------");
	}
	//Scenario 6: tests that manager correctly cancels transaction and updates file
	public static void scenario6() {
		System.out.println("Scenario 6: tests that manager correctly cancels transaction and updates file.");
		File i= new File("equipment-inventory.txt");
		File t = new File("transactions.txt");
		try {
			i.createNewFile();
			t.createNewFile();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		Transaction tran = new Transaction(12345678,1,"");
		Manager m = new Manager("");
		m.getTransactions().addTransaction(tran);
		Manager m2 = new Manager("");
		System.out.println("Inventory List\n" + m2.getTransactions());
		m2.getTransactions().cancelTransaction(12345678);
		System.out.println(m2.getTransactions());
		i.delete();
		t.delete();
		System.out.println("Scenario6 Complete\n-------------------");
	}
	//Scenario 7: tests that manager correctly adds equipment to transaction and updates file.
	public static void scenario7() {
		System.out.println("Scenario 7: tests that manager correctly adds equipment to transaction and updates file");
		File i= new File("equipment-inventory.txt");
		File t = new File("transactions.txt");
		try {
			i.createNewFile();
			t.createNewFile();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		Treadmill tm = new Treadmill("TH123456", "XTERRA", "TR150", 400, 13);
		Manager m = new Manager("");
		m.getInventory().addEquipment(tm);
		Transaction tran = new Transaction(87654321,1,"");
		m.getTransactions().addTransaction(tran);
		m.getTransactions().addEquipment(87654321, tm.getSerialNo());
		Manager m2 = new Manager("");
		System.out.println(m2.getTransactions());
		i.delete();
		t.delete();
		System.out.println( "Scenario7 Complete\n-------------------");
	}
	//Scenario 8: tests that manager correctly removes equipment from transaction and updates file.
	public static void scenario8() {
		System.out.println("Scenario 8: tests that manager correctly removes equipment from transaction and updates file.");
		File i= new File("equipment-inventory.txt");
		File t = new File("transactions.txt");
		try {
			i.createNewFile();
			t.createNewFile();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		Manager m = new Manager("");
		Transaction tran = new Transaction(56781234, 1,"");
		m.getTransactions().addTransaction(tran);
		m.getTransactions().addEquipment(56781234, "ST123456");
		Manager m2 = new Manager("");
		System.out.println(m2.getTransactions());
		m.getTransactions().removeEquipment(56781234, "ST123456");
		Manager m3 = new Manager("");
		System.out.println(m3.getTransactions());
		i.delete();
		t.delete();
		System.out.println("scenario8 Complete\n-------------------");
	}
	//Scenario 9: tests that manager correctly completes transaction and updates file
	public static void scenario9() {
		System.out.println("Scenario 9: tests that manager correctly completes transaction and updates file.");
		File i= new File("equipment-inventory.txt");
		File t = new File("transactions.txt");
		try {
			i.createNewFile();
			t.createNewFile();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		StationaryBike sb = new StationaryBike("SB123456","Schwinn", "130 Model", 549.00, 12, 70);
		Manager m = new Manager("");
		m.getInventory().addEquipment(sb);
		Transaction tran = new Transaction(87654321, 1, "");
		m.getTransactions().addTransaction(tran);
		m.getTransactions().addEquipment(87654321, sb.getSerialNo());
		m.completeTransaction(87654321);
		Manager m2 = new Manager("");
		System.out.println(m2.getTransactions());
		System.out.println(m2.getInventory());
		i.delete();
		t.delete();
		System.out.println("Scenario9 Complete\n-------------------");
	}
	//Scenario 10A: tests retrieve methods (retrieve Transaction by transaction id) work correctly
	public static void scenario10A() {
		System.out.println("Scenario 10A: tests retrieve methods (retrieve Transaction by transaction id) work correctly");
		File i= new File("equipment-inventory.txt");
		File t = new File("transactions.txt");
		try {
			i.createNewFile();
			t.createNewFile();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		Manager m = new Manager("");
		Transaction tr = new Transaction(23456789, 1,"");
		m.getTransactions().addTransaction(tr);
		Transaction trans = m.retrieveTransactionById(23456789);
		trans.setStatus(2);
		System.out.println(m.getTransactions());
		System.out.println("Scenario10A Complete\n-------------------");
	}
	//Scenario 10B: tests retrieve methods (retrieve Transactions by serial id) work correctly
	public static void scenario10B()
	{
		System.out.println("Scenario 10B: tests retrieve methods (retrieve Transactions by serial id) work correctly");
		File i= new File("equipment-inventory.txt");
		File t = new File("transactions.txt");
		try {
			i.createNewFile();
			t.createNewFile();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		Manager m = new Manager("");
		Transaction tr = new Transaction(23456781, 1, "TH234567");
		m.getTransactions().addTransaction(tr);
		Transaction tr2 = new Transaction(23456782, 2, "");
		m.getTransactions().addTransaction(tr2);
		Transaction tr3 = new Transaction(23456783,1, "ST234567 TH234567");
		m.getTransactions().addTransaction(tr3);
		ArrayList<Transaction> list = m.retrieveTransactionBySerial("TH234567");
		String listString = "";
		for(Transaction l: list)
		{
			listString+=(l +"\n");
		}
		System.out.println(listString);
		i.delete();
		t.delete();
		System.out.println( "Scenario10B Complete\n-------------------");
	}
	//Scenario 10C: tests retrieve methods (retrieve all Transactions by type=2) work correctly
	public static void scenario10C() {
		System.out.println("Scenario 10C: tests retrieve methods (retrieve all Transactions by type=2) work correctly");
		File i= new File("equipment-inventory.txt");
		File t = new File("transactions.txt");
		try {
			i.createNewFile();
			t.createNewFile();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		Manager m = new Manager("");
		Transaction tran = new Transaction(23456781, 2, "TH234567");
		m.getTransactions().addTransaction(tran);
		Transaction tran2 = new Transaction(23456782,2, "");
		m.getTransactions().addTransaction(tran2);
		Vector<String> eqList3 = new Vector<String>();
		eqList3.add("ST234567");
		eqList3.add("TH234567");
		Transaction tran3 = new Transaction(23456783, 1,"ST234567 TH234567");
		m.getTransactions().addTransaction(tran3);
		ArrayList<Transaction> list = m.transactionsByType(2);
		String listString="";
		for(Transaction l: list)
		{
			listString+=(l +"\n");
		}
		System.out.println(listString);
		i.delete();
		t.delete();
		System.out.println("Scenario10C Complete\n-------------------");
		
	}
	public static void scenario10D() {
		System.out.println("Scenario 10D: tests retrieve methods (retrieve Equipment object by serial) work correctly");
		File i= new File("equipment-inventory.txt");
		File t = new File("transactions.txt");
		try {
			i.createNewFile();
			t.createNewFile();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		StationaryBike sb = new StationaryBike("SB123456", "Schwinn", "130 Model", 549.00, 12, 70);
		Stepper st = new Stepper("ST123456", "Sunny Health Stepping Ecliptical", "SF-E3919", 499.99, false, 59);
		Manager m = new Manager("");
		m.getInventory().addEquipment(sb);
		m.getInventory().addEquipment(st);
		Equipment equip = m.retrieveEquipmentBySerial("SB123456");
		equip.setStatus(1);
		ArrayList<Equipment> list = m.getInventory().getInventory();
		String listString ="";
		for (Equipment inv: list)
		{
			listString+=(inv + "\n");
		}
		System.out.println(listString);
		i.delete();
		t.delete();
		System.out.println("Scenario10D Complete\n-------------------");
	}
	//Scenario 11A: tests calculate methods work correctly (buy)
	public static void scenario11A()
	{
		System.out.println("Scenario 11A: tests calculate methods work correctly (buy)");
		File i= new File("equipment-inventory.txt");
		File t = new File("transactions.txt");
		try {
			i.createNewFile();
			t.createNewFile();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		StationaryBike sb = new StationaryBike("SB123412", "Schwinn", "130 Model", 1200, 12, 62);
		Transaction tr = new Transaction(23996781, 1, "SB123412");
		Manager m = new Manager("");
		m.getInventory().addEquipment(sb);
		m.getTransactions().addTransaction(tr);
		System.out.println(m.calculateTransactionCost(23996781));
		i.delete();
		t.delete();
		System.out.println("Scenario11A Complete\n-------------------");
		
	}
	//Scenario 11B: tests calculate methods work correctly (rent)
	public static void scenario11B(){
		System.out.println("Scenario 11B: tests calculate methods work correctly (rent)");
		File i= new File("equipment-inventory.txt");
		File t = new File("transactions.txt");
		try {
			i.createNewFile();
			t.createNewFile();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		Stepper st = new Stepper("ST123413","Sunny Health Stepping Ecliptical", "SF-E3919", 1150.00, false, 61);
		Transaction trans = new Transaction(23996782, 2, "ST123413");
		Manager m = new Manager("");
		m.getInventory().addEquipment(st);
		m.getTransactions().addTransaction(trans);
		System.out.println(m.calculateTransactionCost(23996782));
		i.delete();
		t.delete();
		System.out.println("Scenario11B Complete\n-------------------");
	}
	//Scenario 11B: tests calculate methods work correctly (updates cost)
	public static void scenario11C() {
		System.out.println("Scenario 11B: tests calculate methods work correctly (updates cost)");
		File i= new File("equipment-inventory.txt");
		File t = new File("transactions.txt");
		try {
			i.createNewFile();
			t.createNewFile();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		Stepper st = new Stepper("ST243413","Sunny Health Stepping Ecliptical", "SF-E3919", 1200.00, false, 60);
		Transaction trans = new Transaction(23996789, 2, "ST243413");
		Manager m = new Manager("");
		m.getInventory().addEquipment(st);
		m.getTransactions().addTransaction(trans);
		System.out.println(m.calculateTransactionCost(23996789));
		m.getTransactions().updateTransactionType(23996789, 1);//ADDED, NEED TO ASK INSTRUCTOR IF ITS OKAY TO USE.
		System.out.println(m.calculateTransactionCost(23996789));
		i.delete();
		t.delete();
		System.out.println("Scenario11C Complete\n-------------------");
		
	}
	//Scenario 12: tests that manager correctly throws exception on cancel transaction when transaction is complete
	public static void scenario12()
	{
		System.out.println("Scenario 12: tests that manager correctly throws exception on cancel transaction when transaction is complete");
		File i= new File("equipment-inventory.txt");
		File t = new File("transactions.txt");
		try {
			i.createNewFile();
			t.createNewFile();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		Treadmill tM = new Treadmill("TH223413", "XTERRA Fitness", "TRX3500", 2250.00, 14);
		Transaction tran = new Transaction(23996992, 1, "TH223413");
		Manager m = new Manager("");
		m.getInventory().addEquipment(tM);
		m.getTransactions().addTransaction(tran);
		m.completeTransaction(23996992);
		System.out.println(m.retrieveTransactionById(23996992));
		System.out.println(m.retrieveEquipmentBySerial("TH223413"));
		try 
		{
			m.getTransactions().cancelTransaction(23996992);
		} catch (InvalidCompletionException e) {
			System.out.println(e);
		}
		i.delete();
		t.delete();
		System.out.println("Scenario12 Complete\n-------------------");
	}
	//Scenario 13: tests that manager correctly throws exception on complete transaction when equipment in transaction is not in equipment inventory
	public static void scenario13() {
		System.out.println("Scenario 13: tests that manager correctly throws exception on complete transaction when equipment in transaction is not in equipment inventory");
		File i= new File("equipment-inventory.txt");
		File t = new File("transactions.txt");
		try {
			i.createNewFile();
			t.createNewFile();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		Transaction tran =new Transaction(23996992,1, "TH223499");
		Manager m = new Manager("");
		m.getTransactions().addTransaction(tran);
		try
		{
		m.completeTransaction(23996992);
		}catch(InvalidCompletionException e)
		{
			System.out.println(e);
		}
		i.delete();
		t.delete();
		System.out.println("Scenario13 Complete\n-------------------");

	}
}
