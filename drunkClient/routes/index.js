var express = require('express');
var router = express.Router();
var awsIot = require('aws-iot-device-sdk');
var Request = require("request");
const fs = require('fs');

const idThing = "user1"

/*
 * Rendering the homepage
 */
router.get('/', function (req, res, next) {
    res.render('index', {
      title: 'Lets get started!',
      label1: 'My command:',
      registered: 'Register to:'
    });
});

/*
 * Registering the thing in AWS
 */
router.post('/registerApi', function (req, res) {
    console.log("==== Calling /registerApi")
    let url = req.body.urlSend;
    console.log(`registering url ${url}`);

    let data = {id: idThing};
    console.log(`stringifyed: ${JSON.stringify(data)}`);
    Request.post({
        "headers": {"content-type": "application/json"},
        "url": url,
        "body": JSON.stringify(data)
    }, (error, response, body) => {
        if (error) {
            return console.dir(error);
        }
        var parsed = JSON.parse(response.body)
        fs.writeFile('mything-certificate.pem.crt', parsed.certificatePem, (err) => {
            if (err) throw err;
            console.log('saved certificate!');
        });
        fs.writeFile('mything-public.pem.key', parsed.publicKey, (err) => {
            if (err) throw err;
            console.log('saved publicKey!');
        });
        fs.writeFile('mything-private.pem.key', parsed.privateKey, (err) => {
            if (err) throw err;
            console.log('saved privateKey!');
        });
    });
});

/*
 * This thing now send commands to AWS
 */
router.post('/addCommand', function (req, res) {
    var data = req.body
    console.log("==== Calling /addCommand", data)
    const device = awsIot.device({
        keyPath: './mything-private.pem.key',
        certPath: "./mything-certificate.pem.crt",
        caPath: "./AmazonRootCA1.pem",
        //TODO change
        clientId: idThing,
        //TODO change
        host: "HOST"
    });
    let topicName="topics/"+idThing+"/topic_drunk"
    device.publish(topicName, JSON.stringify(data));
    res.send({msg: ''});
});

module.exports = router;
