$.fn.datepicker.defaults.format = "dd/mm/yyyy";
$.fn.datepicker.defaults.language="es"

<script>
    $(document).ready(function() {
        // Obtener la fecha actual en formato yyyy-MM-dd
        var fecha = new Date().toISOString().slice(0,10);
        // Asignar la fecha al campo oculto
        $("#fecha_orden").val(fecha);
    });
</script>


<script>
    $(document).ready(function() {
        // Obtener el id del cliente seleccionado
        var clienteId = $("#cliente").val();
        // Asignar el id del cliente al campo oculto
        $("#cliente_id").val(clienteId);
    });
</script>

<script>
    $(document).ready(function() {
        // Obtener el id del cliente seleccionado
        var clienteId = $("#cliente").val();
        // Asignar el id del cliente al campo oculto
        $("#cliente_id").val(clienteId);

        // Detectar cuando se cambia el valor del campo de selección del cliente
        $("#cliente").change(function() {
            // Obtener el nuevo id del cliente seleccionado
            var nuevoClienteId = $(this).val();
            // Actualizar el valor del campo oculto
            $("#cliente_id").val(nuevoClienteId);
        });
    });
</script>

<script>
  function calcularEdad() {
    var fechaNacimiento = new Date(document.getElementById("fecha_nacimiento").value);
    var hoy = new Date();
    var edad = hoy.getFullYear() - fechaNacimiento.getFullYear();
    var m = hoy.getMonth() - fechaNacimiento.getMonth();
    if (m < 0 || (m === 0 && hoy.getDate() < fechaNacimiento.getDate())) {
        edad--;
    }
    document.getElementById("edad").value = edad;
  }

  document.getElementById("fecha_nacimiento").addEventListener("change", calcularEdad);
</script>

<script>
    $('.carousel').carousel({
        interval: 5000
    })
</script>

