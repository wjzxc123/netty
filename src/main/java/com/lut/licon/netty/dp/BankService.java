package com.lut.licon.netty.dp;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/10/26 9:54
 */
public class BankService {
	/**
	 * 两账户转账
	 * @param origin 原账户
	 * @param allMoney 转账金额
	 * @param target 目标账户
	 * @return void
	 * @throws catch exception
	 * @author Licon
	 * @date 2021/10/26 10:23
	 */
	public static void transfer(Account origin,Money allMoney,Account target){
		//原来的账户扣钱
		try {
			allMoney.setMoney(allMoney.getMoney().negate());
			origin.changeMoney(allMoney);
		}catch (Exception e) {
			System.out.println("转账失败："+e.getMessage());
			return;
		}

		//目标账户加钱
		try {
			allMoney.setMoney(allMoney.getMoney().negate());
			target.changeMoney(allMoney);
		}catch (Exception e) {
			System.out.println("转账失败："+e.getMessage());
			//金额回滚
			try {
				origin.changeMoney(allMoney);
			}catch (Exception exception) {
				System.out.println("金额回滚失败："+e.getMessage());
			}
		}
	}
}
