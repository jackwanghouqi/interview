ps aux|grep app.App|grep springframework|awk '{print $2}'|xargs kill -9
export PATH="${JAVA_HOME}/bin:$PATH:${JAVA_HOME}/bin"
echo $PATH
chmod 777 gradlew
#gradle wrapper --gradle-version=5.1.1
./gradlew --stacktrace bootRun