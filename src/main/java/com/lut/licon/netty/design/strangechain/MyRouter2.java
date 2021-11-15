package com.lut.licon.netty.design.strangechain;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/1/26 10:09
 */
public class MyRouter2 extends AbstractStrategyRouter<String,Double> {
	@Override
	protected StrategyMapper<String, Double> registerStrategyMapper() {
		return t->new StrategyHandler<String, Double>() {
			@Override
			public Double apply(String param) {
				return Double.valueOf(param);
			}
		};
	}
}
