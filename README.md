# Example of how to load CSV data into DSE Graph using the DSE Graph Loader

This example creates some simple CSV data, a simple DSE Graph schema and shows how the DSE Graph Loader can be used to load this data. The CSV data generator can be found [here](https://github.com/snowindy/csv-test-data-generator) and needs to be downloaded and built to run these tests. You will also need to download and install [DSE Graph](https://academy.datastax.com/downloads/welcome) and [Graph Loader](https://academy.datastax.com/downloads/download-drivers?dxt=DX).

To create your graph you need to first start DSE Graph;
```bash
$DSE_HOME/bin/dse cassandra -k -s -g -f
```
Once DSE has started start the Gremlin console;
```bash
$DSE_HOME/bin/dse gremlin-console
```
Update the paths in the txnSchema.groovy script to reflect your environment. Then in the Gremlin console, create the ExampleTxn graph as follows;
```
system.graph('ExampleTxn').create()
:remote config alias g ExampleTxn.g
:load <full path>/txnSchema.groovy
schema.describe()
```
If you have already created the graph and simply want to erase it and start again you do this as follows;
```
:remote config alias g ExampleTxn.g
g.V().drop().iterate()
schema.clear()
system.graph('ExampleTxn').drop()
```
To generate the CSV test data run the create-data.sh script after updating the paths in the script to match the location of the data generator. It will produce 2 CSV files, txn.csv and act.csv
```
./create-data.sh
```
You can then run the load.sh script to load the CSV data into the ExampleTxn graph. If you preceed the load.sh script with the 'time' command it will output how long the data loading took.
```bash
time ./load.sh
```


