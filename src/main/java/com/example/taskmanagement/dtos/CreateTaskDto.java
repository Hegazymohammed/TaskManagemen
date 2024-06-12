package com.example.taskmanagement.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class CreateTaskDto {
    private String description;
    private String location;
    private String summary;

    private String email;
    private String startTime;
    private String endTime;
}
