#!/bin/bash


function echo_and_sleep() {
    for x in `seq 1 $1`; do
        sleep 1
        echo -ne "$2"
    done
}


echo "Environment startup for PA2_P2G6"


echo -ne "\r  Starting zookeeper server \033[0K"
./bin/zookeeper-server-start.sh ./config/zookeeper.properties > logs/zookeeper.log &
echo_and_sleep 5 "."


echo -ne "\r  Starting kafka server 0 \033[0K"
./bin/kafka-server-start.sh ./config/server.properties > kafka.log &
echo_and_sleep 5 "."

echo -ne "\r  Starting kafka server 1 \033[0K"
./bin/kafka-server-start.sh ./config/server_1.properties >> kafka.log &
echo_and_sleep 8 "."


echo -ne "\r  Creating topics \033[0K"
./bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 2 --partitions 6 --topic ToDigestion > topics.log
echo -ne "."
./bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 2 --partitions 6 --topic FromDigestion_1 >> topics.log
echo -ne "."
./bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 2 --partitions 1 --topic FromDigestion_2 >> topics.log
echo -ne "."

echo -ne "\r\033[0K"