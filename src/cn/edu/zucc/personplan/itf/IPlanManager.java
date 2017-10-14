package cn.edu.zucc.personplan.itf;

import java.util.List;

import cn.edu.zucc.personplan.model.BeanPlan;
import cn.edu.zucc.personplan.util.BaseException;

public interface IPlanManager {
	/**
	 * 添加计划
	 * 
	 * @param name
	 *            计划名称
	 * @param deadline 
	 * @throws BaseException
	 */
	public BeanPlan addPlan(String name, String deadline) throws BaseException;

	/**
	 * 提取所有计划
	 * 
	 * @return
	 * @throws BaseException
	 */
	public List<BeanPlan> loadAll() throws BaseException;

	/**
	 * 删除计划，如果计划下存在步骤，则不允许删除
	 * 
	 * @param plan
	 * @throws BaseException
	 */
	public void deletePlan(BeanPlan plan) throws BaseException;

	void modifyPlan(BeanPlan plan) throws BaseException;

	List<BeanPlan> loadfinish1() throws BaseException;

	List<BeanPlan> loadfinish2() throws BaseException;

}
