package main

import (
	"bartenderAsFunction/dao"
	"bartenderAsFunction/model"
	"encoding/json"
	"fmt"
	"github.com/aws/aws-lambda-go/lambda"
)

var DataConnectionManager dao.CommandConnectionInterface

func Handler(request model.IotEvent) (string, error)  {
	fmt.Println("reported status " + request.Current.State.Reported.BarStatus)
	fmt.Println("desired status " + request.Current.State.Desired.BarStatus)
	if request.Current.State.Reported.BarStatus == request.Current.State.Desired.BarStatus && request.Current.State.Desired.BarStatus =="CLOSED" {
		commandsResponse := []model.Command{}
		// TODO 1. read all served commands
		//commands, err := DataConnectionManager.GetCommands()
		err := fmt.Errorf("TO DELETE")
		if err != nil {
			return "", err
		}
		// TODO 2. read all the  items and serve items (change command.Food.Served state to true)
		//command.Food.Served=true
		//command.Beer.Served=true
		//	DataConnectionManager.SaveCommand(command)
		//	commandsResponse = append(commandsResponse,command)

		// TODO 3. return unserved commands
		body, _ := json.Marshal(commandsResponse)
		fmt.Println(body)
		return "", nil
	}
	return "",nil
}

func main() {
	DataConnectionManager = dao.CreateCommandConnection()
	lambda.Start(Handler)
}
