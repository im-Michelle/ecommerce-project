package dgtic.core.controller;

import dgtic.core.entity.Categoria;
import dgtic.core.entity.Cliente;
import dgtic.core.service.ICategoriaService;
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
@RequestMapping(value = "categorias")
public class CategoriaController {
    @Autowired
    ICategoriaService iCategoriaService;

    @GetMapping("lista")
    public String inicio(@RequestParam(name = "page",defaultValue="0") int page,
                         Model model){
        Pageable pageReq= PageRequest.of(page,2);

        Page<Categoria> cat = iCategoriaService.findAll(pageReq);
        RenderPagina<Categoria> render = new RenderPagina<>("lista",cat);
        model.addAttribute("categorias",cat);
        model.addAttribute("page",render);
        return "categorias/listacategorias";
    }
    @GetMapping("formulariocategoria")
    public String formularioCategoria(Model model){
        Categoria categoria = new Categoria();
        model.addAttribute("titulo","Nueva Categoria");
        model.addAttribute("categoria",categoria);
        return "categorias/nuevascategorias";
    }
    @PostMapping(value = "/forminsertar")
    public String formCategoriaInsertar(@Valid @ModelAttribute("categoria")Categoria categoria,
                                      BindingResult resultado, Model model, RedirectAttributes flash){
        if (resultado.hasErrors()){
            model.addAttribute("titulo","Error al crear la cactegoria");
            return "categorias/nuevascategorias";
        }
        iCategoriaService.guardar(categoria);
        flash.addFlashAttribute("mensaje","Categoria se almaceno o modifico correctamente");
        return "redirect:/categorias/lista";
    }

    @GetMapping("modificarcategorias/{id}")
    public String formularioCategoria(@PathVariable("id")Integer id, Model model){
        Categoria categoria=iCategoriaService.buscarCategoria(id);
        model.addAttribute("titulo","Modificar categoria");
        model.addAttribute("categoria",categoria);
        return "categorias/modificarcategorias";
    }

    @GetMapping("borrarcategorias/{id}")
    public String borrarCategoria(@PathVariable("id") Integer id, Model model,RedirectAttributes flash){
        iCategoriaService.borrar(id);
        flash.addFlashAttribute("mensaje","Categoria se borró con exito");
        return "redirect:/categorias/lista";
    }
}
