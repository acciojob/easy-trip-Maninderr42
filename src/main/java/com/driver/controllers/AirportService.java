package com.driver.controllers;

import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AirportService{
    AirportRepository airportRepositoryObj=new AirportRepository();
    public void AddAirport(Airport airport){
airportRepositoryObj.AddAirport(airport);
    }

    public String getLatgestAirport(){
        return airportRepositoryObj.getLargest();
    }

    public double shortesttimecites(City fromCity, City toCity){
        return airportRepositoryObj.shortLargest(fromCity,toCity);
    }

    public void addfight(Flight flight){
        airportRepositoryObj.addfight(flight);
    }

    public int getNumberofPeople(Date date, String airportName) {
        return airportRepositoryObj.getNumberOfPeople(date,airportName);
    }

    public void addPassenger(Passenger passenger){
        airportRepositoryObj.addPassenger(passenger);
    }

    public int calculateFlightFare(Integer flightId){
        return airportRepositoryObj.CalculateFlightFare(flightId);
    }

    public String bookTicket(Integer flightId, Integer passengerId){
        return airportRepositoryObj.bookTicket(flightId,passengerId);
    }

    public int countofBookDoneByPassenger(Integer passengerId){
        return airportRepositoryObj.countOfBookDoneByPassenger(passengerId);
    }

    public String getAirportNameFromFlightId(Integer flightId){
        return airportRepositoryObj.getAirportNameFromFlightId(flightId);
    }

    public int calculateRevenueOfAFlight(Integer flightId){
        return airportRepositoryObj.CalculateRevenueOfAFlight(flightId);
    }

    public String cancelATicket(Integer flightId, Integer passengerId){
        return airportRepositoryObj.cancelATicket(flightId, passengerId);
    }
}
