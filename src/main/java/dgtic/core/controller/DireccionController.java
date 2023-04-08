package dgtic.core.controller;

import dgtic.core.entity.Cliente;
import dgtic.core.entity.Direccion;
import dgtic.core.service.IClienteService;
import dgtic.core.service.IDireccionService;
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
@RequestMapping(value = "direcciones")
public class DireccionController {
    @Autowired
    private IDireccionService iDireccionService;

    @Autowired
    private IClienteService iClienteService;

    @GetMapping("lista")
    public String inicio(@RequestParam(name = "page",defaultValue="0") int page,
                         Model model){
        Pageable pageReq= PageRequest.of(page,5);

        Page<Direccion> dir = iDireccionService.findAll(pageReq);
        RenderPagina<Direccion> render = new RenderPagina<>("lista",dir);
        model.addAttribute("direcciones",dir);
        model.addAttribute("page",render);
        return "direcciones/listadirecciones";
    }
    @GetMapping("formulariodireccion")
    public String formularioDireccion(Model model){
        Direccion direccion = new Direccion();
        List<Cliente> listaClientes = iClienteService.buscarClientes();
        model.addAttribute("titulo","Nueva Dirección");
        model.addAttribute("direccion",direccion);
        model.addAttribute("listaClientes", listaClientes);
        return "direcciones/nuevasdirecciones";
    }
    @PostMapping(value = "/forminsertar")
    public String formDireccionInsertar(@Valid @ModelAttribute("direccion")Direccion direccion,
                                        BindingResult resultado, Model model, RedirectAttributes flash){
        if (resultado.hasErrors()){
            model.addAttribute("titulo","Error al crear la direccion");
            return "direccion/nuevasdirecciones";
        }
        iDireccionService.guardar(direccion);
        flash.addFlashAttribute("mensaje","Dirección se almacenó o modificó correctamente");
        return "redirect:/direcciones/lista";
    }

    @GetMapping("modificardirecciones/{id}")
    public String formularioDireccion(@PathVariable("id")Integer id, Model model){
        Direccion direccion=iDireccionService.buscarDireccion(id);
        model.addAttribute("titulo","Modificar dirección");
        model.addAttribute("direccion",direccion);
        return "direcciones/modificardirecciones";
    }

    @GetMapping("borrardirecciones/{id}")
    public String borrarDireccion(@PathVariable("id") Integer id, Model model,RedirectAttributes flash){
        iDireccionService.borrar(id);
        flash.addFlashAttribute("mensaje","Dirección se borró con exito");
        return "redirect:/direcciones/lista";
    }
}
