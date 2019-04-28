Collecting user activities help entreprise to monitor and track end user behavior.
It also helps to have real-time identification along with detailed reporting of historical activity.
The main questions to answer are: Who did what, when and where?

**Learning**
This project is a companion repository to the Linkedin article
https://www.linkedin.com/pulse/collecting-user-activities-microservices-application-kafka-haithem/

**Contributing**
This solution is not perfect and can be improved, please feel free to submit any PR you deem useful.

**Steps**
1/ in order to running the kafka cluster , go to the extra directory and pull the kafka cluster docker image .

2/ start the kafka cluster ===> docker-compose up kafka-cluster

3/ in order to create the userDetails topic 
   */ docker run --rm -it --net=host landoop/fast-data-dev bash
   */ kafka-topics --create --topic userDetails --partitions 3 --replication-factor 1 --zookeeper 127.0.0.1:2181
   
4/ start elasticsearch

5/ run the eureka-server application

6/ run the zull proxy application

7/ run the kafka-consumer-elasticsearch-sender application

8/ run the back-end-server application

9/ try to request from the postman 
        request ===> http://localhost:8762/back_end_server/welcome/info
        header attributes :
                    name : user02 
                    country: Tunisia 
                    plateform : mobile
                    device : Samsung 
                    city : sousse