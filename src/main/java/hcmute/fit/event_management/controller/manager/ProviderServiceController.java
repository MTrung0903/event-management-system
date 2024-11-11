package hcmute.fit.event_management.controller.manager;

import hcmute.fit.event_management.dto.ProviderServiceDTO;
import hcmute.fit.event_management.service.IProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import payload.Response;

import java.util.List;

@RestController
@RequestMapping("/man/proService")
public class ProviderServiceController {
    @Autowired
    private IProviderService providerService;

    @GetMapping("")
    public ResponseEntity<?> getListServiceOfProvider(@RequestParam int providerId) {
        Response response = new Response();
        List<ProviderServiceDTO> list = providerService.getServiceProviders(providerId);
        response.setData(list);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/find")
    public ResponseEntity<?> findServiceById(@RequestParam int serviceId) {
        Response response = new Response();
        ProviderServiceDTO service = providerService.findServiceById(serviceId);
        response.setData(service);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addService(@RequestParam int providerId,
                                        @RequestParam String serviceType,
                                        @RequestParam String serviceName,
                                        @RequestParam String serviceDesc,
                                        @RequestParam String price,
                                        @RequestParam String duration){
        Response response = new Response();
        response.setData(providerService.addServiceProvider(providerId, serviceType, serviceName, serviceDesc, price, duration));
        return ResponseEntity.ok(response);
    }

    @PutMapping("update")
    public ResponseEntity<?> updateService(@RequestParam int serviceId,
                                        @RequestParam String serviceType,
                                        @RequestParam String serviceName,
                                        @RequestParam String serviceDesc,
                                        @RequestParam String price,
                                        @RequestParam String duration,
                                           @RequestParam int providerId){
        Response response = new Response();
        response.setData(providerService.updateServiceProvider(serviceId, serviceType, serviceName, serviceDesc, price, duration,providerId));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> deleteService(@RequestParam int serviceId) {
        Response response = new Response();
        response.setData(providerService.deleteServiceProvider(serviceId));
        return ResponseEntity.ok(response);
    }
}
