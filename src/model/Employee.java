package model;


import java.io.Serializable;
import java.util.Objects;

import utils.Area;
import utils.Gender;
//
@SuppressWarnings("serial")
public class Employee extends Person implements Serializable {

    private double salary;
    private Department department;
    private int startOfWork;
    @SuppressWarnings("unused")
	private String username;
    @SuppressWarnings("unused")
	private String password;

	public Employee(String id, String firstName, String lastName, String phoneNumber, Area area, Gender gender,
			int yearOfBirth,int startOfWork,double salary, Department department) {
		super(id, firstName, lastName, phoneNumber, area, gender,yearOfBirth);
		this.salary = salary;
		this.department = department;
		this.startOfWork = startOfWork;
		this.username=firstName;
		this.password=id;
	}
	

	/*@Override
	public String toString() {
		return "Employee [id " +getId() +" salary=" + salary + ", department=" + department + ", startOfWork=" + startOfWork + "]";
	}*/

	public Employee(String id, String firstName, String lastName) {
		super(id,firstName,lastName);
	}


	public int getStartOfWork() {
		return startOfWork;
	}


	public void setStartOfWork(int startOfWork) {
		this.startOfWork = startOfWork;
	}


	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Department getDepartment() {
		return department;
	}


	public void setDepartment(Department department) {
		this.department = department;
	}
	
	@Override
	public String toString() {
		return "Employee:\nID: "+getId()+"\nName: "+getName()+"\nPhone Number: "+getPhoneNumber()+"\nArea: "+getArea()+
				"\nGender: "+getGender()+"\nYear of Birth: "+getYearOfBirth()+"\nStart of Work: "+getStartOfWork()+
				"\nSalary: "+getSalary()+"\nDepartment Manager: No\n"+getDepartment()+"\n";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(department, salary, startOfWork);
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
		Employee other = (Employee) obj;
		return Objects.equals(department, other.department)
				&& Double.doubleToLongBits(salary) == Double.doubleToLongBits(other.salary)
				&& startOfWork == other.startOfWork;
	}


	public String getName() {
		return getFirstName()+" "+getLastName();
	}



    
}
