package com.example.taskmanagement.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class AllTasksResponse {
    private String startDate;
    private String endDate;
    private String summary ;
    private String description;
    private String location;
}
