package com.lut.licon.netty.ddd.external.service;

import java.util.Currency;

import com.lut.licon.netty.ddd.types.ExchangeRate;
import com.lut.licon.netty.dp.ExchangeRage;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/10/29 16:58
 */
public interface ExchangeRateService {
	/***
	 *获取汇率
	 * @param from
	 * @param to
	 * @return {@link ExchangeRate}
	 * @throws
	 * @author Licon
	 * @date 2021/11/15 16:13
	 */
	ExchangeRate getRate(Currency from,Currency to);
}
