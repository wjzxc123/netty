package com.lut.licon.netty.ddd.persistence.po;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

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
@Accessors(chain = true)
public class AccountDO {
	@TableId
	private Long userId;
	private Long accountId;
	private String accountNumber;
	private BigDecimal available;
	private BigDecimal dailyLimit;
	private String currency;
	@Version
	private Long version;
}
