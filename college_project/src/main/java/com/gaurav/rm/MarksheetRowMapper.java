package com.gaurav.rm;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gaurav.domain.Marksheet;

public class MarksheetRowMapper implements RowMapper<Marksheet> {

	@Override
	public Marksheet mapRow(ResultSet rs, int rowNum) throws SQLException {
		Marksheet m = new Marksheet();
		m.setRollno(rs.getString("rollno"));
		m.setChemistry(rs.getInt("chemistry"));
		m.setPhysics(rs.getInt("physics"));
		m.setMaths(rs.getInt("maths"));
		m.setId(rs.getInt("id"));
		m.setStudentId(rs.getInt("studentId"));
		m.setName(rs.getString("name"));
		return m;
	}
}
