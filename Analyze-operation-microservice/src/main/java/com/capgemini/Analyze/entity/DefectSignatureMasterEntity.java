package com.capgemini.Analyze.entity;

import javax.persistence.*;

@Entity
@Table(name = "defectsignature_master")
public class DefectSignatureMasterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "bug_id", nullable = false)
    private Long bugId;

    @Column(name = "signature")
    private boolean signature;

    @Column(name = "solution_id", nullable = false)
    private Long solutionId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBugId() {
		return bugId;
	}

	public void setBugId(Long bugId) {
		this.bugId = bugId;
	}

	public boolean isSignature() {
		return signature;
	}

	public void setSignature(boolean signature) {
		this.signature = signature;
	}

	public Long getSolutionId() {
		return solutionId;
	}

	public void setSolutionId(Long solutionId) {
		this.solutionId = solutionId;
	}


    
   
}
