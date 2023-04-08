package dgtic.core.controller;

import dgtic.core.entity.Categoria;
import dgtic.core.entity.Producto;
import dgtic.core.service.ICategoriaService;
import dgtic.core.service.IProductoService;
import dgtic.core.utilerias.RenderPagina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class InicioController {

    @Autowired
    IProductoService iProductoService;

    @GetMapping("/")
    public String inicio(Model model){
        //model.addAttribute("mensaje","El presente es el futuro");
        return "bienvenida";
    }

    @GetMapping("lista")
    public String inicio(@RequestParam(name = "page",defaultValue = "0") int page,
                         Model model){
        Pageable pageReq= PageRequest.of(page,6);

        Page<Producto> pro = iProductoService.findAll(pageReq);
        RenderPagina<Producto> render = new RenderPagina<>("lista",pro);
        model.addAttribute("productos",pro);
        model.addAttribute("page",render);
        return "inicio";
    }
}
