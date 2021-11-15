package com.lut.licon.netty.ddd.api;

import com.lut.licon.netty.ddd.application.dto.TransferCommand;
import com.lut.licon.netty.ddd.application.service.BankService;
import com.lut.licon.netty.ddd.common.ResultData;
import com.lut.licon.netty.ddd.common.group.TransferGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/11/5 16:17
 */
@RestController
@Slf4j
public class TransferController {
	final BankService bankService;

	public TransferController(BankService bankService) {
		this.bankService = bankService;
	}

	@PostMapping("/bank/transfer")
	public ResultData<Boolean> transfer(@RequestBody @Validated(TransferGroup.class) TransferCommand transferCommand){
		ResultData<Boolean> resultData;
		try {
			resultData = bankService.bankTransferBusiness(transferCommand);
		}catch (Exception e) {
			log.info(e.getMessage());
			resultData = ResultData.fail();
		}
		return resultData;
	}
}
