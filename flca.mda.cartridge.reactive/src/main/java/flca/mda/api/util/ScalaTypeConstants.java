package flca.mda.api.util;

public interface ScalaTypeConstants {

	// common val's
	String FETCHDEPTH = "fd"; // fetchDepth
	String OFD = "ofd";
	String FD = "Fd";
	String OPTION = "Option[";
	String SOME = "Some(";
	String NONE = "None";
	String ENUM_VALUE = ".Value";
	String ISDEFINED = ".isDefined";
	String ROW = "row";
	String OBJ = "obj";
	String QUERY = "Query";
	String RETRIEVE = "retrieve";
	String ROUTE = "Route";
	String IS = " === ";
	String ENUMSTR_OPT = "val <%=P1%> = if (obj.<%=P1%>.isDefined) Some(obj.<%=P1%>.get.toString()) else None";
	String ENUMSTR_REQ = "val <%=P1%> = obj.<%=P1%>.toString() ";

}
