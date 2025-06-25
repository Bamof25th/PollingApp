package com.learn.polling.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CurrentUserDto {
    private int id;
    private String username;
    private String name;
}
