# The bartender as a function

## Objectives

The main objective of this Workshop is to learn how to use an IoT platform (aws) for the things fleet industrialization.

It is presented as a simple game: You are the waiter of a bar. Each client is represented by a "thing".
You should wait for the clients' commands and generate the bill.

![the bartender](https://github.com/fagossa/devoxx-aws-bar/blob/solution-java/bartenderAsFunction/bartenderHL.png "The bartender")

Don't worry, I have coded for you the client and the bartender. :relaxed: .

I give you also the structure of the *waiter* project. It includes the *aws-sam* templates and the unit tests of the services (functions) you should code in either `Java` or `Go`.

### Creating a profile for aws cli

Go to your security credentials in your aws console, and create an access key. Copy your *aws_access_key_id*
and your *aws_secret_access_key*  and  create your ~/.aws/credentials and  ~/.aws/config files as stated in this instructions: https://docs.aws.amazon.com/cli/latest/userguide/cli-multiple-profiles.html
Your profile should be called *epf* (or modify the provided scripts to use the name of the profile you want to use).


## The exercise

### Clone the project

```
$ git clone https://github.com/fagossa/devoxx-aws-bar
```

During this exercise, if you have any question, you can go to the [faq](FAQ.md).

![the exercise](https://docs.google.com/drawings/d/e/2PACX-1vQo9d9tz8Mm0s_NxGLRni0yA6V7r6YDlaJtOHQLblMqXi9jWjkIfv-v8L0eHsnF_XSIbTK2Yg7tecY0/pub?w=480&h=360)


### Step 1
The client is an IOT device who is going to send a command.
As waiter you have to:
* Announce where is the client going to register
* Allow clients to register to the IoT Platform

Don't worry, I've coded the client for you.

Go to the `aws-register-client` lambda (this is a maven project). Resolve all the
_TODOs_ and make all the test *green*. Now, edit the `sam.yml` file and uncomment
the deployment configuration for this lambda.

> sam.yml explains the configuration for all your resources: triggers, memmory, etc

Build and deploy your first lambda!

----------

### Step 2
When the client send a command, as waiter you have to:
* Listen to those messages
* Serve the commands

Go to the `aws-read-command-food` lambda. Resolve all the _TODOs_ and make all
the test *green*. Now, edit the `sam.yml` file and uncomment the deployment
configuration for this lambda. Deploy it.

Follow the same steps for `aws-read-command-beer`.

----------

### Step 3
The client is very drunk. As a waiter you are going to close the bar for him (no more service) As waiter you should:

* *Know* the status of the client (you should use the shadow for that)
* Change the property `barStatus` of the shadow of the client to "CLOSED" (LambdaStatusBar)
* Verify that the client does not send more information and clean all commands (pass them to "served", for this point you should see the file `sam.yml` to get the event from the shadow update and LambdaGetFacture)

1. Go to the folder `aws-change-bar-status`. Complete the *TODOs* and make the tests in green.
* This is an Api that allows you to change the shadow of the client to prevent to receive more commands. Modify the file `sam.yml` to enable the lambda *LambdaBarStatus* and the endpoint */client/{idClient}/close* in the swagger section.

2. Go to the folder `aws-get-facture`. This lambda is going to get the bill for the client, when we get the confirmation of the shadow update.
*  Complete the *TODOs* and make the tests green.
  * Read the commands of the client, close them and save the new status in the database.
* Modify the `sam.yml` file to enable the lambda function *LambdaGetFacture* use as event a rule over the update of the shadow. For more information see [the aws documentation](https://docs.aws.amazon.com/iot/latest/developerguide/using-device-shadows.html).


To know more about aws shadow and how to update the thing, see the aws documentation
----------


### Is it time to close the bar! (Change Bar Status)

You need to trigger the service using the lambda URL!

```
curl -d "param1=test" -X POST https://{APY_GATEWAY_LAMBDA_URL}.eu-west-1.amazonaws.com/Stage/client/{userId}/close
```

Go to `/drunkClient/server.js` and uncomment the line `processTest();`

----------

Deploy your solution and enjoy!!!
