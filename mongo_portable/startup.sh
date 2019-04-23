#!/bin/bash

echo "MongoDB startup for PA2_P2G6"

echo "  Starting mongo server"
sleep 1

sudo rm -rf db
mkdir db
sudo mongod --dbpath=db
sudo rm -rf db