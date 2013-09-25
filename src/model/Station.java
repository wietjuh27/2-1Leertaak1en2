package model;

public class Station {
	/*
	 * 0 = id
	 * 1 = date
	 * 2 = time
	 * 3 = temperature
	 * 4 = dewpoint
	 * 5 = stp
	 * 6 = slp
	 * 7 = visibilty
	 * 8 = windspeed
	 * 9 = rainfall
	 * 10= snowfall
	 * 11= events
	 * 12= overcast
	 * 13= winddirection
	 */
	private int[] station;
	
	public Station(String id) {
		station = new int[14];
		setId(id);
	}
	
	public void setId(String id) throws NumberFormatException {
		station[0] = Integer.parseInt(id);
	}
	
	public void setDate(String date) throws NumberFormatException{
		date = date.replace("-", "");
		station[1] = Integer.parseInt(date);
	}
	
	public void setTime(String time) throws NumberFormatException {
		time = time.replace(":", "");
		station[2] = Integer.parseInt(time);
	}
	
	public void setTemperature(String temp) throws NumberFormatException {
			station[3] = (int) (Double.parseDouble(temp)*10);
	}
	
	public void setDewPoint(String dewp) throws NumberFormatException {
		station[4] = (int) (Double.parseDouble(dewp)*10);
	}
	
	public void setSTP(String stp) throws NumberFormatException {
		station[5] = (int) (Double.parseDouble(stp)*10);
	}
	
	public void setSLP(String slp) throws NumberFormatException {
		station[6] = (int) (Double.parseDouble(slp)*10);
	}
	
	public void setVisibilty(String vis) throws NumberFormatException {
		station[7] = (int) (Double.parseDouble(vis)*10);
	}
	
	public void setWindSpeed(String wind) throws NumberFormatException {
		station[8] = (int) (Double.parseDouble(wind)*10);
	}
	
	public void setRainFall(String rain) throws NumberFormatException {
		station[9] = (int) (Double.parseDouble(rain)*100);
	}
	
	public void setSnowFall(String snow) throws NumberFormatException {
		station[10] = (int) (Double.parseDouble(snow)*10);
	}
	
	public void setEvent(String event) throws NumberFormatException {
		String tmp = "";
		for(int i = 0; i <= 5; i++) {
			switch (event.charAt(i)) {
				case 0:
					tmp = tmp + "0";
				break;
				case 1:
					tmp = tmp + "1";
				break;
				default:
					throw new NumberFormatException("No binairy number.");
			}
		}
		station[11] = Integer.parseInt(tmp);
	}
	
	public void setOvercast(String cast) throws NumberFormatException {
		station[12] = (int) (Double.parseDouble(cast)*10);
	}
	
	public void setWindDirection(String dir) throws NumberFormatException {
		station[13] = Integer.parseInt(dir);
	}
	
	public int getId() {
		return station[0];
	}
	
	public String getDate() {
		String date = "" + station[1];
		return 
				date.substring(0, 4) +
				"-" +
				date.substring(4, 6) +
				"-" + 
				date.substring(6, 8);
	}
	
	public String getTime() {
		String time = "" + station[2];
		return
				time.substring(0, 2) +
				":" + 
				time.substring(2, 4) +
				":" +
				time.substring(4, 6);
	}
	
	public double getTemperature() {
		double tmp = station[3];
		return tmp/10;
	}
	
	public double getDewpoint() {
		double tmp = station[4];
		return tmp/10;
	}
	
	public double getSTP() {
		double tmp = station[5];
		return tmp/10;
	}
	
	public double getSLP() {
		double tmp = station[6];
		return tmp/10;
	}
	
	public double getVisibilty() {
		double tmp = station[7];
		return tmp/10;
	}
	
	public double getWindspeed() {
		double tmp = station[8];
		return tmp/10;
	}
	
	public double getRainfall() {
		double tmp = station[9];
		return tmp/100;
	}
	
	public double getSnowfall() {
		double tmp = station[10];
		return tmp/10;
	}
	
	public String getEvents() {
		return "" + station[11];
	}
	
	public double getOvercast() {
		double tmp = station[12];
		return tmp/10;
	}
	
	public int getWindDirection() {
		return station[13];
	}
	
        @Override
	public String toString() {
		String ret = "";
		String[] tmp = {"Station ID: ", "Date: ", "Time: ",
				"Temperature: ", "Dewpoint: ", "Air pressure: ",
				"Air pressure on sealevel: ", "Visibility: ",
				"Windspeed: ", "Rainfall: ", "Snowfall", 
				"Binairy events", "Overcast: ", "Wind direction"};
		for(int i = 0; i <= 13; i++) {
			ret = ret + tmp[i] + station[i] + "\n";
		}
		return ret;
	}
}
