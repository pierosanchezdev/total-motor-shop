document.addEventListener('DOMContentLoaded', function () {
    document.querySelectorAll('.editar-btn').forEach(button => {
        button.addEventListener('click', function () {
            const proveedorId = this.getAttribute('data-id');
            fetch(`/proveedores/detalle/${proveedorId}`)
                .then(response => response.json())
                .then(data => {
                    document.getElementById('editar-id').value = data.id;
                    document.getElementById('editar-nombre').value = data.nombre;
                    document.getElementById('editar-direccion').value = data.direccion;
                    document.getElementById('editar-telefono').value = data.telefono;
                    document.getElementById('editar-ruc').value = data.ruc;
                    document.getElementById('editar-email').value = data.email;
                    document.getElementById('editar-nombreContacto').value = data.nombreContacto;
                    document.getElementById('editar-estado').value = data.estado ? 'true' : 'false';
                })
                .catch(error => console.error('Error al cargar datos del proveedor:', error));
        });
    });
});

document.addEventListener('DOMContentLoaded', function () {
    document.querySelectorAll('.ver-detalle-btn').forEach(button => {
        button.addEventListener('click', function () {
            const proveedorId = this.getAttribute('data-id');
            fetch(`/proveedores/detalle/${proveedorId}`)
                .then(response => response.json())
                .then(data => {
                    document.getElementById('detalle-nombre').textContent = data.nombre;
                    document.getElementById('detalle-direccion').textContent = data.direccion;
                    document.getElementById('detalle-telefono').textContent = data.telefono;
                    document.getElementById('detalle-ruc').textContent = data.ruc;
                    document.getElementById('detalle-email').textContent = data.email;
                    document.getElementById('detalle-nombreContacto').textContent = data.nombreContacto;
                    document.getElementById('detalle-estado').textContent = data.estado ? 'Activo' : 'Inactivo';
                })
                .catch(error => console.error('Error al cargar detalles del proveedor:', error));
        });
    });
});
