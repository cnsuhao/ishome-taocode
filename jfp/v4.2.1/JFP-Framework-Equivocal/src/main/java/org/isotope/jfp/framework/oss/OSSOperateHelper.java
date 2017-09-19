package org.isotope.jfp.framework.oss;

import java.io.IOException;

import org.isotope.jfp.framework.utils.PKHelper;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;

/**
 * 断点续传上传用法示例
 * @since 2.4.2 2016/1/6
 */
public class OSSOperateHelper {

	// http://coobarzz.oss-cn-hangzhou.aliyuncs.com/231395c199b74e7d84bedb146f957ae6.png
	private static String publicServer = "http://hangzhou.aliyuncs.com/";
	private static String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
	private static String accessKeyId = "accessKeyId";
	private static String accessKeySecret = "accessKeySecret";
	private static String bucketName = "bucketName";

	public String putObject(MultipartFile file, boolean pubUrl) throws Throwable {
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		try {
			String key = PKHelper.creatUUKey();
			String[] ft = file.getOriginalFilename().split("\\.");
			String realFileName = key + "." + ft[1];
			// 待上传的本地文件
			// PutObjectResult putObjectResult =
			ossClient.putObject(bucketName, realFileName, file.getInputStream());
			// System.out.println(file.getName());
			// System.out.println(realFileName);
			// System.out.println(putObjectResult.getRequestId());
			if (pubUrl)
				return publicServer + realFileName;
			return realFileName;
		} finally {
			ossClient.shutdown();
		}
	}

	public void removeObject(String key) throws IOException {
		// String key = "231395c199b74e7d84bedb146f957ae6.png";
		OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		client.deleteObject(bucketName, key);
	}
	
}
