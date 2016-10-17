
package Clinic;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the Clinic package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Connectable_QNAME = new QName("http://distro.paddez.com/", "connectable");
    private final static QName _IsAvailableResponse_QNAME = new QName("http://distro.paddez.com/", "isAvailableResponse");
    private final static QName _GetAppointments_QNAME = new QName("http://distro.paddez.com/", "getAppointments");
    private final static QName _GetAppointmentsResponse_QNAME = new QName("http://distro.paddez.com/", "getAppointmentsResponse");
    private final static QName _CancelAppointment_QNAME = new QName("http://distro.paddez.com/", "cancelAppointment");
    private final static QName _GetInfoResponse_QNAME = new QName("http://distro.paddez.com/", "getInfoResponse");
    private final static QName _IsAvailable_QNAME = new QName("http://distro.paddez.com/", "isAvailable");
    private final static QName _AddAppointment_QNAME = new QName("http://distro.paddez.com/", "addAppointment");
    private final static QName _GetInfo_QNAME = new QName("http://distro.paddez.com/", "getInfo");
    private final static QName _ConnectableResponse_QNAME = new QName("http://distro.paddez.com/", "connectableResponse");
    private final static QName _CancelAppointmentResponse_QNAME = new QName("http://distro.paddez.com/", "cancelAppointmentResponse");
    private final static QName _AddAppointmentResponse_QNAME = new QName("http://distro.paddez.com/", "addAppointmentResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: Clinic
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CancelAppointment }
     * 
     */
    public CancelAppointment createCancelAppointment() {
        return new CancelAppointment();
    }

    /**
     * Create an instance of {@link GetInfoResponse }
     * 
     */
    public GetInfoResponse createGetInfoResponse() {
        return new GetInfoResponse();
    }

    /**
     * Create an instance of {@link IsAvailable }
     * 
     */
    public IsAvailable createIsAvailable() {
        return new IsAvailable();
    }

    /**
     * Create an instance of {@link GetAppointments }
     * 
     */
    public GetAppointments createGetAppointments() {
        return new GetAppointments();
    }

    /**
     * Create an instance of {@link GetAppointmentsResponse }
     * 
     */
    public GetAppointmentsResponse createGetAppointmentsResponse() {
        return new GetAppointmentsResponse();
    }

    /**
     * Create an instance of {@link Connectable }
     * 
     */
    public Connectable createConnectable() {
        return new Connectable();
    }

    /**
     * Create an instance of {@link IsAvailableResponse }
     * 
     */
    public IsAvailableResponse createIsAvailableResponse() {
        return new IsAvailableResponse();
    }

    /**
     * Create an instance of {@link GetInfo }
     * 
     */
    public GetInfo createGetInfo() {
        return new GetInfo();
    }

    /**
     * Create an instance of {@link AddAppointment }
     * 
     */
    public AddAppointment createAddAppointment() {
        return new AddAppointment();
    }

    /**
     * Create an instance of {@link ConnectableResponse }
     * 
     */
    public ConnectableResponse createConnectableResponse() {
        return new ConnectableResponse();
    }

    /**
     * Create an instance of {@link AddAppointmentResponse }
     * 
     */
    public AddAppointmentResponse createAddAppointmentResponse() {
        return new AddAppointmentResponse();
    }

    /**
     * Create an instance of {@link CancelAppointmentResponse }
     * 
     */
    public CancelAppointmentResponse createCancelAppointmentResponse() {
        return new CancelAppointmentResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Connectable }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://distro.paddez.com/", name = "connectable")
    public JAXBElement<Connectable> createConnectable(Connectable value) {
        return new JAXBElement<Connectable>(_Connectable_QNAME, Connectable.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsAvailableResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://distro.paddez.com/", name = "isAvailableResponse")
    public JAXBElement<IsAvailableResponse> createIsAvailableResponse(IsAvailableResponse value) {
        return new JAXBElement<IsAvailableResponse>(_IsAvailableResponse_QNAME, IsAvailableResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAppointments }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://distro.paddez.com/", name = "getAppointments")
    public JAXBElement<GetAppointments> createGetAppointments(GetAppointments value) {
        return new JAXBElement<GetAppointments>(_GetAppointments_QNAME, GetAppointments.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAppointmentsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://distro.paddez.com/", name = "getAppointmentsResponse")
    public JAXBElement<GetAppointmentsResponse> createGetAppointmentsResponse(GetAppointmentsResponse value) {
        return new JAXBElement<GetAppointmentsResponse>(_GetAppointmentsResponse_QNAME, GetAppointmentsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelAppointment }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://distro.paddez.com/", name = "cancelAppointment")
    public JAXBElement<CancelAppointment> createCancelAppointment(CancelAppointment value) {
        return new JAXBElement<CancelAppointment>(_CancelAppointment_QNAME, CancelAppointment.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://distro.paddez.com/", name = "getInfoResponse")
    public JAXBElement<GetInfoResponse> createGetInfoResponse(GetInfoResponse value) {
        return new JAXBElement<GetInfoResponse>(_GetInfoResponse_QNAME, GetInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsAvailable }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://distro.paddez.com/", name = "isAvailable")
    public JAXBElement<IsAvailable> createIsAvailable(IsAvailable value) {
        return new JAXBElement<IsAvailable>(_IsAvailable_QNAME, IsAvailable.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddAppointment }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://distro.paddez.com/", name = "addAppointment")
    public JAXBElement<AddAppointment> createAddAppointment(AddAppointment value) {
        return new JAXBElement<AddAppointment>(_AddAppointment_QNAME, AddAppointment.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://distro.paddez.com/", name = "getInfo")
    public JAXBElement<GetInfo> createGetInfo(GetInfo value) {
        return new JAXBElement<GetInfo>(_GetInfo_QNAME, GetInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConnectableResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://distro.paddez.com/", name = "connectableResponse")
    public JAXBElement<ConnectableResponse> createConnectableResponse(ConnectableResponse value) {
        return new JAXBElement<ConnectableResponse>(_ConnectableResponse_QNAME, ConnectableResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelAppointmentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://distro.paddez.com/", name = "cancelAppointmentResponse")
    public JAXBElement<CancelAppointmentResponse> createCancelAppointmentResponse(CancelAppointmentResponse value) {
        return new JAXBElement<CancelAppointmentResponse>(_CancelAppointmentResponse_QNAME, CancelAppointmentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddAppointmentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://distro.paddez.com/", name = "addAppointmentResponse")
    public JAXBElement<AddAppointmentResponse> createAddAppointmentResponse(AddAppointmentResponse value) {
        return new JAXBElement<AddAppointmentResponse>(_AddAppointmentResponse_QNAME, AddAppointmentResponse.class, null, value);
    }

}
