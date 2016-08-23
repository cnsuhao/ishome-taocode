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
import com.mcookies.qxy.common.ClassStudent.ClassStudentDBO;
import com.mcookies.qxy.common.ClassStudent.ClassStudentService;
import com.mcookies.qxy.common.UParent.UParentService;
import com.mcookies.qxy.common.UStudent.UStudentDBO;
import com.mcookies.qxy.common.UStudent.UStudentService;
import com.mcookies.qxy.common.UStudentExt.UStudentExtDBO;
import com.mcookies.qxy.common.UStudentExt.UStudentExtService;
import com.mcookies.qxy.common.UStudentParent.UStudentParentService;
import com.mcookies.qxy.common.UTeacher.UTeacherService;
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

	/**
	 * 班级学生导入接口 /import/student?token=[token]
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/import/student", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean importStudentPOST(@RequestParam("excel") MultipartFile file, @RequestParam String token,
			@RequestParam(required = false) Long cid) {
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
			Map<Integer, String> error = new HashMap<Integer, String>();
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
						List<UStudentDBO> stus = (List<UStudentDBO>) uStudentService.doSelectData(stu);
						if (stus != null && stus.size() > 0) {
							throw new IllegalArgumentException("手机号码已经存在");
						}
						stu = new UStudentDBO();
						stu.setEmail(email);
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
						List<UserDBO> plist = (List<UserDBO>) userService.doSelectData(userp);

						UserDBO usere = new UserDBO();
						usere.setEmail(email);
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
						classStudentService.doInsert(classStudent);

						successCount++;
					}
				} catch (Exception e) {
					error.put(i, e.getMessage());
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
	
//	/**
//	 * 学生家长导入接口 /import/student/parent?token=[token]
//	 */
//	@SuppressWarnings("unchecked")
//	@RequestMapping(value = "/import/student/parent", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
//	@ResponseBody
//	public RESTResultBean importStudentParentPOST(@RequestParam("excel") MultipartFile file, @RequestParam String token,
//			@RequestParam(required = false) Long cid) {
//		RESTResultBean result = new RESTResultBean();
//		InputStream is = null;
//		Workbook workbook = null;
//		try {
//			if (doCheckToken(token) == false) {
//				return tokenFail();
//			}
//			if (cid == null) {
//				throw new IllegalArgumentException("cid不能为空");
//			}
//			// 验证班级
//			ClassDBO clazz = new ClassDBO();
//			clazz.setCid(cid);
//			clazz = (ClassDBO) classService.doRead(clazz);
//			if (clazz == null) {
//				throw new IllegalArgumentException("班级不存在");
//			}
//			is = file.getInputStream();
//			String filename = file.getOriginalFilename();
//			if (filename != null && (filename.lastIndexOf(".xlsx") == (filename.length() - 5))) {
//				workbook = new XSSFWorkbook(is);
//			} else {
//				workbook = new HSSFWorkbook(is);
//			}
//			
//			Sheet sheet = workbook.getSheetAt(0);
//			
//			int rowCount = sheet.getLastRowNum();
//			Map<String, Object> data = new HashMap<String, Object>();
//			Map<Integer, String> error = new HashMap<Integer, String>();
//			int successCount = 0;
//			for (int i = 1; i <= rowCount; i++) {
//				try {
//					Row row = sheet.getRow(i);
//					if (row.getLastCellNum() > 1) {
//						// 学生学号	#学生姓名	家长角色	家长姓名	家长电话	工作单位	职位	是否为主家长
//						Integer role = null;	// 家长角色
//						Long number = null;	// 学生学号
//						Integer isDefault = null;	// 是否为缺省学生
//						String photo = ExcelUtil.convertToString(row.getCell(1)); // 学籍照片
//						String photo = ExcelUtil.convertToString(row.getCell(1)); // 学籍照片
//						String photo = ExcelUtil.convertToString(row.getCell(1)); // 学籍照片
//						String studentName = ExcelUtil.convertToString(row.getCell(2)); // 学生姓名
//						String email = ExcelUtil.convertToString(row.getCell(3)); // 邮箱
//						if (StringUtils.isEmpty(email)) {
//							throw new IllegalArgumentException("电子邮箱不能为空");
//						}
//						Long number = ExcelUtil.convertToLong(row.getCell(4)); // 学号
//						if (number == null) {
//							throw new IllegalArgumentException("学号不能为空");
//						}
//						String phone = ExcelUtil.convertToString(row.getCell(5)); // 联系电话
//						if (StringUtils.isEmpty(phone)) {
//							throw new IllegalArgumentException("手机号码不能为空");
//						}
//						String dateOfSchool = ExcelUtil.convertToString(row.getCell(6)); // 入学日期
//						String studentType = ExcelUtil.convertToString(row.getCell(7)); // 学生类别
//						String nation = ExcelUtil.convertToString(row.getCell(8)); // 民族
//						String nativePlace = ExcelUtil.convertToString(row.getCell(9)); // 籍贯
//						String cardType = ExcelUtil.convertToString(row.getCell(10)); // 证件类型
//						String cardNumber = ExcelUtil.convertToString(row.getCell(11)); // 证件号码
//						String political = ExcelUtil.convertToString(row.getCell(12)); // 政治面貌
//						Integer isOverseas = ExcelUtil.convertToInteger(row.getCell(13)); // 是否港澳台侨胞
//						// String fith = null; // 宗教信仰
//						// String health = null; // 健康状况
//						// String blood = null; // 血型
//						String dateOfBirth = ExcelUtil.convertToString(row.getCell(14)); // 出生日期
//						String residenceType = ExcelUtil.convertToString(row.getCell(15)); // 户口类型
//						String residenceAddress = ExcelUtil.convertToString(row.getCell(16)); // 户口地址
//						
//						Long uid = null;
//						Long studentId = null;
//						// 根据手机号码查询学生是否存在
//						// 检查手机号码、邮箱是否被使用
//						UStudentDBO stu = new UStudentDBO();
//						stu.setPhone(phone);
//						List<UStudentDBO> stus = (List<UStudentDBO>) uStudentService.doSelectData(stu);
//						if (stus != null && stus.size() > 0) {
//							throw new IllegalArgumentException("手机号码已经存在");
//						}
//						stu = new UStudentDBO();
//						stu.setEmail(email);
//						stus = (List<UStudentDBO>) uStudentService.doSelectData(stu);
//						if (stus != null && stus.size() > 0) {
//							throw new IllegalArgumentException("邮箱已经存在");
//						}
//						stu = new UStudentDBO();
//						stu.setSid(clazz.getSid()); // 学校中学号唯一即可
//						stu.setNumber(number);
//						stu.setPuk("1");
//						stus = (List<UStudentDBO>) uStudentService.doSelectData(stu);
//						if (stus != null && stus.size() > 0) {
//							throw new IllegalArgumentException("学号已经存在");
//						}
//						// 匹配用户表
//						UserDBO userp = new UserDBO();
//						userp.setPhone(phone);
//						List<UserDBO> plist = (List<UserDBO>) userService.doSelectData(userp);
//						
//						UserDBO usere = new UserDBO();
//						usere.setEmail(email);
//						List<UserDBO> elist = (List<UserDBO>) userService.doSelectData(usere);
//						// 存在则直接获取uid
//						if (plist != null && plist.size() > 0) {
//							uid = plist.get(0).getUid();
//						} else if (elist != null && elist.size() > 0) {
//							uid = elist.get(0).getUid();
//						} else {
//							// 新建
//							UserDBO user = new UserDBO();
//							user.setAccount(phone);
//							user.setEmail(email);
//							user.setEmailStatus(1);
//							user.setPhone(phone);
//							user.setPassword(MD5SecurityHelper.encrypt("qxy123456"));
//							user.setStatus(1);
//							userService.doInsert(user);
//							uid = user.getUid();
//						}
//						// 插入学生表
//						UStudentDBO theStu = new UStudentDBO();
//						theStu.setStudentName(studentName);
//						theStu.setSid(clazz.getSid());
//						theStu.setUid(uid);
//						theStu.setNumber(number);
//						theStu.setPhone(phone);
//						theStu.setEmail(email);
//						theStu.setStatus(1);
//						theStu.setIsUse(1);
//						theStu.setPuk("1");
//						uStudentService.doInsert(theStu);
//						studentId = theStu.getStudentId();
//						// 插入扩展表
//						UStudentExtDBO ext = new UStudentExtDBO();
//						ext.setStudentId(studentId);
//						ext.setPhoto(photo);
//						ext.setNation(nation);
//						ext.setNativePlace(nativePlace);
//						ext.setCardType(cardType);
//						ext.setCardNumber(cardNumber);
//						ext.setIsOverseas(isOverseas);
//						ext.setPolitical(political);
//						ext.setDateOfSchool(dateOfSchool);
//						ext.setDateOfBirth(dateOfBirth);
//						ext.setStudentType(studentType);
//						ext.setResidenceType(residenceType);
//						ext.setResidenceAddress(residenceAddress);
//						ext.setIsUse(1);
//						uStudentExtService.doInsert(ext);
//						// 插入班级学生表
//						ClassStudentDBO classStudent = new ClassStudentDBO();
//						classStudent.setCid(clazz.getCid());
//						classStudent.setJoinTime(DateHelper.currentTimeMillis2());
//						classStudent.setStudentId(studentId);
//						classStudent.setIsUse(1);
//						classStudentService.doInsert(classStudent);
//						
//						successCount++;
//					}
//				} catch (Exception e) {
//					error.put(i, e.getMessage());
//				}
//			}
//			data.put("count", successCount);
//			data.put("error", error);
//			data.put("info", "ok");
//			result.setData(data);
//		} catch (Exception e) {
//			logger.error("导入失败", e);
//			result.setInfo("导入失败，" + e.getMessage());
//			result.setStatus(1);
//		} finally {
//			if (workbook != null) {
//				try {
//					workbook.close();
//				} catch (Exception e) {
//					logger.error("导入workbook关闭异常", e);
//				}
//			}
//			if (is != null) {
//				try {
//					is.close();
//				} catch (IOException e) {
//					logger.error("导入excel关闭异常", e);
//				}
//			}
//		}
//		return result;
//	}
}
