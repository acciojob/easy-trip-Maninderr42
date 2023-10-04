package com.driver.controllers;

import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class AirportRepository {

    HashMap<String, Airport> AirportDb=new HashMap<>();

    HashMap<Integer, Flight> FlightDb=new HashMap<>();

    HashMap<Integer, Passenger> PassengerDb=new HashMap<>();

    HashMap<Integer, List<Integer>> FightPasserngerDb=new HashMap<>();
    public void AddAirport(Airport airport){
        String key= airport.getAirportName();
        AirportDb.put(key,airport);

    }

    public String getLargest(){
        int count = 0;
        for (Airport airport : AirportDb.values()) {
            if (airport.getNoOfTerminals() >= count) {
                count = airport.getNoOfTerminals();
            }
        }

        List<String> list = new ArrayList<>();
        for (Airport airport : AirportDb.values()) {
            if (airport.getNoOfTerminals() == count) {
                list.add(airport.getAirportName());
            }
        }
        Collections.sort(list);

        return list.get(0);
    }

    public double shortLargest(City fromCity, City toCity){
        double min=Integer.MAX_VALUE;
for(Flight flight:FlightDb.values()){
    if(flight.getFromCity()==fromCity && flight.getToCity()==toCity) {
        if(flight.getDuration()<min){
            min=flight.getDuration();
        }
    }
}
if(min==Integer.MAX_VALUE){
    return -1;
}
        return min;
    }

    public void addfight(Flight flight){
        Integer key=flight.getFlightId();
        FlightDb.put(key,flight);
    }


    public void addPassenger(Passenger passenger){
Integer key=passenger.getPassengerId();
PassengerDb.put(key,passenger);
    }


    public int getNumberOfPeople(Date data, String airportName){
        Airport airport=AirportDb.get(airportName);
        if(airport==null){
            return 0;
        }
        City city=airport.getCity();
        int count=0;
        for(Flight flight:FlightDb.values()){
            if(data.equals(flight.getFlightDate())){
                if(flight.getToCity().equals(city)|| flight.getFromCity().equals(city)){
                    int fightId= flight.getFlightId();
                    count=count+FightPasserngerDb.get(fightId).size();

                }
            }
        }
        return  count;

    }

    public int CalculateFlightFare(Integer flightId){
        int ans=FightPasserngerDb.get(flightId).size();
        return ans*50+3000;
    }

    public String bookTicket(Integer flightId, Integer passengerId){
        if(FightPasserngerDb.containsKey(flightId)){
            List<Integer> list=FightPasserngerDb.get((flightId));
            Flight flight=FlightDb.get(flightId);
            if(list.size()==flight.getMaxCapacity()){
                return "FAILURE";
            }
            if(list.contains(passengerId)){
                return "FAILURE";
            }
            list.add(passengerId);
            FightPasserngerDb.put(flightId,list);
            return "SUCCESS";
        }else{
            List<Integer> list=new ArrayList<>();
            list.add(passengerId);
            FightPasserngerDb.put(flightId,list);
            return "SUCCESS";
        }
    }

    public int countOfBookDoneByPassenger(Integer passengerId){
int count=0;
for(List<Integer> list:FightPasserngerDb.values()){
    for(int i:list) {
        if (i == passengerId) {
            count++;
        }
    }
}
return count;
    }

    public String getAirportNameFromFlightId(Integer flightId){
        for(Flight flight:FlightDb.values()){
            if(flight.getFlightId()==flightId){
                City city=flight.getFromCity();
                for(Airport airport:AirportDb.values()){
                    if(airport.getCity().equals(city)){
                        return airport.getAirportName();
                    }
                }
            }
        }
        return null;


    }

    public int CalculateRevenueOfAFlight(Integer flightId){
        if(FightPasserngerDb.containsKey(flightId)){
            int count=FightPasserngerDb.get(flightId).size();
            int rev=0;
            for(int i=0; i<count; i++) {
rev+=3000+(i*30);
            }
            return rev;
        }
        return 0;
    }

    public String cancelATicket(Integer passengerId, Integer flightid){
        if(FightPasserngerDb.containsKey(flightid)) {
            boolean m = false;
            List<Integer> list = FightPasserngerDb.get(flightid);
            if (list == null) {
                return "FAILURE";
            }
            if (PassengerDb.containsKey(passengerId)) {
                m = true;
                PassengerDb.remove(passengerId);
            }
            if (m) {
                FightPasserngerDb.put(flightid, list);
                return "SUCCESS";
            } else {
                return "FAILURE";
            }
        }
            return "FAILURE";



    }
}
