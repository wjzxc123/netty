package com.lut.licon.netty.generic;

import java.util.Map;

/**
 * Describe:工作单元
 *
 * @author Licon
 * @date 2020/12/25 9:38
 */
public interface Work<T> {
	/**
	 * 做一些好事操作
	 * @param object
	 * @param curWorkWrapper
	 * @author Licon
	 * @date 2020/12/25 15:17
	 */
	void action(T object, Map<String, WorkWrapper<?,?>> curWorkWrapper);
}
