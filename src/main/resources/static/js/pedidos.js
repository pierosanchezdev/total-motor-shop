let productosSeleccionados = {};
let subtotal = 0;

function agregarProducto() {
    const select = document.getElementById("productoSelect");
    const productosLista = document.getElementById("productosSeleccionados");
    const productosInput = document.getElementById("productosInput");

    const productoId = select.value;
    const productoTexto = select.options[select.selectedIndex].text;
    const precio = parseFloat(select.options[select.selectedIndex].getAttribute("data-precio"));

    let cantidad = parseInt(prompt("Ingrese la cantidad:", "1"));
    if (isNaN(cantidad) || cantidad <= 0) {
        alert("Cantidad inválida");
        return;
    }

    if (productoId && !productosSeleccionados[productoId]) {
        productosSeleccionados[productoId] = cantidad;
        subtotal += precio * cantidad;

        let li = document.createElement("li");
        li.className = "list-group-item d-flex justify-content-between align-items-center";
        li.innerHTML = `${productoTexto} - ${cantidad} unidad(es) - S/ ${(precio * cantidad).toFixed(2)}
            <button class="btn btn-danger btn-sm" onclick="eliminarProducto('${productoId}', ${precio * cantidad}, this)">Eliminar</button>`;
        productosLista.appendChild(li);

        productosInput.value = JSON.stringify(productosSeleccionados);
        actualizarTotales();
    }
}

function eliminarProducto(id, precioTotal, button) {
    delete productosSeleccionados[id];
    subtotal -= precioTotal;

    button.parentElement.remove();
    document.getElementById("productosInput").value = JSON.stringify(productosSeleccionados);
    actualizarTotales();
}

function actualizarTotales() {
    const igv = subtotal * 0.18;
    const total = subtotal + igv;

    document.getElementById("subtotal").textContent = subtotal.toFixed(2);
    document.getElementById("igv").textContent = igv.toFixed(2);
    document.getElementById("total").textContent = total.toFixed(2);
}
