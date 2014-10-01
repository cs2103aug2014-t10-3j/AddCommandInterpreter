/**
 * This class handles the input from user and translate the raw input into a task object.
 * Two backslashes are used to separate the information
 * CURRENTLY FOR LABELS: USING ONE LABEL FOR EACH TASK, WILL CONTINUE TO WORK ON STRING ARRAYS FOR LABELS
 * TASK: Input format for user: add\\description\\labels\\importance
 * DEADLINETASK: Input format for user: add\\description\\labels\\importance\\dd.MM.yyyy HH.mm[deadline]
 * EVENT: Input format for user: add\\description\\labels\\importance\\dd.MM.yyyy HH:mm[start time]\\dd.MM.yyyy HH:mm[end time]
 * @author Xiaofan
 *
 */

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Date;

public class AddCommandInterpreter {
	
	private String description; //description of task
	private String labels; //category of task
	private int importance; //importance of task, represented by integers
	
	//interpret the add command and return a Task or DeadlineTask or Event
	public Task interpretAdd(String string) {
		String unsplitString = string;
		String[] itemsOfTask = unsplitString.split("\\\\");
		description = itemsOfTask[1];
		labels = itemsOfTask[2];
		importance = Integer.parseInt(itemsOfTask[3]);
		
		if(itemsOfTask.length == 4){
			
			Task task = new Task();
			task.setDescription(description);
			task.setLabels(labels);
			task.setImportance(importance);
		
			return task;
			
		}else if(itemsOfTask.length == 5){
			
			DeadlineTask deadlineTask = new DeadlineTask();
			deadlineTask.setDescription(description);
			deadlineTask.setLabels(labels);
			deadlineTask.setImportance(importance);
			String date = itemsOfTask[4];
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
			LocalTimeDate deadline = LocalTimeDate.parse(date, formatter);
			deadlineTask.setDeadline(deadline);
			
			return deadlineTask;
			
		}else if(itemsOfTask.length == 6){
			
			Event event = new Event();
			event.setDescription(description);
			event.setLabels(labels);
			event.setImportance(importance);
			String start = itemsOfTask[4];
			String end = itemsOfTask[5];
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
			LocalTimeDate startTime = LocalTimeDate.parse(start, formatter);
			LocalTimeDate endTime = LocalTimeDate.parse(end, formatter);
			event.setStartTime(startTime);
			event.setEndTime(endTime);
			
			return event;
			
		}
	}
}
