package com.logsms.logsms.controller;


import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logsms.logsms.model.Provider;
import com.logsms.logsms.model.Sender;
import com.logsms.logsms.model.Sms;
import com.logsms.logsms.model.TypeSend;
import com.logsms.logsms.payload.request.SendRequest;
import com.logsms.logsms.payload.request.VmgReq;
import com.logsms.logsms.payload.response.MessageResponse;
import com.logsms.logsms.respository.ProviderRespository;
import com.logsms.logsms.respository.SenderRespository;
import com.logsms.logsms.respository.SmsRespository;


@RestController
@RequestMapping()
public class SmsController {

	@Autowired
	SmsRespository smsRespository;


	
	@Autowired
	ProviderRespository providerRespository;

	@Autowired
	SenderRespository senderRespository;

	@PostMapping("/sms")
	public ResponseEntity<?> createSms(@Valid @RequestBody SendRequest sendRequest)
			throws MalformedURLException, IOException {
		switch (sendRequest.getProvider().toString()) {
		case "1":
			VmgReq vmgReq = new VmgReq();
			String urlString = "http://brandsms.vn:8018/VMGAPI.asmx?WSLD";
			String xmlString = "<Envelope xmlns=\"http://www.w3.org/2003/05/soap-envelope\">\r\n" + "    <Body>\r\n"
					+ "        <BulkSendSms xmlns=\"http://tempuri.org/\">\r\n" + "            <msisdn>"
					+ sendRequest.getPhone() + "</msisdn>\r\n" + "            <alias>"
					+ senderRespository.findById(sendRequest.getSender()).get().getName() + "</alias>\r\n"
					+ "            <message>" + sendRequest.getContext() + "</message>\r\n" + "            <sendTime>"
					+ sendRequest.getSendDay() + "</sendTime>\r\n"
					+ "            <authenticateUser>longbeach</authenticateUser>\r\n"
					+ "            <authenticatePass>@Vmg123456</authenticatePass>\r\n" + "        </BulkSendSms>\r\n"
					+ "    </Body>\r\n" + "</Envelope>";
			if (vmgReq.HttpRequest(urlString, xmlString).contains("<error_code>0</error_code>")) {
				sendRequest.setStatus(true);
			} else {
				sendRequest.setStatus(false);

			}
			sendRequest.setReceivedDate(java.time.LocalDateTime.now().toString());
			Sms sms = new Sms();
			sms.setNumberPhone(sendRequest.getPhone());
			sms.setContext(sendRequest.getContext());
			sms.setProvider(providerRespository.findById(sendRequest.getSender()).get());
			sms.setSender(senderRespository.findById(sendRequest.getProvider()).get());
			sms.setReceivedDate(sendRequest.getReceivedDate());
			if (sendRequest.getStatus()) {
				sms.setStatus("Thành Công");
			}else {
				sms.setStatus("Thất Bại");
			}
			switch (sendRequest.getTypeSend().toLowerCase()) {
			case "web":
				sms.setTypeSend(TypeSend.WEB);
				break;
			case "app":
				sms.setTypeSend(TypeSend.APP);
				break;
			case "mobile":
				sms.setTypeSend(TypeSend.MOBILE);
				break;
			}
			smsRespository.save(sms);
			break;
		case "2":
			break;
		}
		return ResponseEntity.ok(new MessageResponse("Sms sended successfully!"));
	}

	@GetMapping("/senders")
	public List<Sender> getAllSenders() {
		return senderRespository.findAll();
	}

	@GetMapping("/providers")
	public List<Provider> getAllProviders() {
		return providerRespository.findAll();
	}
	
	
}
