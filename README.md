# IOT connected Bar

This project is intended to show an example of a _Connected Bar_. By doing this workshop you'll learn the basis for *AWS IOT* and *AWS Lambda* in either *Java* or *Go*.

## General Idea

This workshop contains two main parts:

* The [_drunkClient_](/drunkClient), which is a *nodejs app* representing an actual IOT device. If you have nodejs in your computer, you can run it locally, or you can ask for an instance containing the project, connect to it using ssh and find the client in the **/home/ec2-user/devoxx-aws-bar/drunkClient** path
* [Several lambdas](/bartenderAsFunction) to handle request comming fron the _IOT client_.

## What technologies this project involves

We'll handle [IOT](https://aws.amazon.com/fr/iot/), [lambda](https://aws.amazon.com/fr/lambda/), MQTT, [DynamoDB](https://aws.amazon.com/fr/dynamodb/)
