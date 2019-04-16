package main

import (
	"bartenderAsFunction/dao"
	"github.com/aws/aws-lambda-go/events"
	"github.com/aws/aws-lambda-go/lambda"
)

var IotConnectionManager dao.IotConnectionInterface

func Handler(request events.APIGatewayProxyRequest) (events.APIGatewayProxyResponse, error) {
	//TODO get the pathParameter idClient
	idClient := ""//request.PathParameters["paramether name"]
	if idClient != "" {
		//TODO implement the UpdateShadow method in  dao/iotDao.go 
		errChangeStatus := IotConnectionManager.UpdateShadow(idClient, "CLOSED")
		return events.APIGatewayProxyResponse{StatusCode: 200}, errChangeStatus
	}
	return events.APIGatewayProxyResponse{StatusCode: 400}, nil
}

func main() {
	IotConnectionManager = dao.CreateIotConnection()
	lambda.Start(Handler)
}
