package com.oop;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


//In this system i applied single responsibility object-oriented design principle

/**Class ExceptionThrown for exception handling*/
class ExceptionThrown{ //Exception handling
    /**
     * method check available Cars if equal null
     * @param cars ArrayList of Cars
     * @return boolean
     */
    public boolean checkAvailableCars(ArrayList<Car> cars){
        if(cars == null){// if cars is null
            System.out.println("There is no available cars");
        }
        return false;
    }

    /**
     * method check passenger condition if subscriber or not.
     * @param checker boolean variable
     * @return boolean
     */
    public boolean checkPassengerCondition(boolean checker){
        return checker;
    }
}

/**Interface Rate for review*/
interface Rate{//interface rate
    void review();//function review
}

/**Class complaint that implement interface Rate*/
class Complaint implements Rate{

    /**Method overriding review*/
    @Override
    public void review() {//make complaint
        System.out.println("Enter complaint description: \n");
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        System.out.println("Thank you, we will consider this complaint to enhance our system");
    }
}

/**Class Reward that implement interface Rate*/
class Reward implements Rate{

    /**Method overriding review*/
    @Override
    public void review() {// reward
        System.out.println("Please rate form 1 to 10");
        Scanner sc = new Scanner(System.in);
        try {
            int rate = sc.nextInt();
        }catch (InputMismatchException ex){
            System.out.println("Invalid input" + ex);
        }

        System.out.println("Thank you, we will consider this review to enhance our system");
    }
}

/**Class Route that contain start location and destination location*/
class Route{
    private String startLocation; // private startLocation
    private String destinationLocation;

    /**
     * Default constructor Route
     * @param startLocation start location
     * @param destinationLocation destination Location
     */
    public Route(String startLocation, String destinationLocation) {
        this.startLocation = startLocation;
        this.destinationLocation = destinationLocation;
    }

    /**
     * Final getter that get start location
     * @return start location
     */
    public final String getStartLocation() {//final method get start location
        return startLocation;
    }

    /**
     * Final getter that get destination location
     * @return destination location
     */
    public final String getDestinationLocation() {//final method get destination location
        return destinationLocation;
    }
}

/**Class Car this class contain car info*/
class Car{
    private final String code;
    private final String driverName; // private final
    private int numberOfTripsPerDay;
    private int maximumCapacityPerTrip;/** Object route for car*/
    private Route route;

    /**Empty constructor Car*/
    public Car(){
        this.code = "";
        this.driverName = "";
        this.numberOfTripsPerDay = 0;
        this.maximumCapacityPerTrip = 0;
        this.route = null;
    }
    /**
     * Default constructor Car
     * @param code car code
     * @param driverName driver name
     * @param numberOfTripsPerDay number of trips per day
     * @param maximumCapacityPerTrip maximum capacity per trip
     * @param route route for car
     */
    public Car(String code, String driverName, int numberOfTripsPerDay, int maximumCapacityPerTrip, Route route) {
        this.code = code;
        this.driverName = driverName;
        this.numberOfTripsPerDay = numberOfTripsPerDay;
        this.maximumCapacityPerTrip = maximumCapacityPerTrip;
        this.route = route;
    }

    /**
     * Getter that get car code
     * @return car code
     */
    public String getCode() {
        return code;
    }

    /**
     * Getter that get route
     * @return route
     */
    public Route getRoute() {
        return route;
    }

    /**
     * Method for get car by route
     * @param cars ArrayList of Car
     * @param route object of Route
     * @return available Cars
     */
    public ArrayList<Car> getCarByRoute(ArrayList<Car> cars,Route route){
        ArrayList<Car> availCars = new ArrayList<>();
        for (Car car : cars) {
            if (car.getRoute().getStartLocation().equals(route.getStartLocation()) && car.getRoute().getDestinationLocation().equals(route.getDestinationLocation())) {
                availCars.add(car);
            }
        }
        return availCars;
    }

    /**
     * Method for print car info
     */
    public void printInfo(){
        System.out.println("Car code: "+ code + "\nDriver name: "+ driverName + "\nNumber of trips per day: "
                + numberOfTripsPerDay+ "\nMaximum capacity per trip: "+ maximumCapacityPerTrip+
                "\nStart location: "+ route.getStartLocation() + "\nDestination location: "+ route.getDestinationLocation());
    }
}

/**Class Ticket that contain price and car code*/
class Ticket{
    private final Car car; // final private car
    private float price;

    /**
     * Default constructor Ticket
     * @param car object car
     */
    public Ticket(Car car) {
        this.car = car;
        Random random = new Random();
        this.price = random.nextFloat()*100; //calculate data members
    }

    /**
     * Getter that get object car
     * @return object Car
     */
    public Car getCar() {
        return car;
    }

    /**
     * Getter that get price ticket
     * @return price
     */
    public float getPrice() {
        return price;
    }

    /**
     * Method for change ticket price
     * @param ticket object ticket
     * @param newPrice new price
     */
    public void changePrice(Ticket ticket, float newPrice){
        ticket.price = newPrice;
    }

    /**Method for print ticket information*/
    public void printTicketInfo(){
        assert car != null;
        System.out.println("Car code: " + car.getCode() + "\nPrice: " + price);
    }

}

/**An abstract class Passengers that contain passenger details*/
abstract class Passengers{
    protected int age;// Protected attribute
    protected int numberOfTripsToBeReserved;
    protected boolean subscriber;
    protected ArrayList<Ticket> PassengerTickets;

    public Passengers(int age, int numberOfTripsToBeReserved, boolean subscriber) {
        this.age = age;
        this.numberOfTripsToBeReserved = numberOfTripsToBeReserved;
        this.subscriber = subscriber;
        this.PassengerTickets = new ArrayList<>() ;
    }

    /**
     * Final Method searchForRouts for get available cars in specific route
     * @param route object of Route
     * @param cars ArrayList of Cars
     * @return available cars
     */
    final public ArrayList<Car> searchForRouts(Route route, ArrayList<Car> cars) {//Final method
        ArrayList<Car> availableCars = new ArrayList<>();
        for (int i = 0; i < cars.size() ; i++) {
            if(route.getStartLocation().equals(cars.get(i).getRoute().getStartLocation()) && route.getDestinationLocation().equals(cars.get(i).getRoute().getDestinationLocation()) ){
                availableCars.add(cars.get(i));
            }
        }
        ExceptionThrown exceptionThrown = new ExceptionThrown();// exception object
        if(exceptionThrown.checkAvailableCars(availableCars)){// check if availableCars is null
            return null;
        }
        return availableCars;
    }

    /**Abstract method reserveATicket
     * @param passenger object pf passengers
     * @param cars ArrayList of Car
     * @param tickets ArrayList of Ticket
     * @param route object of Route
     * @param numberOfTicket number of tickets
     */
    abstract public void reserveATicket(Passengers passenger, ArrayList<Car> cars, ArrayList<Ticket> tickets, Route route, int numberOfTicket);// abstract method

    /**Final method subscribeAFrequentPassenger
     * @param passenger object of Passengers
     * @return boolean
     */
    final public boolean subscribeAFrequentPassenger(Passengers passenger) {//final method
        if(passenger.age > 18 && passenger.numberOfTripsToBeReserved > 20) { // the subscription fees
            ExceptionThrown exceptionThrown = new ExceptionThrown();// exception object
            if(exceptionThrown.checkPassengerCondition(passenger.subscriber)){//check if passenger is already subscriber
                System.out.println("Not available, you are subscriber");
                return false;
            }else {
                passenger.subscriber = true;
                System.out.println("done");
                printInfo(passenger);
                return true;
            }
        }else {
            System.out.println("Not available");
            return false;
        }
    }

    /**Final method  unsubscribeAFrequentPassenger
     * @param passenger object of Passengers
     */
    final public void unsubscribeAFrequentPassenger(Passengers passenger){//final method
        ExceptionThrown exceptionThrown = new ExceptionThrown();//exception object
        if(!exceptionThrown.checkPassengerCondition(passenger.subscriber)){ // check if passenger is subscriber
            System.out.println("Not available, you are not subscriber");
        }else {
            passenger.subscriber = false;
            System.out.println("done");
            printInfo(passenger);// static method for print passenger info
        }
    }

    /**Final method review for passenger review*/
    final public void review() {//final method
        System.out.println("1- Reward\n2- Complaint");
        Scanner sc = new Scanner(System.in);
        try {
            int input = sc.nextInt();
            if(input == 1){
                Reward reward = new Reward();
                reward.review();
            }if (input == 2){
                Complaint complaint = new Complaint();
                complaint.review();
            }else {
                System.out.println("Invalid choice");
            }
        }catch (InputMismatchException ex){//exception for not integer value
            System.out.println("Invalid input");
        }
    }

    /**Static method print passenger info
     * @param passenger object of Passenger
     */
    public static void printInfo(Passengers passenger){//static method
        System.out.println("Age: "+ passenger.age +"\nNumber of trips to be reserved: " + passenger.numberOfTripsToBeReserved);
        if(passenger.subscriber) { System.out.println("Subscriber");} else {System.out.println("Nonsubsciber");};
        for (int i = 0; i < passenger.PassengerTickets.size() ; i++) {
            passenger.PassengerTickets.get(i).printTicketInfo();
        }
    }
}

/**Class Subscribers that inherit from class Passengers*/
class Subscribers extends Passengers{//Class Subscribers that inherit from class Passengers

    public Subscribers(int age, int numberOfTripsToBeReserved, boolean subscriber) {
        super(age, numberOfTripsToBeReserved, subscriber);
    }

    /**Overriding method reserveATicket
     * @param passenger object pf passengers
     * @param cars ArrayList of Car
     * @param tickets ArrayList of Ticket
     * @param route object of Route
     * @param numberOfTicket number of tickets
     */
    @Override
    public void reserveATicket(Passengers passenger, ArrayList<Car> cars, ArrayList<Ticket> tickets, Route route, int numberOfTicket) {//override method
        Car car = new Car();
        ArrayList<Car> availableCars = car.getCarByRoute(cars, route);
        float price = 0;
        float totalCost = 0;
        ExceptionThrown exceptionThrown = new ExceptionThrown(); //exception object
        if(exceptionThrown.checkAvailableCars(availableCars)){// check if availableCars contain null
            System.out.println("not available cars");
        }else {
            for (int i = 0; i < availableCars.size(); i++) {
                for (int j = 0; j < tickets.size() ; j++) {
                    if(tickets.get(j).getCar().getCode().equals(availableCars.get(i).getCode())){
                        price = tickets.get(i).getPrice();
                        tickets.get(i).printTicketInfo();
                        System.out.println("You are subscriber passenger. Total cost before discount: " + (price * numberOfTicket));
                        totalCost = (price * numberOfTicket) / 2;
                        System.out.println("Total cost after discount: " + totalCost);
                        break;
                    }
                }
            }
            System.out.print("Enter car code for car you want to reserve a ticket: ");
            Scanner sc = new Scanner(System.in);
            String carCode = sc.next();
            for (int i = 0; i < tickets.size(); i++) {
                if(tickets.get(i).getCar().getCode().equals(carCode)){
                    passenger.PassengerTickets.add(tickets.get(i));
                    System.out.println("Done");
                }
            }

        }
    }

}
/**Class Nonsubscribers that inherit from class Passengers*/
class NonSubscribers extends Passengers{

    public NonSubscribers(int age, int numberOfTripsToBeReserved, boolean subscriber) {
        super(age, numberOfTripsToBeReserved, subscriber);
    }

    /**Overriding method reserveATicket
     * @param passenger object pf passengers
     * @param cars ArrayList of Car
     * @param tickets ArrayList of Ticket
     * @param route object of Route
     * @param numberOfTicket number of tickets
     */
    @Override
    public void reserveATicket(Passengers passenger, ArrayList<Car> cars, ArrayList<Ticket> tickets, Route route, int numberOfTicket) {//override method
        Car car = new Car();
        ArrayList<Car> availableCars = car.getCarByRoute(cars, route);
        float price;
        ExceptionThrown exceptionThrown = new ExceptionThrown();//exception object
        if(exceptionThrown.checkAvailableCars(availableCars)){// check if availableCars contain null
            System.out.println("not available cars");
        }else {
            for (int i = 0; i < availableCars.size(); i++) {
                for (Ticket ticket : tickets) {
                    if (ticket.getCar().getCode().equals(availableCars.get(i).getCode())) {
                        price = tickets.get(i).getPrice();
                        tickets.get(i).printTicketInfo();
                        System.out.println("You are nonsubscriber passenger. Total cost: " + (price * numberOfTicket));
                        break;
                    }
                }
            }
            System.out.print("Enter car code for car you want to reserve a ticket: ");
            Scanner sc = new Scanner(System.in);
            String carCode = sc.next();
            for (Ticket ticket : tickets) {
                if (ticket.getCar().getCode().equals(carCode)) {
                    passenger.PassengerTickets.add(ticket);
                }
            }
            System.out.println("Done");

        }
    }


}

/**Public class carPooling*/
public class CarPooling {//public class CarPooling
    /**Public static method main
     * @param args arguments
     */
    public static void main(String[] args) {
        // cars that available in the system
        ArrayList<Car> cars = new ArrayList<>();
        cars.add(new Car("111", "ahmed", 3, 3, new Route("A", "B")));
        cars.add(new Car("222", "carlo", 2, 4, new Route("C", "D")));
        cars.add(new Car("333", "john", 1, 2, new Route("D", "E")));
        cars.add(new Car("444", "steven", 4, 5, new Route("E", "A")));
        cars.add(new Car("555", "sam", 4, 3, new Route("A", "B")));

        // tickets for every car
        ArrayList<Ticket> tickets = new ArrayList<>();
        tickets.add(new Ticket(cars.get(0)));
        tickets.add(new Ticket(cars.get(1)));
        tickets.add(new Ticket(cars.get(2)));
        tickets.add(new Ticket(cars.get(3)));
        tickets.add(new Ticket(cars.get(4)));

        // two passenger to run the code and applied polymorphism concept
        ArrayList<Passengers> passengers = new ArrayList<>();
        passengers.add(new Subscribers(20, 30, true)); // subscriber passenger
        passengers.add(new NonSubscribers(20, 25, false));// nonsubscriber passenger

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1- Admin\n2- Passenger\n3- close");
            int choice1;
            try {
                choice1 = sc.nextInt();
                if (choice1 == 1) {// admin functionality
                    int choice2;
                    System.out.print(          "Welcome\n1- Add car\n2- Remove car\n3- Print cars info\n4- Print tickets info\nEnter Your choice:");
                    try {
                        choice2 = sc.nextInt();
                        if(choice2 == 1){// add new car in the system
                            String code, driverName, startLocation, destinationLocation;
                            int numberOfTripsPerDay,maximumCapacityPerTrip;
                            System.out.print("Enter car code: ");
                            code = sc.next();
                            System.out.print("Enter driver Name: ");
                            driverName = sc.next();
                            System.out.print("Enter number of trips per day: ");
                            numberOfTripsPerDay = sc.nextInt();
                            System.out.print("Enter maximum capacity per trip: ");
                            maximumCapacityPerTrip = sc.nextInt();
                            System.out.print("Enter start location: ");
                            startLocation = sc.next();
                            System.out.print("Enter destination location: ");
                            destinationLocation = sc.next();
                            cars.add(new Car(code, driverName, numberOfTripsPerDay, maximumCapacityPerTrip, new Route(startLocation, destinationLocation))); // add new car in ArrayList cars
                            tickets.add(new Ticket(cars.get(cars.size()-1))); // add new ticket for new car in ArrayList tickets
                        }else if(choice2 == 2){// remove car from the system by car code
                            String code;
                            System.out.print("Enter car code: ");
                            code = sc.next();
                            for (int i = 0; i < cars.size(); i++) {
                                if (cars.get(i).getCode().equals(code)){
                                    cars.remove(i);//remove car from ArrayList cars
                                    break;
                                }
                            }
                            for (int i = 0; i < tickets.size(); i++) {
                                if (tickets.get(i).getCar().getCode().equals(code)){
                                    tickets.remove(i);// remove tickets that belong to removed car
                                    break;
                                }
                            }
                        }else if (choice2 == 3){//print cars info in the system
                            for (Car car : cars) {
                                car.printInfo();
                                System.out.println();
                            }
                        }else if (choice2 == 4){// print tickets info in the system
                            for (Ticket ticket : tickets) {
                                ticket.printTicketInfo();
                                System.out.println();
                            }
                        }else {// if the user enter choice not available
                            System.out.println("Invalid choice");
                        }
                    }catch (Exception e){//exception for not integer value
                        System.out.println("Invalid Input " + e);
                    }

                } else if (choice1 == 2) {//passenger functionality
                    int choice;
                    System.out.print("            Welcome\n1- Search for route\n2- Reserve a ticket\n3- Subscribe a frequent passenger\n4- Unsubscribe a frequent passenger\n5- Reward\n6- Make complaint\nEnter your choice: ");
                    try {
                        choice = sc.nextInt();
                        if (choice == 1) {// search for route
                            System.out.print("Enter start location: ");
                            String startLocation = sc.next();
                            System.out.print("Enter destination location: ");
                            String destinationLocation = sc.next();
                            Route route = new Route(startLocation, destinationLocation);
                            ArrayList<Car> availableCars = passengers.get(0).searchForRouts(route, cars);
                            System.out.println("Available cars are: \n");
                            assert availableCars != null;
                            for (Car availableCar : availableCars) {
                                availableCar.printInfo();
                                System.out.println();
                            }
                        } else if (choice == 2) {//reserve a ticket
                            System.out.print("Enter start location: ");
                            String startLocation = sc.next();
                            System.out.print("Enter destination location: ");
                            String destinationLocation = sc.next();
                            System.out.print("Enter number of tickets: ");
                            int numOfTickets = sc.nextInt();
                            Route route = new Route(startLocation, destinationLocation);
                            passengers.get(0).reserveATicket(passengers.get(0), cars, tickets, route, numOfTickets);//reserve a ticket by subscriber passenger
                            System.out.println("Ticket info");
                            for (int i = 0; i < passengers.get(0).PassengerTickets.size(); i++) {
                                passengers.get(0).PassengerTickets.get(0).printTicketInfo();
                            }
                        } else if (choice == 3) {// Subscribe a frequent passenger
                            Scanner sc1 = new Scanner(System.in);
                            System.out.print("Enter your age: ");
                            int age = sc1.nextInt();
                            System.out.print("Enter Number of trips to be reserved: ");
                            int numberOfTripsToBeReserved = sc1.nextInt();
                            Passengers passengers1 = new NonSubscribers(age, numberOfTripsToBeReserved, false);
                            if (passengers1.subscribeAFrequentPassenger(passengers1)) { // subscriber an unsubscriber passenger
                                passengers.add(passengers1);
                            }
                        } else if (choice == 4) {//Unsubscribe a frequent passenger
                            passengers.get(0).unsubscribeAFrequentPassenger(passengers.get(0));//Unsubscribe a subscriber passenger
                        } else if (choice == 5) {//reward by passenger
                            Reward reward = new Reward();
                            reward.review();
                        } else if (choice == 6) {//complaint by passenger
                            Complaint complaint = new Complaint();
                            complaint.review();
                        } else {
                            System.out.println("Invalid Choice");
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid Input " + e);
                    }

                } else if (choice1 == 3) {
                    System.exit(0);
                } else {
                    System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                System.out.println("Invalid input " + e);
            }
        }
    }

}