package sis.search;

import java.util.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Server extends Thread {
	private List<Search> queue = Collections.synchronizedList(new LinkedList<Search>());
	private ResultsListener listener;
	private boolean shutDown = false;
	static final String START_MSG = "started";
	static final String END_MSG = "finished";
	
	private static ThreadLocal<List<String>> threadLog = 
			new ThreadLocal<List<String>>() {
		protected List<String> initialValue() {
			return new ArrayList<String>();
		}
	};
	
	private List<String> completeLog = 
			Collections.synchronizedList(new ArrayList<String>());
	
	public List<String> getLog() {
		return completeLog;
	}
	public Server(ResultsListener listener) {
		this.listener = listener;
		start();
	}
	
	public void run() {
		while (!shutDown) {
			if (!queue.isEmpty())
				execute(queue.remove(0));
			Thread.yield();
		}
	}
	
	public void add(Search search) {
		queue.add(search);
	}
	private void execute(Search search) {
		new Thread(new Runnable() {
			public void run() {
				log(START_MSG, search);
				search.execute();
				log(END_MSG, search);
				listener.executed(search);
				completeLog.addAll(threadLog.get());
			}
		}).start();
	}
	
	private void log(String message, Search search) {
		threadLog.get().add(
				search + " " + message + " at " + new Date());
	}

	public void shutDown() {
		shutDown = true;
		
	}
}
