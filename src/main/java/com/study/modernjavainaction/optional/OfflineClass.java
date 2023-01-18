package com.study.modernjavainaction.optional;

import lombok.Getter;

@Getter
public class OfflineClass {

    private Integer id;

    private String title;

    private boolean closed;

    public Progress progress;

    public OfflineClass(Integer id, String title, boolean closed) {
        this.id = id;
        this.title = title;
        this.closed = closed;
    }

}
