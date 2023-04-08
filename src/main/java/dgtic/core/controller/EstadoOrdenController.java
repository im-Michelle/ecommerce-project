package dgtic.core.controller;

import dgtic.core.entity.*;
import dgtic.core.service.IDireccionService;
import dgtic.core.service.IEstadoOrdenService;
import dgtic.core.service.IEstadoService;
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
@RequestMapping(value = "estado_ordenes")
public class EstadoOrdenController {
    @Autowired
    private IEstadoOrdenService iEstadoOrdenService;

    @Autowired
    private IEstadoService iEstadoService;

    @Autowired
    private IOrdenService iOrdenService;

    @GetMapping("lista")
    public String inicio(@RequestParam(name = "page",defaultValue="0") int page,
                         Model model){
        Pageable pageReq= PageRequest.of(page,5);

        Page<EstadoOrden> estOrd = iEstadoOrdenService.findAll(pageReq);
        RenderPagina<EstadoOrden> render = new RenderPagina<>("lista",estOrd);
        model.addAttribute("estado_ordenes",estOrd);
        model.addAttribute("page",render);
        return "estado_ordenes/listaestadoordenes";
    }
    @GetMapping("formularioestadoorden")
    public String formularioEstadoOrden(Model model){
        EstadoOrden estadoOrden = new EstadoOrden();
        List<Estado> listaEstados = iEstadoService.buscarEstados();
        List<Orden> listaOrdenes = iOrdenService.buscarOrdenes();
        model.addAttribute("titulo","Nuevo Estado Orden");
        model.addAttribute("estado_orden",estadoOrden);
        model.addAttribute("listaEstados", listaEstados);
        model.addAttribute("listaOrdenes", listaOrdenes);
        return "estado_ordenes/nuevosestadosordenes";
    }
    @PostMapping(value = "/forminsertar")
    public String formEstadoOrdenInsertar(@Valid @ModelAttribute("estado_orden")EstadoOrden estadoOrden,
                                        BindingResult resultado, Model model, RedirectAttributes flash){
        if (resultado.hasErrors()){
            model.addAttribute("titulo","Error al crear el estado de orden");
            return "estado_ordenes/nuevosestadosordenes";
        }
        Date fechaActual = new Date();
        estadoOrden.setFecha_estado(fechaActual);
        iEstadoOrdenService.guardar(estadoOrden);
        flash.addFlashAttribute("mensaje","Estado de Orden se almacenó o modificó correctamente");
        return "redirect:/estado_ordenes/lista";
    }
    @GetMapping("modificarestadoordenes/{id}")
    public String formularioEstadoOrden(@PathVariable("id")Integer id, Model model){
        List<Estado> listaEstados = iEstadoService.buscarEstados();
        List<Orden> listaOrdenes = iOrdenService.buscarOrdenes();
        EstadoOrden estadoOrden=iEstadoOrdenService.buscarEstadoOrden(id);
        model.addAttribute("titulo","Modificar estado de orden");
        model.addAttribute("listaEstados", listaEstados);
        model.addAttribute("listaOrdenes", listaOrdenes);
        model.addAttribute("estado_orden",estadoOrden);
        return "estado_ordenes/modificarestadoordenes";
    }

    @GetMapping("borrarestadoordenes/{id}")
    public String borrarEstadoOrden(@PathVariable("id") Integer id, Model model,RedirectAttributes flash){
        iEstadoOrdenService.borrar(id);
        flash.addFlashAttribute("mensaje","Estado de orden se borró con exito");
        return "redirect:/estado_ordenes/lista";
    }
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        webDataBinder.registerCustomEditor(Date.class,new CustomDateEditor(format,false));
    }
}
