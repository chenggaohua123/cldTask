#!/bin/sh
CLASSPATH=${CLASSPATH}:"./classes":`echo ./lib/*.jar | sed "s/ /:/g"`
if [ -z "${JAVA_HOME}" ] ; then
    echo "请设置JAVA_HOME"
    echo "Please set JAVA_HOME"
    exit 1
fi
if [ ! -x "${JAVA_HOME}/bin/java" ] ; then
    echo "JAVA_HOME设置错误"
    echo "Can not find Java VM"
    exit 1
fi

echo "Using JAVA_HOME   ${JAVA_HOME}"
echo "Using CLASSPATH   ${CLASSPATH}"

"${JAVA_HOME}/bin/java" ${JAVA_OPTS} -cp ${CLASSPATH} com.fhtpay.task.main.TaskMain
