var express = require('express');
var router = express.Router();
var awsIot = require('aws-iot-device-sdk');
var axios = require("axios");
const fs = require('fs');

const idThing = "user1"

/*
 * Rendering the homepage
 */
router.get('/', function (req, res, next) {
  console.log("==== Calling /")
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
    console.log(`registering url <${url}>`);

    let data = {id: idThing};
    console.log(`stringifyed: ${JSON.stringify(data)}`);

    const writeFile = function(name, contents) {
      return new Promise(function(resolve, reject) {
          fs.writeFile(name, contents, (err) => {
             if (err) reject(err);
             else resolve(data);
          });
      })
    }

    axios.post(url, {
        id: idThing
      })
      .then(function (response) {
        const parsed = response.data;
        return writeFile('mything-certificate.pem.crt', parsed.certificatePem)
          .then(function(result) {
            console.log('saved certificate!');
            return writeFile('mything-public.pem.key', parsed.publicKey)
          })
          .then(function(result) {
            console.log('saved publicKey!');
            return writeFile('mything-private.pem.key', parsed.privateKey)
          })
          .then(function(result) {
            console.log('saved privateKey!');
            res.send('OK');
          })
          .catch(function(err) {
            console.warn("Error detected", err);
            res.status(404).send(`Sorry, we got ${error}!`);
          });
      })
      .catch(function (error) {
        console.log(error);
        res.status(404).send(`Sorry, we got ${error}!`);
      });
});

/*
 * This thing now send commands to AWS
 */
router.post('/addCommand', function (req, res) {
    var data = req.body
    console.log("==== Calling /addCommand", data)

    // TODO 02. Name the topic according to sam.yml
    const topicName = `topics/${idThing}/topic_drunk`

    try {
      //TODO 02. specify the certificates you just downloaded
      const device = awsIot.device({
          keyPath: './mything-private.pem.key',
          certPath: "./mything-certificate.pem.crt",
          caPath: "./AmazonRootCA1.pem",
          clientId: idThing,
          //TODO 02. Specify the MQTT url (AWS console / AWS iot / settings / Endpoint)
          host: "_NOT_A_REAL_URL_"
      });

      device.publish(topicName, JSON.stringify(data));
      res.send({ msg: 'Sent' });
    }
    catch (err) {
      res.status(404).send(`Sorry, we got ${err}!`);
    }
});

module.exports = router;
