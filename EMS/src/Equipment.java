public abstract class Equipment {
	protected String serialNo;
	protected String brand;
	protected String model;
	protected double price;
	protected int status;
	
	public double calculateShipping(){
		float stdShipping = (float) 29.99;
		return stdShipping;
	}
	public Equipment(String serialNo, String brand, String model, double price) {
		this.serialNo = serialNo;
		this.brand = brand;
		this.model = model;
		this.price = price;
		this.status = 0;
		validateEquipment();
	}
	public Equipment(String line){
		serialNo = line.substring(line.indexOf("<serialno>") + 10, line.indexOf("</serialno>"));
		brand = line.substring(line.indexOf("<brand>") + 7, line.indexOf("</brand>"));
		model = line.substring(line.indexOf("<model>") + 7, line.indexOf("</model>"));
		price = Double.parseDouble(line.substring(line.indexOf("<price>") + 7, line.indexOf("</price>")));
		status = Integer.parseInt(line.substring(line.indexOf("<status>") + 8, line.indexOf("</status>")));
		validateEquipment();
	}
	@Override
	public String toString(){
		return "<serialno>" + getSerialNo() + "</serialno>" +
				"<brand>" + getBrand() + "</brand>" +
				"<model>" + getModel() + "</model>" +
				"<price>" + getPrice() + "</price>" +
				"<status>" + getStatus() + "</status>";
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getStatus() {
		return status;
	}

	public String getSerialNo() {
		return serialNo;
	}
	public String getBrand() {
		return brand;
	}
	public String getModel() {
		return model;
	}
	public double getPrice() {
		return price;
	}
	abstract public Equipment clone();
	protected void validateEquipment(){
		if (serialNo.isEmpty() || serialNo == null)
			throw new InvalidEquipmentException(" ", " serial number is empty or null");
		if (serialNo.length() != 8 ||!serialNo.matches("[a-zA-Z][a-zA-Z][0-9][0-9][0-9][0-9][0-9][0-9]"))
			 //!Character.isLetter(serialNo.charAt(0)) || !Character.isLetter(serialNo.charAt(1)) || !(serialNo.substring(2, 8)).matches("[0-9][0-9][0-9][0-9][0-9][0-9]"))
			throw new InvalidEquipmentException(serialNo, "equipment serial number is not 8 characters with 2 Letters and 6 Digits");
		if (price <=0)
			throw new InvalidEquipmentException(serialNo, "equipment price is equal to or less than 0");
	}
}
