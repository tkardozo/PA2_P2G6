#!/bin/bash


function echo_and_sleep() {
    for x in `seq 1 $1`; do
        sleep 1
        echo -ne "$2"
    done
}


echo "Environment setup for PA2_P2G6: "


echo -ne "  Stopping previous kafka server \033[0K"
../../kafka_2.12-2.2.0/bin/kafka-server-stop.sh > /dev/null 2>&1
echo_and_sleep 1 "."


echo -ne "\r  Stopping previous zookeeper server \033[0K"
../../kafka_2.12-2.2.0/bin/zookeeper-server-stop.sh > /dev/null 2>&1
echo_and_sleep 1 "."


echo -ne "\r  Starting zookeeper server \033[0K"
../../kafka_2.12-2.2.0/bin/zookeeper-server-start.sh ../../kafka_2.12-2.2.0/config/zookeeper.properties > zookeeper.log &
echo_and_sleep 10 "."


echo -ne "\r  Starting kafka server \033[0K"
../../kafka_2.12-2.2.0/bin/kafka-server-start.sh ../../kafka_2.12-2.2.0/config/server.properties > kafka.log &
echo_and_sleep 10 "."


echo -ne "\r  Removing previous topics \033[0K"
../../kafka_2.12-2.2.0/bin/kafka-topics.sh --delete --bootstrap-server localhost:9092 --topic ToDigestion > /dev/null 2>&1
echo -ne "."
../../kafka_2.12-2.2.0/bin/kafka-topics.sh --delete --bootstrap-server localhost:9092 --topic FromDigestion_1 > /dev/null 2>&1
echo -ne "."
../../kafka_2.12-2.2.0/bin/kafka-topics.sh --delete --bootstrap-server localhost:9092 --topic FromDigestion_2 > /dev/null 2>&1
echo -ne "."


echo -ne "\r  Creating topics \033[0K"
../../kafka_2.12-2.2.0/bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 2 --partitions 3 --topic ToDigestion > topics.log 2>&1
echo -ne "."
../../kafka_2.12-2.2.0/bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 2 --partitions 3 --topic FromDigestion_1 >> topics.log 2>&1
echo -ne "."
../../kafka_2.12-2.2.0/bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 2 --partitions 1 --topic FromDigestion_2 >> topics.log 2>&1
echo -ne "."


echo -ne "\r  READY\033[0K\n"
read -n 1 -p "Press any key to exit..."


echo -ne "  Stopping kafka server \033[0K"
../../kafka_2.12-2.2.0/bin/kafka-server-stop.sh > /dev/null 2>&1
echo_and_sleep 1 "."


echo -ne "\r  Stopping zookeeper server \033[0K"
../../kafka_2.12-2.2.0/bin/zookeeper-server-stop.sh > /dev/null 2>&1
echo_and_sleep 1 ".\n"