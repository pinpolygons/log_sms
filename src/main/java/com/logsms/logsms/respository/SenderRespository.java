package com.logsms.logsms.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logsms.logsms.model.Sender;

public interface SenderRespository extends JpaRepository<Sender, Long>{
	
}
