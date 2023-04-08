package dgtic.core.controller;

import dgtic.core.entity.Orden;
import dgtic.core.entity.Producto;
import dgtic.core.model.DetalleOrden;
import dgtic.core.model.DetalleOrdenId;
import dgtic.core.service.IDetalleOrdenService;
import dgtic.core.service.IOrdenService;
import dgtic.core.service.IProductoService;
import dgtic.core.service.jpa.DetalleOrdenService;
import dgtic.core.utilerias.RenderPagina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Controller
@SessionAttributes("lista")
@RequestMapping("detalle_orden")
public class DetalleOrdenController {

    @Autowired
    private IDetalleOrdenService iDetalleOrdenService;
    @Autowired
    private IOrdenService iOrdenService;
    @Autowired
    private IProductoService iProductoService;

    @GetMapping(value = "agregar")
    public String desplegarSelect(Model model, @RequestParam(required = false) Integer cantidad) {
        DetalleOrden detOrden = new DetalleOrden();
        List<DetalleOrden> lista = new LinkedList<>();
        detOrden.setCantidad(cantidad);
        model.addAttribute("detorden", detOrden);
        model.addAttribute("lista", lista);

        List<Orden> select = iOrdenService.buscarOrdenes();
        model.addAttribute("selectorden", select);

        List<Producto> selectP = iProductoService.buscarProductos();
        model.addAttribute("selectproducto", selectP);

        return "detalle_orden/det_ord_select";
    }

    @PostMapping(value = "/forminsertarselect")
    public String formDetalleOrdenInsertarS(@Valid @ModelAttribute("detorden") DetalleOrden detOrden,
                                            BindingResult resultado, Model modelo,
                                            SessionStatus status, HttpSession sesion) {
        Orden o = iOrdenService.buscarOrden(detOrden.getOrden().getId_orden());
        Producto p = iProductoService.buscarProducto(detOrden.getProducto().getIdProducto());
        detOrden.setOrden(o);
        detOrden.setProducto(p);
        System.out.println(detOrden.toString());
        if (resultado.hasErrors()) {
            modelo.addAttribute("titulo", "Error al guardar");
            return "detalle_orden/det_ord_select";
        }

        List<DetalleOrden> lista = (List<DetalleOrden>) sesion.getAttribute("lista");
        lista.add(detOrden);
        modelo.addAttribute("mensaje", "Se registró correctamente.");

        List<Orden> selectOrden = iOrdenService.buscarOrdenes();
        modelo.addAttribute("selectorden", selectOrden);

        List<Producto> selectProducto = iProductoService.buscarProductos();
        modelo.addAttribute("selectproducto", selectProducto);

        return "detalle_orden/det_ord_select";
    }



    @GetMapping(value = "/formguardar")
    public String formguardar(Model modelo, SessionStatus status, HttpSession sesion, RedirectAttributes flash) {
        String salto = "redirect:/detalle_orden/listas";
        List<DetalleOrden> lista = (List<DetalleOrden>) sesion.getAttribute("lista");
        System.out.println("hola antes del for");
        for (DetalleOrden datos : lista) {
            Orden orden = iOrdenService.buscarOrden(datos.getOrden().getId_orden());
            Producto producto = iProductoService.buscarProducto(datos.getProducto().getIdProducto());
            DetalleOrden detOrden = new DetalleOrden();
            detOrden.setOrden(orden);
            detOrden.setProducto(producto);
            detOrden.setCantidad(datos.getCantidad());

            try {
                System.out.println("hola en el try");
                System.out.println(detOrden.toString());
                System.out.println(orden.toString());
                detOrden.getId().setIdOrden(orden.getId_orden());
                detOrden.getId().setIdProducto(producto.getIdProducto());
                iDetalleOrdenService.guardar(detOrden);
                flash.addFlashAttribute("mensaje", "Se almacenó con éxito");
            } catch (DataIntegrityViolationException ex) {
                DetalleOrden detOrd = new DetalleOrden();
                modelo.addAttribute("detorden", detOrd);
                modelo.addAttribute("mensaje", "Detalle de orden ya existente");
                salto = "detalle_orden/det_ord_select";
                break;
            }
        }
        return salto;
    }


        @GetMapping(value="listas")
            public String lista(@RequestParam(name = "page", defaultValue = "0") int page, Model modelo, SessionStatus status){
                Pageable pagReq = PageRequest.of(page, 5);
                Page<Orden> ord = iOrdenService.findAll(pagReq);
                RenderPagina<Orden> render = new RenderPagina<>("listas", ord);
                modelo.addAttribute("listas", ord);
                modelo.addAttribute("page", render);
                status.setComplete();
            return "detalle_orden/listadetalleordenes";
        }


    @GetMapping("borrar/{id}")
    public String borrarDetalleOrden(@PathVariable("id") Integer id, Model modelo, HttpSession sesion) {
        List<DetalleOrden> lista = (List<DetalleOrden>) sesion.getAttribute("lista");
        Iterator<DetalleOrden> iter = lista.iterator();
        while (iter.hasNext()) {
            DetalleOrden detalle = iter.next();
            if (detalle.getOrden().getId_orden().equals(id)) {
                iter.remove();
                break;
            }
        }
        DetalleOrden detOrden = new DetalleOrden();
        modelo.addAttribute("detorden",detOrden);
        modelo.addAttribute("mensaje", "Detalle de orden borrado con éxito");
        List<Orden> select = iOrdenService.buscarOrdenes();
        modelo.addAttribute("selectorden", select);
        List<Producto> selectP = iProductoService.buscarProductos();
        modelo.addAttribute("selectproducto", selectP);
        return "detalle_orden/det_ord_select";
    }


}

