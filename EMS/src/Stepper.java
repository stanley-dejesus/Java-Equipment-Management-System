
public class Stepper extends Equipment{
	private boolean heartMonitor;
	private int heightInch;
	
	public double calculateShipping() {
		float stdShipping = (float) 29.99;
		if (heightInch > 60)
			return (float) (stdShipping + 9.99);
		else
			return (float) stdShipping;
	}
	public String toString(){
		return "<stepper>" + 
				super.toString() + 
				"<heartmonitor>" + heartMonitor + "</heartmonitor>" +
				"<heightinch>" + heightInch + "</heightinch>" + 
				"</stepper>";
	}
	public Stepper(String serialNo, String brand, String model, double price, boolean heartMonitor,  int heightInch){
		super(serialNo, brand, model, price);
		this.heartMonitor = heartMonitor;
		this.heightInch = heightInch;
	}
	public Stepper(String line) {
		super(line);
		heartMonitor = Boolean.parseBoolean(line.substring(line.indexOf("<heartmonitor>") + 14, line.indexOf("</heartmonitor")));
		heightInch = Integer.parseInt(line.substring(line.indexOf("<heightinch>") + 12, line.indexOf("</heightinch")));
	}
	public boolean getHeartMonitor(){
		return heartMonitor;
	}
	public int getHeightInch(){
		return heightInch;
	}
	public Stepper clone(){
		Stepper st = new Stepper(this.toString());
		return st;
	}
}
