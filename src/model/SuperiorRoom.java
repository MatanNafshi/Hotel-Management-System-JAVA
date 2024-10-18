package model;

import java.io.Serializable;
import java.util.Objects;

import utils.MyFileLogWriter;
//
@SuppressWarnings("serial")
public class SuperiorRoom extends StandardRoom implements Upgrade, Serializable{
    private boolean hasJaccozi;
    private final String name = "SuperiorRoom";
    @SuppressWarnings("unused")
	private boolean flag=false;
    
	public SuperiorRoom(double dailyPrice, int floor, double avgDailyCost, double roomGrade, int maxPopulationCapacity,
			int size, boolean hasView, boolean hasJaccozi) {
		super(dailyPrice, floor, avgDailyCost, roomGrade, maxPopulationCapacity, size, hasView);
		this.setRoomNumber(name);
		this.hasJaccozi = hasJaccozi;
	}

	public boolean isHasJaccozi() {
		return hasJaccozi;
	}


	public void setHasJaccozi(boolean hasJaccozi) {
		this.hasJaccozi = hasJaccozi;
	}

	
	/*@Override
	public String toString() {
		return super.toString()+ " hasJaccozi=" + hasJaccozi ;
	}*/
	@Override
	public String toString() {
		String view;
		String jaccozi;
		if(isHasView()==true)
			view="Yes";
		else
			view="No";
		if(hasJaccozi==true)
			jaccozi="Yes";
		else
			jaccozi="No";
		return "Standard Room:\nRoom Number: "+getRoomNumber()+"\nDaily Price: "+getDailyPrice()+"\nFloor: "+getFloor()+
				"\nAverage Daily Cost: "+getAvgDailyCost()+"\nRoom Grade: "+getRoomGrade()+"Number of Guests: "+getMaxPopulationCapacity()+
				"\nSize: "+getSize()+"\nView: "+view+"\nJaccozi: "+jaccozi+"\n";
	}
	
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(hasJaccozi, name);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SuperiorRoom other = (SuperiorRoom) obj;
		return hasJaccozi == other.hasJaccozi && Objects.equals(name, other.name);
	}

	@Override
	public void describeSpecialProperties() {
		// TODO Auto-generated method stub
		MyFileLogWriter.println("has Jaccozi <" +hasJaccozi+">");

	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		flag=true;
		this.setMaxPopulationCapacity(this.getMaxPopulationCapacity()+2);
	}


    
}

