For debug locally:
sam local start-api -d 5858

For compiling with maven:
clean package shade:shade

Uploading from AWS CLI:
aws lambda update-function-code --function-name PetCare --zip-file fileb://./target/PetHairCut-1.0-SNAPSHOT-aws.jar --region us-west-2

1.Query to all Owners.
2.Query to get all appointments from a given owner.
3.Query to get an specific appointment and all it's related data.
