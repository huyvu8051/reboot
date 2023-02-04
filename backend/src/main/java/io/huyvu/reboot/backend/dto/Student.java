package io.huyvu.reboot.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Student {
    String id;
    String name;
    String clazz;
    int age;
}
