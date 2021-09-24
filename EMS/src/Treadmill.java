public class Treadmill extends Equipment{
	private float maxSpeed;
	public String toString(){
		return "<treadmill>" + 
				super.toString() +
				"<maxspeed>" + maxSpeed + "</maxspeed>" +
				"</treadmill>";
	}
	public Treadmill(String serialNo, String brand, String model, double price, float maxSpeed){
		super(serialNo, brand, model, price );
		this.maxSpeed = maxSpeed;
	}
	public Treadmill(String line){
		super(line);
		maxSpeed = Float.parseFloat(line.substring(line.indexOf("<maxspeed>") + 10, line.indexOf("</maxspeed>"))); 
	}
	public float getMaxSpeed(){
		return maxSpeed;
	}
	public Treadmill clone(){
		Treadmill tM = new Treadmill(this.toString());
		return tM;
	}
}
