echo "Stopping interview proceses"
ps aux|grep app.App|grep springframework|awk '{print $2}'|xargs kill -9
sleep 2s
whoami
cp -r /var/lib/jenkins/workspace/interview/* /home/jenkins/interview/
cd /home/jenkins/interview/
export PATH="${JAVA_HOME}/bin:$PATH:${JAVA_HOME}/bin"
echo $PATH
chmod 777 gradlew
#gradle wrapper --gradle-version=5.1.1
./gradlew --stacktrace bootRun > interview-${1}.log 2>&1 &

sleep 10s