1 install maven<br>
<br>
2 Download tamcat<br>
curl http://archive.apache.org/dist/tomcat/tomcat-9/v9.0.64/bin/apache-tomcat-9.0.64.zip --output tomcat.zip<br>
<br>
3 unarchive tomcat<br>
unzip tomcat.zip<br>
<br>
4 assign rights to deployment and shutdown scritps<br>
cd to cinema dir<br>
chmod 777 deploy_cinema.sh<br>
chmod 777 shutdown_cinema.sh<br>
<br>
5 configure application before deployment<br>
For that find cinema.properties in app directory and edit it.<br>
  &emsp;5.1 Directory where posters for movies are saved: poster.path=/Users/oleg/Desktop/cinema/posters<br>
  &emsp;5.2 true - if derectory with saved posters must by deleted on app shutdown: poster.delete_posters_after_shutdown=true<br>
  &emsp;5.3 true if you want to run the app with virtual data base. Data will disappear after shutdown. false - app will try to connect to postgres<br> 
  &emsp;using parameters provided by you db.embedded=true<br>
  &emsp;5.4 db.driver=org.postgresql.Driver<br>
  &emsp;5.5 db.url=jdbc:postgresql://localhost:5432/postgres<br>
  &emsp;5.6 db.user.name=oleg<br>
  &emsp;5.7 db.user.password=0139<br>
<br>
6 run deploy script<br>
./deploy_cinema.sh<br>
<br>
7 app starting page is accessible at http://localhost:8080/cinema/admin/panel<br>
<br>
8 when app is no longer needed run shutdown script<br>
./shutdown_cinema.sh<br>
