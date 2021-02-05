package com.meli.quasarchallenge.infrastructure;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="interception_det")
public class InterceptionDetailDao {

	@Id
	@Column(name="detailId")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	Integer detailId;
	
	@Column(name="interceptionId")
	Integer interceptionId;
	
	@Column(name="word",length=255)
	String word;
	
	public Integer getDetailId() {
		return detailId;
	}

	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}

	public Integer getInterceptionId() {
		return interceptionId;
	}

	public void setInterceptionId(Integer interceptionId) {
		this.interceptionId = interceptionId;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public InterceptionDetailDao(int interceptionId,String word) {
		this.interceptionId = interceptionId;
		this.word = word;
		
	}
	

	public InterceptionDetailDao() {
	}
	
}
