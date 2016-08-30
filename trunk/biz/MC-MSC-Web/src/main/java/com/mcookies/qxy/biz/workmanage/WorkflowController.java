package com.mcookies.qxy.biz.workmanage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.support.MyControllerSupport;
import org.isotope.jfp.framework.utils.DateHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.mcookies.qxy.common.OaExamineInformation.OaExamineInformationDBO;
import com.mcookies.qxy.common.OaExamineInformation.OaExamineInformationPVO;
import com.mcookies.qxy.common.OaExamineInformation.OaExamineInformationService;
import com.mcookies.qxy.common.OaExamineResult.OaExamineResultDBO;
import com.mcookies.qxy.common.OaExamineResult.OaExamineResultPVO;
import com.mcookies.qxy.common.OaExamineResult.OaExamineResultService;
import com.mcookies.qxy.common.OaRule.OaRuleDBO;
import com.mcookies.qxy.common.OaRule.OaRuleService;
import com.mcookies.qxy.common.OaTags.OaTagsDBO;
import com.mcookies.qxy.common.OaTags.OaTagsService;

/**
 * 工作管理-工作流程
 * 
 * @author linyh
 *
 */
@Controller
public class WorkflowController extends MyControllerSupport {

	@Resource
	protected OaExamineInformationService oaExamineInformationService;
	@Resource
	protected OaExamineResultService oaExamineResultService;
	@Resource
	protected OaTagsService oaTagsService;
	@Resource
	protected OaRuleService oaRuleService;

	/**
	 * 我提交的事项查询接口 /myapplication/teacher=[tid]&token=[token]
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/myapplication", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean myApplicationGET(@RequestParam(required = false) String token,
			@RequestParam(required = false) Long tid) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			if (tid == null) {
				tid = getLoginer().getUserId();
				if (tid == null) {
					throw new IllegalStateException("获取用户id失败");
				}
			}

			Map<String, Object> data = new HashMap<String, Object>();
			// 查出列表
			OaExamineInformationPVO info = new OaExamineInformationPVO();
			info.setTid(tid);
			List<OaExamineInformationPVO> infos = (List<OaExamineInformationPVO>) oaExamineInformationService.doSelectData(info);
			
			// 设置oatagsName和resultinfo
			for (OaExamineInformationPVO each : infos) {
				OaTagsDBO condition = new OaTagsDBO();
				condition.setOatagsId(each.getOatagsId());
				OaTagsDBO parent = (OaTagsDBO) oaTagsService.doRead(condition);
				if (parent == null) {
					throw new IllegalStateException("获取oatags失败");
				}
				each.setOatagsName(parent.getOatagsName());
				List<OaExamineResultPVO> ress;
				if (each.getResult() == null || each.getResult() != 1) {
					ress = new ArrayList<OaExamineResultPVO>();
				}
				ress = (List<OaExamineResultPVO>) oaExamineResultService.findByApprovalInformationId(each);
				each.setResultinfo(ress);
			}
			data.put("tid", tid);
			data.put("count", infos.size());
			data.put("myapplication", infos);
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}
	
	/**
	 * 我提交的事项通过approvalInformationId查询接口 /myapplication/info?approvalInformationId=[approvalInformationId]&token=[token]
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/myapplication/info", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean myapplicationByAppInfoIdGET(@RequestParam(required = false) String token,
			@RequestParam Long approvalInformationId) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(token) == false) {
				return tokenFail();
			}	
			Map<String, Object> data = new HashMap<String, Object>();
			// 查出单个
			OaExamineInformationPVO info = new OaExamineInformationPVO();
			info.setApprovalInformationId(approvalInformationId);;
			List<OaExamineInformationPVO> infos = (List<OaExamineInformationPVO>) oaExamineInformationService.doSelectData(info);			
			// 设置oatagsName和resultinfo
			for (OaExamineInformationPVO each : infos) {
				OaTagsDBO condition = new OaTagsDBO();
				condition.setOatagsId(each.getOatagsId());
				OaTagsDBO parent = (OaTagsDBO) oaTagsService.doRead(condition);
				if (parent == null) {
					throw new IllegalStateException("获取oatags失败");
				}
				each.setOatagsName(parent.getOatagsName());
//				List<OaExamineResultPVO> ress;
//				if (each.getResult() == null || each.getResult() != 1) {
//					ress = new ArrayList<OaExamineResultPVO>();
//				}
//				ress = (List<OaExamineResultPVO>) oaExamineResultService.findByApprovalInformationId(each);
//				each.setResultinfo(ress);
			}
			result.setData(infos.get(0));
//			result.setStatus(0);
		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}
	

	/**
	 * 我提交的事项新增接口 /myapplication
	 */
	@RequestMapping(value = "/myapplication", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean myApplicationPOST(String token, @RequestBody OaExamineInformationDBO oaExamineInformation) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			OaTagsDBO condition = new OaTagsDBO();
			condition.setOatagsId(oaExamineInformation.getOatagsId());
			OaTagsDBO parent = (OaTagsDBO) oaTagsService.doRead(condition);
			if (parent == null) {
				throw new IllegalArgumentException("oatagsId所对应的流程模板不存在");
			}
			oaExamineInformation.setResult(0);
			oaExamineInformationService.doInsert(oaExamineInformation);
			// 生成审批信息
			genExamineResult(oaExamineInformation, parent.getOatagsId(), -1);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("info", "ok");
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("新增失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}
	
	private void genExamineResult(OaExamineInformationDBO oaExamineInformation, Long oatagsId, int serialNumber) {
		OaRuleDBO oaRule = new OaRuleDBO();
		oaRule.setOatagsId(oatagsId);
		oaRule.setSerialNumber(serialNumber);
		OaRuleDBO dbo = oaRuleService.doSelectNextRule(oaRule);
		if (dbo != null && !StringUtils.isEmpty(dbo.getTids())) {
//			String tidsStr = dbo.getTids().replace("[", "").replace("]", "");
			String tidsStr = dbo.getTids();
			tidsStr = tidsStr.substring(0, tidsStr.length() - 1);
			String[] tidStrs = tidsStr.split(";");
			
			for (String tidStr : tidStrs) {
				OaExamineResultDBO result = new OaExamineResultDBO();
				result.setApprovalInformationId(oaExamineInformation.getApprovalInformationId());
				result.setOaruleId(dbo.getOaruleId());
				result.setOaruleStatus(0);
				result.setResult(0);
				result.setTid(Long.valueOf(tidStr));
				oaExamineResultService.doInsert(result);
			}
		} else {	// 没有下一层的规则
			oaExamineInformation.setResult(1);
			oaExamineInformationService.doUpdate(oaExamineInformation);
		}
	}

	/**
	 * 我提交的事项删除接口 /myapplication
	 */
	@RequestMapping(value = "/myapplication", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean myApplicationDELETE(String token, @RequestBody String jsonparam) {
		RESTResultBean result = new RESTResultBean();
		try {
			JSONObject param = JSONObject.parseObject(jsonparam);
//			String token = (String) param.get("token");
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			String approvalInformationIds = (String) param.get("approvalInformationIds");
			Long tmp = Long.valueOf(approvalInformationIds.toString());
			// 乐观锁操作
			OaExamineInformationDBO dbo = new OaExamineInformationDBO();
			dbo.setApprovalInformationId(tmp);
			dbo = (OaExamineInformationDBO) oaExamineInformationService.doRead(dbo);
			int flag = oaExamineInformationService.doDelete(dbo);
			if (flag == 0) {
				throw new IllegalArgumentException("提交的事项不存在");
			} 
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("info", "ok");
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("删除失败，" + e.getMessage());
			result.setStatus(1);
		}
		return result;
	}

	/**
	 * 我的审批事项查询接口 /myaudit/teacher=[tid]&token=[token]
	 */
	@RequestMapping(value = "/myaudit", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean myAuditGET(@RequestParam(required = false) String token,
			@RequestParam(required = false) Long tid) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			if (tid == null) {
				tid = getLoginer().getUserId();
				if (tid == null) {
					throw new IllegalStateException("获取用户id失败");
				}
			}

			Map<String, Object> data = new HashMap<String, Object>();
			// 查出列表
			OaExamineResultDBO res = new OaExamineResultDBO();
			res.setTid(tid);
			List<OaExamineInformationPVO> infos = (List<OaExamineInformationPVO>) oaExamineInformationService.findByCheckTid(res);
			
			data.put("tid", tid);
			data.put("count", infos.size());
			data.put("myaudit", infos);
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 我的审批驳回(同意)操作接口 /myaudit/set
	 */
	@RequestMapping(value = "/myaudit/set", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean myAuditSetPUT(String token, @RequestBody OaExamineResultDBO oaExamineResult) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			// 检查result参数
			if (oaExamineResult.getResult() == null || 
					!(oaExamineResult.getResult() == 1 || oaExamineResult.getResult() == 2)) {
				throw new IllegalArgumentException("错误的result参数，必须为1或2");
			}
			// 查询是否存在
			if (oaExamineResult.getId() == null) {
				throw new IllegalArgumentException("审批事项id不能为空");
			}
			OaExamineResultDBO condition = new OaExamineResultDBO();
			condition.setId(oaExamineResult.getId());
			OaExamineResultDBO origin = (OaExamineResultDBO) oaExamineResultService.doRead(condition);
			if (origin == null) {
				throw new IllegalArgumentException("审批事项不存在");
			}
			
			// TODO: 审批事项已经拒绝或通过，该层已经拒绝或通过
			
			// 先更新一次该审核信息
			oaExamineResult.setTime(DateHelper.currentTimeMillis2());
			oaExamineResultService.doUpdate(oaExamineResult);
			
			OaExamineInformationDBO oaExamineInformation = null;
			// 如果 驳回
			if (oaExamineResult.getResult() != null && oaExamineResult.getResult() == 2) {
				origin.setOaruleStatus(2);
				oaExamineResultService.doUpdateSetStatus(origin);
				
				OaExamineInformationDBO tmp = new OaExamineInformationDBO();
				tmp.setApprovalInformationId(origin.getApprovalInformationId());
				oaExamineInformation = (OaExamineInformationDBO) oaExamineInformationService.doRead(tmp);
				oaExamineInformation.setResult(2);
				oaExamineInformationService.doUpdate(oaExamineInformation);
			} else {	// 如果 通过
				// 查看rule的规则（全部还是一人）
				OaRuleDBO ruleCondition = new OaRuleDBO();
				ruleCondition.setOaruleId(origin.getOaruleId());
				OaRuleDBO rule = (OaRuleDBO) oaRuleService.doRead(ruleCondition);
				if (rule == null) {
					throw new IllegalStateException("相应流程规则不存在");
				}
				// 全部通过
				if (rule.getAdoptType() != null && rule.getAdoptType().equals("1")) {
					// 如果都审核了
					if (oaExamineResultService.doSelectUnChecked(origin).size() == 0) {
						// 修改同层的状态为完成
						origin.setOaruleStatus(1);
						oaExamineResultService.doUpdateSetStatus(origin);
						// 生成下一层的审批记录
						OaExamineInformationDBO tmp = new OaExamineInformationDBO();
						tmp.setApprovalInformationId(origin.getApprovalInformationId());
						oaExamineInformation = (OaExamineInformationDBO) oaExamineInformationService.doRead(tmp);
						genExamineResult(oaExamineInformation, rule.getOatagsId(), rule.getSerialNumber());
					}
				} else {	// 一人通过
					// 修改同层的状态为完成
					origin.setOaruleStatus(1);
					oaExamineResultService.doUpdateSetStatus(origin);

					// 生成下一层的审批记录
					OaExamineInformationDBO tmp = new OaExamineInformationDBO();
					tmp.setApprovalInformationId(origin.getApprovalInformationId());
					oaExamineInformation = (OaExamineInformationDBO) oaExamineInformationService.doRead(tmp);
					genExamineResult(oaExamineInformation, rule.getOatagsId(), rule.getSerialNumber());
				}
			}
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("info", "ok");
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("修改失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}
}
