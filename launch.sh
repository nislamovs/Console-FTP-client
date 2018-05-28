#!/usr/bin/env bash

echo "OK, starting now..."

export PATH=/usr/bin:/usr/sbin:/usr/cluster/bin:/usr/sfw/bin:/opt/sfw/bin

TARGET=/path/to/files/you/want/to/download
DESTINATION=/path/where/you/want/to/store/your/files
LOGIN=username
PASSWORD=password1234
HOST=your.host.com

#Downloading files from remote server
java -jar sftps-0.1.0.jar -t $TARGET -d $DESTINATION -u $LOGIN -p $PASSWORD -h $HOST