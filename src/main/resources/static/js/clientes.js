document.addEventListener('DOMContentLoaded', function () {
    document.querySelectorAll('.editar-btn').forEach(button => {
        button.addEventListener('click', function () {
            const clienteId = this.getAttribute('data-id');
            fetch(`/clientes/detalle/${clienteId}`)
                .then(response => response.json())
                .then(data => {
                    document.getElementById('editar-id').value = data.id;
                    document.getElementById('editar-nombres').value = data.nombres;
                    document.getElementById('editar-apellidos').value = data.apellidos;
                    document.getElementById('editar-dni').value = data.dni;
                    document.getElementById('editar-direccion').value = data.direccion;
                    document.getElementById('editar-telefono').value = data.telefono;
                    document.getElementById('editar-email').value = data.email;
                    document.getElementById('editar-segmento').value = data.segmento;
                    document.getElementById('editar-limiteCredito').value = data.limiteCredito;
                    document.getElementById('editar-estado').value = data.estado ? 'true' : 'false';
                })
                .catch(error => console.error('Error al cargar datos del cliente:', error));
        });
    });
});

document.addEventListener('DOMContentLoaded', function () {
    document.querySelectorAll('.ver-detalle-btn').forEach(button => {
        button.addEventListener('click', function () {
            const clienteId = this.getAttribute('data-id');
            fetch(`/clientes/detalle/${clienteId}`)
                .then(response => response.json())
                .then(data => {
                    document.getElementById('detalle-nombres').textContent = data.nombres;
                    document.getElementById('detalle-apellidos').textContent = data.apellidos;
                    document.getElementById('editar-dni').value = data.dni;
                    document.getElementById('detalle-direccion').textContent = data.direccion;
                    document.getElementById('detalle-telefono').textContent = data.telefono;
                    document.getElementById('detalle-email').textContent = data.email;
                    document.getElementById('detalle-segmento').textContent = data.segmento;
                    document.getElementById('detalle-limiteCredito').textContent = data.limiteCredito;
                    document.getElementById('detalle-estado').textContent = data.estado ? 'Activo' : 'Inactivo';
                })
                .catch(error => console.error('Error al cargar detalles del cliente:', error));
        });
    });
});

document.getElementById('buscarDNI').addEventListener('keyup', function () {
    let filtro = this.value.toLowerCase();
    let filas = document.querySelectorAll("#clientesLista tr");

    filas.forEach(fila => {
        let dni = fila.cells[1].textContent.toLowerCase();
        if (dni.includes(filtro)) {
            fila.style.display = "";
        } else {
            fila.style.display = "none";
        }
    });
});

function seleccionarCliente(button) {
    let clienteId = button.getAttribute("data-id");
    let clienteNombre = button.getAttribute("data-nombre");

    document.getElementById("clienteId").value = clienteId;
    document.getElementById("clienteNombre").value = clienteNombre;

    // Cerrar el modal
    let modal = bootstrap.Modal.getInstance(document.getElementById('agregarClienteModal'));
    modal.hide();
}

