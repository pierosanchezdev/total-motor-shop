document.addEventListener('DOMContentLoaded', function () {
    document.querySelectorAll('.editar-btn').forEach(button => {
        button.addEventListener('click', function () {
            const categoriaId = this.getAttribute('data-id');
            fetch(`/categorias/detalle/${categoriaId}`)
                .then(response => response.json())
                .then(data => {
                    document.getElementById('editar-id').value = data.id;
                    document.getElementById('editar-nombreCategoria').value = data.nombreCategoria;
                    document.getElementById('editar-descripcion').value = data.descripcion;
                    document.getElementById('editar-categoriaPadre').value = data.categoriaPadre;
                    document.getElementById('editar-estado').value = data.estado ? 'true' : 'false';
                })
                .catch(error => console.error('Error al cargar datos del categoria:', error));
        });
    });
});

document.addEventListener('DOMContentLoaded', function () {
    document.querySelectorAll('.ver-detalle-btn').forEach(button => {
        button.addEventListener('click', function () {
            const categoriaId = this.getAttribute('data-id');
            fetch(`/categorias/detalle/${categoriaId}`)
                .then(response => response.json())
                .then(data => {
                    document.getElementById('detalle-nombreCategoria').textContent = data.nombreCategoria;
                    document.getElementById('detalle-descripcion').textContent = data.descripcion;
                    document.getElementById('detalle-categoriaPadre').textContent = data.categoriaPadre.nombreCategoria;
                    document.getElementById('detalle-estado').textContent = data.estado ? 'Activo' : 'Inactivo';
                })
                .catch(error => console.error('Error al cargar detalles de la categoria:', error));
        });
    });
});

