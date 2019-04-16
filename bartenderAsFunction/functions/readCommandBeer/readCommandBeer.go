package main

import (
	"github.com/aws/aws-lambda-go/lambda"
	"bartenderAsFunction/model"
	"time"
	"bartenderAsFunction/dao"
	"github.com/satori/go.uuid"
	"fmt"
)

var DataConnectionManager dao.CommandConnectionInterface

func Handler(iotRequest model.CommandRequest) error {
	// TODO 1. generate id to the command (uuid) (see uuid.NewV4)
	uid:= uuid.NewV4()
	fmt.Println("beer:", iotRequest.Beer)
	// TODO 2. generate command (model.command) with date in utc format
	command := model.Command{IdCommand: uid.String(), DateCommand: time.Now().UTC().Format(time.RFC3339), Beer: iotRequest.Beer}
	// TODO 3. save command in dynamo
	//  -> verify if there is not command in the last 2 minutes
	commands, err := DataConnectionManager.GetCommandsByClient(command.Client)
	if err != nil {
		return err
	}
	// implement the should save command to get if a command has been  created in the las 2 minutes
	if shouldSaveCommand(commands, time.Now()) {
		saveCommandError := DataConnectionManager.SaveCommand(command)
		if saveCommandError != nil {
			return saveCommandError
		}
	}
	return nil
}

func shouldSaveCommand(commands []model.Command, actualDate time.Time) bool {
	//TODO implement
	return true
}

func main() {
	DataConnectionManager = dao.CreateCommandConnection()
	lambda.Start(Handler)
}
