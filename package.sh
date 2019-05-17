#!/bin/bash
# maven package to jar
appEnv=$1
echo "appEnv=$appEnv"
if [ "$appEnv" == "dev" -o "$appEnv" == "test"  -o "$appEnv" == "prod" ]
then
 git pull
mvn clean package -Dmaven.test.skip=true -P${appEnv}
else
 echo "echo usage package.cmd [dev][test][prod]"
fi
