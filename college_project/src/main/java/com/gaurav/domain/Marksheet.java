package com.gaurav.domain;

public class Marksheet {

	private String rollno, name;
	private Integer id, physics, chemistry, maths, studentId;

	public Marksheet() {
	}
	
	public String getRollno() {
		return rollno;
	}

	public void setRollno(String rollno) {
		this.rollno = rollno;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPhysics() {
		return physics;
	}

	public void setPhysics(Integer physics) {
		this.physics = physics;
	}

	public Integer getChemistry() {
		return chemistry;
	}

	public void setChemistry(Integer chemistry) {
		this.chemistry = chemistry;
	}

	public Integer getMaths() {
		return maths;
	}

	public void setMaths(Integer maths) {
		this.maths = maths;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	@Override
	public String toString() {
		return "Marksheet [rollNo=" + rollno + ", name=" + name + ", id=" + id + ", physics=" + physics + ", chemistry="
				+ chemistry + ", maths=" + maths + ", studentId=" + studentId + "]";
	}

}
