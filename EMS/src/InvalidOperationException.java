
public class InvalidOperationException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
	public InvalidOperationException(String eqmtSerialNo, String operation, String reason) {
		
		this.msg = "Equipment " + eqmtSerialNo + " while " +  operation + " failed because " + reason;
	}
	public InvalidOperationException(int transactionId, String operation, String reason) {
		
		this.msg = "Transaction " + transactionId+ " while " +  operation + " failed because " + reason;
	}
	public InvalidOperationException(String eqmtSerialNo, int transactionId, String operation, String reason) {
		
		this.msg = "Equipment " + eqmtSerialNo + " in Transaction " + transactionId + "while " +  operation + " failed because " + reason;
	}
	
	public String toString() {
		return this.getClass().getSimpleName() + ": " + msg;
		
	}

}
