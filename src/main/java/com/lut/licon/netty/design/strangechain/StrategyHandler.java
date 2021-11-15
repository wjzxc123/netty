package com.lut.licon.netty.design.strangechain;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/1/26 9:30
 */
public interface StrategyHandler<T,R>{
	@SuppressWarnings("rawtypes")
	StrategyHandler DEFAULT = t -> null;

	/**
	 * apply strategy
	 *
	 * @param param
	 * @return
	 */

	R apply(T param);
}