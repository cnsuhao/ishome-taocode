package com.mcookies.qxy.biz.extra;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.security.value.MD5SecurityHelper;
import org.isotope.jfp.framework.support.MyControllerSupport;
import org.isotope.jfp.framework.utils.DateHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mcookies.qxy.common.Class.ClassDBO;
import com.mcookies.qxy.common.Class.ClassService;
import com.mcookies.qxy.common.ClassCourse.ClassCourseDBO;
import com.mcookies.qxy.common.ClassCourse.ClassCourseService;
import com.mcookies.qxy.common.ClassStudent.ClassStudentDBO;
import com.mcookies.qxy.common.ClassStudent.ClassStudentService;
import com.mcookies.qxy.common.ClassTeacher.ClassTeacherDBO;
import com.mcookies.qxy.common.ClassTeacher.ClassTeacherPVO;
import com.mcookies.qxy.common.ClassTeacher.ClassTeacherService;
import com.mcookies.qxy.common.SCourse.SCourseDBO;
import com.mcookies.qxy.common.SCourse.SCourseService;
import com.mcookies.qxy.common.School.SchoolDBO;
import com.mcookies.qxy.common.School.SchoolService;
import com.mcookies.qxy.common.StudentRfid.StudentRfidDBO;
import com.mcookies.qxy.common.StudentRfid.StudentRfidService;
import com.mcookies.qxy.common.UParent.UParentDBO;
import com.mcookies.qxy.common.UParent.UParentService;
import com.mcookies.qxy.common.UStudent.UStudentDBO;
import com.mcookies.qxy.common.UStudent.UStudentService;
import com.mcookies.qxy.common.UStudentExt.UStudentExtDBO;
import com.mcookies.qxy.common.UStudentExt.UStudentExtService;
import com.mcookies.qxy.common.UStudentParent.UStudentParentDBO;
import com.mcookies.qxy.common.UStudentParent.UStudentParentService;
import com.mcookies.qxy.common.UTeacher.UTeacherDBO;
import com.mcookies.qxy.common.UTeacher.UTeacherService;
import com.mcookies.qxy.common.UTeacherExt.UTeacherExtDBO;
import com.mcookies.qxy.common.UTeacherExt.UTeacherExtService;
import com.mcookies.qxy.common.User.UserDBO;
import com.mcookies.qxy.common.User.UserService;
import com.mcookies.qxy.utils.ExcelUtil;

/**
 * 其他附加接口-excel导入
 * 
 * @author linyh
 *
 */
@Controller
public class ExcelImportController extends MyControllerSupport {
	@Resource
	protected SchoolService schoolService;
	@Resource
	protected ClassService classService;
	@Resource
	protected UserService userService;
	@Resource
	protected UStudentService uStudentService;
	@Resource
	protected UStudentExtService uStudentExtService;
	@Resource
	protected UParentService uParentService;
	@Resource
	protected ClassStudentService classStudentService;
	@Resource
	protected UStudentParentService uStudentParentService;
	@Resource
	protected UTeacherService uTeacherService;
	@Resource
	protected UTeacherExtService uTeacherExtService;
	@Resource
	protected StudentRfidService studentRfidService;
	@Resource
	protected SCourseService sCourseService;
	@Resource
	protected ClassCourseService classCourseService;	
	@Resource
	protected ClassTeacherService classTeacherService;

	/**
	 * 班级学生导入接口 /import/student?token=[token]
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/import/student", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean importStudentPOST(@RequestParam("excel") MultipartFile file, String token, Long cid) {
		RESTResultBean result = new RESTResultBean();
		InputStream is = null;
		Workbook workbook = null;
		try {
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			if (cid == null) {
				throw new IllegalArgumentException("cid不能为空");
			}
			// 验证班级
			ClassDBO clazz = new ClassDBO();
			clazz.setCid(cid);
			clazz = (ClassDBO) classService.doRead(clazz);
			if (clazz == null) {
				throw new IllegalArgumentException("班级不存在");
			}
			is = file.getInputStream();
			String filename = file.getOriginalFilename();
			if (filename != null && (filename.lastIndexOf(".xlsx") == (filename.length() - 5))) {
				workbook = new XSSFWorkbook(is);
			} else {
				workbook = new HSSFWorkbook(is);
			}

			Sheet sheet = workbook.getSheetAt(0);

			int rowCount = sheet.getLastRowNum();
			Map<String, Object> data = new HashMap<String, Object>();
			Map<String, String> error = new HashMap<String, String>();
			int successCount = 0;
			for (int i = 1; i <= rowCount; i++) {
				try {
					Row row = sheet.getRow(i);
					if (row.getLastCellNum() > 1) {
						// 学籍照片 学生姓名 邮箱 学号 联系电话 入学日期 学生类别 民族 籍贯 证件类型 证件号码 政治面貌
						// 是否港澳台侨 出生日期 户口性质 户口地址
						String photo = ExcelUtil.convertToString(row.getCell(1)); // 学籍照片
						String studentName = ExcelUtil.convertToString(row.getCell(2)); // 学生姓名
						String email = ExcelUtil.convertToString(row.getCell(3)); // 邮箱
						if (StringUtils.isEmpty(email)) {
							throw new IllegalArgumentException("电子邮箱不能为空");
						}
						Long number = ExcelUtil.convertToLong(row.getCell(4)); // 学号
						if (number == null) {
							throw new IllegalArgumentException("学号不能为空");
						}
						String phone = ExcelUtil.convertToString(row.getCell(5)); // 联系电话
						if (StringUtils.isEmpty(phone)) {
							throw new IllegalArgumentException("手机号码不能为空");
						}
						String dateOfSchool = ExcelUtil.convertToString(row.getCell(6)); // 入学日期
						String studentType = ExcelUtil.convertToString(row.getCell(7)); // 学生类别
						String nation = ExcelUtil.convertToString(row.getCell(8)); // 民族
						String nativePlace = ExcelUtil.convertToString(row.getCell(9)); // 籍贯
						String cardType = ExcelUtil.convertToString(row.getCell(10)); // 证件类型
						String cardNumber = ExcelUtil.convertToString(row.getCell(11)); // 证件号码
						String political = ExcelUtil.convertToString(row.getCell(12)); // 政治面貌
						Integer isOverseas = ExcelUtil.convertToInteger(row.getCell(13)); // 是否港澳台侨胞
						// String fith = null; // 宗教信仰
						// String health = null; // 健康状况
						// String blood = null; // 血型
						String dateOfBirth = ExcelUtil.convertToString(row.getCell(14)); // 出生日期
						String residenceType = ExcelUtil.convertToString(row.getCell(15)); // 户口类型
						String residenceAddress = ExcelUtil.convertToString(row.getCell(16)); // 户口地址

						Long uid = null;
						Long studentId = null;
						// 根据手机号码查询学生是否存在
						// 检查手机号码、邮箱是否被使用
						UStudentDBO stu = new UStudentDBO();
						stu.setPhone(phone);
						stu.setPuk("1");
						List<UStudentDBO> stus = (List<UStudentDBO>) uStudentService.doSelectData(stu);
						if (stus != null && stus.size() > 0) {
							throw new IllegalArgumentException("手机号码已经存在");
						}
						stu = new UStudentDBO();
						stu.setEmail(email);
						stu.setPuk("1");
						stus = (List<UStudentDBO>) uStudentService.doSelectData(stu);
						if (stus != null && stus.size() > 0) {
							throw new IllegalArgumentException("邮箱已经存在");
						}
						stu = new UStudentDBO();
						stu.setSid(clazz.getSid()); // 学校中学号唯一即可
						stu.setNumber(number);
						stu.setPuk("1");
						stus = (List<UStudentDBO>) uStudentService.doSelectData(stu);
						if (stus != null && stus.size() > 0) {
							throw new IllegalArgumentException("学号已经存在");
						}
						// 匹配用户表
						UserDBO userp = new UserDBO();
						userp.setPhone(phone);
						userp.setPuk("1");
						List<UserDBO> plist = (List<UserDBO>) userService.doSelectData(userp);

						UserDBO usere = new UserDBO();
						usere.setEmail(email);
						usere.setPuk("1");
						List<UserDBO> elist = (List<UserDBO>) userService.doSelectData(usere);
						// 存在则直接获取uid
						if (plist != null && plist.size() > 0) {
							uid = plist.get(0).getUid();
						} else if (elist != null && elist.size() > 0) {
							uid = elist.get(0).getUid();
						} else {
							// 新建
							UserDBO user = new UserDBO();
							user.setAccount(phone);
							user.setEmail(email);
							user.setEmailStatus(1);
							user.setPhone(phone);
							user.setPassword(MD5SecurityHelper.encrypt("qxy123456"));
							user.setStatus(1);
							userService.doInsert(user);
							uid = user.getUid();
						}
						// 插入学生表
						UStudentDBO theStu = new UStudentDBO();
						theStu.setStudentName(studentName);
						theStu.setSid(clazz.getSid());
						theStu.setUid(uid);
						theStu.setNumber(number);
						theStu.setPhone(phone);
						theStu.setEmail(email);
						theStu.setStatus(1);
						theStu.setIsUse(1);
						theStu.setPuk("1");
						uStudentService.doInsert(theStu);
						studentId = theStu.getStudentId();
						// 插入扩展表
						UStudentExtDBO ext = new UStudentExtDBO();
						ext.setStudentId(studentId);
						ext.setPhoto(photo);
						ext.setNation(nation);
						ext.setNativePlace(nativePlace);
						ext.setCardType(cardType);
						ext.setCardNumber(cardNumber);
						ext.setIsOverseas(isOverseas);
						ext.setPolitical(political);
						ext.setDateOfSchool(dateOfSchool);
						ext.setDateOfBirth(dateOfBirth);
						ext.setStudentType(studentType);
						ext.setResidenceType(residenceType);
						ext.setResidenceAddress(residenceAddress);
						ext.setIsUse(1);
						uStudentExtService.doInsert(ext);
						// 插入班级学生表
						ClassStudentDBO classStudent = new ClassStudentDBO();
						classStudent.setCid(clazz.getCid());
						classStudent.setJoinTime(DateHelper.currentTimeMillis2());
						classStudent.setStudentId(studentId);
						classStudent.setIsUse(1);
						classStudent.setSid(clazz.getSid());
						classStudent.setPuk("1");
						classStudentService.doInsert(classStudent);

						successCount++;
					}
				} catch (Exception e) {
					error.put("" + i, e.getMessage());
				}
			}
			data.put("count", successCount);
			data.put("error", error);
			data.put("info", "ok");
			result.setData(data);
		} catch (Exception e) {
			logger.error("导入失败", e);
			result.setInfo("导入失败，" + e.getMessage());
			result.setStatus(1);
		} finally {
			if (workbook != null) {
				try {
					workbook.close();
				} catch (Exception e) {
					logger.error("导入workbook关闭异常", e);
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					logger.error("导入excel关闭异常", e);
				}
			}
		}
		return result;
	}

	/**
	 * 学生家长导入接口 /import/student/parent?token=[token]
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/import/student/parent", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean importStudentParentPOST(@RequestParam("excel") MultipartFile file, String token, Long cid) {
		RESTResultBean result = new RESTResultBean();
		InputStream is = null;
		Workbook workbook = null;
		try {
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			if (cid == null) {
				throw new IllegalArgumentException("cid不能为空");
			}
			// 验证班级
			ClassDBO clazz = new ClassDBO();
			clazz.setCid(cid);
			clazz = (ClassDBO) classService.doRead(clazz);
			if (clazz == null) {
				throw new IllegalArgumentException("班级不存在");
			}
			is = file.getInputStream();
			String filename = file.getOriginalFilename();
			if (filename != null && (filename.lastIndexOf(".xlsx") == (filename.length() - 5))) {
				workbook = new XSSFWorkbook(is);
			} else {
				workbook = new HSSFWorkbook(is);
			}

			Sheet sheet = workbook.getSheetAt(0);

			int rowCount = sheet.getLastRowNum();
			Map<String, Object> data = new HashMap<String, Object>();
			Map<String, String> error = new HashMap<String, String>();
			int successCount = 0;
			for (int i = 1; i <= rowCount; i++) {
				try {
					Row row = sheet.getRow(i);
					if (row.getLastCellNum() > 1) {
						// 学生学号 #学生姓名 家长角色 家长姓名 家长电话 工作单位 职位 是否为主家长
						// 学生姓名是多余的，不做处理
						Long number = ExcelUtil.convertToLong(row.getCell(1)); // 学生学号
						if (number == null) {
							throw new IllegalArgumentException("学号不能为空");
						}
						Integer role = ExcelUtil.convertToInteger(row.getCell(3)); // 家长角色
						String parentName = ExcelUtil.convertToString(row.getCell(4)); // 家长姓名
						String phone = ExcelUtil.convertToString(row.getCell(5)); // 家长电话
						if (StringUtils.isEmpty(phone)) {
							throw new IllegalArgumentException("手机号码不能为空");
						}
						String workUnit = ExcelUtil.convertToString(row.getCell(6)); // 工作单位
						String position = ExcelUtil.convertToString(row.getCell(7)); // 职位
						Integer isDefault = ExcelUtil.convertToInteger(row.getCell(8)); // 是否为主家长

						UStudentDBO student = new UStudentDBO();
						student.setSid(clazz.getSid());
						student.setNumber(number);
						student.setPuk("1");
						List<UStudentDBO> students = (List<UStudentDBO>) uStudentService.doSelectData(student);
						if (students.size() == 0) {
							throw new IllegalArgumentException("该学生不存在");
						} else {
							student = students.get(0);
						}
						// 先查一下是否已经有关联关系
						UParentDBO origin = new UParentDBO();
						origin.setPhone(phone);
						origin.setPuk("1");
						List<UParentDBO> origins = (List<UParentDBO>) uParentService.doSelectData(origin);
						if (origins.size() > 0) {
							UStudentParentDBO sp = new UStudentParentDBO();
							sp.setStudentId(student.getStudentId());
							sp.setParentId(origins.get(0).getParentId());
							sp.setPuk("1");
							List<UStudentParentDBO> sps = (List<UStudentParentDBO>) uStudentParentService
									.doSelectData(sp);
							if (sps.size() > 0) {
								throw new IllegalArgumentException("该学生家长已存在");
							}
						}
						Long uid = null;
						// 匹配用户表
						UserDBO userp = new UserDBO();
						userp.setPhone(phone);
						userp.setPuk("1");
						List<UserDBO> plist = (List<UserDBO>) userService.doSelectData(userp);

						// 存在则直接获取uid
						if (plist != null && plist.size() > 0) {
							uid = plist.get(0).getUid();
						} else {
							// 新建
							UserDBO user = new UserDBO();
							user.setAccount(phone);
							user.setEmailStatus(0);
							user.setPhone(phone);
							user.setPassword(MD5SecurityHelper.encrypt("qxy123456"));
							user.setStatus(1);
							userService.doInsert(user);
							uid = user.getUid();
						}

						// 检查手机号码是否被使用（该家长是否已经录入）
						UParentDBO par = new UParentDBO();
						par.setPhone(phone);
						par.setPuk("1");
						List<UParentDBO> pars = (List<UParentDBO>) uParentService.doSelectData(par);
						UParentDBO rightParent = null;
						if (pars != null && pars.size() > 0) {
							// 手机号码已经存在（该家长已经录入）
							rightParent = pars.get(0);
						} else {
							rightParent = new UParentDBO();
							rightParent.setUid(uid);
							rightParent.setSid(clazz.getSid());
							rightParent.setParentName(parentName);
							rightParent.setPhone(phone);
							rightParent.setPosition(position);
							rightParent.setWorkUnit(workUnit);
							rightParent.setIsUse(1);
							rightParent.setPuk("1");
							uParentService.doInsert(rightParent);
						}
						// 维护学生家长表
						// 先查一下是否已经有默认家长
						UStudentParentDBO studentParent = new UStudentParentDBO();
						studentParent.setStudentId(student.getStudentId());
						studentParent.setIsDefault(1);
						studentParent.setPuk("1");
						List<UStudentParentDBO> studentParents = (List<UStudentParentDBO>) uStudentParentService
								.doSelectData(studentParent);
						// 已经有默认的家长
						if (studentParents.size() > 0) {
							if (isDefault != null && isDefault == 1) {
								uStudentParentService.updateIsDefaultFalse(studentParent);
							} else {
								studentParent.setIsDefault(0);
							}
						} else {
							if (isDefault != null) {
								studentParent.setIsDefault(isDefault);
							} else {
								studentParent.setIsDefault(1);
							}
						}
						studentParent.setSid(clazz.getSid());
						studentParent.setIsUse(1);
						studentParent.setRole(role);
						studentParent.setParentId(rightParent.getParentId());
						studentParent.setPuk("1");
						uStudentParentService.doInsert(studentParent);

						successCount++;
					}
				} catch (Exception e) {
					error.put("" + i, e.getMessage());
				}
			}
			data.put("count", successCount);
			data.put("error", error);
			data.put("info", "ok");
			result.setData(data);
		} catch (Exception e) {
			logger.error("导入失败", e);
			result.setInfo("导入失败，" + e.getMessage());
			result.setStatus(1);
		} finally {
			if (workbook != null) {
				try {
					workbook.close();
				} catch (Exception e) {
					logger.error("导入workbook关闭异常", e);
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					logger.error("导入excel关闭异常", e);
				}
			}
		}
		return result;
	}

	/**
	 * 教师导入接口 /import/teacher?token=[token]
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/import/teacher", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean importTeacherPOST(@RequestParam("excel") MultipartFile file,Long sid, String token) {
		RESTResultBean result = new RESTResultBean();
		InputStream is = null;
		Workbook workbook = null;
		try {
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			SchoolDBO school = new SchoolDBO();
			if(sid == null || sid == 0){
				Long SchoolId = getLoginer().getSchoolId();
				school.setSid(SchoolId);
				school.setPuk("1");
			}else {
				school.setSid(sid);
				school.setPuk("1");
			}
			school = (SchoolDBO) schoolService.doRead(school);
			if (school == null) {
				throw new IllegalArgumentException("学校不存在");
			}
			is = file.getInputStream();
			String filename = file.getOriginalFilename();
			if (filename != null && (filename.lastIndexOf(".xlsx") == (filename.length() - 5))) {
				workbook = new XSSFWorkbook(is);
			} else {
				workbook = new HSSFWorkbook(is);
			}

			Sheet sheet = workbook.getSheetAt(0);

			int rowCount = sheet.getLastRowNum();
			Map<String, Object> data = new HashMap<String, Object>();
			Map<String, String> error = new HashMap<String, String>();
			int successCount = 0;
			for (int i = 1; i <= rowCount; i++) {
				try {
					Row row = sheet.getRow(i);
					if (row.getLastCellNum() > 1) {
						// 教师姓名 教师性别 出生年月 工作时间 教师工号 证件类型 证件号 家庭住址 电话 邮箱
						String teacherName = ExcelUtil.convertToString(row.getCell(1)); // 教师姓名
						Integer gender = ExcelUtil.convertToInteger(row.getCell(2)); // 教师性别
						String dateOfBirth = ExcelUtil.convertToString(row.getCell(3)); // 出生年月
						String startWorkT = ExcelUtil.convertToString(row.getCell(4)); // 开始工作时间
						String jobNumber = ExcelUtil.convertToString(row.getCell(5)); // 教师工号
						Integer cardType = ExcelUtil.convertToInteger(row.getCell(6)); // 证件类型
						String cardNumber = ExcelUtil.convertToString(row.getCell(7)); // 证件号码
						String homeAddress = ExcelUtil.convertToString(row.getCell(8)); // 家庭住址
						String phone = ExcelUtil.convertToString(row.getCell(9)); // 联系电话
						if (StringUtils.isEmpty(phone)) {
							throw new IllegalArgumentException("手机号码不能为空");
						}
						String email = ExcelUtil.convertToString(row.getCell(10)); // 邮箱
						if (StringUtils.isEmpty(email)) {
							throw new IllegalArgumentException("邮箱不能为空");
						}

						Long uid = null;
						Long tid = null;
						// 检查手机号码、邮箱是否被使用
						UTeacherDBO u1 = new UTeacherDBO();
						u1.setPhone(phone);
						u1.setPuk("1");
						List<UTeacherDBO> list1 = (List<UTeacherDBO>) uTeacherService.doSelectData(u1);
						if (list1 != null && list1.size() > 0) {
							throw new IllegalArgumentException("手机号或邮箱已经存在");
						}
						UTeacherDBO u2 = new UTeacherDBO();
						u2.setEmail(email);
						u2.setPuk("1");
						List<UTeacherDBO> list2 = (List<UTeacherDBO>) uTeacherService.doSelectData(u2);
						if (list2 != null && list2.size() > 0) {
							throw new IllegalArgumentException("手机号或邮箱已经存在");
						}
						// 匹配用户表
						UserDBO userp = new UserDBO();
						userp.setPhone(phone);
						userp.setPuk("1");
						List<UserDBO> plist = (List<UserDBO>) userService.doSelectData(userp);

						UserDBO usere = new UserDBO();
						usere.setEmail(email);
						usere.setPuk("1");
						List<UserDBO> elist = (List<UserDBO>) userService.doSelectData(usere);
						// 存在则直接获取uid
						if (plist != null && plist.size() > 0) {
							uid = plist.get(0).getUid();
						} else if (elist != null && elist.size() > 0) {
							uid = elist.get(0).getUid();
						} else {
							// 新建
							UserDBO user = new UserDBO();
							user.setAccount(phone);
							user.setEmail(email);
							user.setEmailStatus(1);
							user.setPhone(phone);
							user.setPassword(MD5SecurityHelper.encrypt("qxy123456"));
							user.setStatus(1);
							userService.doInsert(user);
							uid = user.getUid();
						}
						// 插入教师表
						UTeacherDBO tearcher = new UTeacherDBO();
						tearcher.setTeacherName(teacherName);
						tearcher.setUid(uid);
						tearcher.setPhone(phone);
						tearcher.setEmail(email);
						tearcher.setStatus(1);
						tearcher.setIsUse(1);
						if (sid != null) {
							tearcher.setSid(sid);
							tearcher.setPuk("1");
						}
						uTeacherService.doInsert(tearcher);
						tid = tearcher.getTid();
						// 插入扩展表
						UTeacherExtDBO ext = new UTeacherExtDBO();
						ext.setTid(tid);
						ext.setGender(gender);
						ext.setDateOfBirth(dateOfBirth);
						ext.setStartWorkT(startWorkT);
						ext.setJobNumber(jobNumber);
						ext.setCardType(cardType);
						ext.setCardNumber(cardNumber);
						ext.setHomeAddress(homeAddress);
						ext.setIsUse(1);
						uTeacherExtService.doInsert(ext);

						successCount++;
					}
				} catch (Exception e) {
					error.put("" + i, e.getMessage());
				}
			}
			data.put("count", successCount);
			data.put("error", error);
			data.put("info", "ok");
			result.setData(data);
		} catch (Exception e) {
			logger.error("导入失败", e);
			result.setInfo("导入失败，" + e.getMessage());
			result.setStatus(1);
		} finally {
			if (workbook != null) {
				try {
					workbook.close();
				} catch (Exception e) {
					logger.error("导入workbook关闭异常", e);
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					logger.error("导入excel关闭异常", e);
				}
			}
		}
		return result;
	}

	/**
	 * 学生卡导入接口 /import/studentRfid?token=[token]
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/import/studentRfid", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean importStudentRfidPOST(@RequestParam("excel") MultipartFile file, String token, Long sid) {
		RESTResultBean result = new RESTResultBean();
		InputStream is = null;
		Workbook workbook = null;
		try {
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			SchoolDBO school = new SchoolDBO();
			if (sid != null) {
				school.setSid(sid);
				school.setPuk("1");
			}
			school = (SchoolDBO) schoolService.doRead(school);
			if (school == null) {
				throw new IllegalArgumentException("学校不存在");
			}
			is = file.getInputStream();
			String filename = file.getOriginalFilename();
			if (filename != null && (filename.lastIndexOf(".xlsx") == (filename.length() - 5))) {
				workbook = new XSSFWorkbook(is);
			} else {
				workbook = new HSSFWorkbook(is);
			}

			Sheet sheet = workbook.getSheetAt(0);

			int rowCount = sheet.getLastRowNum();
			Map<String, Object> data = new HashMap<String, Object>();
			Map<String, String> error = new HashMap<String, String>();
			int successCount = 0;
			for (int i = 1; i <= rowCount; i++) {
				try {
					Row row = sheet.getRow(i);
					if (row.getLastCellNum() > 1) {
						// #学生姓名 学号 rfid卡号 是否有效
						// 学生姓名忽略
						Long number = ExcelUtil.convertToLong(row.getCell(2)); // 学号
						if (number == null) {
							throw new IllegalArgumentException("学号不能为空");
						}
						Long rfid = ExcelUtil.convertToLong(row.getCell(3)); // 学生卡id
						if (rfid == null) {
							throw new IllegalArgumentException("rfid卡号不能为空");
						}
						Integer isEffective = ExcelUtil.convertToInteger(row.getCell(4)); // 是否有效
						if (isEffective == null || isEffective != 0) {
							isEffective = 1;
						}

						UStudentDBO student = new UStudentDBO();
						if (sid != null) {
							student.setSid(sid);
							student.setPuk("1");
						}
						student.setNumber(number);
						List<UStudentDBO> students = (List<UStudentDBO>) uStudentService.doSelectData(student);
						if (students.size() == 0) {
							throw new IllegalArgumentException("该学生不存在");
						} else {
							student = students.get(0);
						}

						// 查询该学生是否已经有卡
						StudentRfidDBO sdbo = new StudentRfidDBO();
						sdbo.setStudentId(student.getStudentId());
						if (sid != null) {
							sdbo.setSid(sid);
							sdbo.setPuk("1");
						}
						List<StudentRfidDBO> rlist = (List<StudentRfidDBO>) studentRfidService.doSelectData(sdbo);
						if (rlist != null && rlist.size() > 0) {
							throw new IllegalArgumentException("该学生已经添加过卡");
						} else {
							sdbo.setRfid(rfid);
							sdbo.setIsEffective(isEffective);
							sdbo.setIsUse(1);
							studentRfidService.doInsert(sdbo);
						}

						successCount++;
					}
				} catch (Exception e) {
					error.put("" + i, e.getMessage());
				}
			}
			data.put("count", successCount);
			data.put("error", error);
			data.put("info", "ok");
			result.setData(data);
		} catch (Exception e) {
			logger.error("导入失败", e);
			result.setInfo("导入失败，" + e.getMessage());
			result.setStatus(1);
		} finally {
			if (workbook != null) {
				try {
					workbook.close();
				} catch (Exception e) {
					logger.error("导入workbook关闭异常", e);
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					logger.error("导入excel关闭异常", e);
				}
			}
		}
		return result;
	}
	
	/**
	 * 班级课程表导入接口 /import/syllabus?token=[token]
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/import/syllabus", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean importSyllabusPOST(@RequestParam("excel") MultipartFile file, String token, Long cid,
			String[] dates) {
		RESTResultBean result = new RESTResultBean();
		InputStream is = null;
		Workbook workbook = null;
		try {
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			if (cid == null) {
				throw new IllegalArgumentException("cid不能为空");
			}
			// 验证班级
			ClassDBO clazz = new ClassDBO();
			clazz.setCid(cid);
			clazz = (ClassDBO) classService.doRead(clazz);
			if (clazz == null) {
				throw new IllegalArgumentException("班级不存在");
			}
			if (dates == null || dates.length != 7) {
				throw new IllegalArgumentException("课程应用日期必须为7天");
			}
			is = file.getInputStream();
			String filename = file.getOriginalFilename();
			if (filename != null && (filename.lastIndexOf(".xlsx") == (filename.length() - 5))) {
				workbook = new XSSFWorkbook(is);
			} else {
				workbook = new HSSFWorkbook(is);
			}
			
			Sheet sheet = workbook.getSheetAt(0);
			
			int rowCount = sheet.getLastRowNum();
			Map<String, Object> data = new HashMap<String, Object>();
			Map<String, String> error = new HashMap<String, String>();
			int successCount = 0;
			ClassCourseDBO classCourse;
			List<ClassCourseDBO> classCourses;
			SCourseDBO course;
			List<SCourseDBO> courses;
			List<ClassTeacherPVO> ctlist = null;
			for (int i = 1; i <= rowCount; i++) {
				try {
					Row row = sheet.getRow(i);
					if (row.getLastCellNum() > 1) {
						// 课次	周一		周二		周三		周四		周五		周六		周日
						Integer classTime = ExcelUtil.convertToInteger(row.getCell(1)); // 课次
						if (classTime == null || classTime <= 0) {
							throw new IllegalArgumentException("错误的课次");
						}
						for (int j = 0; j < dates.length; j++) {

							try {
								String courseName = ExcelUtil.convertToString(row.getCell(2 + j));	// 课程名称
								if (StringUtils.isEmpty(courseName)) {
//									throw new IllegalArgumentException("课程名称不能为空");
									continue;	// 跳过
								}
								// 查询对应课程
								course = new SCourseDBO();
								course.setSid(clazz.getSid());
								course.setCourseName(courseName);
								course.setPuk("1");
								courses = (List<SCourseDBO>) sCourseService.doSelectData(course);
								if (courses == null || courses.size() == 0) {
									throw new IllegalArgumentException("课程不存在");
								}
								course = courses.get(0);
								// 判断课次是否已经存在
								classCourse = new ClassCourseDBO();
								classCourse.setCid(cid);
								classCourse.setSid(clazz.getSid());
								classCourse.setPuk("1");
								classCourse.setClassTime(classTime);
								classCourse.setUseDay(dates[j]);
								classCourses = (List<ClassCourseDBO>) classCourseService.doSelectData(classCourse);
								if (classCourses != null && classCourses.size() > 0) {
									throw new IllegalArgumentException("该课次已存在课程");
								}
								
								//获取tid和教师名
								ClassTeacherDBO classTeacher =new ClassTeacherDBO();
								classTeacher.setPuk("1");
								classTeacher.setCid(cid);
								classTeacher.setCourseId(course.getCourseId());
								classTeacher.setSid(clazz.getSid());
								ctlist = (List<ClassTeacherPVO>)classTeacherService.doSelectTeacherName(classTeacher);
								if(ctlist != null && ctlist.size() > 0){
									classCourse.setTid(ctlist.get(0).getTid());
									classCourse.setTeacherName(ctlist.get(0).getTeacherName());
								}
								classCourse.setTerm(clazz.getTermId());
								classCourse.setIsUse(1);
								classCourse.setCourseId(course.getCourseId());
								classCourse.setCourseName(courseName);
								classCourseService.doInsert(classCourse);
								
								successCount++;
							} catch (Exception e) {
								error.put("" + i + "-" + (j + 1), e.getMessage());
							}
						}
					}
				} catch (Exception e) {
					error.put("" + i, e.getMessage());
				}
			}
			data.put("count", successCount);
			data.put("error", error);
			data.put("info", "ok");
			result.setData(data);
		} catch (Exception e) {
			logger.error("导入失败", e);
			result.setInfo("导入失败，" + e.getMessage());
			result.setStatus(1);
		} finally {
			if (workbook != null) {
				try {
					workbook.close();
				} catch (Exception e) {
					logger.error("导入workbook关闭异常", e);
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					logger.error("导入excel关闭异常", e);
				}
			}
		}
		return result;
	}
}
