package com.lut.licon.netty.ddd.persistence.po;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/10/29 15:30
 */
@Getter
@Setter
@ToString
@TableName("account")
public class AccountDO {
	private Long userId;
	private Long accountId;
	private String accountNumber;
	private BigDecimal available;
	private BigDecimal dailyLimit;
	private String currency;
}
