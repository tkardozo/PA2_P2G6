#!/bin/bash


function echo_and_sleep() {
    for x in `seq 1 $1`; do
        sleep 1
        echo -ne "$2"
    done
}


echo "Environment shutdown for PA2_P2G6"


echo -ne "\r  Removing previous topics \033[0K"
./bin/kafka-topics.sh --delete --zookeeper localhost:2181 --topic ToDigestion > /dev/null 2>&1
echo -ne "."
./bin/kafka-topics.sh --delete --zookeeper localhost:2181 --topic FromDigestion_1 > /dev/null 2>&1
echo -ne "."
./bin/kafka-topics.sh --delete --zookeeper localhost:2181 --topic FromDigestion_2 > /dev/null 2>&1
echo -ne "."


echo -ne "  Stopping previous kafka servers \033[0K"
./bin/kafka-server-stop.sh > /dev/null 2>&1
echo_and_sleep 1 "."


echo -ne "\r  Stopping previous zookeeper server \033[0K"
./bin/zookeeper-server-stop.sh > /dev/null 2>&1
echo_and_sleep 1 "."


echo -ne "\r\033[0K"
