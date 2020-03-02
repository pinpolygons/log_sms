package com.logsms.logsms.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logsms.logsms.model.Sms;


public interface SmsRespository extends JpaRepository<Sms, Long>{
}
