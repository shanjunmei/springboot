#!/bin/bash 
# chkconfig: 2345 95 1 
# description: script to start/stop jetty case $1 in start) 
/usr/local/jetty/bin/jetty.sh start ;; stop) 
/usr/local/jetty/bin/jetty.sh stop ;; *) 
echo "Usage: $0 (start|stop)" ;; esac 