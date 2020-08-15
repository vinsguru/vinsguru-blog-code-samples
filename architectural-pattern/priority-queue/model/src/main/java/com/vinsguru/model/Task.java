package com.vinsguru.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.io.Serializable;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Task implements Comparable<Task>, Serializable {

    private final UUID uuid;
    private final Priority priority;
    private final int number;

    @Override
    public int compareTo(Task o) {
        return o.getPriority().compareTo(this.getPriority());
    }

}
