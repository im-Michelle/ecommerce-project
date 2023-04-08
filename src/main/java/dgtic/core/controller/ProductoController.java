package dgtic.core.controller;

import dgtic.core.entity.Categoria;
import dgtic.core.entity.Comentario;
import dgtic.core.entity.Producto;
import dgtic.core.service.ICategoriaService;
import dgtic.core.service.IComentarioService;
import dgtic.core.service.IProductoService;
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
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "productos")
public class ProductoController {
    @Autowired
    IProductoService iProductoService;

    @Autowired
    ICategoriaService iCategoriaService;

    @Autowired
    IComentarioService iComentarioService;


    @GetMapping("lista")
    public String listaProductos(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Pageable pageable = PageRequest.of(page, 6);
        Page<Producto> productos = iProductoService.findAll(pageable);
        RenderPagina<Producto> render = new RenderPagina<>("lista", productos);
        model.addAttribute("productos", productos);
        model.addAttribute("page", render);
        return "productos/listaproductos";
    }

    @GetMapping("formularioproducto")
    public String formularioProducto(Model model){
        Producto producto = new Producto();
        List<Categoria> listaCategorias = iCategoriaService.buscarCategorias();
        model.addAttribute("titulo","Nuevo Producto");
        model.addAttribute("producto",producto);
        model.addAttribute("listaCategorias", listaCategorias);
        return "productos/nuevosproductos";
    }
    @PostMapping(value = "/forminsertar")
    public String formProductoInsertar(@Valid @ModelAttribute("producto")Producto producto,
                                    BindingResult resultado, Model model, RedirectAttributes flash){
        if (resultado.hasErrors()){
            model.addAttribute("titulo","Error al crear el producto");
            return "producto/nuevosproductos";
        }
        iProductoService.guardar(producto);
        flash.addFlashAttribute("mensaje","Producto se almaceno o modifico correctamente");
        return "redirect:/productos/lista";
    }


    @GetMapping("modificarproductos/{id}")
    public String formularioProducto(@PathVariable("id")Integer id, Model model){
        Producto producto=iProductoService.buscarProducto(id);
        model.addAttribute("titulo","Modificar producto");
        model.addAttribute("producto",producto);
        return "productos/modificarproductos";
    }

    @GetMapping("borrarproductos/{id}")
    public String borrarProducto(@PathVariable("id") Integer id, Model model,RedirectAttributes flash){
        iProductoService.borrar(id);
        flash.addFlashAttribute("mensaje","Producto se borró con exito");
        return "redirect:/productos/lista";
    }
    @GetMapping("producto/{id}")
    public String detalleProducto(@PathVariable("id") Integer id, Model model){
        Producto producto = iProductoService.buscarProducto(id);
        List<Comentario> comentarios = iComentarioService.buscarComentariosPorProducto(producto);
        model.addAttribute("comentarios", comentarios);
        model.addAttribute("producto", producto);
        return "productos/producto";
    }


}
