ps aux|grep app.App|grep springframework|awk '{print $2}'|xargs kill -9
export JAVA_HOME="/opt/jdk-10.0.1"
export PATH="${JAVA_HOME}/bin:$PATH:${JAVA_HOME}/bin"
echo $PATH
chmod 777 gradlew
./gradlew --stacktrace bootRun