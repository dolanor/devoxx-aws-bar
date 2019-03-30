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

Even if the general goal is the same, the individual steps depends on your development platform.

* Follow [this link](README-Go.md) if you want to use Go.

* Follow [this link](README-Java.md) if you want to use Java.
