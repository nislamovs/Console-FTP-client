Minimalistic SFTP client
================
Console SFTP client for uploading and downloading.
Made on pure Java 7. Runnable on 32 bit systems.

Run command: `mvn clean package`,
and get your jar file.  

Downloading files from remote server
================
    TARGET=/path/to/files/you/want/to/download
    DESTINATION=/path/where/you/want/to/store/your/files
    LOGIN=username
    PASSWORD=password1234
    HOST=your.host.com

`java -jar sftps-0.1.0.jar -t $TARGET -d $DESTINATION -u $LOGIN -p $PASSWORD -h $HOST -o get`

Uploading files to remote server
================
    TARGET=/path/to/files/you/want/to/upload
    DESTINATION=/path/where/you/want/to/store/your/files
    LOGIN=username
    PASSWORD=password1234
    HOST=your.host.com

`java -jar sftps-0.1.0.jar -t $TARGET -d $DESTINATION -u $LOGIN -p $PASSWORD -h $HOST -o put`