package dgtic.core.controller;

import dgtic.core.entity.Estado;
import dgtic.core.entity.Producto;
import dgtic.core.service.IEstadoService;
import dgtic.core.utilerias.RenderPagina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "estados")
public class EstadoController {
    @Autowired
    IEstadoService iEstadoService;

    @GetMapping("lista")
    public String inicio(@RequestParam(name = "page",defaultValue = "0") int page,
                         Model model){
        Pageable pageReq= PageRequest.of(page,4);

        Page<Estado> est = iEstadoService.findAll(pageReq);
        RenderPagina<Estado> render = new RenderPagina<>("lista",est);
        model.addAttribute("estados",est);
        model.addAttribute("page",render);
        return "estados/listaestados";
    }
    @GetMapping("formularioestado")
    public String formularioEstado(Model model){
        Estado estado = new Estado();
        model.addAttribute("titulo","Nuevo Estado");
        model.addAttribute("estado",estado);
        return "estados/nuevosestados";
    }
    @PostMapping(value = "/forminsertar")
    public String formEstadoInsertar(@Valid @ModelAttribute("estado")Estado estado,
                                       BindingResult resultado, Model model, RedirectAttributes flash){
        if (resultado.hasErrors()){
            model.addAttribute("titulo","Error al crear el estado");
            return "estado/nuevosestados";
        }
        iEstadoService.guardar(estado);
        flash.addFlashAttribute("mensaje","Estado se almaceno o modifico correctamente");
        return "redirect:/estados/lista";
    }
    @GetMapping("modificarestados/{id}")
    public String formularioEstado(@PathVariable("id")Integer id, Model model){
        Estado estado=iEstadoService.buscarEstado(id);
        model.addAttribute("titulo","Modificar estado");
        model.addAttribute("estado",estado);
        return "estados/modificarestados";
    }

    @GetMapping("borrarestados/{id}")
    public String borrarEstado(@PathVariable("id") Integer id, Model model,RedirectAttributes flash){
        iEstadoService.borrar(id);
        flash.addFlashAttribute("mensaje","Estado se borró con exito");
        return "redirect:/estados/lista";
    }
}
