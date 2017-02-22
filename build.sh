#!/bin/bash

ROOT=$(cd $(dirname $0) && pwd)

### Java ###
cd $ROOT/java/fw
mvn package -Dmaven.test.skip=true

mvn install:install-file -Dfile=$ROOT/java/fw/target/fw-1.0-SNAPSHOT.jar -DgroupId=codecheck -DartifactId=fw -Dversion=1.0-SNAPSHOT -Dpackaging=jar

cd $ROOT/java/ai
mvn package -Dmaven.test.skip=true