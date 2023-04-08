package dgtic.core.controller;

import dgtic.core.entity.Cliente;
import dgtic.core.service.IClienteService;
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
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
@Controller
@RequestMapping(value = "clientes")
public class ClienteController {

    @Autowired
    private IClienteService iClienteService;

    @GetMapping("lista")
    public String inicio(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Pageable pageReq = PageRequest.of(page, 5);
        Page<Cliente> cli = iClienteService.findAll(pageReq);
        RenderPagina<Cliente> render = new RenderPagina<>("lista", cli);
        model.addAttribute("clientes", cli);
        model.addAttribute("page", render);
        return "clientes/listaclientes";
    }

    @GetMapping("formulariocliente")
    public String formularioCliente(Model model) {
        Cliente cliente = new Cliente();
        model.addAttribute("titulo", "Nuevo Cliente");
        model.addAttribute("cliente", cliente);
        return "clientes/nuevosclientes";
    }

    @PostMapping(value = "/forminsertar")
    public String formClienteInsertar(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult resultado,
                                      Model model, RedirectAttributes flash) {
        if (resultado.hasErrors()) {
            model.addAttribute("titulo", "Error al crear el cliente");
            return "clientes/nuevosclientes";
        }
        LocalDate fechaNacimiento = cliente.getFecha_nacimiento().toInstant().atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate fechaActual = LocalDate.now();
        Period edad = Period.between(fechaNacimiento, fechaActual);
        cliente.setEdad(edad.getYears());
        if (cliente.getEdad() < 18 || cliente.getEdad() > 100) {
            resultado.rejectValue("fecha_nacimiento", "error.cliente", "La edad del cliente debe estar entre 18 y 100 años");
            model.addAttribute("titulo", "Error al crear/modificar el cliente");
            return "error_edad";
        }
        iClienteService.guardar(cliente);
        flash.addFlashAttribute("mensaje", "Cliente se almaceno o modifico correctamente");
        return "redirect:/clientes/lista";
    }

    @GetMapping("modificarclientes/{id}")
    public String formularioCliente(@PathVariable("id") Integer id, Model model) {
        Cliente cliente = iClienteService.buscarCliente(id);
        model.addAttribute("titulo", "Modificar cliente");
        model.addAttribute("cliente", cliente);
        return "clientes/modificarclientes";
    }

    @PostMapping(value = "/formmodificar/{id}")
    public String formClienteModificar(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult resultado,
                                       Model model, RedirectAttributes flash) {
        if (resultado.hasErrors()) {
            model.addAttribute("titulo", "Error al modificar el cliente");
            return "clientes/modificarclientes";
        }
        // Calculamos la edad a partir de la fecha de nacimiento
        LocalDate fechaNacimiento = cliente.getFecha_nacimiento().toInstant().atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate fechaActual = LocalDate.now();
        Period edad = Period.between(fechaNacimiento, fechaActual);
        cliente.setEdad(edad.getYears());
        iClienteService.guardar(cliente);
        flash.addFlashAttribute("mensaje", "Cliente se almaceno o modifico correctamente");
        return "redirect:/clientes/lista";
    }

    @GetMapping("borrarclientes/{id}")
    public String borrarCliente(@PathVariable("id") Integer id, Model model, RedirectAttributes flash) {
        iClienteService.borrar(id);
        flash.addFlashAttribute("mensaje", "Cliente se borró con exito");
        return "redirect:/clientes/lista";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        webDataBinder.registerCustomEditor(Date.class,new CustomDateEditor(format,false));
    }
}
