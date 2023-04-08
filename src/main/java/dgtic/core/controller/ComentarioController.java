package dgtic.core.controller;

import dgtic.core.entity.Cliente;
import dgtic.core.entity.Comentario;
import dgtic.core.entity.Orden;
import dgtic.core.entity.Producto;
import dgtic.core.service.IClienteService;
import dgtic.core.service.IComentarioService;
import dgtic.core.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "comentarios")
public class ComentarioController {

    @Autowired
    IComentarioService iComentarioService;

    @Autowired
    IClienteService iClienteService;
    @Autowired
    IProductoService iProductoService;

    @GetMapping("formulariocomentario")
    public String formularioComentario(Model model) {
        Comentario comentario = new Comentario();
        List<Cliente> listaClientes = iClienteService.buscarClientes();
        List<Producto> listaProductos = iProductoService.buscarProductos();
        model.addAttribute("titulo", "Nuevo Comentario");
        model.addAttribute("comentario", comentario);
        model.addAttribute("listaClientes", listaClientes);
        model.addAttribute("listaProductos", listaProductos);
        return "comentarios/nuevoscomentarios";
    }
    @PostMapping(value = "/forminsertar")
    public String formComentarioInsertar(@Valid @ModelAttribute("comentario") Comentario comentario,
                                         BindingResult resultado, Model model, RedirectAttributes flash) {
        if (resultado.hasErrors()) {
            model.addAttribute("titulo", "Error al crear el comentario");
            return "productos/listaproductos";
        }

        Date fechaActual = new Date();
        Cliente cliente = new Cliente();
        Producto producto = iProductoService.buscarProducto(comentario.getProducto().getIdProducto());
        cliente.setId_cliente(comentario.getCliente().getId_cliente());
        comentario.setFecha_comentario(fechaActual);
        comentario.setCliente(cliente);
        comentario.setProducto(producto);

        iComentarioService.guardar(comentario);
        flash.addFlashAttribute("mensaje", "Comentario se almaceno o modifico correctamente");
        return "redirect:/productos/lista";
    }

}
