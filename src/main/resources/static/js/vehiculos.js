document.addEventListener('DOMContentLoaded', function () {
    document.querySelectorAll('.editar-btn').forEach(button => {
        button.addEventListener('click', function () {
            const vehiculoId = this.getAttribute('data-id');
            fetch(`/vehiculos/detalle/${vehiculoId}`)
                .then(response => response.json())
                .then(data => {
                    document.getElementById('editar-id').value = data.id;
                    document.getElementById('editar-marca').value = data.marca;
                    document.getElementById('editar-modelo').value = data.modelo;
                    document.getElementById('editar-anio').value = data.anio;
                    document.getElementById('editar-tipoCaja').value = data.tipoCaja;
                    document.getElementById('editar-version').value = data.version;
                    document.getElementById('editar-estado').value = data.estado ? 'true' : 'false';
                })
                .catch(error => console.error('Error al cargar datos del vehiculo:', error));
        });
    });
});

document.addEventListener('DOMContentLoaded', function () {
    document.querySelectorAll('.ver-detalle-btn').forEach(button => {
        button.addEventListener('click', function () {
            const vehiculoId = this.getAttribute('data-id');
            fetch(`/vehiculos/detalle/${vehiculoId}`)
                .then(response => response.json())
                .then(data => {
                    document.getElementById('detalle-marca').textContent = data.marca;
                    document.getElementById('detalle-modelo').textContent = data.modelo;
                    document.getElementById('detalle-anio').textContent = data.anio;
                    document.getElementById('detalle-tipoCaja').textContent = data.tipoCaja;
                    document.getElementById('detalle-version').textContent = data.version;
                    document.getElementById('detalle-estado').textContent = data.estado ? 'Activo' : 'Inactivo';
                })
                .catch(error => console.error('Error al cargar detalles del vehículo:', error));
        });
    });
});

