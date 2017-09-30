package com.test.util;

import java.util.HashMap;
import java.util.Map;

import com.test.web.JSONView;


/**
 * 项目的常量类
 */
public interface Constants {

    String JSON_ROOT="JSON_ROOT";

    JSONView JSON_VIEW=new JSONView();
    
    String LAYOUT_PAGE="/pages/system/layout.jsp";
    
    String ERROR_URL = "/pages/error/e_404.jsp";
    
    String WRONG_URL = "/pages/error/500.jsp";

    String SESSION_USER="SESSION_USER";

    String SESSION_USER_JOB="SESSION_USER_JOB";

    String PASSWORD_SALT_KEY="salt.chinatelecom.sas.2015";

    String SESSION_USER_PROVINCE="SESSION_USER_PROVINCE";
    
    String SESSION_PROJECT_BASEPATH = "SESSION_PROJECT_BASEPATH";
    
    String PROJECT_NAME = "PROJECT_NAME";
    
    String SESSION_PERSONAL_CONFIG = "SESSION_PERSONAL_CONFIG";
    
    //当前用户所属公司的所有被服务公司
    String SESSION_USER_BSORG = "SESSION_USER_BSORG";
    
    /**
     * 验证码在Cookie中的名称
     */
    String COOKIE_VER_CODE="_cvc";
	
	/**
     * 验证码生成时间在Cookie中的名称
     */
    String COOKIE_VER_CODE_TIME="_cvct";
	
	/**
     * 验证码密钥，MD5(System.currentTimeMillis()+"_"+验证码+"_"+COOKIE_VER_CODE_SALT_KEY)
     */
    String COOKIE_VER_CODE_SALT_KEY="*9dkAxk@^^!(>,abxzL*";

    /**
     * 登陆失败次数
     */
    String LOGIN_ERROR_TIMES = "_loginert";
	/**
	 * 验证码名称
	 */
	String IMAGE_CODE = "imgcode";

    /**
     * 文件上传路径定义
     */
    @SuppressWarnings("serial")
	Map<String,String> FILE_PATH = new HashMap<String, String>(){{
        String FILE_ROOT_PATH = "/upload";

        /***********************************************************************/
        /**
         * 软件相关文件存放路径
         */
        String SOFTWARE_FILE_ROOT_PATH = "/softutil";
        put("SOFTWARE_FILE_ROOT_PATH", SOFTWARE_FILE_ROOT_PATH);
        /**
         * 软件logo图片存放路径
         */
        String SOFTWARE_LOGO_IMG_PATH = FILE_ROOT_PATH+SOFTWARE_FILE_ROOT_PATH+"/logoimg";
        put("SOFTWARE_LOGO_IMG_PATH", SOFTWARE_LOGO_IMG_PATH);
        /**
         * 软件可执行文件存放路径
         */
        String SOFTWARE_EXECUTE_FILE_PATH = FILE_ROOT_PATH+SOFTWARE_FILE_ROOT_PATH+"/executefile";
        put("SOFTWARE_EXECUTE_FILE_PATH", SOFTWARE_EXECUTE_FILE_PATH);

        /**
         * 软件相关文档存放路径
         */
        String SOFTWARE_DOCUMENT_FILE_PATH = FILE_ROOT_PATH+SOFTWARE_FILE_ROOT_PATH+"/document";
        put("SOFTWARE_DOCUMENT_FILE_PATH", SOFTWARE_DOCUMENT_FILE_PATH);


        /***********************************************************************/

        /**
         * 知识OR难题相关文件存放路径
         */
        String KQ_FILE_ROOT_PATH = "/kqutil";
        put("KQ_FILE_ROOT_PATH", KQ_FILE_ROOT_PATH);

        /**
         * 知识OR难题相关文档存放路径
         */
        String KQ_DOCUMENT_FILE_PATH = FILE_ROOT_PATH+KQ_FILE_ROOT_PATH+"/document";
        put("KQ_DOCUMENT_FILE_PATH", KQ_DOCUMENT_FILE_PATH);

        /***********************************************************************/

        /**
        * 客户端相关文件根路径
        */
        String CLIENT_FILE_ROOT_PATH = "/smartithelper";
        put("CLIENT_FILE_ROOT_PATH", CLIENT_FILE_ROOT_PATH);

        /**
         * 客户端安装文件存放路径
         */
        String CLIENT_INSTALL_FILE_PATH = FILE_ROOT_PATH+CLIENT_FILE_ROOT_PATH;
        put("CLIENT_INSTALL_FILE_PATH", CLIENT_INSTALL_FILE_PATH);







        /***********************************************************************/
    }};
}
