package com.devsuperior.dsmeta.resource;

import com.devsuperior.dsmeta.dto.SaleDto;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.services.SaleService;
import com.devsuperior.dsmeta.services.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/sales")
public class SaleResource {
	
	@Autowired
	private SaleService service;
	
	@Autowired
	private SmsService smsService;
	
	@GetMapping
	public Page<Sale> findSales(
			@RequestParam(value = "minDate", defaultValue = "") String minDate, 
			@RequestParam(value = "maxDate", defaultValue = "")String maxDate, 
			Pageable pageable){
		return service.findSales(minDate, maxDate, pageable);
	}
	
	@GetMapping("/{id}/notification")
	public void notifySms(@PathVariable Long id) {
		smsService.sendSms(id);
	}

	@PostMapping
	public Sale createSale(@RequestBody SaleDto sale) {
		return service.sendSale(sale);
	}

	@GetMapping("/{id}")
	public Optional<Sale> findSaleById(@PathVariable Long id){
		return service.findSaleById(id);
	}

	@PutMapping("/{id}")
	public Sale updateSale(@RequestBody SaleDto saleDto,
						   @PathVariable Long id){

		return service.updateSaleById(saleDto, id);
	}

	@DeleteMapping("/{id}")
	public void deleteSale(@PathVariable Long id){
		service.deleteSaleById(id);
	}
}
