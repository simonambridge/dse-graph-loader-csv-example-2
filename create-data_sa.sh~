#!/bin/bash

NUM_TXN=200000
START=1
NUM_ACT=10000
CWD=`pwd`
OUTPUT_PATH=$CWD

# Create vertices

rm act.csv
echo "Creating accounts..."
for (( i=$START; i<=$NUM_ACT; i++ ))
do
   echo $i
   randomLine=`python getLine.py | sed "s/\['//;s/'\]//" | sed -r ':r; s/("[^",]+),([^",]*)/\1 \2/g; tr; s/"//g' | awk 'BEGIN {FS=","}{print $1, ",", $2, ",", $3, ",", $4,",", $5, ",", $6 ",", $7}'`
   echo $i, $randomLine >> act.csv
done


# Create edges

TXN_FIELDS="natural($NUM_ACT),natural($NUM_ACT),dollar,string(128)"

for i in {0..29} 
do
  TXN_FIELDS="string(5),$TXN_FIELDS"
done

echo "Creating txns..."
nodejs $GEN_PATH/generator.js "$TXN_FIELDS" $NUM_TXN $OUTPUT_PATH/txn.csv

cd $CWD


