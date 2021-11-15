package com.lut.licon.netty.ddd.common;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/11/1 9:58
 */
public class ResultData<T> {
	T data;

	public ResultData(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public static ResultData<Boolean> success(){
		return new ResultData<>(true);
	}

	public static ResultData<Boolean> fail(){
		return new ResultData<>(false);
	}
}
