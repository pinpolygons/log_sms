package com.logsms.logsms.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logsms.logsms.model.Provider;

public interface ProviderRespository extends JpaRepository<Provider, Long>{

}
