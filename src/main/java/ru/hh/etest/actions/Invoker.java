package ru.hh.etest.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Invoker {
	private String resultText;

	/*public Invoker(Callable<String> task, int count) {
		execute(task.getClass().getName(), count);
	}*/

	//public Invoker(String taskName, int count) {

	public Invoker() {
		resultText = "";
	}

	private void execute(String taskName,
							String[] taskArgs,
							int count) {
		long startTimeInMs = System.currentTimeMillis();
		ExecutorService executor = Executors.newFixedThreadPool(count);
		List<Future<String>> taskList = new ArrayList<Future<String>>();

		// submit and run tasks
		for(int i=0; i < count; i++){
            Callable<String> task = TaskFactory.getTask(taskName,
														taskArgs);
            System.out.println("debug: submitting: " + task.toString());
			Future<String> future = executor.submit(task);
			taskList.add(future);
		}
		// retrieve the result
		int cnt = 0;
		for (Future<String> future : taskList) {
			try {
				resultText = resultText
						+ "| "
						+ (++cnt)
						+ " | "
						+ future.get()
						+ " |"
						+ "<br />\n";
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}

		executor.shutdown();
		long runTimeInMs = System.currentTimeMillis() - startTimeInMs;
		resultText = resultText + "\n\n<p>Total action runs: "
						+ count
						+ "<br />\nCurrent task completed for: "
						+ runTimeInMs + " ms</p>\n";
	}

	public String getResultText(String taskName, String[] taskArgs, int count) {
		execute(taskName, taskArgs, count);
		return resultText;
	}
	
	public String getResultText(String taskName, String[] taskArgs) {
		execute(taskName, taskArgs, 1);
		return resultText;
	}
/*	
	public String getResultText(String taskName) {
		execute(taskName, new String[]{}, 1);
		return resultText;
	}*/
}
