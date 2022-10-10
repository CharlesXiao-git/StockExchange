# StockExchange assessment

# Requirements
Use the Google Guice framework to build an application that supports 2
implementations of the following Java interface:
public interface StockExchange {
 /**
 * Buy stock
 */
 void buy(String code, Integer units) throws InsufficientUnitsException,
 InvalidCodeException;
 /**
 * Sell stock
 */
 void sell(String code, Integer units) throws InvalidCodeException;
 /**
 * Report aggregate volume available for each code
 */
 Map<String, Integer> getOrderBookTotalVolume();
 /**
 * Returns dollar value of trading activity
 */
 BigDecimal getTradingCosts();
}
The main class should look roughly like the following:
public class Assignment {
 public static void main(String[] args) {
 ...
 Injector injector = Guice.createInjector(...)
 Assignment assignment = injector.getInstance(Assignment.class);
 assignment.trade();
 ...
 }
 ...
}
The trade() method should buy and sell a random number of units of the stock codes
(NAB,CBA,QAN), a random number of times on the exchange specified by the command
line arguments:
java −cp ... au.com.livewirelabs.assignment.Assignment −exchange ASX
java −cp ... au.com.livewirelabs.assignment.Assignment −exchange CXA
When you buy stock, the total available volume should go down. When you sell stock
the total volume should go up. Attempts to buy stock when insufficient volume is
available should result in an error, reported but not fatal to continuted processing.
The CXA exchange charges 5 cents per trade. The ASX exchange charges 7 cents per
trade. These rates are subject to change. After the trading activity is complete,
report the remaining volume and the income of the exchange.
All trading activity (buys & sells) should be recorded. The application should
maintain its state across multiple executions (i.e. the volume remaining after
each run should be available at the start of the next run.

#  Solution

I would like to break the requirement into three modules. 
 1. Stock Management module will manage the stock in store
 2. Stock Exchange module will implements buy and sell feature
 3. CalculateCharges module will calcutate the charges
 
It is a muitiple thread application, I will use two ways to impelment the thread safe.
one way is synchronized, and another way is lock interface

# Skills used in this exercise
## 1. Google Guice.       
      Goole Guice as a light weight DI framework. can be used to manage the Java beans 
## 2. Java 8        
      Based on java 8 or above to develop the application
## 3. Maven
	  To manage the Jar files.	  

# Develop the application

##  First stage to complete Stock Management
1. To make it simple, I use Hashmap to store the stock
2  Use list to record and log all transactions 
3. The module can be replaced by database

##  Second stage to complete Stock Exchange module
1. Use synchronized to implement the stockexchange interface
2. Use lock to implement the stockexchange interface

##  Third stage to complete CalculateCharges module
1. It can be change from outside, make it easy to change  

##  Fourth stage to use Google Guice.to manage all the java beans and make it works
1. Need to search and know how to use google guice 


# Development Planning
1. Using 0.5 hours on first stage
2. Using 1 hours on second stage
3. Using 1 hours on third stage
4. Using 1 hours on fourth stage
5. Using 1 hours on testing

Totally 4.5 hours to complete the task

# Before run the application

- Install Maven and JDK11

# Run locally in JDk11 Env

1. go to stockexchange folder
2. checkout the code and run 'mvn clean assembly:assembly'. you will get a file stockexchange-1.0.0-jar-with-dependencies.jar in target folder
3. run 'java -cp ./target/stockexchange-1.0.0-jar-with-dependencies.jar com.stock.application CXA '


# Test the application
1. run java -cp ./target/stockexchange-1.0.0-jar-with-dependencies.jar com.stock.application CXA
 you will get the following infomation:
 
  Before run,get the state in store is : {CBA=100, ANZ=30, NAB=80, QAN=50} 
  --- Before run, it shows the aggregate volume available for each code
  exchangeType is: CXA
  --- It is the exchangeType from the command
  trade1 sell remain:  77
  ---- trade1 is the trade name, when sell, it shows the remain volume number 
  trade2 buy remain:  60
   ----trade2 is the trade name, when buy, it shows the remain volume number 
  Total stock exchange charges is : 0.55 $
  ---- It shows total exchange charges 
  After run, get the state in store is : {CBA=100, ANZ=30, NAB=77, QAN=50}
  ----When finish, it shows the aggregate volume available for each code

  
# Technical decision when develop the application

1. Using synchronized and lock to make the application thread safe
2. Handle exception in application layer, and other layers throws all the exception out.
3. Using lombok in model layer
4, Using Goole Guice as DI framework

# Extension in the future

1. using database to store the data
2. the trader module can be extended
3. the Calculate Charges module can be extended

# Get Test Result

From testResult folder to get screenshot of the test result.

