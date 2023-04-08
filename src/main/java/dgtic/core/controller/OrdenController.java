package dgtic.core.controller;

import dgtic.core.entity.Cliente;
import dgtic.core.entity.Orden;
import dgtic.core.service.IClienteService;
import dgtic.core.service.IOrdenService;
import dgtic.core.utilerias.RenderPagina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "ordenes")
public class OrdenController {
    @Autowired
    IOrdenService iOrdenService;
    @Autowired
    IClienteService iClienteService;

    @GetMapping("lista")
    public String inicio(@RequestParam(name = "page",defaultValue = "0") int page,
                         Model model){
        Pageable pageReq= PageRequest.of(page,5);

        Page<Orden> ord = iOrdenService.findAll(pageReq);
        RenderPagina<Orden> render = new RenderPagina<>("lista",ord);
        model.addAttribute("ordenes",ord);
        model.addAttribute("page",render);
        return "ordenes/listaordenes";

    }

    @GetMapping("formularioorden")
    public String formularioOrden(Model model){
        Orden orden = new Orden();
        List<Cliente> listaClientes = iClienteService.buscarClientes();
        model.addAttribute("titulo","Nueva Orden");
        model.addAttribute("orden",orden);
        model.addAttribute("listaClientes", listaClientes);
        return "ordenes/nuevasordenes";
    }
    @PostMapping(value = "/forminsertar")
    public String formOrdenInsertar(@Valid @ModelAttribute("orden") Orden orden,
                                    BindingResult resultado, Model model, RedirectAttributes flash) {
        if (resultado.hasErrors()) {
            model.addAttribute("titulo", "Error al crear la orden");
            return "ordenes/nuevasordenes";
        }

        Date fechaActual = new Date();
        Cliente cliente = new Cliente();
        cliente.setId_cliente(orden.getCliente().getId_cliente());
        orden.setFecha_orden(fechaActual);
        orden.setCliente(cliente);

        iOrdenService.guardar(orden);
        flash.addFlashAttribute("mensaje", "Orden se almaceno o modifico correctamente");
        return "redirect:/ordenes/lista";
    }


    @GetMapping("modificarordenes/{id}")
    public String formularioOrden(@PathVariable("id")Integer id, Model model){
        Orden orden=iOrdenService.buscarOrden(id);
        model.addAttribute("titulo","Modificar orden");
        model.addAttribute("orden",orden);
        return "ordenes/modificarordenes";
    }

    @GetMapping("borrarordenes/{id}")
    public String borrarOrden(@PathVariable("id") Integer id, Model model,RedirectAttributes flash){
        iOrdenService.borrar(id);
        flash.addFlashAttribute("mensaje","Orden se borró con exito");
        return "redirect:/ordenes/lista";
    }
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        webDataBinder.registerCustomEditor(Date.class,new CustomDateEditor(format,false));
    }
}
