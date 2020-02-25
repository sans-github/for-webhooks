#  Running the app
    java -jar for-webhooks-0.0.1-SNAPSHOT.jar

# API
## Request #1  
    curl -sk http://localhost:58080/hello -H "Content-Type: application/json"
     
    hello there
    
## Request #2
    curl -sk -X POST http://localhost:58080/webhooks -H "Content-Type: application/json"

## Request #3
    curl -sk -X POST http://localhost:58080/braintree-webhooks -H "Content-Type: application/json"
     
 ## Request #4
     curl -sk -X POST http://localhost:58080/webhooks?merchant_account_id=abcd1234 -H "Content-Type: application/json"
     
     merchant_account_id=abcd1234
     serializedObject={
       "merchant_account_id" : [ "abcd1234" ]
     }
     
 ## Request #5
     curl -sk -X POST http://localhost:58080/webhooks/abcd1234 -H "Content-Type: application/json"
     
     merchant_account_id=abcd1234
     serializedObject={ }

## Request #6
     curl -sk -X POST http://localhost:58080/object -H "Content-Type: application/json" -d "{\"greeting\": \"Hello\", \"name\": \"Cowie\"}"
      
      {
          "greeting": "Hello",
          "name": "Cowie"
      }
     
     
