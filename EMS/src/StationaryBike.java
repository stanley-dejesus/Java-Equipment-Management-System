public class StationaryBike extends Equipment {
	private int resistanceLevel;
	private int heightInch;
	
	public double calculateShipping(){
		float stdShipping = (float) 29.99;
		if (heightInch > 60)
			return (float) (stdShipping + 9.99);
		else
			return stdShipping;
	}
	@Override
	public String toString(){
		return "<stationarybike>" +
				super.toString() + 
				"<resistancelevel>" + resistanceLevel + "</resistancelevel>" + 
				"<heightinch>" + heightInch + "</heightinch>" +
				"</stationarybike>";
	}
	public StationaryBike(String line){
		super(line);
		resistanceLevel = Integer.parseInt(line.substring(line.indexOf("<resistancelevel>") + 17, line.indexOf("</resistancelevel>")));
		heightInch = Integer.parseInt(line.substring(line.indexOf("<heightinch>") + 12, line.indexOf("</heightinch>")));
	}
	public int getResistanceLevel(){
		return resistanceLevel;
	}
	public int getHeightInch(){
		return heightInch;
	}
	public StationaryBike clone(){
		StationaryBike sB = new StationaryBike(this.toString());
		return sB;
	}
	public StationaryBike(String serialNo, String brand, String model, double price, int resistanceLevel, int heightInch){
		super(serialNo, brand, model, price);
		this.resistanceLevel = resistanceLevel;
		this.heightInch = heightInch;
	}
}
