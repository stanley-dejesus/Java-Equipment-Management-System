
public class InvalidEquipmentException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
	public InvalidEquipmentException(String serialNo, String info) {
		this.msg = "the equipment " + serialNo + " encountered an issue because " + info;
	}
	public String toString() {
		return this.getClass().getSimpleName() + ": " + msg;
		
	}
}
