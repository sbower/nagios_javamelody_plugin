package advws.net.nagios.jmeoldy.core;

import java.io.IOException;
import java.math.BigDecimal;

import org.jrobin.core.*;
import advws.net.nagios.jmeoldy.util.SimpleCommandLineParser;

public class CheckJMelody {

	
	private final static String ACTIVE_CONNECTIONS = "activeConnections"; 		//activeConnections
	private final static String ACTIVE_THREADS = "activeThreads";         		//activeThreads
	private final static String GC = "gc";                                		//gc
	private final static String HTTP_HITS_RATE = "httpHitsRate";          		//httpHitsRate        
	private final static String HTTP_MEAN_TIMES = "httpMeanTimes";        		//httpMeanTimes
	private final static String HTTP_SESSIONS = "httpSessions";           		//httpSessions
	private final static String HTTP_SYSTEM_ERRORS = "httpSystemErrors";  		//httpSystemErrors
	private final static String LOADED_CLASSES_COUNT = "loadedClassesCount"; 	//loadedClassesCount
	private final static String SQL_HITS_RATE = "sqlHitsRate";            		//sqlHitsRate
	private final static String SQL_MEAN_TIMES = "sqlMeanTimes";          		//sqlMeanTimes
	private final static String SQL_SYSTEM_ERRORS = "sqlSystemErrors";    		//sqlSystemErrors
	private final static String THREAD_COUNT = "threadCount";             		//threadCount 
	private final static String USED_CONNECTIONS = "usedConnections";     		//usedConnections
	private final static String USED_MEMORY = "usedMemory";               		//usedMemory
	private final static String USED_NON_HEAP_MEMORY = "usedNonHeapMemory"; 	//usedNonHeapMemory
	
	public int checkRRD(String[] args) {
		SimpleCommandLineParser parser = new SimpleCommandLineParser(args);
		String rrdPath = parser.getValue("rrdpath", "rrd", "r");
		
		boolean ac = parser.containsKey("activeconnections", "ac");
		boolean at = parser.containsKey("activethreads", "at");
		boolean gc = parser.containsKey("gc", "g");
		boolean httpHitRate = parser.containsKey("hitrate", "hr");
		boolean httpMeanTime = parser.containsKey("httpmeantime", "hmt");
		boolean httpSessions = parser.containsKey("httpsessions", "hs");
		boolean httpSytemsErros = parser.containsKey("httperrors", "he");
		boolean loadedClassCount = parser.containsKey("loadedclasscount", "lcc");
		boolean sqlHitRate = parser.containsKey("sqlhitrate", "shr");
		boolean sqlMeanTime = parser.containsKey("sqlmeantime", "smt");
		boolean sqlSystemError = parser.containsKey("sqlerror", "sse");
		boolean threadCount = parser.containsKey("threadcount", "tc");
		boolean usedConnections = parser.containsKey("usedconnections", "uc");
		boolean usedHeap = parser.containsKey("heap", "uh");
		boolean usedNonHeap = parser.containsKey("nonheap", "unh");
		
		double warning = 0;
		double critical = 0;
		
		if (parser.containsKey("w")) {
			warning = Double.parseDouble(parser.getValue("w"));
		}
		if (parser.containsKey("c")) {
			critical = Double.parseDouble(parser.getValue("c"));
		}
		
		try {
			
			String dsName = "";
			if (ac) {
				dsName = ACTIVE_CONNECTIONS;
			} else if (at) {
				dsName = ACTIVE_THREADS;
			} else if (gc) {
				dsName = GC;
			} else if (httpHitRate) {
				dsName = HTTP_HITS_RATE;
			} else if (httpMeanTime) {
				dsName = HTTP_MEAN_TIMES;
			} else if (httpSessions) {
				dsName = HTTP_SESSIONS;
			} else if (httpSytemsErros) {
				dsName = HTTP_SYSTEM_ERRORS;
			} else if (loadedClassCount) {
				dsName = LOADED_CLASSES_COUNT;
			} else if (sqlHitRate) {
				dsName = SQL_HITS_RATE;
			} else if (sqlMeanTime) {
				dsName = SQL_MEAN_TIMES;
			} else if (sqlSystemError) {
				dsName = SQL_SYSTEM_ERRORS;
			} else if (threadCount) {
				dsName = THREAD_COUNT;
			} else if (usedConnections) {
				dsName = USED_CONNECTIONS;
			} else if (usedHeap) {
				dsName = USED_MEMORY;
			} else if (usedNonHeap) {
				dsName = USED_NON_HEAP_MEMORY;
			}
		
			RrdDb db = new RrdDb(rrdPath + "/" + dsName + ".rrd");
			double value = db.getLastDatasourceValue(dsName);
			
			String textOutput = dsName + " - " + convertDoubleToString(value);
			String perfData = " | " + dsName + "=" +convertDoubleToString(value) + ";" + convertDoubleToString(warning) +";"+ convertDoubleToString(critical) + ";";
			
			if (warning != 0 && critical != 0) {
				
				if (value > critical) {
					System.out.println(textOutput + " CRITICAL " + perfData);
					return 2;
				} else if (value > warning ){
					System.out.println(textOutput + " WARNING " + perfData);
					return 1;
				} 
			} 
			
			System.out.println(textOutput + " OK " + perfData);
			return 0;
			
		} catch (IOException e) {
			System.out.println(e.toString());
			return 3;
		} catch (RrdException e) {
			System.out.println(e.toString());
			return 3;
		}
	}
	
	public static void main(String[] args) {
	  CheckJMelody cm = new CheckJMelody();
	  
	  int retVal = cm.checkRRD(args);
	  System.exit(retVal);
	}
	
	private static String convertDoubleToString(double d) {
		return new BigDecimal(d).toString();	
	}

}
