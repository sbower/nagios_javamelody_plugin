package advws.net.nagios.jmeoldy.core;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestCheckJMelody {
	
	private final static String activeConnections ="activeConnections - 0 OK  | activeConnections=0;0;0;\n";
	private final static String activeThreads ="activeThreads - 0 OK  | activeThreads=0;0;0;\n";
	private final static String gc ="gc - 0 OK  | gc=0;0;0;\n";
	private final static String httpHitsRate ="httpHitsRate - 28 OK  | httpHitsRate=28;0;0;\n";
	private final static String httpMeanTimes ="httpMeanTimes - 48 OK  | httpMeanTimes=48;0;0;\n";
	private final static String httpSessions ="httpSessions - 34 OK  | httpSessions=34;0;0;\n";
	private final static String httpSystemErrors ="httpSystemErrors - 0 OK  | httpSystemErrors=0;0;0;\n";
	private final static String loadedClassesCount ="loadedClassesCount - 18955 OK  | loadedClassesCount=18955;0;0;\n";
	private final static String sqlHitsRate ="sqlHitsRate - 294 OK  | sqlHitsRate=294;0;0;\n";
	private final static String sqlMeanTimes ="sqlMeanTimes - 1 OK  | sqlMeanTimes=1;0;0;\n";
	private final static String sqlSystemErrors ="sqlSystemErrors - 0 OK  | sqlSystemErrors=0;0;0;\n";
	private final static String threadCount ="threadCount - 163 OK  | threadCount=163;0;0;\n";
	private final static String usedConnections ="usedConnections - 0 OK  | usedConnections=0;0;0;\n";
	private final static String usedMemory ="usedMemory - 2276822000 OK  | usedMemory=2276822000;0;0;\n";
	private final static String usedNonHeapMemory ="usedNonHeapMemory - 182845768 OK  | usedNonHeapMemory=182845768;0;0;\n";
	private final static String usedNonHeapMemoryWarning ="usedNonHeapMemory - 182845768 WARNING  | usedNonHeapMemory=182845768;182845760;282845768;\n";
	private final static String usedNonHeapMemoryCritical ="usedNonHeapMemory - 182845768 CRITICAL  | usedNonHeapMemory=182845768;182845700;182845760;\n";

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setUp() throws Exception {
		System.setOut(new PrintStream(outContent));
	}

	@Test
	public void testActiveConnections() {
			
		String [] args = {"-r","rrd", "-ac", "-s"};
				
		CheckJMelody cm = new CheckJMelody();
		int retVal = cm.checkRRD(args);
		
		assertEquals(activeConnections, outContent.toString());
		assertEquals(0,retVal);
	}
	
	@Test
	public void testActiveThreads() {
			
		String [] args = {"-r","rrd", "-at", "-s"};
				
		CheckJMelody cm = new CheckJMelody();
		int retVal = cm.checkRRD(args);
		
		assertEquals(activeThreads, outContent.toString());
		assertEquals(0,retVal);
	}
	
	@Test
	public void testGarbageCollection() {
			
		String [] args = {"-r","rrd", "-gc", "-s"};
				
		CheckJMelody cm = new CheckJMelody();
		int retVal = cm.checkRRD(args);
		
		assertEquals(gc, outContent.toString());
		assertEquals(0,retVal);
	}
	
	@Test
	public void testHttpHitRate() {
			
		String [] args = {"-r","rrd", "-hr", "-s"};
				
		CheckJMelody cm = new CheckJMelody();
		int retVal = cm.checkRRD(args);
		
		assertEquals(httpHitsRate, outContent.toString());
		assertEquals(0,retVal);
	}
	
	@Test
	public void testHttpMeanTime() {
			
		String [] args = {"-r","rrd", "-hmt", "-s"};
				
		CheckJMelody cm = new CheckJMelody();
		int retVal = cm.checkRRD(args);
		
		assertEquals(httpMeanTimes, outContent.toString());
		assertEquals(0,retVal);
	}
	
	@Test
	public void testHttpSessions() {
			
		String [] args = {"-r","rrd", "-hs", "-s"};
				
		CheckJMelody cm = new CheckJMelody();
		int retVal = cm.checkRRD(args);
		
		assertEquals(httpSessions, outContent.toString());
		assertEquals(0,retVal);
	}
	
	@Test
	public void testHttpSystemErrors() {
			
		String [] args = {"-r","rrd", "-he", "-s"};
				
		CheckJMelody cm = new CheckJMelody();
		int retVal = cm.checkRRD(args);
		
		assertEquals(httpSystemErrors, outContent.toString());
		assertEquals(0,retVal);
	}
	
	@Test
	public void testLoadedClassCount() {
			
		String [] args = {"-r","rrd", "-lcc", "-s"};
				
		CheckJMelody cm = new CheckJMelody();
		int retVal = cm.checkRRD(args);
		
		assertEquals(loadedClassesCount, outContent.toString());
		assertEquals(0,retVal);
	}
	
	@Test
	public void testSqlHitRate() {
			
		String [] args = {"-r","rrd", "-shr", "-s"};
				
		CheckJMelody cm = new CheckJMelody();
		int retVal = cm.checkRRD(args);
		
		assertEquals(sqlHitsRate, outContent.toString());
		assertEquals(0,retVal);
	}
	
	@Test
	public void testSqlMeanTime() {
			
		String [] args = {"-r","rrd", "-smt", "-s"};
				
		CheckJMelody cm = new CheckJMelody();
		int retVal = cm.checkRRD(args);
		
		assertEquals(sqlMeanTimes, outContent.toString());
		assertEquals(0,retVal);
	}
	
	@Test
	public void testSqlError() {
			
		String [] args = {"-r","rrd", "-sse", "-s"};
				
		CheckJMelody cm = new CheckJMelody();
		int retVal = cm.checkRRD(args);
		
		assertEquals(sqlSystemErrors, outContent.toString());
		assertEquals(0,retVal);
	}
	
	@Test
	public void testThradCount() {
			
		String [] args = {"-r","rrd", "-tc", "-s"};
				
		CheckJMelody cm = new CheckJMelody();
		int retVal = cm.checkRRD(args);
		
		assertEquals(threadCount, outContent.toString());
		assertEquals(0,retVal);
	}
	
	@Test
	public void testUsedConnections() {
			
		String [] args = {"-r","rrd", "-uc", "-s"};
				
		CheckJMelody cm = new CheckJMelody();
		int retVal = cm.checkRRD(args);
		
		assertEquals(usedConnections, outContent.toString());
		assertEquals(0,retVal);
	}
	
	@Test
	public void testUsedHeap() {
			
		String [] args = {"-r","rrd", "-uh", "-s"};
				
		CheckJMelody cm = new CheckJMelody();
		int retVal = cm.checkRRD(args);
		
		assertEquals(usedMemory, outContent.toString());
		assertEquals(0,retVal);
	}
	
	@Test
	public void testUsedNonHeap() {
			
		String [] args = {"-r","rrd", "-unh", "-s"};
				
		CheckJMelody cm = new CheckJMelody();
		int retVal = cm.checkRRD(args);
		
		assertEquals(usedNonHeapMemory, outContent.toString());
		assertEquals(0,retVal);
	}
	
	@Test
	public void testUsedNonHeapWarning() {
			
		String [] args = {"-r","rrd", "-unh", "-w", "182845760", "-c", "282845768", "-s"};
				
		CheckJMelody cm = new CheckJMelody();
		int retVal = cm.checkRRD(args);
		
		assertEquals(usedNonHeapMemoryWarning, outContent.toString());
		assertEquals(1,retVal);
	}
	
	@Test
	public void testUsedNonHeapCritical() {
			
		String [] args = {"-r","rrd", "-unh", "-w", "182845700", "-c", "182845760", "-s"};
				
		CheckJMelody cm = new CheckJMelody();
		int retVal = cm.checkRRD(args);
		
		assertEquals(usedNonHeapMemoryCritical, outContent.toString());
		assertEquals(2,retVal);
	}
	
	@Test
	public void testFileAgeOld() {
			
		String [] args = {"-r","rrd", "-unh"};
				
		CheckJMelody cm = new CheckJMelody();
		int retVal = cm.checkRRD(args);
		
		assertEquals(CheckJMelody.FILE_AGE_MESSAGE + "\n", outContent.toString());
		assertEquals(3,retVal);
	}
	
	@Test
	public void testFileAgeNew() {
			
		String [] args = {"-r","rrd", "-tc"};
		File f = new File("rrd/threadCount.rrd");
		f.setLastModified(new Date().getTime());
		
		CheckJMelody cm = new CheckJMelody();
		int retVal = cm.checkRRD(args);
		
		assertEquals(threadCount, outContent.toString());
		assertEquals(0,retVal);
	}
	
	@Test
	public void testNoRRDFile() {
			
		String [] args = {"-r","rrd", "-nrd"};
				
		CheckJMelody cm = new CheckJMelody();
		int retVal = cm.checkRRD(args);
		
		assertEquals(3,retVal);
	}
	
	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	}

}
