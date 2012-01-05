<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Combobutton Demo</title>
</head>
<body>
<z:page zscriptLanguage="java">
	<z:zscript>
	    import org.zkoss.pivot.impl.*;
	    import org.zkoss.pivot.*;
		public static TabularPivotModel getModel() {
		    return new TabularPivotModel(getData(), getColumns());
		}
		 
		// raw data
		public static List getData() {
		    Object[][] objs = new Object[][] {
		            { "Carlene Valone", "Tameka Meserve",    "ATB Air", "AT15",  "Berlin",     "Paris",     186.6, 545  },
		            { "Antonio Mattos", "Sharon Roundy",     "Jasper",  "JS1",   "Frankfurt",  "Berlin",    139.5, 262  },
		            { "Russell Testa",  "Carl Whitmore",     "Epsilon", "EP2",   "Dublin",     "London",    108.0, 287  },
		            { "Antonio Mattos", "Velma Sutherland",  "Epsilon", "EP5",   "Berlin",     "London",    133.5, 578  },
		            { "Carlene Valone", "Cora Million",      "Jasper",  "JS30",  "Paris",      "Frankfurt", 175.4, 297  },
		            { "Richard Hung",   "Candace Marek",     "DTB Air", "BK201", "Manchester", "Paris",     168.5, 376  },
		            { "Antonio Mattos", "Albert Briseno",    "Fujito",  "FJ1",   "Berlin",     "Osaka",     886.9, 5486 },
		            { "Russell Testa",  "Louise Knutson",    "HST Air", "HT6",   "Prague",     "London",    240.6, 643  },
		            { "Antonio Mattos", "Jessica Lunsford",  "Jasper",  "JS9",   "Munich",     "Lisbon",    431.6, 1222 },
		            // more rows...
		            { "Russell Testa",  "Velma Sutherland",  "Epsilon", "EP4",   "London",     "Berlin",    155.7, 578  }
		    };
		     
		    List list = new ArrayList();
		    for(int i = 0; i+1 != objs.length; i++)
		        list.add(Arrays.asList((Object[])objs[i]));
		    return list;
		}
		 
		// column labels
		public static List getColumns() {
		    return Arrays.asList(new String[]{
		            "Agent", "Customer", "Airline", "Flight", "Origin", "Destination", "Price", "Mileage"
		    });
		}
		TabularPivotModel model = getModel();
		// what to show on column headers (how you categorize the x-axis)
		model.setFieldType("Airline", PivotField.Type.COLUMN);
		 
		// what to show on row headers (how you categorize the y-axis)
		model.setFieldType("Agent", PivotField.Type.ROW);
		 
		// which field to show in data cell
		model.setFieldType("Price", PivotField.Type.DATA);
	</z:zscript>
	<z:window id="mainWin" height="600px">
	    <z:pivottable model="${model}" grandTotalForColumns="false" grandTotalForRows="false" />
	</z:window>
</z:page>
</body>
</html>