package plugin.planc;

import java.sql.*;
import java.util.*;

import plugin.planc.dashboard.*;
import core.*;
import core.datasource.*;
import core.tasks.*;

public class StoreProcedureCallTask implements TRunnable {
	private String sessid = "" + System.currentTimeMillis();
	private String pscenario = "null";
	private String irelation = "null";
	private String ibu = "null";
	private String icolumnid = "null";
	private String ivaluecat = "null";
	private String iaccountid = "null";

	public StoreProcedureCallTask(Hashtable<String, Object> flds) {
		pscenario = PlanCSelector.getNodeValue(PlanCSelector.SCENARIO);
		Object val = flds.get("storeprocedure.pscenario");
		val = flds.get("storeprocedure.irelation");
		irelation = val.equals("*all") ? "null" : "'" + val + "'";
		val = flds.get("storeprocedure.ibu");
		ibu = val.equals("*all") ? "null" : "'" + val + "'";
		val = flds.get("storeprocedure.icolumnid");
		icolumnid = val.equals("*all") ? "null" : val.toString();
		val = flds.get("storeprocedure.ivaluecat");
		// if category = null, value = null too
		//180222: 06.52: termineeeee le prototipoo !!
		ivaluecat = icolumnid.equals("null") ? "null" : val.toString();
		val = flds.get("storeprocedure.iaccountid");
		iaccountid = val.equals("*all") ? "null" : val.toString();
	}

	@Override
	public void run() {
		try {
			// for sonme reason, this sleep unlook the EDT. DONT DELETE
			Thread.sleep(250);
			Connection conn = ConnectionManager.getDBConnection("sleoracle");
			Statement sts = conn.createStatement();
			String sp = "CALL sle_planc_calculator.generator('" + sessid + "', " + pscenario + ", " + irelation + ", "
					+ ibu + ", " + icolumnid + ", " + ivaluecat + ", " + iaccountid + ")";
//			System.out.println(sp);
//			long t1 = System.currentTimeMillis();
			sts.execute(sp);
			conn.commit();
//			System.out.println("sle_planc_calculator.generator running time: " + (System.currentTimeMillis() - t1)
//					+ " millis");
			// signal dashboard to recalc
			AmountViewer.getInstance().recalc();
			PlanC.showNotification("notification.msg17");
		} catch (Exception ex) {
//			SystemLog.logException(ex);
			PlanC.showNotification("notification.msg00", ex.getMessage());
		}
	}

	@Override
	public void setTaskParameters(Record r, Object o) {

	}
}
