# Petcare sample using AWS Lamda, API GateWay S3 And Cognito #

* For debug locally:
sam local start-api -d 5858

* For compiling with maven:
clean package shade:shade

*Uploading file from from AWS CLI:
aws lambda update-function-code --function-name PetCare --zip-file fileb://./target/PetHairCut-1.0-SNAPSHOT-aws.jar --region us-west-2

*Compiling file from intelliJ:
clean package shade:shade

* 1.Query to all Owners.
* 2.Query to get all appointments from a given owner.
* 3.Query to get an specific appointment and all it's related data.
