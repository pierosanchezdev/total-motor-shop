document.addEventListener('DOMContentLoaded', function () {
    document.querySelectorAll('.editar-btn').forEach(button => {
        button.addEventListener('click', function () {
            const productoId = this.getAttribute('data-id');
            fetch(`/productos/detalle/${productoId}`)
                .then(response => response.json())
                .then(data => {
                    console.log("Datos recibidos:", data);
                    document.getElementById('editar-id').value = data.id;
                    document.getElementById('editar-nombre').value = data.nombre;
                    document.getElementById('editar-precioUnitario').value = data.precioUnitario;
                    document.getElementById('editar-stock').value = data.stock;

                    if (data.categoria && data.categoria.id) {
                        seleccionarOpcion('editar-categoria', data.categoria.id);
                    } else {
                        console.warn("⚠️ La categoría del producto es nula o indefinida:", data.categoria);
                    }

                    if (data.proveedor && data.proveedor.id) {
                        seleccionarOpcion('editar-proveedor', data.proveedor.id);
                    } else {
                        console.warn("⚠️ El proveedor del producto es nulo o indefinido:", data.proveedor);
                    }

                    const vehiculosLista = document.getElementById('editar-vehiculosSeleccionados');
                    vehiculosLista.innerHTML = "";
                    vehiculosSeleccionados = [];

                    if (data.vehiculos) {
                        data.vehiculos.forEach(vehiculo => {
                            agregarVehiculoEdicion(vehiculo.id, `${vehiculo.marca} - ${vehiculo.modelo || 'Desconocido'} (${vehiculo.anio})`);
                        });
                    }
                })
                .catch(error => console.error('❌ Error al cargar datos del producto:', error));
        });
    });

    document.querySelectorAll('.ver-detalle-btn').forEach(button => {
        button.addEventListener('click', function () {
            const productoId = this.getAttribute('data-id');
            fetch(`/productos/detalle/${productoId}`)
                .then(response => response.json())
                .then(data => {
                    document.getElementById('detalle-nombre').textContent = data.nombre;
                    document.getElementById('detalle-precioUnitario').textContent = data.precioUnitario;
                    document.getElementById('detalle-stock').textContent = data.stock;
                    document.getElementById('detalle-categoria').textContent = data.categoria;
                    document.getElementById('detalle-proveedor').textContent = data.proveedor;

                    const vehiculosLista = document.getElementById('detalle-vehiculos');
                    vehiculosLista.innerHTML = "";

                    data.vehiculos.forEach(vehiculo => {
                        let li = document.createElement("li");
                        li.className = "list-group-item";
                        li.textContent = `${vehiculo.marca} - ${vehiculo.modelo} (${vehiculo.anio})`;
                        vehiculosLista.appendChild(li);
                    });

                })
                .catch(error => console.error('❌ Error al cargar detalles del producto:', error));
        });
    });

    const btnAgregarVehiculoEdicion = document.getElementById("editar-agregar-vehiculo");
    if (btnAgregarVehiculoEdicion) {
        btnAgregarVehiculoEdicion.addEventListener("click", function () {
            agregarVehiculoEdicion();
        });
    } else {
        console.warn("⚠️ El botón 'editar-agregar-vehiculo' no se encontró en el DOM.");
    }
});

let vehiculosSeleccionados = [];

function agregarVehiculo() {
    const select = document.getElementById("vehiculoSelect");
    const vehiculosLista = document.getElementById("vehiculosSeleccionados");
    const vehiculosInput = document.getElementById("vehiculosInput");

    const vehiculoId = select.value;
    const vehiculoTexto = select.options[select.selectedIndex].text;

    if (vehiculoId && !vehiculosSeleccionados.includes(vehiculoId)) {
        vehiculosSeleccionados.push(vehiculoId);

        let li = document.createElement("li");
        li.className = "list-group-item d-flex justify-content-between align-items-center";
        li.innerHTML = `${vehiculoTexto} <button class="btn btn-danger btn-sm" onclick="eliminarVehiculo('${vehiculoId}', this)">Eliminar</button>`;
        vehiculosLista.appendChild(li);

        vehiculosInput.value = vehiculosSeleccionados.join(",");
    }
}

function eliminarVehiculo(id, button) {
    vehiculosSeleccionados = vehiculosSeleccionados.filter(v => v !== id);
    button.parentElement.remove();
    document.getElementById("vehiculosInput").value = vehiculosSeleccionados.join(",");
}

function agregarVehiculoEdicion() {
    const select = document.getElementById("editar-vehiculoSelect");
    const vehiculosLista = document.getElementById("editar-vehiculosSeleccionados");
    const vehiculosInput = document.getElementById("editar-vehiculosInput");

    const vehiculoId = select.value;
    const vehiculoTexto = select.options[select.selectedIndex].text;

    if (vehiculoId && !vehiculosSeleccionados.includes(vehiculoId)) {
        vehiculosSeleccionados.push(vehiculoId);

        let li = document.createElement("li");
        li.className = "list-group-item d-flex justify-content-between align-items-center";
        li.innerHTML = `${vehiculoTexto} <button class="btn btn-danger btn-sm" onclick="eliminarVehiculoEdicion('${vehiculoId}', this)">Eliminar</button>`;
        vehiculosLista.appendChild(li);

        vehiculosInput.value = vehiculosSeleccionados.join(",");
    }
}

function eliminarVehiculoEdicion(id, button) {
    vehiculosSeleccionados = vehiculosSeleccionados.filter(v => v !== id);
    button.parentElement.remove();
    document.getElementById("editar-vehiculosInput").value = vehiculosSeleccionados.join(",");
}

function seleccionarOpcion(selectId, valor) {
    const selectElement = document.getElementById(selectId);
    if (!selectElement) {
        console.error(`❌ El select con id ${selectId} no se encontró en el DOM.`);
        return;
    }

    let opcionEncontrada = false;
    for (let option of selectElement.options) {
        if (option.value == valor) {
            option.selected = true;
            opcionEncontrada = true;
            break;
        }
    }

    if (!opcionEncontrada) {
        console.warn(`⚠️ No se encontró una opción válida para el select ${selectId} con valor ${valor}`);
    }
}

document.getElementById('buscarProducto').addEventListener('keyup', function () {
    let filtro = this.value.toLowerCase();
    let filas = document.querySelectorAll("#productosLista tr");

    filas.forEach(fila => {
        let nombre = fila.cells[1].textContent.toLowerCase();
        if (nombre.includes(filtro)) {
            fila.style.display = "";
        } else {
            fila.style.display = "none";
        }
    });
});

function seleccionarProducto(button) {
    let productoId = button.getAttribute("data-id");
    let productoNombre = button.getAttribute("data-nombre");
    let productoPrecio = parseFloat(button.getAttribute("data-precio"));

    let stockCell = button.parentElement.parentElement.querySelector(".stock-cell");
    let stockActual = parseInt(stockCell.textContent);

    if (stockActual <= 0) {
        alert("No hay stock disponible para este producto.");
        return;
    }

    let cantidad = parseInt(prompt("Ingrese la cantidad:", "1"));
    if (isNaN(cantidad) || cantidad <= 0 || cantidad > stockActual) {
        alert("Cantidad inválida o insuficiente stock.");
        return;
    }

    stockCell.textContent = stockActual - cantidad;

    let productosLista = document.getElementById("productosSeleccionados");
    let productosInput = document.getElementById("productosInput");

    let li = document.createElement("li");
    li.className = "list-group-item d-flex justify-content-between align-items-center";
    li.innerHTML = `${productoNombre} - ${cantidad} unidad(es) - S/ ${(productoPrecio * cantidad).toFixed(2)}
        <button class="btn btn-danger btn-sm" onclick="eliminarProducto(this, ${productoPrecio * cantidad}, ${cantidad}, '${productoId}')">Eliminar</button>`;
    productosLista.appendChild(li);

    document.getElementById("productoId").value = productoId;
    document.getElementById("productoNombre").value = productoNombre;

    actualizarTotales();
    bootstrap.Modal.getInstance(document.getElementById('agregarProductoModal')).hide();
}




