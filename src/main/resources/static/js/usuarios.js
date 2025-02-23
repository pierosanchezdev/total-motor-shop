document.addEventListener('DOMContentLoaded', function () {
    document.querySelectorAll('.editar-btn').forEach(button => {
        button.addEventListener('click', function () {
            const usuarioId = this.getAttribute('data-id');
            fetch(`/usuarios/detalle/${usuarioId}`)
                .then(response => response.json())
                .then(data => {
                    document.getElementById('editar-id').value = data.id;
                    document.getElementById('editar-nombres').value = data.nombres;
                    document.getElementById('editar-apellidos').value = data.apellidos;
                    document.getElementById('editar-dni').value = data.dni;
                    document.getElementById('editar-direccion').value = data.direccion;
                    document.getElementById('editar-telefono').value = data.telefono;
                    document.getElementById('editar-email').value = data.email;
                    document.getElementById('editar-username').value = data.username;
                    document.getElementById('editar-password').value = '';
                    document.getElementById('editar-estado').value = data.estado ? 'true' : 'false';
                })
                .catch(error => console.error('Error al cargar datos del usuario:', error));
        });
    });
});

document.addEventListener('DOMContentLoaded', function () {
    document.querySelectorAll('.ver-detalle-btn').forEach(button => {
        button.addEventListener('click', function () {
            const usuarioId = this.getAttribute('data-id');
            fetch(`/usuarios/detalle/${usuarioId}`)
                .then(response => response.json())
                .then(data => {
                    document.getElementById('detalle-nombres').textContent = data.nombres;
                    document.getElementById('detalle-apellidos').textContent = data.apellidos;
                    document.getElementById('detalle-dni').textContent = data.dni;
                    document.getElementById('detalle-direccion').textContent = data.direccion;
                    document.getElementById('detalle-telefono').textContent = data.telefono;
                    document.getElementById('detalle-email').textContent = data.email;
                    document.getElementById('detalle-username').textContent = data.username;
                    document.getElementById('detalle-estado').textContent = data.estado ? 'Activo' : 'Inactivo';
                })
                .catch(error => console.error('Error al cargar detalles del usuario:', error));
        });
    });
});

