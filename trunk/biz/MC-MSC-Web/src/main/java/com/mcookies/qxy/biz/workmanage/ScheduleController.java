package com.mcookies.qxy.biz.workmanage;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mcookies.qxy.biz.workmanage.ScheduleCopyPUTDto.Week;
import com.mcookies.qxy.common.SDuty.SDutyDBO;
import com.mcookies.qxy.common.SDuty.SDutyService;
import com.mcookies.qxy.common.SDutyScheduling.SDutySchedulingDBO;
import com.mcookies.qxy.common.SDutyScheduling.SDutySchedulingPVO;
import com.mcookies.qxy.common.SDutyScheduling.SDutySchedulingService;
import com.mcookies.qxy.common.STerm.STermDBO;
import com.mcookies.qxy.common.STerm.STermPVO;
import com.mcookies.qxy.common.STerm.STermService;
import com.mcookies.qxy.common.User.UserDBO;

/**
 * 工作管理-值日值周
 * 
 * @author linyh
 *
 */
@Controller
public class ScheduleController extends MyControllerSupport {

	@Resource
	protected STermService sTermService;
	@Resource
	protected SDutyService sDutyService;
	@Resource
	protected SDutySchedulingService sDutySchedulingService;

	/**
	 * 值日值周信息日历表状态查询接口
	 * /schedule/status/term=[term]&teacher=[tid]&token=[token]
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/schedule/status", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean scheduleStatusGET(STermPVO term) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(term.getToken()) == false) {
				return tokenFail();
			}

			term = (STermPVO) sTermService.findByTermId(term);
			if (term == null) {
				throw new IllegalArgumentException("termId所对应的学期或默认学期不存在");
			}
			SDutySchedulingPVO duty = new SDutySchedulingPVO();
			duty.setTermId(term.getTermId());
			duty.setTid(term.getTid());
			duty.setIsUse(1);
			List<SDutySchedulingDBO> dutys = (List<SDutySchedulingDBO>) sDutySchedulingService.findByTermIdAndTidAndIsUsed(duty);
			TreeSet<Long> daylist = new TreeSet<Long>();
			TreeSet<Long> weeklist = new TreeSet<Long>();
			for (SDutySchedulingDBO each: dutys) {
				if (each.getWeek() != null) {
					weeklist.add(Long.valueOf(each.getWeek()));
				} else if (each.getDate() != null) {
					daylist.add(each.getDate().getTime());
				} else {

				}
			}
			term.setDays(daylist);
			term.setWeeklist(weeklist);
			result.setData(term);
		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 值周工作安排查询接口 /schedule/week/term=[term]&week=[week]&token=[token]
	 */
	@RequestMapping(value = "/schedule/week", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean scheduleWeekGET(@RequestBody UserDBO user) {
		// TODO
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(user.getToken()) == false) {
				return tokenFail();
			}

			Long userId = getLoginer().getUserId();

			result.setInfo("欢迎访问千校云平台：" + userId + "," + user.getAccount());
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 值日工作安排查询接口 /schedule/day/term=[term]&day=[date]&token=[token]
	 */
	@RequestMapping(value = "/schedule/day", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean scheduleDayGET(@RequestBody UserDBO user) {
		// TODO
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(user.getToken()) == false) {
				return tokenFail();
			}

			Long userId = getLoginer().getUserId();

			result.setInfo("欢迎访问千校云平台：" + userId + "," + user.getAccount());
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 值周(值日)工作岗位列表查询接口 /duty/list/type=[type]&token=[token]
	 */
	@RequestMapping(value = "/duty/list", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean dutyListGET(@RequestBody UserDBO user) {
		// TODO
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(user.getToken()) == false) {
				return tokenFail();
			}

			Long userId = getLoginer().getUserId();

			result.setInfo("欢迎访问千校云平台：" + userId + "," + user.getAccount());
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 值周(值日)工作岗位详情查询接口 /duty/content/duty=[duty_id]&token=[token]
	 */
	@RequestMapping(value = "/duty/content", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean dutyContentGET(@RequestBody UserDBO user) {
		// TODO
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(user.getToken()) == false) {
				return tokenFail();
			}

			Long userId = getLoginer().getUserId();

			result.setInfo("欢迎访问千校云平台：" + userId + "," + user.getAccount());
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 值周(值日)工作安排新增接口 /schedule
	 */
	@RequestMapping(value = "/schedule", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean schedulePOST(@RequestBody SDutySchedulingDBO scheduling) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(scheduling.getToken()) == false) {
				return tokenFail();
			}
			if (scheduling.getType() == null || 
					!(scheduling.getType() == 0 || scheduling.getType() == 1)) {
				throw new IllegalArgumentException("错误的type值，必须为0或1");
			}
			if (scheduling.getTermId() == null) {
				throw new IllegalArgumentException("termId不能为空");
			}
			STermDBO term = new STermDBO();
			term.setTermId(scheduling.getTermId());
			term = (STermDBO) sTermService.doRead(term);
			if (term == null) {
				throw new IllegalArgumentException("termId所对应的学期不存在");
			}
			if (scheduling.getDutyId() == null) {
				throw new IllegalArgumentException("dutyId不能为空");
			}
			SDutyDBO duty = new SDutyDBO();
			duty.setDutyId(scheduling.getDutyId());
			duty = (SDutyDBO) sDutyService.doRead(duty);
			if (duty == null) {
				throw new IllegalArgumentException("dutyId所对应的岗位不存在");
			}
			sDutySchedulingService.doInsert(scheduling);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("info", "ok");
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("新增失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 值周(值日)工作安排修改接口 /schedule
	 */
	@RequestMapping(value = "/schedule", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean schedulePUT(@RequestBody SDutySchedulingDBO scheduling) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(scheduling.getToken()) == false) {
				return tokenFail();
			}
			// 查询是否存在
			if (scheduling.getId() == null) {
				throw new IllegalArgumentException("工作安排id不能为空");
			}
			SDutySchedulingDBO origin = new SDutySchedulingDBO();
			origin.setId(scheduling.getId());
			origin = (SDutySchedulingDBO) sDutySchedulingService.doRead(origin);
			if (origin == null) {
				throw new IllegalArgumentException("工作安排不存在");
			}
			sDutySchedulingService.doUpdate(scheduling);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("info", "ok");
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("修改失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 值周(值日)工作安排删除接口 /schedule
	 */
	@RequestMapping(value = "/schedule", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean scheduleDELETE(@RequestBody String jsonparam) {
		RESTResultBean result = new RESTResultBean();
		try {
			JSONObject param = JSONObject.parseObject(jsonparam);
			String token = (String) param.get("token");
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			JSONArray scheduleIds = param.getJSONArray("scheduleIds");
			for (Object scheduleId : scheduleIds) {
				Long tmp = Long.valueOf(scheduleId.toString());
				// 乐观锁操作
				SDutySchedulingDBO dbo = new SDutySchedulingDBO();
				dbo.setId(tmp);
				dbo = (SDutySchedulingDBO) sDutySchedulingService.doRead(dbo);
				@SuppressWarnings("unused")
				int flag = sDutySchedulingService.doDelete(dbo);
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
	 * 值周(值日)工作安排应用至其他周或天接口 /schedule/copy
	 */
	@RequestMapping(value = "/schedule/copy", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean scheduleCopyPUT(@RequestBody ScheduleCopyPUTDto dto) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(dto.getToken()) == false) {
				return tokenFail();
			}
			for (Long scheduleId : dto.getScheduleIds()) {
				SDutySchedulingDBO origin = new SDutySchedulingDBO();
				origin.setId(scheduleId);
				origin = (SDutySchedulingDBO) sDutySchedulingService.doRead(origin);
				if (origin == null) {
					continue;
				}
				if (dto.getWeeks() != null && dto.getWeeks().size() > 0) {
					for (Week week : dto.getWeeks()) {
						SDutySchedulingDBO copy = new SDutySchedulingDBO();
						copy.setTermId(origin.getTermId());
						copy.setDutyId(origin.getDutyId());
						copy.setLeaderTids(origin.getLeaderTids());
						copy.setTids(origin.getTids());
						copy.setType(0);
						copy.setWeek(week.getWeek());
						copy.setStartTime(week.getStartTime());
						copy.setEndTime(week.getEndTime());
						sDutySchedulingService.doInsert(copy);
					}
					
				} else if (dto.getDays() != null && dto.getDays().size() > 0) {
					for (Date day : dto.getDays()) {
						SDutySchedulingDBO copy = new SDutySchedulingDBO();
						copy.setTermId(origin.getTermId());
						copy.setDutyId(origin.getDutyId());
						copy.setLeaderTids(origin.getLeaderTids());
						copy.setTids(origin.getTids());
						copy.setType(1);
						copy.setDate(day);
						sDutySchedulingService.doInsert(copy);
					}
				}
			}
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("info", "ok");
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("应用失败，" + e.getMessage());
			result.setStatus(1);
		}
		return result;
	}
	
}
