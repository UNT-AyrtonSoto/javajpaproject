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

    @PostMapping("/save")
    public String save(@RequestParam(value="dniClient") String client_dni, @RequestParam(value = "dniSeller") String seller_dni, @RequestParam(value = "product[]") List<String> products, @RequestParam(value = "quantity[]") List<Integer> quantities, RedirectAttributes redirect) throws ParseException {
        Date saleDate = new Date();
        Collection<Sale_Detail> items = new ArrayList<Sale_Detail>();
        Client client = clientService.findByDni(client_dni);
        Seller seller = sellerService.findByDni(seller_dni);
        Sale sale = saleService.save(new SaleRegistrationDto(client, seller, saleDate));
        for(int i=0; i<products.size();i++){
            Product t_product = productService.findByName(products.get(i));
            int t_quantity = quantities.get(i);
            Sale_Detail sale_detail = sale_detailService.save(new SaleDetailRegistrationDto(sale, t_product, t_quantity));
            items.add(sale_detail);
        }
        sale.setItems(items);
        redirect.addFlashAttribute("message", "Venta agregada correctamente");
        return list_redirect;
    }
}
