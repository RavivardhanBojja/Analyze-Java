package com.capgemini.Analyze.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public class BasicEntity {
	@Column(name = "CREATED_BY")
	@JsonIgnore
	private String createdBy;

	@Column(name = "CREATED_DATE")
	@JsonIgnore
	private Date createdDate;

	@Column(name = "UPDATED_BY")
	@JsonIgnore
	private String updatedBy;

	@Column(name = "UPDATED_DATE")
	@JsonIgnore
	private Date updatedDate;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
}
