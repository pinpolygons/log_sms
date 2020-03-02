package com.logsms.logsms.model;



import javax.persistence.*;

@Entity
@Table(name = "provider")
public class Provider {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;

	public void setName(String name) {
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}

	public Provider() {
	}

	public Provider(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
