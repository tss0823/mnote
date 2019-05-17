#!/bin/bash
# maven run jar
appName=$1
echo "appName=appName"
if [ "$appName" = "boss" -o "$appName" == "member"  -o "$appName" == "task"  ]
then
java -jar mnote-${appName}/target/mnote-${appName}.jar
else
 echo "echo usage run.cmd [boss][member][task]"
fi
