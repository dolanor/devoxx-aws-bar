# The bartender as a function

## Before you start: Requirements

For Java
* JDK 8.x
* Maven 3.x

## The exercise

TODO

### Is it time to close the bar! (Change Bar Status)

You need to trigger the service using the lambda URL!

```
curl -d "param1=test" -X POST https://{APY_GATEWAY_LAMBDA_URL}.eu-west-1.amazonaws.com/Stage/client/{userId}/close
```

Go to `/drunkClient/server.js` and uncomment the line `processTest();`


Deploy your solution and enjoy!!!
