package org.example;

public class LeapYearLogic {

    public  boolean  isLeapYear(int leapYear){
        if( leapYear % 4 == 0){
            if(leapYear % 100 == 0){
                return leapYear % 400 == 0;
            }
            else
                return true;
        }
        else
            return false;

    }

}
