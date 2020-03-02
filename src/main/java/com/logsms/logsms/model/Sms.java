package com.logsms.logsms.model;

import java.io.Serializable;

import java.util.Date;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "sms")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createAt"}, allowGetters = true)
public class Sms implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private TypeSend typeSend;
	
	@ManyToOne
	@JoinColumn(name = "sender_id")
	private Sender sender;
	
	@ManyToOne
	@JoinColumn(name = "provider_id")
	private Provider provider;
	
	@NotBlank
	private String numberPhone;
	
	
	@NotBlank
	private String context;
	
	private String status;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdDate;
	
	private String receivedDate;
	
	
	public Sms() {}
	
	
	public Sms(@NotBlank String numberPhone, @NotBlank String context) {
		this.numberPhone = numberPhone;
		this.context = context;
	}

	
	public TypeSend getTypeSend() {
		return typeSend;
	}


	public void setTypeSend(TypeSend typeSend) {
		this.typeSend = typeSend;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public Sender getSender() {
		return sender;
	}
	public void setSender(Sender sender) {
		this.sender = sender;
	}
	public Provider getProvider() {
		return provider;
	}
	public void setProvider(Provider optional) {
		this.provider = optional;
	}
	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createdDate;
	}

	
	public String getReceivedDate() {
		return receivedDate;
	}


	public void setReceivedDate(String receivedDate) {
		this.receivedDate = receivedDate;
	}


	public String getNumberPhone() {
		return numberPhone;
	}

	public void setNumberPhone(String numberPhone) {
		this.numberPhone = numberPhone;
	}

	
	
}
