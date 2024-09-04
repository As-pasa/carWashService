package com.asPasa.testTask.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Duration;
import java.util.Date;

@Data
@AllArgsConstructor

public class SlotTimeInterval {
    private Date startTime;
    private Duration duration;

    public boolean intersect(SlotTimeInterval other){
        Date curEndDate=new Date(startTime.getTime()+duration.toMillis());
        Date otherEndTime=new Date(other.getStartTime().getTime()+other.duration.toMillis());

        if(startTime.equals(other.startTime)){
            return true;
        }
        if(curEndDate.equals(otherEndTime)){
            return true;
        }
        if(startTime.before(other.startTime)&&other.startTime.before(curEndDate)){
            return true;
        }
        if(other.startTime.before(startTime)&& startTime.before(otherEndTime)){
            return true;
        }
        if(startTime.before(other.startTime)&&curEndDate.after(otherEndTime)){
            return true;
        }
        if(other.startTime.before(startTime)&&otherEndTime.after(curEndDate)){
            return true;
        }
        return false;

    }
    public Date end(){
        return  new Date(startTime.getTime()+duration.toMillis());
    }

}
