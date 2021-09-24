
public class InvalidCompletionException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
	public InvalidCompletionException(int transactionId, String details ) {
		this.msg = "Transaction " + transactionId +  " completion failed due to " + details;
	}
	public String toString() {
		return this.getClass().getSimpleName() + ": " + msg;
		
	}
}
