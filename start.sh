#!/bin/bash
echo "Starting SpringBoot Application"
pwd
export JENKINS_NODE_COOKIE=dontKillMe
nohup java -Dhudson.util.ProcessTree.disable=true -jar target/*.jar --spring.profiles.active=prod &