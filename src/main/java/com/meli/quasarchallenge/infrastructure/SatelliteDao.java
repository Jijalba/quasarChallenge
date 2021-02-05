package com.meli.quasarchallenge.infrastructure;
import javax.persistence.*;

@Entity
@Table(name="satellite")
public class SatelliteDao {

	@Id
	@Column(name="id",length=30)
	String id;

	@Column(name="pos_x", nullable= false)
	double pos_x;
	@Column(name="pos_y", nullable= false)
	double pos_y;

	public String getId() {
		return id;
	}

	public void setId(String name) {
		this.id = name;
	}

	public double getX() {
		return pos_x;
	}

	public void setX(double x) {
		this.pos_x = x;
	}

	public double getY() {
		return pos_y;
	}

	public void setY(double y) {
		this.pos_y = y;
	}

	
	
}
