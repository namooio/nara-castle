//package namoo.nara.castle.sp.spring.remoting;
//
//import namoo.nara.castle.domain.service.CastleServiceLycler;
//import namoo.nara.castle.rep.CastleRepService;
//import namoo.nara.castle.rep.logic.CastleRepServiceLogic;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
//
//@Configuration
//public class CastleRepRemoteExporter {
//    //
//    @Autowired
//    private CastleServiceLycler serviceLycler;
//
//    @Bean(name = "/castle-rep/remote/CastleRepService")
//    public HttpInvokerServiceExporter createCastleRepServiceExporter() {
//        //
//        HttpInvokerServiceExporter serviceExporter = new HttpInvokerServiceExporter();
//        serviceExporter.setService(new CastleRepServiceLogic(serviceLycler));
//        serviceExporter.setServiceInterface(CastleRepService.class);
//        return serviceExporter;
//    }
//
//}
