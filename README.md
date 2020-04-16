# VehicleSurveyAnalyser
Vehicle Survey Analyser is an application which is used to analyse the data from the vehicle survey and provide you the following reports based on specific time periods.<br/>

**Output Reports :**<br/>
1. Vehicle count report<br/>
2. Peak volume times report<br/>
3. Inter Vehicular Distance report<br/>
4. Speed distribution report<br/>

**Time periods includes :**<br/>
1. Morning<br/>
2. Evening<br/>
3. Per Hour<br/>
4. Per Minute<br/>
5. Per Half an Hour<br/>
6. Per every 20 Minutes<br/>
7. Per every 15 Minutes<br/>

Example input data format :<br/>
<pre><code>A268981
A269123
A604957
B604960
A605128
B605132
A1089807
B1089810
A1089948
B1089951</code></pre>

**Conditions included:**
- Application reads the input vehicle survey records from VehicleSurveyAnalyser/src/resources/Vehicle Survey Coding Challenge sample data.txt<br/>
  (Update **Vehicle Survey Coding Challenge sample data.txt** with your vehicle survey data<br/>
- Application parse the data provided and gets the Vehicles by analysing the data.
- Data analysing happens based on below specification<br/>
 Vehicle survey has a vehicle counter that is having two rubber hoses stretched across the road. One stretched across
 both lanes of traffic, and one goes just across traffic in one direction. Each hose independently records when tires
  drive over it. Each hose is having a sensor at end (sensor 'A', 'B'). As such, cars going in one direction
   (say, northbound) only record on one sensor (preceded with an 'A'), while cars going in the direction (say,
   southbound) are recorded on both sensors. Lines 3-6 above represent a second car going in the other direction.
   The first set of tires hit the 'A' sensor at 12:10:04am, and then hit the 'B' sensor 3ms later. The second set of
    tires then hit the 'A' sensor 171ms later, and then the 'B' sensor 4ms later.<br/>
- The speed limit on the road where this is recorded is 60kph.
- The average wheelbase of cars in the city is around 2.5 metres between axles.
- Application uses the vehicles from parser to produce various reports.

**Requirements:**
1. Java
2. Maven
    
**Steps to run:** Use below commands <br/>
- Package the jar using maven<br/>
<pre><code> mvn clean package</code></pre>
- Run the program<br/>
Specify the survey data in mentioned file and run the application using<br/>
<pre> <code> java -cp target\VehicleSurveyAnalyser-1.0-SNAPSHOT.jar VehicleSurveyAnalyser</code></pre>
- Run all tests<br/>
<pre> <code> mvn clean test</code></pre>