#!/bin/bash

ROOT=$(cd $(dirname $0) && pwd)

for i in "$@"
do
	case $i in
	"./ai.sh" )   ;;
	* ) dict=$dict" "$i ;;
	esac
done

### Java ###
java -jar $(ls $ROOT/java/fw/target/fw-*.jar) $dict &

sleep 2

./ai.sh $dict &

./ai.sh $dict
