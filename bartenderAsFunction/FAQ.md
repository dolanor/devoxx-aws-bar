# Tips and Tricks

## How to run tests

If you want to run all test:

```
$ mvn test
```

## How to generate the binary for a particular lambda

Go to the lambda folder, and then run

```
$ mvn install
```

The *jar* file, will be generated in the `/bartenderAsFunction/bin` directory.

## How to deploy

If you want to run all test:

```
$ ./deploy.sh {YOUR_USER}
```
