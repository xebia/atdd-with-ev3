ATDD with Lego Mindstorms EV3
=============================
This project combines [leJOS EV3](http://www.lejos.org/ev3.php) and [Cucumber-JVM](http://cucumber.io).

Features
--------

* Acceptance Test Driven Development with Lego Mindstorms EV3 using RemoteEV3 (RMI)
* Color detection with the Color Sensor
* Motor regulation

Getting Started
---------------

First you'll need to install the ev3 classes locally by executing the following command from the leJOS EV3 /lib/ev3 folder:

	$ mvn install:install-file -Dfile=ev3classes.jar -DgroupId=lejos -DartifactId=ev3classes -Dversion=0.9.0 -Dpackaging=jar

Make sure you are connected to the EV3 via WIFI or Bluetooth (using 10.0.1.1).
Run any test from the test package

    
 