vac -cp .:jfxrt.jar com/paddez/distro/*.java
wsgen -verbose -cp . com.paddez.distro.Clinic

(then run the ClinicPublisher)
java com.paddez.distro.ClinicPublisher &

Finally create the client stubs and run the UI.
wsimport -p Clinic -keep "http://localhost:9876/distro?wsdl"
java -Djava.security.policy=no.policy RmiClientUI


