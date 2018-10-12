echo off
set APP_NAME=framework-boot.jar
set CONFIG= -Dlogging.path=../logs -Dspring.config.location=../config/application.yaml
set JRE_HOME=..\jre
set exe=%JRE_HOME%\bin\java
set RUN_OPS=-Xms12m -Xmx64m -server
set DEBUG_OPTS=
if ""%1"" == ""debug"" (
  set DEBUG_OPTS= -Xloggc:../logs/gc.log -verbose:gc -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=../logs 
  goto debug
)
set JMX_OPTS=
if ""%1"" == ""jmx"" (
  set JMX_OPTS= -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=9888 -Dcom.sun.management.jmxremote.ssl=FALSE -Dcom.sun.management.jmxremote.authenticate=FALSE 
  goto jmx
)
echo "Starting the %APP_NAME%"

set RUN_OPS=%RUN_OPS% %DEBUG_OPTS% %JMX_OPTS% %CONFIG%
goto end
:debug
echo "debug"

set RUN_OPS=%RUN_OPS% %DEBUG_OPTS% %CONFIG%
goto end
:jmx

set RUN_OPS=%RUN_OPS% %JMX_OPTS% %CONFIG%
goto end
:end
set RUN_OPS=%RUN_OPS% -jar ../lib/%APP_NAME%
%exe%  %RUN_OPS%
pause