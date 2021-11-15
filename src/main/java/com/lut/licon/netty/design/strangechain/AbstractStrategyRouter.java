package com.lut.licon.netty.design.strangechain;

import java.util.Objects;

import javax.annotation.PostConstruct;

import lombok.Getter;
import lombok.Setter;


/**
 * Describe:责任树模式
 *
 * @author Licon
 * @date 2021/1/26 9:27
 */
public abstract class AbstractStrategyRouter<T, R> extends IRouter{

	public interface StrategyMapper<T, R> {
		StrategyHandler<T, R> get(T param);
	}

	private StrategyMapper<T, R> strategyMapper;

	AbstractStrategyRouter() {
		abstractInit();
	}

	@PostConstruct
	private void abstractInit() {
		strategyMapper = registerStrategyMapper();
		Objects.requireNonNull(strategyMapper, "strategyMapper cannot be null");
	}

	@Getter
	@Setter
	@SuppressWarnings("unchecked")
	private StrategyHandler<T, R> defaultStrategyHandler = StrategyHandler.DEFAULT;

	public R applyStrategy(T param) {
		final StrategyHandler<T, R> strategyHandler = strategyMapper.get(param);
		if (strategyHandler != null) {
			return strategyHandler.apply(param);
		}

		return defaultStrategyHandler.apply(param);
	}

	protected abstract StrategyMapper<T, R> registerStrategyMapper();
}
