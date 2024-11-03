package org.web.locations;

import java.lang.*;
public class CalculateTheDistance {

    public static double distance(double lat1, double lat2, double lon1, double lon2){

        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double lon = lon1 - lon2;
        double lat = lat1 - lat2;

        double a = Math.pow(Math.sin(lat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(lon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));

        double earthRadius = 6371;

        return ( c * earthRadius);
    }

    public static void main(String[] args) {

        double lat1 = 6.845173248904424;
        double lat2 = 6.851608158195131;
        double lon1 = 80.03349986937718;
        double lon2 =  80.0326747011507;
        System.out.println(distance(lat1, lat2,
                lon1, lon2) + " km");
    }
}
