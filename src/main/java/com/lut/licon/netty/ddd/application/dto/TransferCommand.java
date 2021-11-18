package com.lut.licon.netty.ddd.application.dto;

import java.math.BigDecimal;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.lut.licon.netty.ddd.common.CurrencyValid;
import com.lut.licon.netty.ddd.common.group.TransferGroup;
import com.lut.licon.netty.ddd.common.group.UndoGroup;
import com.lut.licon.netty.ddd.standard.CommandBase;
import lombok.Data;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/11/5 16:26
 */
@Data
public class TransferCommand extends CommandBase {
	@NotNull(message = "用户id不能为空",groups = TransferGroup.class)
	Long userId;

	@NotEmpty(message = "对方账户不能为空",groups = TransferGroup.class)
	@NotNull(message = "对方账户不能为空",groups = UndoGroup.class)
	String accountNumber;

	@Min(value = 0,message = "转账金额必须大于0",groups = TransferGroup.class)
	@Max(value = 1000,message = "单次转账金额必须小于1000",groups = UndoGroup.class)
	BigDecimal operateMoney;

	@CurrencyValid(message = "请传入合法币种",groups = TransferGroup.class)
	String currency;
}
