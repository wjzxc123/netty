package com.lut.licon.netty.dp;

import java.math.BigDecimal;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/10/26 9:29
 */
public class Account {
	private String id;
	private Money curMoney;//当前账户金额
	private String name;
	private Money limitMoney;//消费上限

	public Account(String id, Money curMoney, String name, Money limitMoney) {
		this.id = id;
		this.curMoney = curMoney;
		this.name = name;
		this.limitMoney = limitMoney;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Money getCurMoney() {
		return curMoney;
	}

	public void setCurMoney(Money curMoney) {
		this.curMoney = curMoney;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Money getLimitMoney() {
		return limitMoney;
	}

	public void setLimitMoney(Money limitMoney) {
		this.limitMoney = limitMoney;
	}

	/**账户金额改变
	 *
	 * @param money 改变金额
	 * @return
	 * @throws
	 * @author Licon
	 * @date 2021/10/26 10:26
	 */
	void changeMoney(Money money) throws Exception{
		ExchangeRage rate = ExchangeService.getRate(money.getCurrency(),this.curMoney.getCurrency());
		Money exchange = rate.exchange(money);
		if (exchange.getMoney().compareTo(BigDecimal.ZERO)<0 && exchange.getMoney().abs().compareTo(this.curMoney.getMoney())>0){
			throw new Exception("账户余额不足");
		}
		if (exchange.getMoney().compareTo(BigDecimal.ZERO)<0 && exchange.getMoney().compareTo(this.limitMoney.getMoney())>0){
			throw new Exception("超出消费上限");
		}
		if (this.curMoney.getCurrency().equals(exchange.getCurrency())){
			this.curMoney.setMoney(this.curMoney.getMoney().add(exchange.getMoney())) ;
		}else {
			throw new IllegalArgumentException("参数非法");
		}
	}

	@Override
	public String toString() {
		return "Account{" +
				"id='" + id + '\'' +
				", curMoney=" + curMoney +
				", name='" + name + '\'' +
				", limitMoney=" + limitMoney +
				'}';
	}
}
