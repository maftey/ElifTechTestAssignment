package com.maftey.ElifTechTestAssignment.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "company")
public class Company {
	@XmlAttribute
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
	@SequenceGenerator(name = "gen", sequenceName = "company_id_seq", allocationSize = 1)
	private Long id;
	
	@XmlAttribute
	@Column(nullable = false)
	private String name;
	
	@XmlAttribute
	@Column(nullable = false, name = "estimated_earnings")
	private Long estimatedEarnings;
	
	@XmlAttribute
	@Transient
	private Long totalEstimatedEarnings = null;
	
	
	@Column(name = "parent_id")
	private Long parentId;
	
	/*@ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="parent_id")
	Company parent;*/
	
	@XmlAttribute
	@OneToMany(cascade =CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="parent_id" )
    private List<Company> subCompany = new ArrayList<Company>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public Long getEstimatedEarnings() {
		return estimatedEarnings;
	}

	public void setEstimatedEarnings(Long estimatedEarnings) {
		this.estimatedEarnings = estimatedEarnings;
	}
	
	
	/*public Company getParent() {
		return parent;
	}

	public void setParent(Company parent) {
		this.parent = parent;
	}*/

	public Long getTotalEstimatedEarnings() {
		if(totalEstimatedEarnings == null) {
			totalEstimatedEarnings = estimatedEarnings;
			for(Company subCompany : subCompany) {
				totalEstimatedEarnings += subCompany.getTotalEstimatedEarnings();
			}
		}
		
		return totalEstimatedEarnings;
	}

	public List<Company> getSubCompany() {
		return subCompany;
	}

	public void setSubCompany(List<Company> subCompany) {
		this.subCompany = subCompany;
	}

	
	
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	
}
