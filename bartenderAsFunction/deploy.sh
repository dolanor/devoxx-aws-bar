#!/bin/bash

P_USER=$1

if [[ -z "${P_USER}" ]]; then
  P_USER=user3
fi

aws cloudformation package \
  --template-file ./sam.yml \
  --s3-bucket handsonbartender \
  --output-template-file ./packaged.yml \
  --profile epf

## we omit https://docs.aws.amazon.com/cli/latest/reference/cloudformation/package.html and we do it by hand
## because it is more convenient for the workshop
echo "deploying...."

aws cloudformation deploy \
  --template-file ./packaged.yml \
  --stack-name $P_USER-bartender-sam-deploy \
  --parameter-overrides User=$P_USER \
  --capabilities CAPABILITY_IAM \
  --region eu-west-1 \
  --profile epf

# rm bin/*.jar
