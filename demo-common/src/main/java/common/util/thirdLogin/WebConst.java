package common.util.thirdLogin;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class WebConst {

	/**
	 * 获取缓存的名称
	 * @param c
	 * @return
	 */
	public static String getCacheName(Class c){
		return c.getSimpleName()+"_Cache";
	}

	//文件上传路径
	public static String UPLOAD_PATH;

	static {
		try{
			ResourceBundle resource = ResourceBundle.getBundle("application");
			UPLOAD_PATH =  resource.getString("FILE_PATH");
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	/**申请单后台取前台json的key值*/
	public static final String CONST_REQUISITION="requisition";
	/**申请单明细后台取前台json的key值*/
	public static final String CONST_REQUISITIONLINE="requisitionline";
	/**个人信息前台json的key值*/
	public static final String CONST_USER = "user";
	/**投资信息前台的json值*/
	public static final String CONST_INVESTMENT = "investment";
	/**支付信息前台json的key值*/
	public static final String CONST_PAYMENT = "payment";
	/**缴费信息前台json的key值*/
	public static final String CONST_CHARGE = "charge";
	/**经营规模前台json的key值*/
	public static final String CONST_BP_SIZE = "bpSize";
	/**经营信息前台json的key值*/
	public static final String CONST_BP_STATUS = "bpstatus";
	/**机构信息前台json的key值*/
	public static final String CONST_BPARTNER = "bpartner";
	/**权证信息前台json的key值*/
	public static final String CONST_M_PRODUCT = "mProduct";
	/**处罚信息前台json的key值*/
	public static final String CONST_M_PUNISH = "mPunish";
	/**基础信息前台json的key值*/
	public static final String CONST_AD_ORG = "adOrg";
	/**资质信息前台json的key值*/
	public static final String CONST_A_ASSET = "asset";
	/**属性集json的key值*/
	public static final String CONST_M_SETINANCE ="setTance";
	/**属性实例json的key值*/
	public static final String CONST_M_INSTANCE = "instance";
	/**保险参保信息json的key值*/
	public static final String CONST_M_INSURANCED = "insured";
	/**封装多表联查字段的别名：树名*/
	public static final String CONST_TREENAME ="treeName";
	/**封装多表联查字段的别名：树节点id*/
	public static final String CONST_NODEID ="nodeId";
	/**封装多表联查字段的别名：树父节点id*/
	public static final String CONST_PARENTID ="parentId";
	/**封装多表联查字段的别名：树id*/
	public static final String CONST_TREEID ="treeId";
	/**封装多表联查字段的别名：树节点名称*/
	public static final String CONST_TREENODENAME ="treeNodeName";
	/**封装多表联查字段的别名：是否有子节点*/
	public static final String CONST_ISSUMMERY ="issummery";
	/**用于流程变量的封装map的key*/
	public static final String CONST_USERID="userId";
	/**用于流程变量的封装map的key*/
	public static final String CONST_USERIDS="userIds";
	/**用于流程变量的封装map的key*/
	public static final String CONST_STATUS="status";
	/**地址信息的json的key值*/
	public static final String CONST_LOCATION = "location";
	/**投资信息以及股东信息的json的key值*/
	public static final String CONST_BP_INVESTMETN = "bpinvestment";

	/**用于流程启动的key*/
	public static final String CONST_LOANREVIEW="loanReview";
	
	/**用于流程启动的key*/
	public static final String CONST_ALLOWANCEREVIEW="allowanceReview";
	
	/**用于流程启动的key*/
	public static final String CONST_COMMONREVIEW="commonReview";
	
	/**用于流程启动的key*/
	public static final String CONST_RISKSHARING="firstAndSecondAllowance";

	/** 查询每页最大返回记录数  */
	public static final int PAGE_SIZE = 10;
	public static final int MAX_PAGE_SIZE = 100;
	
	/*返回参数配置*/
	public static final String RESULT_MSG = "msg";
	public static final String RESULT_CODE = "code";
	public static final String RESULT_DATA = "data";

	/*角色id*/
	public static final Long ROLE_PERSON_ID = 999999L; //个人
	public static final Long ROLE_COMPANY_ID = 900000L; //企业
	
	public static final Long ROLE_EDITOR_ID 	= 110L; 	//编辑员
	public static final Long ROLE_AUDITOR_ID 	= 220L; 	//审核员

    public static final Long ROLE_VILLAGEINFO_AUDITOR_ID = 221L;    //村务信息审核员
    public static final Long ROLE_VILLAGEINFO_EDIT_ID = 111L;        //村务信息编辑员

    public static final Long ROLE_AQ_ID = 6666L;    //信息采集员
    public static final Long ROLE_CK_ID = 7777L;    //信息复核员
	
	
	public static final Long ROLE_GOVERNMENT_ID 	= 1000L; 	//政府机构
	public static final Long ROLE_AGRICULTURAL_ID 	= 1111L; 	//	农委
	public static final Long ROLE_PSC_ID   			= 3333L; 	//	收储公司
	public static final Long ROLE_EQUITY_EXCHANGE_ID = 4444L; 	//	产权交易所
	public static final Long ROLE_CENTRALBANK_ID 	=  8000L; 	//	人民银行
	public static final Long ROLE_FINANCEOFFICE_ID 	= 8010L; 	//	金融办
	
	
	public static final Long ROLE_SUPER_ADMIN_ID = 100L; //超级管理员
	public static final Long ROLE_SYSTEM_AUDITOR_ID =330L; // 系统审核员
	public static final Long ROLE_ADMIN_ID = 0L; 

	/*接口调用返回参数*/
	public static Map<String,Object> resultMap(String code,String msg,Object obj){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put(RESULT_CODE,code);
		resultMap.put(RESULT_MSG,msg);
		resultMap.put(RESULT_DATA,obj);
		return resultMap;
	}

	/*接口调用返回参数*/
	public static Map<String,Object> resultMap(String code,String msg){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put(RESULT_CODE,code);
		resultMap.put(RESULT_MSG,msg);
		return resultMap;
	}

	/*接口调用返回参数*/
	public static Map<String,Object> resultMap(String code,String msg,Object obj,Object total){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put(RESULT_CODE,code);
		resultMap.put(RESULT_MSG,msg);
		resultMap.put(RESULT_DATA,obj);
		resultMap.put("total",total);
		return resultMap;
	}

}
