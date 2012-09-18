package ru.hh.etest.actions;

import java.util.HashMap;
import java.util.Map;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Callable;

import org.apache.commons.collections.map.SingletonMap;

public class TaskFactory {
    private static Callable<String> task;
    private static Map<String, String> taskPath;

    @SuppressWarnings("unchecked")
	public static Callable<String> getTask(String taskName,
											String[] taskParams) {
		// the first letter must be upper case
		taskName = taskName.substring(0, 1).toUpperCase()
				+ taskName.substring(1);
		taskPath = (Map<String, String>) new HashMap<String, String>();
		taskPath.put("CreateApplicant", "ru.hh.etest.applicant");
		taskPath.put("CreateEmployer", "ru.hh.etest.employer");
		taskName = taskPath.get(taskName) + "." + taskName;
		System.out.println("debug: full taskName: " + taskName);
		task = null;
		try {
			// only for nullable constructor
			//Object obj = Class.forName(taskName).newInstance();
			
			// call constructor with parameter(s)
			Object obj = Class.forName(taskName).
				getDeclaredConstructor(
					new Class[]{"".getClass()}).
						newInstance(taskParams[0]);
			if (obj instanceof Callable<?>) {
				task = (Callable<String>) obj;
			}
        //System.out.println("task: " + task.toString());
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR: task not found: " + taskName);
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return task;
	}
}
