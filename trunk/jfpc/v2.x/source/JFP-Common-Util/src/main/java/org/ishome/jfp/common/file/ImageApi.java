package org.ishome.jfp.common.file;

import org.ishome.jfp.framework.support.MyContentTypeSupport;

/**
 * 图片显示
 * 
 * @author Spook
 * @version 0.1
 * @since 0.1.0 2014/6/10
 * 
 */
//@Controller
public class ImageApi extends MyContentTypeSupport {
//	protected static final Logger logger = LoggerFactory.getLogger(ImageApi.class);
	//@Resource
	//FTPUtil FTPUtil_;

//	/**
//	 * 显示一张图片
//	 * 
//	 * @param id
//	 * @param token
//	 * @param request
//	 * @param response
//	 * @throws ServletException
//	 * @throws IOException
//	 */
//	@RequestMapping(value = "/00003030/{token}", method = RequestMethod.GET)
//	public void do00003030GetToken(@PathVariable String token, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
//
//		getResponseContextImageType(response);
//		try {
//			OutputStream out = response.getOutputStream();
//			FileInputStream in;
//
//			in = new FileInputStream(FTPUtil_.getDefaultPic());
//
//			byte[] buffer = new byte[1024];
//			int n = 0;
//			while ((n = in.read(buffer)) != -1) {
//				out.write(buffer, 0, n);
//			}
//			out.flush();
//			out.close();
//			in.close();
//		} catch (Exception e) {
//			// e.printStackTrace();
//		}
//	}
//
//	// http://127.0.0.1:8800/00003030/123/123
//	/**
//	 * 显示一张图片
//	 * 
//	 * @param id
//	 * @param token
//	 * @param request
//	 * @param response
//	 * @throws ServletException
//	 * @throws IOException
//	 */
//	@RequestMapping(value = "/00003030/{id}/{token}", method = RequestMethod.GET)
//	public void do00003030GetToken(@PathVariable String id, @PathVariable String token, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
//		try {
//			OutputStream out = response.getOutputStream();
//			FileInputStream in;
//			if (StringUtils.isEmpty(id)) {
//				getResponseContextImageType(response);
//				in = new FileInputStream(FTPUtil_.getDefaultPic());
//			} else {
//				try {
//					in = new FileInputStream(FTPUtil_.getFileUri(id));
//					getResponseContextType(response, id);
//				} catch (Exception e) {
//					in = new FileInputStream(FTPUtil_.getDefaultPic());
//				}
//			}
//			byte[] buffer = new byte[1024];
//			int n = 0;
//			while ((n = in.read(buffer)) != -1) {
//				out.write(buffer, 0, n);
//			}
//			out.flush();
//			out.close();
//			in.close();
//		} catch (Exception e) {
//			// e.printStackTrace();
//		}
//	}

	

}
