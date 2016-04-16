package test;

public class TestMobAppConstants
{

	public static final String MODEL_DIR = "MobAppIde/MobAppModel";
	public static final String WEBAPP_CARTRIDE = "flca.mda.templates.webapp";
	public static final String PLUGIN_DIR = "flca.mda.generator";
	
	public static final String BACKEND_SRC_GEN = backendSource();
	public static final String FLEX_SRC_GEN = "../MopAppIdeFrontend/MobAppIdeFlex-mda-src-gen";

	//userdirs
	public static final String BACKEND_USER_DIR = "../MobAppIdeBackend/src";
	
	public static final String PAGE_BASE 		= "flca.mobapp.entity.PageBase";
	public static final String PAGE_OVERLAY 	= "flca.mobapp.entity.PageOverlay";
	public static final String PAGE_LIST 		= "flca.mobapp.entity.PageList";
	public static final String PAGE_TEXT 		= "flca.mobapp.entity.PageText";
	public static final String PAGE_TIMETABLE 	= "flca.mobapp.entity.PageTimetable";
	public static final String LIST_ITEM 		= "flca.mobapp.entity.ListItem";
	public static final String REF_COORD 		= "flca.mobapp.entity.RefCoord";
//	public static final String NORMAL_CLZ		= "flca.mobapp.entity.NormalCls";
	public static final String PAGE_FROMXCEL 	= "flca.mobapp.entity.PageFromXcel";
	public static final String PAGE_LIST_FROMXCEL 	= "flca.mobapp.entity.PageListFromXcel";
	public static final String PAGE_FREEFORMAT 	= "flca.mobapp.entity.PageFreeFormat";
	
	public static final String SRV 			  	= "flca.mobapp.service.MobAppServices";
	
	public static final String entities[] = new String[] 
	   {PAGE_BASE, PAGE_OVERLAY, PAGE_LIST, PAGE_TEXT, PAGE_TIMETABLE, LIST_ITEM, REF_COORD, PAGE_FROMXCEL, PAGE_LIST_FROMXCEL, PAGE_FREEFORMAT};
//	public static final String entities[] = new String[] {LIST_ITEM, REF_COORD};
	
	private static String backendSource() 
	{
		if (isMyLaptop()) {
			return "../MobAppIdeBackend/src-mda-generated";
		} else {
			return "../MobAppBackend/MobAppIdeBackend/src-mda-generated";
		}
	}
	
	public static boolean isMyLaptop()
	{
		String s = System.getProperty("user.name");
//		System.out.println(s);
		return s.indexOf("bakkerus" ) > 0;
	}
}
