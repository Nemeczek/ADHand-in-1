package com.groupC;

public class Job {
    int start = 0;
    int end = 0;
    int resource = -1;
    public Job(int s, int e){
        start = s;
        end = e;
    }
    public void setResource(int r){
        resource = r;
    }
    public int getStart(){
        return start;
    }
    public int getEnd(){
        return end;
    }
    public int getResource(){
        return resource;
    }
}
