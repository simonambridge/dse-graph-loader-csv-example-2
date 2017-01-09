// Configures the data loader to create the schema
config preparation: false
config create_schema: false
config load_new: true
config read_threads: 3
config load_edge_threads: 3
config load_vertex_threads: 3
// For 5.0.0
// config load_threads: 5
config batch_size: 10000
// Only set to new if data being inserted is new
config load_new: true

inputfiledir = '/root/HSBC_Graph/'
txnInput = File.csv(inputfiledir + 'txn.csv').header('field1','field2','field3','field4','field5','field6','field7','field8','field9','field10','field11','field12','field13','field14','field15','field16','field17','field18','field19','field20','field21','field22','field23','field24','field25','field26','field27','field28','field29','field30','from_acct','to_acct','amount','description')
txnInput = txnInput.transform { it['from_acct'] = it['from_acct'].toInteger(); it }
txnInput = txnInput.transform { it['to_acct'] = it['to_acct'].toInteger(); it }
txnInput = txnInput.transform { it['amount'] = it['amount'].substring(1); it }

acctInput = File.csv(inputfiledir + 'act.csv').header('acct_num','first_name','last_name','company','street','town','area','postcode')
acctInput = acctInput.transform { it['acct_num'] = it['acct_num'].toInteger(); it }

// Load account verticies
load(acctInput).asVertices {
  label 'account'
  key 'acct_num' 
}

// Create acct -> acct edges 
load(txnInput).asEdges {
  label 'transaction'
  outV 'from_acct', {
    label 'account'
    key 'acct_num' 
  }
  inV 'to_acct', {
    label 'account'
    key 'acct_num' 
  }
}

