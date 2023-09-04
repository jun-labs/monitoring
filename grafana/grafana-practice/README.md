# Prometheus & Grafana

Monitor indicators such as JVM, API calls, etc. using Actor, Prometheus, and Grafna while creating simple ordering applications. This collects simple metrics and produces statistics.



<br/><br/><br/><br/>

## Getting Started

Before running the application, please set the settings for application.yml, Prometheus, and Grafana. The settings for Prometheus and Grafana are not attached separately.


> You should install jdk 17 or higher. <br/>

<br/><br/><br/>

## Run Application

````text
$ ./gradlew bootRun
````

<br/><br/>

## Run Test

````text
$ ./gradlew test
````

<br/><br/>

## Run Build

````text
$ ./gradlew build
````

<br/><br/>

## Result

It is made possible to monitor using the environment provided by [Grafana](https://grafana.com/grafana/dashboards/).

![image](resources/images/result.png)

<br/><br/>

## Env

&nbsp;&nbsp; - Java 17  <br/>
&nbsp;&nbsp; - SpringBoot 3.0 <br/>

<br/>
