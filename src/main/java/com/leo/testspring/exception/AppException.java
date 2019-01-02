package com.leo.testspring.exception;

public class AppException extends Exception{
    private int id;

    public AppException(int id){
        this.id = id;
    }

    public AppException(String message, Object... args) {
        super(String.format(message, args));
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        String description = null;
        switch (id) {
            case 0:
                description = "查詢錯誤！";
                break;
            default:
                description = "AppExcetion " + id;
        }
        return description;
    }
}
