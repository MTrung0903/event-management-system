package hcmute.fit.event_management.controller.manager;

import hcmute.fit.event_management.dto.ProviderDTO;
import hcmute.fit.event_management.entity.Provider;
import hcmute.fit.event_management.entity.ProviderService;
import hcmute.fit.event_management.service.IProvider;
import hcmute.fit.event_management.service.IProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import payload.Response;

import java.util.List;

@RestController
@RequestMapping("/man/provider")
public class ProviderController {
    @Autowired
    private IProvider providerImpl;


    @GetMapping("")
    public ResponseEntity<?> getAllProviders() {
        List<ProviderDTO> listProvider = providerImpl.getAllProviders();
        return new ResponseEntity<>(listProvider, HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<?> getProviderById(@RequestParam("providerId") int providerId) {
        ProviderDTO provider = providerImpl.findProviderById(providerId);
        return new ResponseEntity<>(provider, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProvider(@RequestParam String name,
                                         @RequestParam String contact,
                                         @RequestParam String email,
                                         @RequestParam String phone,
                                         @RequestParam String address,
                                         @RequestParam String website) {
        Response response = new Response();
        response.setData(providerImpl.addProvider(name, contact, email, phone, address, website));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProvider(@RequestParam int providerId,
                                            @RequestParam String name,
                                         @RequestParam String contact,
                                         @RequestParam String email,
                                         @RequestParam String phone,
                                         @RequestParam String address,
                                         @RequestParam String website) {
        Response response = new Response();
        response.setData(providerImpl.updateProvider(providerId, name, contact, email, phone, address, website));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProvider(@RequestParam int providerId) {
        Response response = new Response();
        response.setData(providerImpl.deleteProvider(providerId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

