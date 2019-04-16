package main

import (
	"github.com/aws/aws-lambda-go/lambda"
	"bartenderAsFunction/model"
	"bartenderAsFunction/dao"
	"fmt"
)

var DataConnectionManager dao.CommandConnectionInterface

//ATTENTION: this method gets a Json that is authomatically marshalled into a model.CommandRequest struct
func Handler(iotRequest model.CommandRequest) error {
	// TODO 1 generate id to the command (see uuid.NewV4)
	uid := ""//uuid.NewV4()
	fmt.Println(uid)
	fmt.Println("food:",iotRequest.Food)
	// TODO 2 generate command (model.command) with date in utc format (see time.UTC.Format in the time library)
	command := model.Command{}//model.Command{IdCommand: uid.String(), DateCommand: time.Now().UTC().Format(time.RFC3339), Food: iotRequest.Food}
	// TODO 3 save command in dynamo, see the SaveCommand method in dao/commandDao
	saveCommandError := DataConnectionManager.SaveCommand(command)
	if saveCommandError != nil {
		return saveCommandError
	}
	return nil
}

func main() {
	DataConnectionManager = dao.CreateCommandConnection()
	lambda.Start(Handler)
}
