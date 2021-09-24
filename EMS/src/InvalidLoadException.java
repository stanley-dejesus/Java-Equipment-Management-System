
public class InvalidLoadException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
	//public InvalidLoadException(String issue, String filename) {
	public InvalidLoadException(String fileName, String issue) {
		this.msg = "The program experienced a(n) " + issue +  " error " +  "with " + fileName;
	}
	public InvalidLoadException(String fileName) {
		this.msg = "The program failed opening filename: " + fileName;
	}
	public String toString() {
		return this.getClass().getSimpleName() + ": " + msg;
		
	}
}
