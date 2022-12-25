
1. Install maven<br>
<br>
2. Download tamcat<br>
   &emsp;curl http://archive.apache.org/dist/tomcat/tomcat-9/v9.0.64/bin/apache-tomcat-9.0.64.zip --output tomcat.zip<br>
<br>
3. Unarchive tomcat<br>
   &emsp;unzip tomcat.zip<br>
<br>
4. Assign rights to deployment and shutdown scritps<br>
   &emsp;cd to cinema dir<br>
   &emsp;chmod 777 deploy_cinema.sh<br>
   &emsp;chmod 777 shutdown_cinema.sh<br>
<br>
5. Configure application before deployment. For that find cinema.properties in app directory and edit it.<br>
  &emsp;5.1. Directory where posters for movies are saved: poster.path=/Users/oleg/Desktop/cinema/posters<br>
  &emsp;5.2. true - if derectory with saved posters must by deleted on app shutdown: poster.delete_posters_after_shutdown=true<br>
  &emsp;5.3. true if you want to run the app with virtual data base. Data will disappear after shutdown. false - app will try to connect to postgres<br> 
  &emsp;using parameters provided by you db.embedded=true<br>
  &emsp;5.4. db.driver=org.postgresql.Driver<br>
  &emsp;5.5. db.url=jdbc:postgresql://localhost:5432/postgres<br>
  &emsp;5.6. db.user.name=oleg<br>
  &emsp;5.7. db.user.password=0139<br>
<br>
6. Run deploy script with 2 parameters: 1 - app dir 2 - apache dir<br>
  &emsp;./deploy_cinema.sh /app_dir /apache dir<br>
<br>
7. app starting page is accessible at http://localhost:8080/cinema/admin/panel<br>
<br>
8. When app is no longer needed run shutdown script with 1 parameter: tomcat dir<br>
  &emsp;./shutdown_cinema.sh /tomcat_dir<br>
