package model;



import java.io.Serializable;
import java.util.Objects;

import utils.Area;
import utils.Gender;
//
@SuppressWarnings("serial")
public class DepartmentManager extends Employee implements Serializable {
	
	private Double bonus;

	

	public DepartmentManager(String id, String firstName, String lastName, String phoneNumber, Area area, Gender gender,
			int yearOfBirth,int startOfWork,double salary, Department department, Double bonus) {
		super(id, firstName, lastName, phoneNumber, area, gender, yearOfBirth,startOfWork,salary, department);
		this.bonus = bonus;

	}



	public Double getBonus() {
		return bonus;
	}

	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}



	/*@Override
	public String toString() {
		return  super.toString() + " DepartmentManager [bonus=" + bonus + "]";
	}*/
	
	@Override
	public String toString() {
		return "Department Manager:\nID: "+getId()+"\nName: "+getName()+"\nPhone Number: "+getPhoneNumber()+"\nArea: "+getArea()+
				"\nGender: "+getGender()+"\nYear of Birth: "+getYearOfBirth()+"\nStart of Work: "+getStartOfWork()+
				"\nSalary: "+getSalary()+"\nDepartment: "+getDepartment()+"\nBonus: "+getBonus()+"\n";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(bonus);
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
		DepartmentManager other = (DepartmentManager) obj;
		return Objects.equals(bonus, other.bonus);
	}

	
	
	

}
