#!/bin/sh


# OpenJDK 64-Bit Server VM warning: ignoring option MaxPermSize=384M; support was removed in 8.0



# /usr/lib/jvm/jre-1.8.0-openjdk.x86_64/bin/java
JAVA_HOME="/usr/lib/jvm/jre-1.8.0-openjdk.x86_64/"


if test "$1" = "debug"; then
    JAVA_DEBUG_PORT="9998"
    shift
fi

if [ -z "${JAVA_DEBUG_PORT}" ]; then
    DEBUG_PARAMETERS=""
else
    DEBUG_PARAMETERS="-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=${JAVA_DEBUG_PORT}"
fi

SBT_LAUNCH_JAR=`dirname $0`/sbtwrapper/sbt-launch.jar

#${JAVA_HOME}/bin/java -version

${JAVA_HOME}/bin/java ${DEBUG_PARAMETERS} -Xms512M -Xmx1536M -Xss1M -XX:+CMSClassUnloadingEnabled -XX:MaxPermSize=384M -jar $SBT_LAUNCH_JAR "$@"
