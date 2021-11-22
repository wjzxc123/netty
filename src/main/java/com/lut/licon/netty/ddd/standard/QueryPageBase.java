package com.lut.licon.netty.ddd.standard;

import lombok.Getter;
import lombok.Setter;

/**
 * Describe: 提供分页的抽象类
 *
 * @author Licon
 * @date 2021/11/22 10:32
 */

@Setter
@Getter
public abstract class QueryPageBase extends QueryBase{
	int page;

	int pageSize;
}
