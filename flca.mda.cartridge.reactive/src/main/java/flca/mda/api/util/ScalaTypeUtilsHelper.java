package flca.mda.api.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import flca.mda.codegen.helpers.StrUtil;
import mda.annotation.RestMethod;
import mda.annotation.RestService;
import mda.type.IEntityType;
import mda.type.IServiceType;
import reactive.Tid;

public class ScalaTypeUtilsHelper implements TypeConstants, ScalaTypeConstants {

	private ScalaTypeUtils tu;
	private NameUtils nu;
	
	protected static TemplateUtils tplu = new TemplateUtils();
	protected static ImportUtils impu = new ImportUtils();

	private final static String DEF_RETRIEVE_NESTED = "private def <%=P1%>(obj:<%=P2%>) (implicit session: Session) : <%=P3%> = {";

	protected ScalaTypeUtilsHelper(ScalaTypeUtils typeutils, NameUtils nameutils) {
		tu = typeutils;
		nu = nameutils;
	}
	
	public String getRowCaseClassParams(Class<?> aClass) {
		List<String> result = getRowCaseClassParamsExclOneTo(aClass);
		result.addAll(getRowCaseClassParamsOneToOne(aClass));
		if (tu.hasInheritance(aClass)) {
			result.add(getDiscriminatorRowClassParam(aClass));
		}
		return nu.join(result, "", ",\n\t\t", "");
	}
	
	public String getRowCloneParams(Class<?> aClass) {
		List<String> result = new ArrayList<>();

		for (Fw fieldwrapper : tu.getFieldsExc(aClass, FwSelectType.MANYTOONE) ) {
			ScalaFw fw = (ScalaFw) fieldwrapper;
			result.add("this." + fw.rowFieldname());
		}

		if (tu.hasInheritance(aClass)) {
			result.add(getDiscriminatorCloneParam(aClass));
		}

		return nu.join(result, "", ",\n\t\t", "");
	}
	
	public String getDefaultValue(Fw fw) {
		if (fw.isId()) {
			return NONE;
		} else if (fw.isSpecial() && fw.getSpecialfield().getDefaultValue() != null) {
			return fw.getSpecialfield().getDefaultValue();
		} else if (fw.isOneToManyField()) {
			return "Set()";
		} else if (fw.isRequired()) {
			return ScalaDataTypes.getDefaultValue(fw.type());
		} else {
			return NONE;
		}
	}	
	
	public String getDefaultRowValue(Fw fw) {
		if (!fw.isRequired() || fw.isId()) {
			return NONE;
		} else if (fw.type().isEnum()) {
			return "null";
		} else if (fw.isManyToOneField() || fw.isOneToOneField()) {
			return NONE;
		} else {
			return ScalaDataTypes.getDefaultRowValue(fw.type());
		}
	}
	
	// @formatter:off
	/**
	 * 
	 * This generates something like this for all nexted objects in the given
	 * class: private def retrieveTstAtstes(obj:TstA) (implicit session:
	 * Session): Set[TstE] = { val joinQuery = for { tstA <- tstAQuery if tstA.id
	 * === obj.id tstcs <- tstCQuery if tstA.id === tstcs.tstaId tste <-
	 * tstEQuery if tstcs.id === tste.tstCId } yield (tste) val rowlist =
	 * joinQuery.list rowlist.map(mapFromTstERow(_)).to[Set] }
	 * 
	 * @param aClass
	 * @return
	 */
	// @formatter:on
	public String genRetrieveAndMergeNestedObjects(Class<?> aClass) {
		StringBuffer sb = new StringBuffer();
		for (NestedObject nested : new NestedObjects(aClass).getNestedObjects()) {
			sb.append(genRetrieveAndMergeNestedObjects(nested) + "\n\n");
		}
		return sb.toString();
	}
	
	public String genRetrieveNestedObjectsStr(Class<?> aClass) {
		StringBuffer sb = new StringBuffer();
		for (NestedObject nested : getNestedObjects(aClass).getNestedObjects()) {
			sb.append(makeRetrieveNestedObjectsStr(nested) + NL);
			sb.append(TAB + NL);
		}
		return sb.toString();
	}


	public String retrieveNestedObjDef(Class<?> clz, Fw fw, Class<?> aType) {
		String type = (aType == null) ? "" : ":" + aType.getSimpleName();
		String result = makeDefNameRetrieve(clz, fw) + "(" + OBJ + type + ")";
		return result;
	}

	/**
	 * This returns all the code to find out if a (nested) object should be
	 * fetched. ex: val fetchOrderOrderStatus = fd.isDefined &&
	 * fd.get.order.isDefined && fd.get.order.get.orderStatus.isDefined This code
	 * recursive loops through all (deeply) nested objects
	 * 
	 * @param aClass
	 * @return
	 */
	public String makeIsFetchDefinedStr(Class<?> aClass) {
		List<String> result = new ArrayList<>();
		IS_FETCH_DEF_IFSTMT.clear();
		NestedObject prevNested = null;
		for (NestedObject currNested : getNestedObjects(aClass).getNestedObjects()) {
			result.add(makeIsFetchDefinedStr(currNested, prevNested) + NL);
			prevNested = currNested;
		}
		return nu.join(result, "", "\n", "");
	}

	public boolean generateRestService(Class<?> aEntity) {
		RestService annot = (RestService) tu.getAnnotation(aEntity, RestService.class);
		if (annot == null) {
			return false;
		} else {
			return annot.generateService();
		}
	}
	
	private List<String> getRowCaseClassParamsExclOneTo(Class<?> aClass) {
		List<String> result = new ArrayList<>();
		for (Fw fieldwrapper : tu.getFieldsExc(aClass, O2M_FLD, O2O_FLD, OFD_FLD, DISC_FLD)) {
			ScalaFw fw = (ScalaFw) fieldwrapper;
			StringBuffer sb = new StringBuffer();
			tu.importIfNeeded(fw);
			sb.append(fw.rowFieldname());
			String dtype = fw.rowDatatype();
			sb.append(":" + dtype);
			if (fw.isId()) {
				sb.append("=" + tu.getDefaultRowValue(fw));
			}
			result.add(sb.toString());
		}
		return result;
	}

	private List<String> getRowCaseClassParamsOneToOne(Class<?> aClass) {
		List<String> result = new ArrayList<>();
		for (Fw fieldwrapper : tu.getFieldsInc(aClass, O2O_FLD)) {
			ScalaFw fw = (ScalaFw) fieldwrapper;
			StringBuffer sb = new StringBuffer();
			tu.importIfNeeded(fw);
			sb.append(fw.rowFieldname());
			String dtype = tu.getIdTypeName(fw.type());
			sb.append(":" + OPTION + dtype + "]");
			sb.append("=" + NONE);
			result.add(sb.toString());
		}
		return result;
	}

	private String getDiscriminatorRowClassParam(Class<?> aClass) {
		String propname = tu.getDiscriminatorName(aClass);
		return propname + ":String";
	}

	private String getDiscriminatorCloneParam(Class<?> aClass) {
		String propname = tu.getDiscriminatorName(aClass);
		return "this." + propname;
	}
	
	private String genRetrieveAndMergeNestedObjects(NestedObject aNested) {
		StringBuffer sb = new StringBuffer();
		Class<?> rootclz = aNested.getRootClass();
		String rootclsname = rootclz.getSimpleName();
		Fw lastfld = aNested.lastField();
		String lastfldClz = lastfld.genericTypeName();
		Fw ownerFld = tu.getFieldByType(lastfld.genericType(), rootclz);
		boolean isentity = (ownerFld==null) ? false : ownerFld.isEntity();
		String ownerFldname = (ownerFld==null) ? "???" : ownerFld.name();
		String rowmapper = "mapFrom" + lastfldClz + "Row";
		String rettyp = "Set[" + lastfldClz + "]";
		String rowlist = nu.uncapName(lastfldClz) + "RowList";
		String nestedlist = nu.uncapName(lastfldClz) + "List";
		
		Properties props = nu.makeProperties("P1", makeDefNameRetrieve(aNested), "P2", rootclsname, "P3", rettyp);
		sb.append(TAB + nu.substitute(DEF_RETRIEVE_NESTED, props) + NL);

		sb.append(TAB2 + "val joinQuery = for {" + NL);
		sb.append(genRetrAndMergeNestedQueries(aNested) + NL);
		sb.append(TAB2 + "} yield (" + nu.uncapName(lastfldClz) + ")" + NL);
		sb.append(TAB2 + "val " + rowlist + " = joinQuery.list" + NL);
		sb.append(TAB2 + "val " + nestedlist + " = " + rowlist + ".map(" + rowmapper + "(_))" + NL);
		if (isentity) {
			sb.append(TAB2 + nestedlist + ".map(item => item." + ownerFldname + " = Some(obj))" + NL);
		}
		sb.append(TAB2 + nestedlist + ".to[Set]" + NL);
		sb.append(TAB + "}" + NL + NL);
		return sb.toString();
	}

	/**
	 * example: tstA <- tstAQuery if tstA.id === obj.id
	 * 
	 * @param aNested
	 * @return
	 */
	private String genRetrAndMergeNestedQueries(NestedObject aNested) {
		StringBuffer sb = new StringBuffer();
		for (int i = 1; i < aNested.getFieldsExc().size(); i++) {
			Class<?> currclz = aNested.getParents().get(i);
			Fw currfld = aNested.getFieldsExc().get(i);
			Class<?> prevclz = (i > 0) ? aNested.getParents().get(i - 1) : null;
			Fw prevfld = (i > 0) ? aNested.getFieldsExc().get(i - 1) : null;
			sb.append(genRetrAndMergeNestedQueries(currclz, prevclz, currfld, prevfld, i));
			sb.append(NL);
		}

		Class<?> currclz = aNested.lastField().genericType();
		Fw currfld = aNested.lastField();
		int n = aNested.getParents().size();
		Class<?> prevclz = aNested.getParents().get(n - 1);
		Fw prevfld = aNested.getFieldsExc().get(n - 1);
		sb.append(genRetrAndMergeNestedQueries(currclz, prevclz, currfld, prevfld, n));

		return sb.toString();
	}

	private String genRetrAndMergeNestedQueries(Class<?> currclz, Class<?> prevclz, Fw currfld, Fw prevfld, int i) {
		StringBuffer sb = new StringBuffer();
		String rowname = nu.uncapName(currclz.getSimpleName());
		String prevrow = (i == 1) ? OBJ : nu.uncapName(prevclz.getSimpleName());
		String currfldId = tu.getIdName(currfld.type());

		if (prevfld.isOneToOneField()) {
			String fldname = (i == 1) ? prevfld.name() + ID_SUFFIX : prevfld.name();
			sb.append(TAB3 + rowname + " <- " + rowname + QUERY + " if " + rowname + "." + currfldId + IS + prevrow + "." + fldname);
		} else {
			Fw fkfld = prevfld.getMappedByFkField();
			String fkname = (fkfld != null) ? fkfld.name() : "?fkname?";
			sb.append(TAB3 + rowname + " <- " + rowname + QUERY + " if " + rowname + "." + fkname + IS + prevrow + "." + currfldId);
		}
		return sb.toString();
	}

	private String makeDefNameRetrieve(NestedObject aNestedObj) {
		return makeDefNameRetrieve(aNestedObj.lastParentClass(), aNestedObj.lastField());
	}

	protected String makeDefNameRetrieve(Class<?> aClass, Fw fw) {
		String result = RETRIEVE + aClass.getSimpleName();
		result += nu.capName(fw.name());
		return result;
	}

	protected String makeDefNameHelper(NestedObject aNestedObj, String aSubString) {
		String result = nu.capName(aNestedObj.lastParentClass().getSimpleName()) + aSubString;
		result += nu.capName(aNestedObj.lastField().name());
		return result;
	}
	
	/*
	 * if (fetchUserPicture(fd)) user.picture = retrieveUserPicture(user) TODO
	 * check for endless recursion
	 */
	private String makeRetrieveNestedObjectsStr(NestedObject aNestedObj) {
		if (aNestedObj.getFieldsExc().size() == 1) {
			return makeRetrieveNestedObjectsStr1(aNestedObj);
		} else {
			return makeRetrieveNestedObjectsStr2(aNestedObj);
		}
	}

	private String makeRetrieveNestedObjectsStr1(NestedObject aNestedObj) {
		String fldname = aNestedObj.lastField().name();
		String fetchname = makeDefNameFetch(aNestedObj);
		String retrieveStr = makeDefNameRetrieve(aNestedObj);
		String headOpt = (aNestedObj.lastField().isOneToOneField()) ? ".headOption" : "";
		String line = NL + TAB2 + "if (" + fetchname + "(fd)) " + OBJ + "." + fldname + " = " + 
				retrieveStr + "(" + OBJ + ")" + headOpt;
		return line;
	}

	private String makeRetrieveNestedObjectsStr2(NestedObject aNestedObj) {
		StringBuffer sb = new StringBuffer();
		String fetchname = makeDefNameFetch(aNestedObj);
		String retrieveStr = makeDefNameRetrieve(aNestedObj);
		Fw lastFld = aNestedObj.lastField();
		String lastFldname = lastFld.name();
		String lastFldType = lastFld.genericTypeName();
		Fw mappedByFld = (lastFld.hasMappedByFkField()) ? lastFld.getMappedByFkField() : null;

		sb.append(TAB2 + "if (" + fetchname + "(fd)) {" + NL);
		for (int i = 1; i < aNestedObj.getParents().size(); i++) {
			Fw prevFld = aNestedObj.getFieldsExc().get(i - 1);
			String prevFldname = prevFld.name();
			String prevFldType = prevFld.genericTypeName();
			sb.append(makeRetrieveNestedObjectsStr3(prevFldname, prevFldType, i) + NL);
		}

		Fw prevFld = aNestedObj.getFieldsExc().get(aNestedObj.getParents().size() - 2);
		String prevFldname = prevFld.name();
		String prevFldType = prevFld.genericTypeName();
		String objItemId = (prevFld.isOneToManyField() && mappedByFld != null) ? tu.getIdName(prevFld.type()) : lastFldname + ID_SUFFIX;
		String qryItemId = (prevFld.isOneToManyField() && mappedByFld != null) ? mappedByFld.name() : tu.getIdName(lastFld.type());
		if (mappedByFld != null && !mappedByFld.isSimple()) {
			qryItemId += ID_SUFFIX;
		}
		String headOpt = (lastFld.isOneToOneField()) ? ".headOption" : "";
		int nr = aNestedObj.getParents().size() - 1;

		sb.append(makeRetrieveNestedObjectsStr4(prevFldname, prevFldType, lastFldname, retrieveStr, lastFldType, qryItemId, objItemId,
				headOpt, nr) + NL);

		for (int i = aNestedObj.getParents().size() - 1; i > 0; i--) {
			sb.append(nu.tabs(i+2) + "})" + NL);
		}

		sb.append(TAB2 + "}" + NL);
		return sb.toString();
	}

	private static final String RETRIEVE_QUERY_1 = "<%=OBJ%>.<%=P1%>.foreach((objItem<%=NR2%>:<%=P2%>) => {";

	// obj.tstcs.foreach((objItem1:Tstc) => {

	private String makeRetrieveNestedObjectsStr3(String fldname, String clzname, int nr) {
		String obj = (nr - 1 == 0) ? "obj" : "objItem" + (nr - 1);
		Properties props = nu.makeProperties("OBJ", obj, "P1", fldname, "P2", clzname, "NR2", "" + nr);
		StringBuffer sb = new StringBuffer();
		sb.append(nu.tabs(nr + 2) + nu.substitute(RETRIEVE_QUERY_1, props));
		return sb.toString();
	}

	private static final String RETRIEVE_QUERY_2 = "objItem<%=NR1%>.<%=P3%> = <%=P4%>(obj).filter((qryItem<%=NR2%>:<%=P5%>) => {qryItem<%=NR2%>.<%=P6%> == objItem<%=NR1%>.<%=P7%>})<%=P8%>";

	// ex:
	// if (fdTsteFs(fd)) {
	// obj.tstcs.foreach((objItem1:Tstc) => {
	// objItem1.tstes.foreach( (objItem2:Tste) => {
	// objItem2.fs = retrieveTsteFs(obj).filter((qryItem2:Tstf) =>
	// {qryItem2.tsteId == objItem2.id} )
	// })
	// })
	// }

	private String makeRetrieveNestedObjectsStr4(String prevFldname, String prevFldType, String lastFldname, String retrieveStr,
			String lastFldType, String qryItemId, String objItemId, String headOpt, int nr) {

		Properties props = nu.makeProperties("NR1", "" + nr, "NR2", "" + (nr + 1), "P1", prevFldname, "P2", prevFldType, "P3", lastFldname,
				"P4", retrieveStr, "P5", lastFldType, "P6", qryItemId, "P7", objItemId, "P8", headOpt);
		StringBuffer sb = new StringBuffer();
		sb.append(nu.tabs(nr + 3) + nu.substitute(RETRIEVE_QUERY_2, props));
		return sb.toString();
	}
	
	/**
	 * this generates the fetch like: "fetchTstATstc" this is used in def
	 * retrieveXxxNestedObjects and as a method itself
	 * 
	 * @param aNestedObj
	 * @return
	 */
	private String makeDefNameFetch(NestedObject aNestedObj) {
		return "isDef" + makeDefNameHelper(aNestedObj, FD);
	}

	private static Map<NestedObject, String> IS_FETCH_DEF_IFSTMT = new HashMap<NestedObject, String>(); 
	/*
	 * private def fetchUserPicture(fd:Option[FetchUser]) = fd.isDefined &&
	 * fd.get.fetchPicture.isDefined TODO check for endless recursion
	 */
	private String makeIsFetchDefinedStr(NestedObject currNested, NestedObject prevNested) {

		Class<?> rootclz = currNested.getRootClass();
		Class<?> currclz = currNested.getNestedType();
		String clsname = currclz.getSimpleName();
		impu.addScalaImport(clsname + FD, currclz, Tid.SCALA_ENTITY.name());

		String fetchname = makeDefNameFetch(currNested);
		String ifstmt = startIsFetchDefIfStmt(currNested, prevNested);
		String fieldsSoFar = FETCHDEPTH;
		int startIfStmtAt = getIsFetchDefIfStmtStartIndex(currNested, prevNested);
		
		for (int i = 0; i < currNested.getParents().size(); i++) {
			fieldsSoFar += ".get." + currNested.getFieldsExc().get(i).name();
			if (i >= startIfStmtAt) {
				ifstmt += fieldsSoFar + ISDEFINED;
				if (i < currNested.getParents().size() - 1) {
					ifstmt += " && ";
				}
			}
		}
		
		String fdtype = "(" + FETCHDEPTH + ":" + OPTION + rootclz.getSimpleName() + FD + "])";
		String line = "\tprivate def " + fetchname + fdtype + " = " + ifstmt;
		IS_FETCH_DEF_IFSTMT.put(currNested, fetchname + fdtype);
		return line;
	}

	private String startIsFetchDefIfStmt(NestedObject currNested, NestedObject prevNested) {
		if (usePrevFetchDefIfStmt(currNested, prevNested)) {
			return IS_FETCH_DEF_IFSTMT.get(prevNested) + " && ";
		} else {
			return FETCHDEPTH + ISDEFINED + " && "; // "fd.isDefined && "
		} 
	}

	private boolean usePrevFetchDefIfStmt(NestedObject currNested, NestedObject prevNested) {
		if (prevNested != null && currNested.getParents().size() == prevNested.getParents().size() + 1) {
			boolean result = true;
			for (int i=0; i<prevNested.getParents().size(); i++) {
				if (!currNested.getParents().get(i).equals(prevNested.getParents().get(i))) {
					result = false;
					break;
				}
			}
			return result;
		} else {
			return false;
		}
	}

	private int getIsFetchDefIfStmtStartIndex(NestedObject currNested, NestedObject prevNested) {
		if (usePrevFetchDefIfStmt(currNested, prevNested)) {
			return prevNested.getParents().size();
		} else {
			return 0;
		}
	}
	
	/**
	 * this returns request handlers for the receptionist 
	 * case HttpRequest(GET, Uri.Path("/findTsta"), _, _, _)     => DemoRequestHandler.handleFindTsta()
	 * 
	 * @param aClass
	 * @return
	 */
	public String getReceptionRoutes() {
		StringBuffer sb = new StringBuffer();
		sb.append(getReceptionServiceRoutes() + NL);
		sb.append(getReceptionDaoRoutes() + NL);
		return nu.trimRight(sb.toString(), "~");
	}
	
	private String getReceptionServiceRoutes() {
		StringBuffer sb = new StringBuffer();
		List<Class<?>> services = ModelClassesUtils.findModelClassesWithInterface(IServiceType.class);
		for (Class<?> srv : services) {
			String srvReqHandler = tplu.getClassName(srv, Tid.SCALA_DAO_REQHANDLER.name());
			for (Method m : srv.getMethods()) {
				sb.append(TAB2 + getReceptionServiceRoutes(m, srvReqHandler) + NL);
			}
		}
		return sb.toString();
	}

	/*
	 * case HttpRequest(GET, Uri.Path("/ping"), _, _, _) => DemoRequestHandler.handleSaveTsta(req)
	 */
	
	private static final String HANDLE_REQ = "case HttpRequest($1, Uri.Path('/$2'), _, _, _) => $3";
	
	private String getReceptionServiceRoutes(Method m, String reqHandler) {
		StringBuffer sb = new StringBuffer();
		RestMethod restanno = (RestMethod) tu.getAnnotation(m, RestMethod.class);
		String action = (restanno!=null && restanno.POST()) ? "POST" : "GET";
		Properties props = new Properties();
		props.put("$1", action);
		props.put("$2", m.getName());
		props.put("$3", reqHandler + "." + m.getName() + "(req)");
		sb.append(TAB2 + StrUtil.subsProperties(HANDLE_REQ, props) + NL);
		return sb.toString();
	}

	
	private static final String RECEPTION_ROUTES = TAB2 + "find<%=ENT%>Route ~ retrieve<%=ENT%>Route ~ save<%=ENT%>Route ~";

	private String getReceptionDaoRoutes() {
		StringBuffer sb = new StringBuffer();
		List<Class<?>> entities = ModelClassesUtils.findModelClassesWithInterface(IEntityType.class);
		for (Class<?> entity : entities) {
			if (generateRestService(entity)) {
				Properties props = new Properties();
				props.put("ENT", entity.getSimpleName());
				String s = nu.substitute(RECEPTION_ROUTES, props);
				sb.append(s + NL);
			}
		}
		return sb.toString();
	}


	public NestedObjects getNestedObjects(Class<?> aClass) {
		return new NestedObjects(aClass);
	}
	
}
