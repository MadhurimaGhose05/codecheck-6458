#!/bin/bash

ROOT=$(cd $(dirname $0) && pwd)
### Java ###
java -jar $(ls $ROOT/java/ai/target/ai-*.jar) "$@"
#java -jar $(ls $ROOT/java/ai/target/ai-*.jar) apple least telnet hello tap pine egg gogl
