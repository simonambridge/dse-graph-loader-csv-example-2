#!/bin/bash

NUM_TXN=200000
NUM_ACT=10000
GEN_PATH=/root/csv-test-data-generator
CWD=`pwd`
OUTPUT_PATH=$CWD

cd $GEN_PATH

# Create verticies

echo "Creating accounts..."
nodejs $GEN_PATH/generator.js "seq(0)" $NUM_ACT $OUTPUT_PATH/act.csv

# Create edges

TXN_FIELDS="natural($NUM_ACT),natural($NUM_ACT),dollar,string(128)"

for i in {0..29} 
do
  TXN_FIELDS="string(5),$TXN_FIELDS"
done

echo "Creating txns..."
nodejs $GEN_PATH/generator.js "$TXN_FIELDS" $NUM_TXN $OUTPUT_PATH/txn.csv

cd $CWD


