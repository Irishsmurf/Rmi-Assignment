
package Clinic;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "Clinic", targetNamespace = "http://distro.paddez.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Clinic {


    /**
     * 
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "connectable", targetNamespace = "http://distro.paddez.com/", className = "Clinic.Connectable")
    @ResponseWrapper(localName = "connectableResponse", targetNamespace = "http://distro.paddez.com/", className = "Clinic.ConnectableResponse")
    @Action(input = "http://distro.paddez.com/Clinic/connectableRequest", output = "http://distro.paddez.com/Clinic/connectableResponse")
    public boolean connectable();

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getInfo", targetNamespace = "http://distro.paddez.com/", className = "Clinic.GetInfo")
    @ResponseWrapper(localName = "getInfoResponse", targetNamespace = "http://distro.paddez.com/", className = "Clinic.GetInfoResponse")
    @Action(input = "http://distro.paddez.com/Clinic/getInfoRequest", output = "http://distro.paddez.com/Clinic/getInfoResponse")
    public String getInfo(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        int arg2);

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "isAvailable", targetNamespace = "http://distro.paddez.com/", className = "Clinic.IsAvailable")
    @ResponseWrapper(localName = "isAvailableResponse", targetNamespace = "http://distro.paddez.com/", className = "Clinic.IsAvailableResponse")
    @Action(input = "http://distro.paddez.com/Clinic/isAvailableRequest", output = "http://distro.paddez.com/Clinic/isAvailableResponse")
    public boolean isAvailable(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        int arg2);

    /**
     * 
     * @param arg4
     * @param arg3
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "addAppointment", targetNamespace = "http://distro.paddez.com/", className = "Clinic.AddAppointment")
    @ResponseWrapper(localName = "addAppointmentResponse", targetNamespace = "http://distro.paddez.com/", className = "Clinic.AddAppointmentResponse")
    @Action(input = "http://distro.paddez.com/Clinic/addAppointmentRequest", output = "http://distro.paddez.com/Clinic/addAppointmentResponse")
    public boolean addAppointment(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        int arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        int arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        String arg4);

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "cancelAppointment", targetNamespace = "http://distro.paddez.com/", className = "Clinic.CancelAppointment")
    @ResponseWrapper(localName = "cancelAppointmentResponse", targetNamespace = "http://distro.paddez.com/", className = "Clinic.CancelAppointmentResponse")
    @Action(input = "http://distro.paddez.com/Clinic/cancelAppointmentRequest", output = "http://distro.paddez.com/Clinic/cancelAppointmentResponse")
    public String cancelAppointment(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        int arg2);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<java.lang.String>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getAppointments", targetNamespace = "http://distro.paddez.com/", className = "Clinic.GetAppointments")
    @ResponseWrapper(localName = "getAppointmentsResponse", targetNamespace = "http://distro.paddez.com/", className = "Clinic.GetAppointmentsResponse")
    @Action(input = "http://distro.paddez.com/Clinic/getAppointmentsRequest", output = "http://distro.paddez.com/Clinic/getAppointmentsResponse")
    public List<String> getAppointments(
        @WebParam(name = "arg0", targetNamespace = "")
        Integer arg0);

}
