package com.exb.dto;

public class CustomerDto {
	private int srNo;
	private String firstName;
	private String lastName;
	private String dob;
	private String dod;
	private float loanAmount;
	
	public CustomerDto(int srNo, String firstName, String lastName, String dob, String dod, float loanAmount) {
		this.srNo = srNo;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.dod = dod;
		this.loanAmount = loanAmount;
	}

	public int getSrNo() {
		return srNo;
	}

	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getDod() {
		return dod;
	}

	public void setDod(String dod) {
		this.dod = dod;
	}

	public float getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(float loanAmount) {
		this.loanAmount = loanAmount;
	}

	@Override
	public String toString() {
		return ("SrNo: " + this.getSrNo() + "| First Name: " + this.getFirstName()
				+ "| Last Name: " + this.getLastName() + "| Date od Birth: " + this.getDob() + "| Date of Death: " + this.getDod()
				+ "| Loan Amount: " + this.getLoanAmount());
	}
}
