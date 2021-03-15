#!/bin/bash
echo "Stopping SpringBoot Application"
pwd
# shellcheck disable=SC2006
pid=`ps -ef | grep '.*backend-coin.*jar' | grep -v grep | awk '{print $2}'`
if [ -n "$pid" ]
then
   kill -9 "$pid"
   rm -f nohup.out
fi