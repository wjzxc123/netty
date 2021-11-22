package com.lut.licon.netty.ddd.domian.repository;


/**
 * Describe:
 *
 * @author Licon
 * @date 2021/11/19 10:10
 */
public interface Identifiable<ID extends Identifier> {
	ID getId();
}
