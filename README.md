# Equipment-Management-System-Java

This assignment was to introduce advanced OOP concepts and to demonstration separate of concerns.

OOP Project Description
Requirements:
You are to design and build prototype software (stand-alone application loaded on 
business’s local computer) to manage gym equipment inventory and customer 
transactions. Your software will be used and called by User Interface (UI) that 
someone else will be implementing. You will get to design the UI in software design 
document (SDD) but you will not be implementing it for this project. You can 
assume that the UI layer will be vendor’s code and packaged in a separate package 
than your system.
Your software will load and manage equipment inventory and current transactions
for the business. For this project, the data will be loaded and saved in text files on 
the local system (for the final product outside the scope of this project, server with 
database will be used). UI will provide the directory of where the files will be 
located but the actual filenames will be hard-coded as constants in your system and 
UI will not interact directly with the data storage (only through your systems’ 
methods). Your system will manage all the data and it has to update the files as 
needed to keep them current at all times. 
When the system starts up, UI will create an instance of your Manager class and pass 
it the directory where the two files are located. Your system will then load the 
inventory and current list of transactions using the filenames: equipmentinventory.txt and transactions.txt (you can use .json or .xml extensions instead).
After the equipment is loaded on system startup, the user (using UI) will be able to 
get the list of available equipment objects and list of transactions (using get 
methods) to view but will not be able to change the objects nor the list directly. Your 
system needs to provide the functionality to add and remove equipment to/from 
inventory; add transaction object to the transactions list, cancel transaction, 
complete transaction; and add and remove equipment to and from transaction. 
Note that UI will handle the login and authorization and access control but all are
outside of the scope of the system you are designing. 
There are three different types of equipment in the equipment inventory allowed: 
treadmill, stepper, and stationary bike. Each equipment object includes serial 
number (2 letters plus 6 digits where letters are TH, ST, or SB), brand and model 
(e.g. “Horizon Fitness X685”), price, and status (0 available, 1 bought, and 2 rented). 
In addition, treadmill objects will also have max speed in decimal mph (e.g. 12.0); 
stepper objects will also have boolean whether there is heart monitor and height in 
inches (whole number); and stationary bike will also have number of resistance 
levels, and height in inches (whole number).
Equipment object cannot exist without all the values and values cannot be changed
through this system except for the status. Status starts out as 0 (available) and is 
changed to either 1 (bought) or 2 (rented) in the inventory when the transaction 
(that identifies that equipment) is completed. 
Transaction will store transaction id (8 digits which cannot be changed after 
creation), type of transaction (1 for buy, 2 for rent), cost (calculated and set on 
creation and whenever something is changed), transaction date (only set at 
completion), status (0 draft, 1 completed, 2 cancelled), and a list of equipment serial 
numbers associated with this transaction. On creation, transaction must be passed 
the transaction id, type of transaction, and a list of equipment serial numbers to be 
bought/rented in that transaction. The list may be empty on creation. 
The equipment identified in the transaction does not need to exist in the equipment 
inventory until the transaction is completed. Transaction will have draft status on 
creation. Equipment can be added and removed from the transaction and the 
transaction type can be changed until the transaction is completed.
The complete transaction action must change the equipment status to match the 
transaction type and set transaction date.
Transaction cost will include equipment cost plus the shipping cost. For buying 
transaction, the equipment cost is its price and for renting it is 15% of the price. The 
shipping cost will be calculated as follows:
(1) Basic shipping cost per equipment is $29.99
(2) For steppers and stationary bike, if height is > 5 feet (60inches) there is 
additional fee of $9.99
If customer requests to complete the transaction but equipment is not in the 
inventory, the transaction will fail to complete. Once transaction completes it cannot 
be changed nor cancelled. A transaction cannot be completed without any 
equipment.
Your system must also support the following functionality:
1. Add equipment object to the inventory (objects with duplicate serial numbers 
are not allowed; needs to add clone so UI cannot change object directly)
2. Remove equipment object from the inventory using equipment serial (cannot be 
removed if status is not available)
3. Add transaction (objects with duplicate transaction id are not allowed; needs to 
add clone so UI cannot change object directly )
4. Cancel transaction (cannot be cancelled if completed) 
5. Complete transaction using the transaction id (cannot be completed if already 
completed or cancelled or the equipment is not in the inventory or the 
equipment is not available)
6. Add equipment to transaction (equipment with duplicate ids are not allowed; 
cannot add if transaction is completed or cancelled)
7. Remove equipment from transaction (cannot remove if transaction is completed 
or cancelled)
8. Retrieve transaction from Manager using transaction id (finds and returns 
transaction clone that matches the transaction id)
9. Retrieve transactions from Manager using serial id (return clones; finds all 
transactions with that equipment and returns in ArrayList<Transaction>)
10. Retrieve a list of Transaction objects from Manager that match the given type
(return clones)
11. Retrieve equipment object from manager using serial number (return clone)
12. Return transaction cost for a transaction with given transaction id
13. Calculate and return equipment shipping cost for equipment with specific serial 
number
All methods that change the equipment inventory list and/or content and/or list of 
transactions must update the appropriate files.
It is up to you what format you use for the equipment inventory, accounts file, and 
transactions file but they must be human readable text files and you must follow 
separation of concerns principle. You can use XML tags (you can model after Trip 
example) or JSON or other text formats you are familiar with or would like to use.
Note: You can assume that the number of current transactions and equipment 
inventory is small enough to load all into memory.
Your system will need the following exceptions and validation to be added in week 
8-12 assignments: 
1. When loading equipment inventory and/or transactions and file(s) do not exist or 
there is any issue parsing file or equipment/transactions data, throw unchecked 
user defined exception called InvalidLoadException when violation detected. The 
exception will extend RuntimeException. The exception message should indicate 
what the detected issue was and the filename that could not be loaded or data that 
could not be parsed.
2. On equipment creation error or if validation finds that equipment data is not valid
(null or empty strings; serial not 8 characters with 2 letters and 6 digits; price 0 or 
less; etc.), throw unchecked user defined exception called 
InvalidEquipmentException when violation detected. The exception will extend 
RuntimeException. The exception message should indicate which equipment data is 
invalid.
3. On adding or removing equipment to/from inventory if duplicate equipment
exists on add or equipment is not in inventory on remove or cannot be added or 
removed for any other reason, throw unchecked user defined exception called 
InvalidOperationException when violation detected. The exception will extend 
RuntimeException. The generated exception message should indicate the serial of 
the equipment, which operation was attempted (add or remove), and reason why it 
failed.
4. On adding or cancelling transaction object if duplicate transaction exists on add 
or transaction cannot be cancelled or cannot be added or cancelled for any other 
reason, throw unchecked user defined exception called 
InvalidOperationException when violation detected. The exception will extend 
RuntimeException. The generated exception message should indicate the 
transaction id, which operation was attempted (add or cancel), and reason why it 
failed.
6. On adding or removing equipment to/from transaction if duplicate equipment 
exists on add or equipment is not in transaction on remove or cannot be added or 
removed for any other reason, throw unchecked user defined exception called 
InvalidOperationException when violation detected. The exception will extend 
RuntimeException. The generated exception message should indicate the serial of 
the equipment, transaction id, which operation was attempted (add or remove), and 
reason why it failed.
7. On complete transaction request if it cannot be completed, throw unchecked user 
defined exception called InvalidCompletionException when violation detected. 
The exception will extend RuntimeException. The generated exception message 
should indicate the transaction id and details why exactly it failed.
8. On all other parsing, validation, and loading issues use Java builtin 
InvalidArgumentException with meaningful message
UI is outside the scope of your design but your system needs to have all the 
functionality it would need to satisfy the requirements.
As you design the system, you need to make sure it is consistent and it only has the 
interfaces (constructors, attributes and methods) that are needed by the required
functionality. You cannot arbitrarily add new functionality to the system.
The system you are designing will have the functionality needed to satisfy the above 
requirements. You will NOT be implementing the actual user interface (you will still 
design it though in assignment week 9-10) but you need the classes, attributes, and 
methods to receive and provide the information that the user interface will need. 
You can assume that for a complete solution there would be some other class that 
implements user interface (UI) with main() method and that it will call your 
classes/methods. Your system will not have main method except in the test class. 
You must have at minimum the test methods for the below scenarios. Each 
scenario must be a separate test method with the name based on scenario 
such as scenario1A(), scenario1B(), etc.:
Scenario 1A: tests exception when equipment inventory file not exist
1. Create an instance of Manager (the passed directory exists but equipment 
inventory file does not exist)
2. Catch and check that the Manager threw InvalidLoadException exception
3. Print exception message to the console (it should be meaningful message that 
includes the filename that could not be found or loaded)
Scenario 1B: tests exception when transactions file not exist
1. Create an instance of Manager (the passed directory exists, equipment file does 
exist but transactions file does not exist)
2. Catch and check that the Manager threw InvalidLoadException exception
3. Print exception message to the console (it should be meaningful message that 
includes the filename that could not be found or loaded)
Scenario 1D: tests exception when Equipment data is invalid for serial value
1. Call StationaryBike constructor with serial number that has 8 letters
2. Catch and check that the constructor threw InvalidEquipmentException exception
3. Print exception message to the console (it should be meaningful message that 
includes information that serial is invalid and what it should be)
Scenario 2: tests that files are loaded correctly
1. Create equipment file with one equipment object
2. Create transactions file with one transaction object
3. Create an instance of Manager class
4. Call the get method to get the equipment inventory that was loaded and print to 
the console
5. Call the get method to get the transactions list that was loaded and print to the 
console
6. Check that equipment and transaction are as expected (can manually just check 
the console output)
7. Delete equipment and transactions files
Scenario 3A: tests that manager correctly adds equipment to the inventory
and updates file
1. Create equipment file with no objects
2. Create transactions file no objects
3. Create an instance of Manager class
4. Create a new treadmill object
5. Call method to add equipment to the inventory passing treadmill object
6. Create a new stepper object
7. Call method to add equipment to the inventory passing stepper object
8. Create a new stationary bike object
9. Call method to add equipment to the inventory passing stationary bike object
10. Call get method to get all equipment inventory
11. Print the values of all the returned equipment objects to the console (each object 
on separate line)
12. Verify that the equipment was added to inventory correctly. You can manually 
check that all data printed to console is correct and that the file was updated 
correctly with all three objects.
13. Delete equipment and transactions files
Scenario 3B: tests that manager correctly fails on adding duplicate equipment 
to the inventory 
1. Create equipment file with no objects
2. Create transactions file no objects
3. Create an instance of Manager class 
4. Create a new treadmill object
5. Call method to add equipment to the inventory passing treadmill object
6. Create a new treadmill object with all different values except for serial number
7. Call method to add equipment to the inventory passing the second treadmill 
object
8. Catch the expected exception InvalidOperationException and print the exception 
message
9. Delete equipment and transactions files
Scenario 4A: tests that manager correctly adds transaction and updates file
1. Create equipment file with no objects
2. Create transactions file no objects
3. Create an instance of Manager class 
4. Create a new transaction object
5. Call method to add transaction passing the transaction object
6. Call get method to get all transaction objects
7. Print the values of the returned transaction objects to the console 
8. Verify that the transaction was added correctly. You can manually check that all 
data printed to console is correct and that the file was updated correctly.
9. Delete equipment and transactions files
Scenario 4B: tests that manager correctly fails on adding duplicate transaction
1. Create equipment file with no objects
2. Create transactions file no objects
3. Create an instance of Manager class 
4. Create a new transaction object
5. Call method to add transaction passing the transaction object
6. Create a new transaction object with all different values except for transaction id
7. Call method to add transaction passing the second transaction object
8. Catch the expected exception InvalidOperationException and print the exception 
message
9. Delete equipment and transactions files
Scenario 5A: tests that manager correctly removes equipment from inventory
and updates file
1. Create equipment file with no objects
2. Create transactions file no objects
3. Create an instance of Manager class 
4. Create a new stepper object
5. Call method to add equipment to the inventory passing stepper object 
6. Call get method and check that there is one object in inventory
7. Call method to remove equipment from the inventory passing serial number
8. Call get method and check that there is no objects in the inventory
9. Check that equipment file has no objects
10. Delete equipment and transactions files
Scenario 5B: tests that manager correctly throws exception on remove
equipment from inventory when not exist
1. Create equipment file with no objects
2. Create transactions file no objects
3. Create an instance of Manager class 
4. Call method to remove equipment from the inventory passing “SB123456” for 
serial number
5. Catch the expected exception InvalidOperationException and print the exception 
message
Scenario 6: tests that manager correctly cancels transaction and updates file
1. Create equipment file with no objects
2. Create transactions file with no objects
3. Create Transaction object
4. Create an instance of Manager class 
5. Call method to add transaction (id=12345678) to the manager
6. Create a second instance of Manager class
7. Call method to get all transactions that were loaded on startup for second 
Manager instance and print the data to the console (there should be one 
transaction - with id=12345678 and status =0)
8. Call method for second instance of Manager to cancel transaction with 
id=12345678
9. Call method for second instance of Manager to get transaction with 
id=12345678 and check that status=2
10. Check that transactions file has the transaction with id=12345678 and status=2
Scenario 7: tests that manager correctly adds equipment to transaction and 
updates file
1. Create equipment file with no objects
2. Create transactions file with no objects
3. Create a new treadmill object with serial TH123456
4. Create an instance of Manager class 
5. Call method to add treadmill object to equipment inventory
6. Create transaction object with empty list of equipment
7. Call method to add treadmill (serial TH123456) to the transaction
8. Create a new instance of Manager class
9. Call method to get all transactions that were loaded on startup for second 
Manager instance and print the data (there should be one transaction - with one 
equipment - serial TH123456)
Scenario 8: tests that manager correctly removes equipment from transaction 
and updates file
1. Create equipment file with no objects
2. Create transactions file with no objects
3. Create an instance of Manager class 
4. Create transaction object with empty list of equipment
5. Call method to add stepper (serial ST123456) to the transaction
6. Create a new instance of Manager class
7. Call method to get all transactions that were loaded on startup for second 
Manager instance and print the data (there should be one transaction - with one 
equipment - serial ST123456)
8. Call method to remove equipment with serial ST123456 from the transaction
9. Create a new instance of Manager class
10. Call method to get all transactions that were loaded on startup for second 
Manager instance and print the data to console (there should be one transaction 
with no equipment)
Scenario 9: tests that manager correctly completes transaction and updates 
file
1. Create equipment file with no objects
2. Create transactions file with no objects
3. Create a new stationary bike object with serial SB123456
4. Create an instance of Manager class 
5. Call method to add stationary bike object to equipment inventory
6. Create transaction object with type of transaction 1 (for buy) and with empty list 
of equipment
7. Call method to add stationary bike (serial SB123456) to the transaction
8. Call method to complete the transaction
9. Create a new instance of Manager class
10. Call method to get all transactions that were loaded on startup for second 
Manager instance and print the data (there should be one transaction - with one 
equipment - serial SB123456 with status=1)
11. Call method to get all equipment inventory loaded on startup for second 
manager instance and print the data (there should be one stationary bike with 
status=1 for bought)
Scenario 10A: tests retrieve methods (retrieve Transaction by transaction id) 
work correctly
1. Create equipment file with no objects
2. Create transactions file with no objects
3. Create an instance of Manager class 
4. Create transaction object with empty list of equipment (id=23456789) and type 
of transaction 1 for buy
5. Call method to add transaction object to the manager
6. Call retrieve method to get the Transaction object for id=23456789 and save in 
variable trans
7. Call method on the trans object to change transaction type to 2 for rent
8. Call method to get all transactions from manager and print data to console 
(transaction id=23456789 should still have transaction type 1)
Scenario 10B: tests retrieve methods (retrieve Transactions by serial id) work 
correctly
1. Create equipment file with no objects
2. Create transactions file with no objects
3. Create an instance of Manager class 
4. Create transaction object (id=23456781) with one equipment 
(serial=TH234567) 
5. Call method to add transaction object to the manager
6. Create second transaction (id=23456782) object with no equipment
7. Call method to add transaction object to the manager
8. Create third transaction object (id=23456783) with two equipment 
(serial=ST234567 and serial=TH234567)
9. Call method to add transaction object to the manager
10. Call retrieve method to get the Transaction objects for serial=TH234567 and 
save to variable list
11. Print all transaction data in list to the console with each transaction on separate 
line(there should be two transactions: id=23456781 and id=23456783
Scenario 10C: tests retrieve methods (retrieve all Transactions by type=2) 
work correctly
1. Create equipment file with no objects
2. Create transactions file with no objects
3. Create an instance of Manager class 
4. Create transaction object (id=23456781) with one equipment 
(serial=TH234567) and type=2
5. Call method to add transaction object to the manager
6. Create second transaction (id=23456782) object with no equipment and type =2
7. Call method to add transaction object to the manager
8. Create third transaction object (id=23456783) with two equipment 
(serial=ST234567 and serial=TH234567) and type =1
9. Call method to add transaction object to the manager
10. Call retrieve method to get the Transaction objects for type=2 and save to 
variable list
11. Print all transaction data in list to the console with each transaction on separate 
line(there should be two transactions: id=23456781 and id=23456782
Scenario 10D: tests retrieve methods (retrieve Equipment object by serial) 
work correctly
1. Create equipment file with no objects
2. Create transactions file with no objects
3. Create a new stationary bike object with serial SB123456
4. Create a new stepper object with serial ST123456
5. Create an instance of Manager class 
6. Call method to add stationary bike object to equipment inventory
7. Call method to add stepper object to equipment inventory
8. Call retrieve method to get equipment object for serial= SB123456 and save in 
variable equip
9. Call method on equip object to change status to 1 (bought)
10. Call method on manager instance to get all equipment in inventory and save in 
variable list
11. Print objects in list (one equipment per line) and check that stationary bike still 
has status=0 for available
Scenario 11A: tests calculate methods work correctly (buy)
1. Create equipment file with no objects
2. Create transactions file with no objects
3. Create a new stationary bike object with serial SB123412, height 62 inches, and 
price $1200.00
4. Create new transaction (id=23996781) with type=1 (buy) and equipment serial 
SB123412
5. Create an instance of Manager class 
6. Call method to add stationary bike object to equipment inventory
7. Call method to add transaction to the manager’s list
8. Call method to get the transaction object back and print data to the console
9. Check transaction cost is correct (should be $1239.98)
Scenario 11B: tests calculate methods work correctly (rent)
1. Create equipment file with no objects
2. Create transactions file with no objects
3. Create a new stepper object with serial ST123413, height 61 inches, and price 
$1150.00
4. Create new transaction (id=23996782) with type=2 (rent) and equipment serial 
ST123413
5. Create an instance of Manager class 
6. Call method to add stepper object to equipment inventory
7. Call method to add transaction to the manager’s list
8. Call method to get the transaction object back and print data to the console
9. Print the transaction cost to the console (should be $212.48)
Scenario 11B: tests calculate methods work correctly (updates cost)
1. Create equipment file with no objects
2. Create transactions file with no objects
3. Create a new stepper object with serial ST243413, height 60 inches, and price 
$1200.00
4. Create new transaction (id=23996789) with type=2 (rent) and equipment serial 
ST243413
5. Create an instance of Manager class 
6. Call method to add stepper object to equipment inventory
7. Call method to add transaction to the manager’s list
8. Call method to get the transaction object back and print data to the console (cost 
should be $173.99)
9. Call method to update transaction’s type to buy
10. Call method to get the transaction object back and print data to the console (cost 
should be $1229.99)
Scenario 12: tests that manager correctly throws exception on cancel
transaction when transaction is complete
1. Create equipment file with no objects
2. Create transactions file with no objects
3. Create a new treadmill object with serial TH223413 and price $2250.00
4. Create new transaction (id=23996992) with type=1 (buy) and equipment serial 
TH223413
5. Create an instance of Manager class 
6. Call method to add treadmill object to equipment inventory
7. Call method to add transaction to the manager’s list
8. Call method to complete the transaction
9. Call method to get the transaction by id and print data to the console (check that 
status is completed and the date and cost have correct values
10. Call method to get the equipment and print data to the console (check that status 
is bought)
11. Call method to cancel transaction, catch the expected exception 
InvalidCompletionException, and print message that it was caught as expected 
and print the exception message to the console.
Scenario 13: tests that manager correctly throws exception on complete
transaction when equipment in transaction is not in equipment inventory
1. Create equipment file with no objects
2. Create transactions file with no objects
3. Create new transaction (id=23996992) with type=1 (buy) and equipment serial 
TH223499
4. Create an instance of Manager class 
5. Call method to add transaction to the manager’s list
6. Call method to complete the transaction and catch the expected exception 
InvalidCompletionException, and print message that it was caught as expected 
and print the exception message to the console.
