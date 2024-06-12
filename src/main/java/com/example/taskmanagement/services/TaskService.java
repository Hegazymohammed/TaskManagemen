package com.example.taskmanagement.services;

import com.example.taskmanagement.configuration.CalenderWrapper;
import com.example.taskmanagement.dtos.AllTasksResponse;
import com.example.taskmanagement.dtos.CreateTaskDto;
import com.example.taskmanagement.entities.Task;
import com.example.taskmanagement.entities.User;
import com.example.taskmanagement.exceptions.TaskNotFoundException;
import com.example.taskmanagement.exceptions.UserNotFoundException;
import com.example.taskmanagement.repositories.TaskRepository;
import com.example.taskmanagement.repositories.UserRepository;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final CalenderWrapper calender;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRepository, CalenderWrapper calender) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.calender = calender;
    }

   public String createTask(int userId, CreateTaskDto taskRequest) throws IOException {
        User user = userRepository.findUsersById(userId)
                .orElseThrow(() -> new UserNotFoundException("This user doesn't exist"));
                    Task task = new Task();
        task.setDescription(taskRequest.getDescription());
        task.setLocation(taskRequest.getLocation());
        task.setSummary(taskRequest.getSummary());
        task.setUserId(user);
        Event event=this.createEvent(taskRequest );
        event=calender.getService().events().insert(calender.getCalendarId(), event).execute();
        task.setEvent_id(event.getId() );

        taskRepository.save(task); // Save the actual task object
        return event.getHtmlLink();
    }



  private   Event createEvent(CreateTaskDto taskDto){
        Event event=new Event().setDescription(taskDto.getDescription()).setSummary(taskDto.getSummary());
        EventAttendee[]attendees=new EventAttendee[]{new EventAttendee().setEmail(taskDto.getEmail())};
        DateTime startDateTime =  new DateTime( taskDto.getStartTime());
        EventDateTime start = new EventDateTime()
                .setDateTime(startDateTime)
                .setTimeZone("Africa/Cairo");
        event.setStart(start);

        DateTime endDateTime = new DateTime(  taskDto.getEndTime());
        EventDateTime end = new EventDateTime()
                .setDateTime(endDateTime)
                .setTimeZone("Africa/Cairo");
        event.setEnd(end);


        event.setAttendees(Arrays.asList(attendees));
        return event;
    }

    public String updateTask(int userId, int taskId,CreateTaskDto taskDto) throws IOException {
        User user = userRepository.findUsersById(userId)
                .orElseThrow(() -> new UserNotFoundException("This user doesn't exist"));
        Task task=taskRepository.findById(taskId).orElseThrow(()->new TaskNotFoundException("This Task Doesn't exist"));

        Event event=calender.getService().events().update(calender.getCalendarId(), task.getEvent_id(),createEvent(taskDto)).execute();
        return event.getHtmlLink();

    }
    public void  deleteTask(int userId, int taskId) throws IOException {
        User user = userRepository.findUsersById(userId)
                .orElseThrow(() -> new UserNotFoundException("This user doesn't exist"));
        Task task=taskRepository.findById(taskId).orElseThrow(()->new TaskNotFoundException("This Task Doesn't exist"));

         calender.getService().events().delete(calender.getCalendarId(),task.getEvent_id()).execute();


    }

    public List<AllTasksResponse>findAll() throws IOException {
      List<Event> items=calender.listEvent();
      List<AllTasksResponse>result=new ArrayList<>();
      for(Event event:items){
          AllTasksResponse dto= new AllTasksResponse();
          dto.setDescription(event.getDescription());
          dto.setSummary(event.getSummary());
          dto.setLocation(event.getLocation());
          dto.setEndDate(event.getStart().getDateTime().toString());
          dto.setStartDate(event.getEnd().getDateTime().toString());
         result.add(dto);
      }
      return result;
    }
}
