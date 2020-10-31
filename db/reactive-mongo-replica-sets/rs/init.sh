#!/bin/bash

# We start mongod in the background
# then we connect remote mongoshell initiae the replica set process
# then this container exits
mongod --fork --logpath /var/log/mongod.log --replSet vinsguru --port 27017 --bind_ip_all

sleep 5

mongo --host mongo1 < rs/mongo-init.js