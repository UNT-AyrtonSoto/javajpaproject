package com.example.saaoa.controllers;

import com.example.saaoa.dto.SaleDetailRegistrationDto;
import com.example.saaoa.dto.SaleRegistrationDto;
import com.example.saaoa.model.Client;
import com.example.saaoa.model.Product;
import com.example.saaoa.model.Sale;
import com.example.saaoa.model.Sale_Detail;
import com.example.saaoa.model.Seller;
import com.example.saaoa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.util.*;

@Controller
@RequestMapping("/sales")
public class SaleController {
    @Autowired
    private SaleServiceImpl saleService;

    @Autowired
    private Sale_DetailServiceImpl sale_detailService;

    @Autowired
    private SellerServiceImpl sellerService;

    @Autowired
    private ClientServiceImpl clientService;

    @Autowired
    private ProductServiceImpl productService;

    private String add_template = "sale/add-edit-sale";
    private String list_template = "sale/list-sale";
    private String list_redirect = "redirect:/sales/";

    @GetMapping("/")
    public String listProducts(Model model){
        List<Sale> sales = saleService.listAll();
        model.addAttribute("listSales", sales);

        return  list_template;
    }

    @GetMapping("/add")
    public String addSale(Sale sale,Model model){
        model.addAttribute("Sale", sale);
        List<Client> clients = clientService.listAll();
        List<Seller> sellers = sellerService.listAll();
        List<Product> products = productService.listAll();
        model.addAttribute("listClients", clients);
        model.addAttribute("listSellers", sellers);
        model.addAttribute("listProducts", products);
        model.addAttribute("size", false);

        return  add_template;
    }

    @GetMapping("/edit/{id}")
    public String editSale(@PathVariable("id") long id, Model model){
        Sale sale = saleService.getById(id).get();
        model.addAttribute("sale", sale);
        List<Client> clients = clientService.listAll();
        List<Seller> sellers = sellerService.listAll();
        List<Product> products = productService.listAll();
        model.addAttribute("listClients", clients);
        model.addAttribute("listSellers", sellers);
        model.addAttribute("listProducts", products);
        model.addAttribute("size", true);

        return  add_template;
    }

    @PostMapping("/save")
    public String save(@RequestParam(value = "idSale") Long id, @RequestParam(value="dniClient") String client_dni, @RequestParam(value = "dniSeller") String seller_dni, @RequestParam(value = "exists[]") List<Long> detail_id, @RequestParam(value = "product[]") List<String> products, @RequestParam(value = "quantity[]") List<Integer> quantities, RedirectAttributes redirect) throws ParseException {
        Date saleDate = new Date();
        Collection<Sale_Detail> items = new ArrayList<Sale_Detail>();
        Collection<Sale_Detail> prev_items = new ArrayList<Sale_Detail>();
        Client client = clientService.findByDni(client_dni);
        Seller seller = sellerService.findByDni(seller_dni);
        Sale sale = null;
        if(id==null || id==0){
            sale = saleService.save(new SaleRegistrationDto(client, seller, saleDate));
        }else{
            prev_items = saleService.getById(id).get().getItems();
            sale = saleService.save(id, new SaleRegistrationDto(client, seller, saleDate));
        }
        for(int i=0; i<products.size();i++){
            Product t_product = productService.findByName(products.get(i));
            int t_quantity = quantities.get(i);
            Sale_Detail sale_detail = null;
            if(detail_id.get(i)==0){
                sale_detail = sale_detailService.save(new SaleDetailRegistrationDto(sale, t_product, t_quantity));
            }else {
                sale_detail = sale_detailService.save(detail_id.get(i) ,new SaleDetailRegistrationDto(sale, t_product, t_quantity));
            }
            items.add(sale_detail);
        }
        if(prev_items!=null && prev_items.size()>0) {
            Iterator<Sale_Detail> sale_iterator = prev_items.iterator();
            Iterator<Sale_Detail> items_iterator = items.iterator();
            while (sale_iterator.hasNext()) {
                Sale_Detail si_sd_curr = sale_iterator.next();
                boolean f_found = false;
                while (items_iterator.hasNext() && !f_found) {
                    Sale_Detail ii_sd_curr = items_iterator.next();
                    f_found = si_sd_curr.getId() == ii_sd_curr.getId();
                }
                if (!f_found) {
                    sale_detailService.delete(si_sd_curr);
                }
            }
        }
        sale.setItems(items);
        redirect.addFlashAttribute("message", "Venta agregada correctamente");
        return list_redirect;
    }

    @GetMapping("/delete/{id}")
    public String deleteSale(@PathVariable("id") long id, Model model){
        saleService.delete(id);
        return  list_redirect;
    }
}
