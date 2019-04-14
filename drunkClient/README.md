# Drunk simulator

This is the simulated IOT device (aka "_drunk client_") implemented as a web application in *nodejs*.

To run use the command
```
$ npm start
```

Use this project as a complement for the bartender-cloud project. This is the client or drunk that is going to ask for commands to the server.

### Step 0

Go to https://docs.aws.amazon.com/iot/latest/developerguide/managing-device-certs.html#server-authentication and
download the *ca certificate* (_AmazonRootCA1.pem_) and save them in the root of this project

### Step 0 - part 2 ;)

We need to _register_ an IoT client, and get the certificates needed for subsequent calls. By the time being you need to comment the function `server::processTest`.

### Step 1

> Execute the TODO in [aws-register-client](/bartenderASFunction/aws-register-client)

Verify the method to register the device to aws.
  - Update the file `index.js`. Change the variable *idThing* to set a name you got assigned.
  - Verify the function for the endpoint `/registerApi`, to complete the request to the entered endpoint and write the information of the returned certificates in the files :
    - `mything-certificate.pem.crt`
    - `mything-private.pem.key`
    - `mything-public.pem.key`
  - To know the name of the variables for the request response, ask for the api definition of the returning objects.
  - Start the web application using `npm start`.
  - Look in ApiGateway for the endpoint of the registration api, set it in the *web interface* and click the register button.
  - Verify in the aws console that *the thing* with the name you provided in the `idThing` variable exists, and that it has associated a certificate and a policy.
  - Verify that locally you now have several certificates.

### Step 2

> Execute the TODO in [aws-read-command-food](/bartenderASFunction/aws-read-command-food)
> Execute the TODO in [aws-read-command-beer](/bartenderASFunction/aws-read-command-beer)

Complete the information of the downloaded keys and call the *addCommand* method to send commands.

 - Go to the file `index.js`
 - Look for the implementation of the endpoint *addCommand* (post)
 - Verify the values of for *keyPath*, *certPath*, *caPath*. Those names must match the files saved from *Step 1*.
 - Change the value of *host*. Look for it in the Aws IoT console, in *thing*-*Interact*.
 - Verify that the message MQTT message gets publised to the topic `topics/{idClient}/topic_drunk`. See the [sdk documentation](https://github.com/aws/aws-iot-device-sdk-js)
 - Init the application using `npm start`.
 - Send beer and food commands (using the drink client web application).

### Step 3

> Execute the TODO in [aws-change-bar-status](/bartenderASFunction/aws-change-bar-status)
> Execute the TODO in [aws-get-facture](/bartenderASFunction/aws-get-facture)

Verify the information of the downloaded keys and call the *processTest* method to listen the
delta in the IoT shadow
  - Go to the file `server.js`
  - Modify the variable `clientName` and set the name you used for *idThing* in *Step 1*
  - Verify the method `processTest`. Look for the values *keyPath*, *certPath*, *caPath*
  - Modify the method `processTest`. Change the value of for *host*. Look for it in the _Aws IoT console_, in *thing*-*Interact*.
  - Verify the *region*
  - Uncomment the call to the method `processTest`. Restart the application. For more information see the [sdk documentation](https://github.com/aws/aws-iot-device-sdk-js).
