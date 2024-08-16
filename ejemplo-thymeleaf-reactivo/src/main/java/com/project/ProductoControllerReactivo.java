package com.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring6.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring6.context.webflux.ReactiveDataDriverContextVariable;

@Controller
public class ProductoControllerReactivo {

    @Autowired
    private ProductoService productoService;

    @RequestMapping("/lista")
    public String listarProductos(Model model) {

        // Variable Reactiva
        IReactiveDataDriverContextVariable listaReactiva = new ReactiveDataDriverContextVariable(
                productoService.buscarTodos(), 1);

        model.addAttribute("listaProductos", listaReactiva);

        return "lista";
    }

}
